package com.test.yourself.model;

import com.test.yourself.model.enums.QuestionMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    private String name;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Answer> answers;

    @Enumerated(EnumType.STRING)
    private QuestionMode mode;
}
