package com.test.yourself.util;

import com.test.yourself.model.test.Test;

public interface Cache<T extends Test>{
   void addTestToCache(T test);
   T getTestById(Long id);
   void removeTestById(Long id);
}
