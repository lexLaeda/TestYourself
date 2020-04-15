package com.test.yourself.model;

import com.test.yourself.model.test.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private List<Test> tests;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "subject")
    private Set<Question> questions;

}
