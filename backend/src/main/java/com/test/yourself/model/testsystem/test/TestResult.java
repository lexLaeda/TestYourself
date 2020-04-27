package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.testsystem.subject.Question;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "test_result")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class TestResult extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private SubjectTest subjectTest;

    private Integer correctAmount;

    private Integer percent;

    @ElementCollection
    private List<Long> listOfCorrectId = new ArrayList<>();

    @OneToOne
    private AnswerSheet answerSheet;
}
