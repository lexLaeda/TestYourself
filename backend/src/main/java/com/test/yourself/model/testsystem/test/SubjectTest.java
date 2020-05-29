package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "test")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class SubjectTest extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private TestMode mode;

    @ManyToMany
    @JoinTable(
            name = "test_questions",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questions = new ArrayList<>();

}
