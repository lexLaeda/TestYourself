package com.test.yourself.service.test;

import com.test.yourself.model.AbstractEntity;

import java.util.List;

public interface DataService <T extends AbstractEntity>{
    T add(T entity);
    T update(Long id, T entity);
    T findById(Long id);
    T findByName(String name);
    List<T> findAll();
    T deleteById(Long id);
    T delete(T entity);
    void deleteAll();
}