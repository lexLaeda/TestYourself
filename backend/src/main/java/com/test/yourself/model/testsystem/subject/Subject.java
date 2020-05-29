package com.test.yourself.model.testsystem.subject;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.testsystem.test.SubjectTest;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Subject extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long id;

    @Column(unique = true, name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", orphanRemoval = true)
    private List<SubjectTest> subjectTests = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject", orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

}
