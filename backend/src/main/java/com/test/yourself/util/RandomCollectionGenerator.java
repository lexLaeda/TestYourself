package com.test.yourself.util;

import com.test.yourself.model.Question;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;


public interface RandomCollectionGenerator {

    <T extends List<V>, V> Set<V> getRandomUniqueSet(T collection, int size);
    <T extends List<V>, V> List<V> getRandomUniqueList(T collection, int size);
}
