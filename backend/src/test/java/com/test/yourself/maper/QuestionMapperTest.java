package com.test.yourself.maper;

import com.test.yourself.model.Question;
import com.test.yourself.model.Subject;
import com.test.yourself.service.subject.SubjectService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest()
public class QuestionMapperTest {
    @Autowired
    private SubjectService subjectService;

    private QuestionMapper questionMapper;
    private static String SUBJECTNAME = "Java";

    private static String QUESTIONNAME = "How many";
    private static String DESC = "About";

    private static Long TESTID = 999999999999999999L;

    private static Set<String> answers;



    @Before
    public void setUp() throws Exception {
        questionMapper = new QuestionMapper(subjectService);
        answers = new HashSet<>();

    }

    @Test
    public void toDTO() {
        Subject subject = Subject.builder()
                .questions(new HashSet<>())
                .name(SUBJECTNAME)
                .id(TESTID)
                .build();
        Question question = Question.builder()
                .id(TESTID)
                .name(QUESTIONNAME)
                .description(DESC)
                .subject(subject)
                .build();
    }

    @Test
    public void fromDTO() {
    }
}