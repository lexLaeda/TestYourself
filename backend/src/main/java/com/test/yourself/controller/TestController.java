package com.test.yourself.controller;

import com.test.yourself.model.test.Test;
import com.test.yourself.service.test.TestGeneratorService;
import com.test.yourself.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", allowedHeaders = "http://localhost:8080")
public class TestController {

    @Autowired
    @Qualifier(value = "testServiceDb")
    private TestService testServiceDb;

    @Autowired
    @Qualifier(value = "testServiceCache")
    private TestService testServiceCache;




    @GetMapping(value = "/random test", params = {"subject_id", "amount"})
    public @ResponseBody Test generateRandomTest(
            @RequestParam("subject_id") Long subjectID,
            @RequestParam("amount") int amount) {
            return null;
    }

}
