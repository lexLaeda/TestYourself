package com.test.yourself.model.testsystem.subject;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.enums.QuestionMode;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Question extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(nullable = false, name = "text")
    private String text;

    @Column(name = "description")
    private String description;

    @Column(nullable = false, name = "mode")
    @Enumerated(EnumType.STRING)
    private QuestionMode mode;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "question_answer",
            joinColumns = {@JoinColumn(name = "question_id")},
            inverseJoinColumns = {@JoinColumn(name = "answer_id")})
    private List<Answer> answers = new ArrayList<>();

}
