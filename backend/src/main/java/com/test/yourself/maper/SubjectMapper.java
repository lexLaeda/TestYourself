package com.test.yourself.maper;

import com.test.yourself.dto.SubjectDTO;
import com.test.yourself.model.Subject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Component
public class SubjectMapper implements Mapper<Subject,SubjectDTO> {
    @Override
    public SubjectDTO toDTO(Subject subject) {
        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .description(subject.getDescription())
                .numberOfQuestions(subject.getQuestions().size())
                .build();
    }

    @Override
    public Subject fromDTO(SubjectDTO subjectDTO) {
        return Subject.builder()
                .name(subjectDTO.getName())
                .description(subjectDTO.getDescription())
                .questions(new HashSet<>())
                .tests(new ArrayList<>())
                .build();
    }
}
