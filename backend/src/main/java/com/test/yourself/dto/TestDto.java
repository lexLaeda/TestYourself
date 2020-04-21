package com.test.yourself.dto;

import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
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
