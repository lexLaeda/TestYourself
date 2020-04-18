package com.test.yourself.model;

import com.test.yourself.model.test.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
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

    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
    private List<Test> tests = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "subject")
    private Set<Question> questions = new HashSet<>();

}
