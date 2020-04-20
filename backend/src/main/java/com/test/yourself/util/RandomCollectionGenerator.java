package com.test.yourself.util;

import java.util.List;
import java.util.Set;


public interface RandomCollectionGenerator {

    <T extends List<V>, V> Set<V> getRandomUniqueSet(T collection, int size);
    <T extends List<V>, V> List<V> getRandomUniqueList(T collection, int size);
}
