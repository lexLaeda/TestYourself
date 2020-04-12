package com.test.yourself.dto;

import com.test.yourself.model.Answer;
import com.test.yourself.model.Subject;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class QuestionDTO {

    private Long id;
    private String subjectName;
    private String name;
    private String description;
    private Set<Answer> answers;
    private String mode;
}
