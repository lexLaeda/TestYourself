package com.test.yourself.controller;

import com.test.yourself.dto.SubjectDto;
import com.test.yourself.maper.SubjectMapper;
import com.test.yourself.model.subject.Subject;
import com.test.yourself.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/subjects")
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
    @PostMapping(value = "/add",produces = "application/json")
    public SubjectDto addNewSubject(@RequestBody SubjectDto subjectDTO){
        Subject subject = subjectMapper.toEntity(subjectDTO);
        Subject savedSubject = subjectService.add(subject);
        return subjectMapper.toDto(savedSubject);
    }

    @PutMapping("/update/{id}")
    public SubjectDto updateSubject(@RequestBody SubjectDto subjectDTO, @PathVariable Long id){
        Subject subject = subjectMapper.toEntity(subjectDTO);
        Subject updatedSubject = subjectService.update(subject, id);
        return subjectMapper.toDto(updatedSubject);
    }
    @PutMapping("/update_name")
    @ResponseBody
    public SubjectDto updateSubjectName(
            @RequestParam("sub_name") String subjectName,
            @RequestParam("sub_id") Long id){
        Subject updatedSubject = subjectService.updateName(subjectName,id);
        return subjectMapper.toDto(updatedSubject);
    }

    @GetMapping("/{id}")
    public SubjectDto findSubjectById(@PathVariable Long id){

        SubjectDto sub = new SubjectDto();
        sub.setId(1L);
        sub.setName("Java");
        sub.setDescription("helloWorld");

        return sub;
    }
}
