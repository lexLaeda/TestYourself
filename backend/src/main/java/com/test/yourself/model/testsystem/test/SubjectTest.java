package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "subjectTests")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class SubjectTest extends AbstractEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @OneToMany(mappedBy = "subjectTest")
    private Set<TestResult> testResults = new HashSet<>();

    private String name;

    @Enumerated(EnumType.STRING)
    private TestMode testMode;

    @ManyToMany
    @JoinTable(
            name = "test_questions",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id"))
    private List<Question> questions = new ArrayList<>();

}
