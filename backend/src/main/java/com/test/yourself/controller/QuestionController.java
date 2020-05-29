package com.test.yourself.controller;

import com.test.yourself.dto.QuestionDto;
import com.test.yourself.mapper.QuestionMapper;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.service.test.QuestionService;
import com.test.yourself.service.test.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private QuestionService questionService;
    private QuestionMapper questionMapper;
    private SubjectService subjectService;

    @Autowired
    public QuestionController(QuestionMapper questionMapper, QuestionService questionService, SubjectService subjectService) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
        this.subjectService = subjectService;
    }

    @PostMapping("/add")
    public ResponseEntity<QuestionDto> addQuestion(@Valid @RequestBody QuestionDto questionDTO) {
        Question question = questionMapper.toEntity(questionDTO);
        Question fromdb = questionService.add(question);
        return new ResponseEntity<>(questionMapper.toDto(fromdb), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDTO) {
        Question question = questionMapper.toEntity(questionDTO);
        return new ResponseEntity<>(questionMapper.toDto(questionService.update(id, question)), HttpStatus.OK);
    }

    @GetMapping("/subject")
    @ResponseBody
    public ResponseEntity<List<QuestionDto>> findAllBySubject(@RequestParam("sub_id") Long subjectId) {
        Subject subject = subjectService.findById(subjectId);
        List<QuestionDto> questions = questionService.findAllBySubject(subject).stream()
                .map(question -> questionMapper.toDto(question))
                .collect(Collectors.toList());
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionDto>> findAll() {
        List<QuestionDto> questions = questionService.findAll().stream()
                .map(question -> questionMapper.toDto(question)).
                        collect(Collectors.toList());
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> findById(@PathVariable Long id) {
        QuestionDto questionDto = questionMapper.toDto(questionService.findById(id));
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        Boolean isDeleted = questionService.deleteById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

}
