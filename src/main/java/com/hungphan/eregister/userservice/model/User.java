package com.hungphan.eregister.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "USER_GEN", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_GEN", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

    private String username;
    private String password;

    private String fullName;
    private Integer age;

    public User(String username, String password, String fullName, Integer age) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
    }
}
