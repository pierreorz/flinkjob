package com.ayden.flinkjob.condition;

import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Map<String, Object> annotationAttributes = metadata
                .getAnnotationAttributes(ConditionalOnSystemProperty.class.getName());
        String propertyName = String.valueOf(annotationAttributes.get("name"));
        String propertyValue = String.valueOf(annotationAttributes.get("value"));
        String systemPropertyValue = System.getProperty(propertyName);
        return propertyValue.equals(systemPropertyValue)||"*".equals(systemPropertyValue);

    }

}