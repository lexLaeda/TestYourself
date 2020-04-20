package com.test.yourself.controller;

import com.test.yourself.dto.QuestionDto;
import com.test.yourself.maper.QuestionMapper;
import com.test.yourself.model.subject.Question;
import com.test.yourself.service.subject.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private QuestionService questionService;
    private QuestionMapper questionMapper;

    @Autowired
    public QuestionController(QuestionMapper questionMapper, QuestionService questionService){
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }

    @PostMapping("/add")
    public QuestionDto addQuestion(@RequestBody QuestionDto questionDTO){
        Question question = questionMapper.toEntity(questionDTO);
        Question fromdb = questionService.addQuestion(question);
        return questionMapper.toDto(fromdb);
    }
    @PutMapping("/update/{id}")
    public QuestionDto updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDTO){
        Question question = questionMapper.toEntity(questionDTO);
        return questionMapper.toDto(questionService.update(question, id));
    }


    @GetMapping("/subject")
    @ResponseBody
    public List<QuestionDto> findAllBySubject(@RequestParam("sub_id") Long subjectId){
        return questionService.findAllBySubjectId(subjectId).stream()
                .map(question -> questionMapper.toDto(question))
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<QuestionDto> findAll(){
        return questionService.findAll().stream()
                .map(question -> questionMapper.toDto(question)).
                        collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public QuestionDto findById(@PathVariable Long id){
        return questionMapper.toDto(questionService.findById(id));
    }

}
