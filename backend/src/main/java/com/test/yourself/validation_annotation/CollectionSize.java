package com.test.yourself.validation_annotation;

import com.test.yourself.validation_annotation.validators.CollectionSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CollectionSizeValidator.class)
@Documented
public @interface CollectionSize {
    int min();
    int max();
    String message() default "phone number is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
