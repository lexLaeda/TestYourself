package model.result;

import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class UserAnswers {
    private Long testId;
    private String testName;
    private Map<Integer, List<Integer>> results;
}
