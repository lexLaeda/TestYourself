package com.test.yourself.model.test;

import com.test.yourself.model.Question;
import com.test.yourself.model.Subject;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;


@Data
@Builder
@Entity
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set <Question> questions;

}
