package com.springboot.id1212project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    //specifies the primary key of the entity
    @Id
    //specifies the generation strategies for the values of primary keys
    @SequenceGenerator(
            name = "invoice_sequence",
            sequenceName = "invoice_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_sequence"
    )
    private long invoiceId;
    private int days;
    private String address;
    private Date invoiceDate;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "card_id",
            referencedColumnName = "cardId",
            nullable = false
    )
    private Card card;

    @ManyToMany(
            targetEntity = User.class,
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "invoice_user_map",
            joinColumns = @JoinColumn(
                    name = "invoice_id",
                    referencedColumnName = "invoiceId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )
    )
    private List<User> users;

    public void addUsers(User user){
        if(users == null) users = new ArrayList<>();
        users.add(user);
    }

}
