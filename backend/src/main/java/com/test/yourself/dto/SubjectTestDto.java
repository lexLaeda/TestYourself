package com.test.yourself.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SubjectTestDto extends AbstractDto {

    private Long subjectId;

    private String name;

    private List<QuestionDto> questions;
}
