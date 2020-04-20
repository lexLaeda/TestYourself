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

    @Column(name = "crated",updatable = false)
    private LocalDateTime crated;

    @Column(name = "updated", insertable = false)
    private LocalDateTime updated;

    @PrePersist
    public void  toCreate(){
        setCrated(LocalDateTime.now());
    }

    @PreUpdate
    public void toUpdate(){
        setUpdated(LocalDateTime.now());
    }
}
