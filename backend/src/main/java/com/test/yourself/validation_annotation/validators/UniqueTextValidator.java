package com.test.yourself.validation_annotation.validators;

import com.test.yourself.validation_annotation.UniqueText;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueTextValidator implements ConstraintValidator<UniqueText, Collection<?>> {
    private String fieldName;
    private Class<?> aClass;
    @Override
    public void initialize(UniqueText constraintAnnotation) {
        fieldName = constraintAnnotation.elementFieldName();
        aClass = constraintAnnotation.elementClass();
    }

    @Override
    public boolean isValid(Collection<?> value, ConstraintValidatorContext context) {
        Set<String> collect = value.stream()
                .map(this::getFieldValue)
                .collect(Collectors.toSet());
        return collect.size() == value.size();
    }


    private String getFieldValue(Object object){
        String methodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        Method method = ReflectionUtils.findMethod(aClass, methodName);
        String value = (String) ReflectionUtils.invokeMethod(method,object);
        return value;
    }
}
