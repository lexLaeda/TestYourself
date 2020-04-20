package com.test.yourself.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.yourself.model.subject.Answer;
import com.test.yourself.model.subject.Subject;
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
public class QuestionDto extends AbstractDto{
    @JsonProperty("subject")
    private Long subjectId;

    private String name;

    private String description;

    private List<Answer> answers;

    private List<Integer> correctAnswers;


}
