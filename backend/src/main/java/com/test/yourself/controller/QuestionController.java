package com.test.yourself.controller;

import com.test.yourself.dto.QuestionDTO;
import com.test.yourself.maper.QuestionMapper;
import com.test.yourself.model.Question;
import com.test.yourself.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*",allowedHeaders = "http://localhost:8080")
public class QuestionController {
    private QuestionService questionService;
    private QuestionMapper questionMapper;

    @Autowired
    public QuestionController(QuestionMapper questionMapper, QuestionService questionService){
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }

    @PostMapping("/add")
    public QuestionDTO addQuestion(@RequestBody QuestionDTO questionDTO){
        Question question = questionMapper.fromDTO(questionDTO);
        Question fromdb = questionService.addQuestion(question);
        return questionMapper.toDTO(fromdb);
    }
}
