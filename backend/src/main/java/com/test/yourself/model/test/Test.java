package com.test.yourself.model.test;

import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
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

    @ElementCollection
    private Set <Question> questions;

}
