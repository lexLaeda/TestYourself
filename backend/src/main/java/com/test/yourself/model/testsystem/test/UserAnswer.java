package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.model.testsystem.subject.Question;
import lombok.*;
import org.hibernate.engine.internal.Cascade;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_answers")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserAnswer extends AbstractEntity {

    @OneToOne
    private Question question;

    @ElementCollection
    private List<Integer> answerList = new ArrayList<>();
}
