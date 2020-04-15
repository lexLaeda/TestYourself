package model;

import lombok.Data;

import java.util.Map;
@Data
public class Test {
    private Long id;
    private String name;
    private Map<Integer, Question> questions;
}
