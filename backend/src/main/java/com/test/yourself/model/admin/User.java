package com.test.yourself.model.admin;

import com.test.yourself.model.AbstractEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Role> roles = new HashSet<>();
}
