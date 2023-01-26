package com.github.themidgart;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(value = {"classpath:schema.sql", "classpath:data.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@ExtendWith(TimingExtension.class)
public abstract class ContextTestConfiguration {
}