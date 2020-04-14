package com.test.yourself.controller;

import com.test.yourself.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/subjects")
@CrossOrigin(origins = "*",allowedHeaders = "http://localhost:8080")
public class SubjectController {

    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @GetMapping("/map")
    public Map<Long,String> findMapSubjects(){
        return subjectService.findMapSubjects();
    }
}
