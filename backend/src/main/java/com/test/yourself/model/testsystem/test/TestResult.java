package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TestResult extends AbstractEntity {


    private Long id;

    private SubjectTest subjectTest;

    private List<QuestionAnswer> questionAnswers;

}
