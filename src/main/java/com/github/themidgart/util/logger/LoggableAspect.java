package com.github.themidgart.util.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@Aspect
@Slf4j
public class LoggableAspect {
    private record ParamForLog(String fieldName, String fieldValue, String objectName) {
        @Override
        public String toString() {
            return "логируемое поле - " + fieldName +
                    " cо значением - " + fieldValue +
                    " объекта " + objectName;
        }
    }

    @Around("@annotation(loggable)")
    public Object logMethodParams(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        HashSet<String> loggableFields = new HashSet<>(List.of(loggable.loggableFields()));
        Object[] inputObjects = joinPoint.getArgs();
        List<ParamForLog> fieldParams = new ArrayList<>();

        try {
            for (int i = 0; i < inputObjects.length; i++) {
                int finalI = i;
                ReflectionUtils.doWithFields(inputObjects[i].getClass(), field -> {
                    if (loggableFields.contains(field.getName())) {
                        field.setAccessible(true);
                        fieldParams.add(new ParamForLog(field.getName(),
                                field.get(inputObjects[finalI]).toString(),
                                inputObjects[finalI].getClass().toString()));
                    }
                });
            }
            log.info("Началась обработка метода  {} с полями {}", joinPoint.getSignature(), fieldParams);
        } catch (Exception ignored) {
            log.warn("Не удалось логгировать параметры при вызове метода {}", joinPoint.getSignature());
        }
        return joinPoint.proceed();
    }
}
