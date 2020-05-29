package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.testsystem.subject.Question;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserAnswer extends AbstractEntity {
    private SubjectTest subjectTest;
    private List<QuestionAnswer> questionAnswers = new ArrayList<>();
}
