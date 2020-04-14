package com.test.yourself.model;

import com.test.yourself.model.enums.QuestionMode;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Subject subject;

    private String name;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Answer> answers;

    @Enumerated(EnumType.STRING)
    private QuestionMode mode;
}
