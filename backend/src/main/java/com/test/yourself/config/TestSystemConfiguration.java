package com.test.yourself.config;

import com.test.yourself.service.test.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestSystemConfiguration {

    @Bean
    public TestValidationService getTestValidation(){
        return new TestValidationServiceImpl();
    }

    @Bean
    public TestService getDbService(){
        return new TestServiceDb();
    }

    @Bean
    public TestService getCacheService(){
        return new TestServiceCache();
    }
}
