package model.result;

import lombok.Data;
import model.Question;

import java.util.List;
import java.util.Map;
@Data
public class QuestionResult {

    private String questionName;
    private Map<Integer,String> correctAnswers;
    private Map<Integer,String> userAnswers;
}
