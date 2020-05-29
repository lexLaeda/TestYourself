package com.test.yourself.validation_annotation.validators;

import com.test.yourself.validation_annotation.CollectionSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class CollectionSizeValidator implements ConstraintValidator<CollectionSize, Collection<?>> {

    private int minSize;
    private int maxSize;
    @Override
    public void initialize(CollectionSize constraintAnnotation) {
        minSize = constraintAnnotation.min();
        maxSize = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Collection<?> value, ConstraintValidatorContext context) {
        return minSize <= value.size() && value.size() <= maxSize;
    }
}
