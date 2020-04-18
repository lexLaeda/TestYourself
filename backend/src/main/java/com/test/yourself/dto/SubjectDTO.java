package com.test.yourself.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {

    private Long id;

    private String name;

    private String description;

    @JsonProperty("num_q")
    private int numberOfQuestions;
}
