package model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Question {
    private Long id;
    private String name;
    private Map<Integer, String> answers;
    private List<Integer> correctAnswers;
}
