package com.test.yourself.model.testsystem.subject;

import com.test.yourself.model.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "answers")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Answer extends AbstractEntity {

    private Integer number;
    private String title;

}
