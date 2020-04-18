package com.test.yourself.model;

import com.test.yourself.model.enums.QuestionMode;
import com.test.yourself.model.test.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "questions")
    private List<Test> tests;

    private String name;

    private String description;

    @ElementCollection
    @CollectionTable(name = "order_question_mapping",
            joinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "answer_number")
    @Column(name = "answer")
    private Map<Integer,String> answers;

    @ElementCollection
    private List<Integer> correctAnswers;
    
}
