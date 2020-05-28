package com.test.yourself.controller;

import com.test.yourself.dto.AnswerSheetDto;
import com.test.yourself.dto.TestResultDto;
import com.test.yourself.mapper.AnswerSheetMapper;
import com.test.yourself.mapper.TestResultMapper;
import com.test.yourself.model.testsystem.test.AnswerSheet;
import com.test.yourself.model.testsystem.test.TestResult;
import com.test.yourself.service.test.TestResultService;
import com.test.yourself.service.test.TestVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/test_verification")
public class TestVerificationController {


    private TestVerificationService verificationService;
    private AnswerSheetMapper answerSheetMapper;
    private TestResultMapper testResultMapper;
    private TestResultService testResultService;

    public TestVerificationController(TestVerificationService verificationService, TestResultMapper testResultMapper,
                                      AnswerSheetMapper answerSheetMapper, TestResultService testResultService) {
        this.verificationService = verificationService;
        this.testResultMapper = testResultMapper;
        this.answerSheetMapper = answerSheetMapper;
        this.testResultService = testResultService;
    }

    @PostMapping("/test_result")
    public ResponseEntity<TestResultDto> verifySubjectTest(@RequestBody AnswerSheetDto answerSheetDto) {

        AnswerSheet answerSheet = answerSheetMapper.toEntity(answerSheetDto);
        TestResult testResult = verificationService.checkTest(answerSheet);
        TestResult savedResult = testResultService.add(testResult);
        TestResultDto testResultDto = testResultMapper.toDto(savedResult);
        return new ResponseEntity<>(testResultDto, HttpStatus.OK);
    }

}
