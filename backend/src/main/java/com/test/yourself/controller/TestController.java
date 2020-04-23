package com.test.yourself.controller;

import com.test.yourself.dto.TestDto;
import com.test.yourself.maper.TestMapper;
import com.test.yourself.model.testsystem.test.SubjectTest;
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

    @Autowired
    public void setTestMapper(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Autowired
    public void setTestService(TestServiceImpl testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/generate", params = {"id", "number"})
    public @ResponseBody ResponseEntity<TestDto> generateRandomTest(
            @RequestParam("id") Long subjectID,
            @RequestParam("number") int amount) {

        SubjectTest randomSubjectTest = testService.getRandomTest(subjectID,amount);
        TestDto testDto = testMapper.toDto(randomSubjectTest);
        return new ResponseEntity<>(testDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> findTestById(@PathVariable("id") Long id){
        SubjectTest subjectTest = testService.findTestById(id);
        TestDto testDto = testMapper.toDto(subjectTest);

        return new ResponseEntity<>(testDto,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TestDto> addNewTest(@RequestBody TestDto testDto){
        SubjectTest subjectTestToAdd = testMapper.toEntity(testDto);
        SubjectTest addedToDb = testService.addTest(subjectTestToAdd);
        return new ResponseEntity<>(testMapper.toDto(addedToDb),HttpStatus.OK);
    }

}
