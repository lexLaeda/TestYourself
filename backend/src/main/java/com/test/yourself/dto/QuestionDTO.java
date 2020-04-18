package com.test.yourself.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.List;
import java.util.Map;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Long id;

    private Long subjectId;

    private String name;

    private String description;

    private String mode;

    private Map<Integer,String> answers;

    private List<Integer> correctAnswers;
}
