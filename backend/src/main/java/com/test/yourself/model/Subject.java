package com.test.yourself.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Subject {
    private Long id;
    private String name;
    private List<Question> questions;
}
