package com.test.yourself.controller;

import com.test.yourself.dto.SubjectTestDto;
import com.test.yourself.dto.TestResultDto;
import com.test.yourself.dto.UserAnswerDto;
import com.test.yourself.mapper.TestMapper;
import com.test.yourself.mapper.UserAnswerMapper;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.model.testsystem.test.UserAnswer;
import com.test.yourself.service.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private TestServiceImpl testService;

    private TestMapper testMapper;
    private SubjectService subjectService;
    private QuestionService questionService;

    @Autowired
    public TestController(TestServiceImpl testService, TestMapper testMapper,
                          SubjectService subjectService,
                          QuestionService questionService,
                          UserAnswerMapper userAnswerMapper) {
        this.testService = testService;
        this.testMapper = testMapper;
        this.subjectService = subjectService;
        this.questionService = questionService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<SubjectTestDto> findTestById(@PathVariable("id") Long id) {
        SubjectTest subjectTest = testService.findById(id);
        SubjectTestDto subjectTestDto = testMapper.toDto(subjectTest);

        return new ResponseEntity<>(subjectTestDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SubjectTestDto> addNewTest(@RequestBody SubjectTestDto testDto) {
        SubjectTest subjectTestToAdd = testMapper.toEntity(testDto);
        SubjectTest addedToDb = testService.add(subjectTestToAdd);
        return new ResponseEntity<>(testMapper.toDto(addedToDb), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<SubjectTestDto> addNewTest(@PathVariable("id") Long id,
                                                    @RequestBody SubjectTestDto testDto) {
        SubjectTest subjectTest = testMapper.toEntity(testDto);
        SubjectTest update = testService.update(id, subjectTest);
        return new ResponseEntity<>(testMapper.toDto(update), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        Boolean isDeleted = testService.deleteById(id);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<SubjectTestDto> getRandomTest(@RequestParam("id") Long subjectId,
                                                        @RequestParam("amount") int amount){
        Subject subject = subjectService.findById(subjectId);
        List<Question> questions = questionService.findAllBySubject(subject);
        SubjectTest randomTest = testService.createRandomTest(questions,amount);
        SubjectTestDto subjectTestDto = testMapper.toDto(randomTest);
        return new ResponseEntity<>(subjectTestDto,HttpStatus.OK);
    }


}
