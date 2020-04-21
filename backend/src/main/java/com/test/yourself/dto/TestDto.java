package com.test.yourself.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TestDto extends AbstractDto{

    private Long subjectId;

    private String name;

    private List<QuestionDto> questions;
}
