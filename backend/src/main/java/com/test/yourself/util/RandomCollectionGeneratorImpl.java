package com.test.yourself.util;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RandomCollectionGeneratorImpl implements RandomCollectionGenerator{
    @Override
    public <T extends List<V>, V> Set<V> getRandomUniqueSet(T list, int size) {
        Set<Integer> uniqueIndexes = getUniqueIndexes(size);
        return uniqueIndexes.stream()
                .map(list::get)
                .collect(Collectors.toSet());
    }

    @Override
    public <T extends List<V>, V> List<V> getRandomUniqueList(T list, int size) {
        Set<Integer> uniqueIndexes = getUniqueIndexes(size);
        return uniqueIndexes.stream()
                .map(list::get)
                .collect(Collectors.toList());
    }

    private Set<Integer> getUniqueIndexes(int size) {
        Set<Integer> uniqueIndexes = new HashSet<>();
        while (uniqueIndexes.size() < size){
            Integer index = getRandomInt(size);
            uniqueIndexes.add(index);
        }
        return uniqueIndexes;
    }

    private Integer getRandomInt(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
}
