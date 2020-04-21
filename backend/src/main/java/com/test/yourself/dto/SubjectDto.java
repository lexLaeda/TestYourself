package com.test.yourself.dto;


import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectDto extends AbstractDto{

    private String name;

    private String description;

    private int amount;
}
