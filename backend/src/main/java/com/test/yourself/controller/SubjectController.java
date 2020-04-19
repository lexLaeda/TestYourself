package com.test.yourself.controller;

import com.test.yourself.dto.SubjectDTO;
import com.test.yourself.maper.SubjectMapper;
import com.test.yourself.model.Subject;
import com.test.yourself.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.ControllerAdviceBean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.InvocationHandler;
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

        SubjectDTO sub = new SubjectDTO();
        sub.setId(1L);
        sub.setName("Java");
        sub.setDescription("helloWorld");
        sub.setNumberOfQuestions(0);
        return sub;
    }
}
