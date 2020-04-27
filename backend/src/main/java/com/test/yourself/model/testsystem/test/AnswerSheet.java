package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "answer_sheet")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class AnswerSheet extends AbstractEntity {

    @OneToOne
    private SubjectTest subjectTest;

    @OneToMany
    private List<UserAnswer> userAnswers = new ArrayList<>();

    private LocalDateTime testEnded;

    private LocalDateTime testStarted;

}
