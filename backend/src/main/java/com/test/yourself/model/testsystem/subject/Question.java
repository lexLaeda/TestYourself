package com.test.yourself.model.testsystem.subject;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.enums.QuestionMode;
import com.test.yourself.model.testsystem.test.SubjectTest;
import lombok.*;
import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Question extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionMode mode;

    @ManyToMany(mappedBy = "questions")
    private List<SubjectTest> subjectTests;

    @ElementCollection
    @CollectionTable(name = "question_answers", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "question_answer",nullable = false)
    private List<Answer> answers;

    @ElementCollection
    private List<Integer> correctAnswers;

}
