package com.test.yourself.util;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.testsystem.subject.Question;
import lombok.SneakyThrows;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class ReflectionUpdate {
    private static final String SET = "set";
    private static final String GET = "get";

    public static  <T extends AbstractEntity> T updateObject(T source, T target){
        Class<? extends AbstractEntity> sourceClass = source.getClass();
        Class<? extends AbstractEntity> targetClass = target.getClass();
        Method[] declaredMethods = sourceClass.getDeclaredMethods();
        for (Method method : declaredMethods){
            String getMethodName = method.getName();
            if (getMethodName.substring(0,3).equals(GET)){
                try {
                    Method getMethod = sourceClass.getMethod(getMethodName);
                    String setMethodName = SET + getMethodName.substring(3);
                    Method setMethod = targetClass.getMethod(setMethodName, method.getReturnType());
                    ReflectionUtils.invokeMethod(setMethod,target,ReflectionUtils.invokeMethod(getMethod,source));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return target;
    }

}
