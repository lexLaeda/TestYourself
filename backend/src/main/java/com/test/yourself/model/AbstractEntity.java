package com.test.yourself.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "created",updatable = false)
    private LocalDateTime created;

    @Column(name = "updated", insertable = false)
    private LocalDateTime updated;

    @PrePersist
    public void  toCreate(){
        setCreated(LocalDateTime.now());
    }

    @PreUpdate
    public void toUpdate(){
        setUpdated(LocalDateTime.now());
    }
}
