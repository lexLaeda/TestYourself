package com.test.yourself.controller;

import com.test.yourself.dto.QuestionDTO;
import com.test.yourself.maper.QuestionMapper;
import com.test.yourself.model.Question;
import com.test.yourself.service.subject.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @PutMapping("/update/{id}")
    public QuestionDTO updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO questionDTO){
        Question question = questionMapper.fromDTO(questionDTO);
        return questionMapper.toDTO(questionService.update(question, id));
    }


    @GetMapping("/subject")
    @ResponseBody
    public List<QuestionDTO> findAllBySubject(@RequestParam("sub_id") Long subjectId){
        return questionService.findAllBySubjectId(subjectId).stream()
                .map(question -> questionMapper.toDTO(question))
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<QuestionDTO> findAll(){
        return questionService.findAll().stream()
                .map(question -> questionMapper.toDTO(question)).
                        collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public QuestionDTO findById(@PathVariable Long id){
        return questionMapper.toDTO(questionService.findById(id));
    }
}
