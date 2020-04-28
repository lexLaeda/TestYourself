package com.test.yourself.model.testsystem.subject;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.enums.QuestionMode;
import com.test.yourself.model.testsystem.test.SubjectTest;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Question extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Subject subject;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionMode mode;

    @ManyToMany(mappedBy = "questions")
    private List<SubjectTest> subjectTests = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Answer> answers = new ArrayList<>();

    @ElementCollection
    @Column(nullable = false)
    private List<Integer> correctAnswers = new ArrayList<>();

}
