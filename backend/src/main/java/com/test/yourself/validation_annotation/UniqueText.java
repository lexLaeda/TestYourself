package com.test.yourself.validation_annotation;


import com.test.yourself.validation_annotation.validators.UniqueTextValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTextValidator.class)
@Documented
public @interface UniqueText {
    Class<?> elementClass();

    String elementFieldName();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    String message() default "not unique";

}
