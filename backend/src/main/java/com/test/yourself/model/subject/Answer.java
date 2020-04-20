package com.test.yourself.model.subject;

import com.test.yourself.model.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "answers")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Answer extends AbstractEntity {
    private Integer number;
    private String title;
}
