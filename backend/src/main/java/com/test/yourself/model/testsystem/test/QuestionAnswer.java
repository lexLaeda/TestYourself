package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.model.testsystem.subject.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionAnswer extends AbstractEntity {

    private Question question;

    private List<Answer> answers = new ArrayList<>();
}
