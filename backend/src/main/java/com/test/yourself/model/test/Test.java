package com.test.yourself.model.test;

import com.test.yourself.model.AbstractEntity;
import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "tests")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Test extends AbstractEntity {


    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    private String name;

    @ElementCollection
    private List<Question> questions;

}
