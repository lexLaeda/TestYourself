package model.result;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class TestResults {

    private String testName;
    private int correctAmount;
    private int persent;

    private Map<Integer, QuestionResult> questionResults;
}
