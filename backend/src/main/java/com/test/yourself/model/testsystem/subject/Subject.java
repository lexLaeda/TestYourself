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
@Table(name = "subjects")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Subject extends AbstractEntity {

    @Column(unique = true,nullable = false, updatable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private List<SubjectTest> subjectTests = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "subject")
    private List<Question> questions = new ArrayList<>();

}
