package com.test.yourself.model.testsystem.subject;

import com.test.yourself.validation_annotation.TextCheck;
import com.test.yourself.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "answer")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Answer extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @Column(name = "text")
    @TextCheck
    @NotBlank(message = "текст ответа не может быть пустым")
    private String text;

}
