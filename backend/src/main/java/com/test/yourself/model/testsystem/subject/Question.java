package com.test.yourself.model.testsystem.subject;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.enums.QuestionMode;
import com.test.yourself.model.testsystem.test.Test;
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
public class Question extends AbstractEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private QuestionMode mode;

    @ManyToMany(mappedBy = "questions")
    private List<Test> tests;

    @ElementCollection
    @CollectionTable(name = "question_answers", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "question_answer")
    private List<Answer> answers;

    @ElementCollection
    private List<Integer> correctAnswers;

}
