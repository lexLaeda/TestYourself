package model;

import lombok.Data;

import java.util.List;

@Data
public class Subject {
    private Long id;
    private String name;
    private List<Question> questions;
}
