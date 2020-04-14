package com.test.yourself.controller;

import com.test.yourself.model.test.Test;
import com.test.yourself.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "http://localhost:8080")
@RequestMapping("/api/test")
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(TestService testService){
            this.testService = testService;
    }
    @GetMapping(value = "/random test", params = {"subject_id", "amount"})
    public @ResponseBody Test generateRandomTest(
            @RequestParam("subject_id") Long subjectID,
            @RequestParam("amount") int amount) {
            return testService.generateRandomTest(subjectID,amount);
    }
}
