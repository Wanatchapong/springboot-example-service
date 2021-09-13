package com.me.example.dtos.validation;

import com.me.example.models.TodoStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ValueOfTodoStatusValidator implements ConstraintValidator<ValueOfTodoStatus, TodoStatus> {
    private TodoStatus[] statuses;

    @Override
    public void initialize(ValueOfTodoStatus constraintAnnotation) {
        this.statuses = constraintAnnotation.anyOf();
    }

    @Override
    public boolean isValid(TodoStatus value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(statuses).contains(value);
    }
}
