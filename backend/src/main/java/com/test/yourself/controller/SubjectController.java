package com.test.yourself.controller;

import com.test.yourself.dto.SubjectDto;
import com.test.yourself.mapper.SubjectMapper;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.service.test.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/subjects")
public class SubjectController {

    private SubjectService subjectService;
    private SubjectMapper subjectMapper;

    @Autowired
    public SubjectController(SubjectService subjectService, SubjectMapper subjectMapper) {
        this.subjectService = subjectService;
        this.subjectMapper = subjectMapper;
    }

    @GetMapping("/map")
    public Map<Long, String> findMapSubjects() {
        return subjectService.findMapSubjects();
    }

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<SubjectDto> addNewSubject(@RequestBody SubjectDto subjectDto) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        Subject savedSubject = subjectService.add(subject);
        return new ResponseEntity<>(subjectMapper.toDto(savedSubject), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto subjectDto, @PathVariable Long id) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        Subject updatedSubject = subjectService.update(id, subject);
        return new ResponseEntity<>(subjectMapper.toDto(updatedSubject), HttpStatus.OK);
    }

    @PutMapping("/update_name")
    @ResponseBody
    public ResponseEntity<SubjectDto> updateSubjectName(
            @RequestParam("sub_name") String subjectName,
            @RequestParam("sub_id") Long id) {
        Subject updatedSubject = subjectService.updateName(subjectName, id);
        return new ResponseEntity<>(subjectMapper.toDto(updatedSubject), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> findSubjectById(@PathVariable Long id) {
        SubjectDto subjectDto = subjectMapper.toDto(subjectService.findById(id));
        return new ResponseEntity<>(subjectDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectDto>> findAll() {
        List<SubjectDto> subList = subjectService.findAll().stream()
                .map(subject -> subjectMapper.toDto(subject))
                .collect(Collectors.toList());
        return new ResponseEntity<>(subList, HttpStatus.OK);
    }
}
