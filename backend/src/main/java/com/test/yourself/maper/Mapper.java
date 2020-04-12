package com.test.yourself.maper;

public interface Mapper<F,T> {
    T toDTO(F object);
    F fromDTO(T objectDTO);
}
