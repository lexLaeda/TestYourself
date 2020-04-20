package com.test.yourself.controller;

import com.test.yourself.model.test.Test;
import com.test.yourself.service.test.TestService;
import com.test.yourself.service.test.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private TestServiceImpl testService;

    @Autowired
    public void setTestService(TestServiceImpl testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/random test", params = {"subject_id", "amount"})
    public @ResponseBody Test generateRandomTest(
            @RequestParam("subject_id") Long subjectID,
            @RequestParam("amount") int amount) {
            return  null;
    }

}
