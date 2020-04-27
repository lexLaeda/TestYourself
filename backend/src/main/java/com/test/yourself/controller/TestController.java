package com.test.yourself.controller;

import com.test.yourself.dto.SubjectTestDto;
import com.test.yourself.mapper.TestMapper;
import com.test.yourself.model.testsystem.test.AnswerSheet;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.model.testsystem.test.TestResult;
import com.test.yourself.service.test.TestVerificationService;
import com.test.yourself.service.test.SubjectService;
import com.test.yourself.service.test.TestResultService;
import com.test.yourself.service.test.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private TestServiceImpl testService;

    private TestMapper testMapper;
    private TestVerificationService testVerificationService;
    private TestResultService testResultService;
    private SubjectService subjectService;

    @Autowired
    public TestController(TestServiceImpl testService, TestMapper testMapper,
                          TestVerificationService testVerificationService,
                          TestResultService testResultService, SubjectService subjectService) {
        this.testService = testService;
        this.testMapper = testMapper;
        this.testVerificationService = testVerificationService;
        this.testResultService = testResultService;
        this.subjectService = subjectService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<SubjectTestDto> findTestById(@PathVariable("id") Long id){
        SubjectTest subjectTest = testService.findById(id);
        SubjectTestDto subjectTestDto = testMapper.toDto(subjectTest);

        return new ResponseEntity<>(subjectTestDto,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SubjectTestDto> addNewTest(@RequestBody SubjectTestDto testDto){
        SubjectTest subjectTestToAdd = testMapper.toEntity(testDto);
        SubjectTest addedToDb = testService.add(subjectTestToAdd);
        return new ResponseEntity<>(testMapper.toDto(addedToDb),HttpStatus.OK);
    }

    @GetMapping("/test_results")
    public ResponseEntity<TestResult> getTestResults(@RequestBody AnswerSheet userAnswers){
        TestResult results = testVerificationService.checkTest(userAnswers);
        testResultService.add(results);
        return new ResponseEntity<>(results,HttpStatus.OK);
    }

}
