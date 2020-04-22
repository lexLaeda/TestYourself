package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "subjectTests")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class SubjectTest extends AbstractEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    private String name;

    @Enumerated(EnumType.STRING)
    TestMode testMode;

    @ManyToMany
    @JoinTable(
            name = "test_questions",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<Question> questions;

}
