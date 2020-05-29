package com.test.yourself.validation_annotation;

import com.test.yourself.validation_annotation.validators.CollectionSizeValidator;
import com.test.yourself.validation_annotation.validators.TextCheckValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TextCheckValidator.class)
@Documented
public @interface TextCheck {
    String message() default "phone number is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
