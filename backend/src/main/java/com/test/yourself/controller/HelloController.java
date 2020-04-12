package com.test.yourself.controller;

import com.test.yourself.model.Question;
import com.test.yourself.model.Subject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*",allowedHeaders = "http://localhost:8080")
public class HelloController {

    @GetMapping("/hello")
    public Subject sayHello() {

        Subject javaScript = Subject.builder()
                .id(1L)
                .name("JavaScript")
                .build();
        String strQ = "Где ключи от танка?";
        Question question = Question.builder()
                .id(1L)
                .question(strQ)
                .subject("javaScript")
                .answers(Arrays.asList("У Васи","У Глухова","У Бэша"))
                .correctAnswers(Arrays.asList("У Васи"))
                .build();
        String strQ2 = "Где еда?";
        Question question2 = Question.builder()
                .id(2L)
                .question(strQ2)
                .subject("javaScript")
                .answers(Arrays.asList("У Васи","У Паши","У Бэша"))
                .correctAnswers(Arrays.asList("У Бэша"))
                .build();
        javaScript.setQuestions(Arrays.asList(question,question2));
        return javaScript;
    }
}
