package com.test.yourself.controller;

import com.test.yourself.dto.QuestionDTO;
import com.test.yourself.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*",allowedHeaders = "http://localhost:8080")
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService =questionService;
    }

    @PostMapping("/add")
    public QuestionDTO addQuestion(@RequestBody QuestionDTO questionDTO){
        return questionService.addQuestion(questionDTO);
    }
}
