package com.test.yourself.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Question> questions;
}
