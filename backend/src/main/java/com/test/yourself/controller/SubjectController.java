package com.test.yourself.controller;

import com.test.yourself.dto.SubjectDTO;
import com.test.yourself.maper.SubjectMapper;
import com.test.yourself.model.Subject;
import com.test.yourself.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/subjects")
@CrossOrigin(origins = "*",allowedHeaders = "http://localhost:8080")
public class SubjectController {

    private SubjectService subjectService;
    private SubjectMapper subjectMapper;

    @Autowired
    public SubjectController(SubjectService subjectService, SubjectMapper subjectMapper){
        this.subjectService = subjectService;
        this.subjectMapper = subjectMapper;
    }

    @GetMapping("/map")
    public Map<Long,String> findMapSubjects(){
        return subjectService.findMapSubjects();
    }
    @PostMapping("/add")
    public SubjectDTO addNewSubject(@RequestBody SubjectDTO subjectDTO){
        Subject subject = subjectMapper.fromDTO(subjectDTO);
        Subject savedSubject = subjectService.add(subject);
        return subjectMapper.toDTO(savedSubject);
    }

    @PutMapping("/update/{id}")
    public SubjectDTO updateSubject(@RequestBody SubjectDTO subjectDTO, @PathVariable Long id){
        Subject subject = subjectMapper.fromDTO(subjectDTO);
        Subject updatedSubject = subjectService.update(subject, id);
        return subjectMapper.toDTO(updatedSubject);
    }
    @PutMapping("/update_name")
    @ResponseBody
    public SubjectDTO updateSubjectName(
            @RequestParam("sub_name") String subjectName,
            @RequestParam("sub_id") Long id){
        Subject updatedSubject = subjectService.updateName(subjectName,id);
        return subjectMapper.toDTO(updatedSubject);
    }

    @GetMapping("/{id}")
    public SubjectDTO findSubjectById(@PathVariable Long id){
        Subject subject = subjectService.findSubjectById(id);
        return subjectMapper.toDTO(subject);
    }
}