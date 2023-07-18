package com.github.themidgart.util.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Aspect
@Slf4j
public class LoggableAspect {
    @Around("@annotation(loggable)")
    public Object logMethodParams(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        try {
            HashSet<String> loggableFields = new HashSet<>(List.of(loggable.loggableFields()));
            Object[] objects = joinPoint.getArgs();
            Map<String, String> objectFields = new HashMap<>();
            Arrays.stream(joinPoint.getArgs())
                    .map(Object::getClass)
                    .map(Class::getDeclaredFields)
                    .flatMap(Arrays::stream)
                    .filter(field -> loggableFields.contains(field.getName()))
                    .forEach(field -> {
                        field.setAccessible(true);
                        try {
                            objectFields.put(field.getName(), field.get(objects[0]).toString());
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    });
            log.info("Началась обработка запроса со в методе {} co следующими параметрами {}",
                    joinPoint.getSignature(), objectFields);
        } catch (Exception ignored) {
            log.warn("Не удалось логгировать параметры при вызове метода {}", joinPoint.getSignature());
        }
        return joinPoint.proceed();
    }
}
