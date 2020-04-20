package com.test.yourself.model.subject;

import com.test.yourself.model.AbstractEntity;
import lombok.*;

import javax.persistence.Embeddable;


@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Answer {
    private Integer number;
    private String title;
}
