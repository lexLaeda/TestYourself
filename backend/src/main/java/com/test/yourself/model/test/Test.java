package com.test.yourself.model.test;

import com.test.yourself.model.Question;
import com.test.yourself.model.Subject;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Set;


@Data
@Builder
public class Test {

    private Long id;

    private Subject subject;

    private Set<Question> questionSet;

}
