package com.test.yourself.controller;

import com.test.yourself.dto.SubjectTestDto;
import com.test.yourself.mapper.TestMapper;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.service.test.TestGeneratorService;
import com.test.yourself.service.test.SubjectService;
import com.test.yourself.service.test.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/generator")
public class TestGeneratorController {

    private TestGeneratorService testGeneratorService;
    private SubjectService subjectService;
    private TestMapper testMapper;
    private TestService testService;
    public TestGeneratorController(TestGeneratorService testGeneratorService,
                                   SubjectService subjectService, TestMapper testMapper, TestService testService) {
        this.testGeneratorService = testGeneratorService;
        this.subjectService = subjectService;
        this.testMapper = testMapper;
        this.testService = testService;
    }

    @GetMapping("/test")
    public ResponseEntity<SubjectTestDto> generateTestBySubjectId(@RequestParam(value = "subjectId") Long subjectId,
                                                                  @RequestParam(value = "size") Integer size){
        Subject subject = subjectService.findById(subjectId);
        SubjectTest subjectTest = testGeneratorService.generateTest(subject, size);
        testService.add(subjectTest);
        return new ResponseEntity<>(testMapper.toDto(subjectTest), HttpStatus.OK);
    }
}
