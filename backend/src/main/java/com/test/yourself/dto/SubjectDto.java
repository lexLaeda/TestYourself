package com.test.yourself.dto;


import com.test.yourself.validation_annotation.TextCheck;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectDto extends AbstractDto {


    @NotBlank
    @TextCheck
    private String name;


    @NotBlank
    @TextCheck
    private String description;

    private int amountQuestions;

    private int amountTests;
}
