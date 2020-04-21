package com.test.yourself.dto;


import com.test.yourself.model.subject.Question;
import com.test.yourself.model.test.Test;
import lombok.*;

import java.util.List;

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
