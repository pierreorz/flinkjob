package com.ayden.flinkjob.condition;

import java.lang.annotation.*;
import org.springframework.context.annotation.Conditional;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(MyCondition.class)
public @interface ConditionalOnSystemProperty {
    String name();

    String value();
}