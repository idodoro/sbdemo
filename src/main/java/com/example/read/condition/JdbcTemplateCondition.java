package com.example.read.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by lenovo on 2018/7/19.
 */
public class JdbcTemplateCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        try {
            conditionContext.getClassLoader().loadClass("org.springframework.jdbc.core.JdbcTemplate");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
