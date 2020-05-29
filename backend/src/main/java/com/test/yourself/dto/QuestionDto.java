package com.test.yourself.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.yourself.validation_annotation.CollectionSize;
import com.test.yourself.validation_annotation.TextCheck;
import com.test.yourself.model.enums.QuestionMode;
import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.validation_annotation.UniqueText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto extends AbstractDto {

    @JsonProperty("subject")
    private Long subjectId;

    @TextCheck
    private String name;

    @TextCheck
    private String description;

    private QuestionMode mode;

    @CollectionSize(min = 2,max = 20, message = "Колличество ответов должно быть от 2 до 20")
    @UniqueText(elementClass = Answer.class, elementFieldName = "text")
    private List<Answer> answers = new ArrayList<>();

}
