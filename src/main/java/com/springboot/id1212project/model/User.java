package com.springboot.id1212project.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//lombok library to reduce boilerplate code such as getters, setters, constructors, toString, equals and hashcode methods for Java class
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name="users",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"email","phone"}
        )
)
public class User {
    //specifies the primary key of the entity
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    //specifies the generation strategies for the values of primary keys
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(
            name = "email",
            nullable = false
    )
    private String email;
    @Column(name = "phone")
    private int phone;

    @Column(name = "password",
            nullable = false)
    private String password;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch= FetchType.LAZY
    )
    private Card myCard;

//    @ManyToMany(
//            mappedBy = "users",
//            cascade = CascadeType.MERGE,
//            fetch = FetchType.EAGER
//    )
//    private List<Invoice> invoices;
}
