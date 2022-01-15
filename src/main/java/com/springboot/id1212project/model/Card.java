package com.springboot.id1212project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class Card {

    @Id
    private long cardId;
    @Column(
        name = "type"
    )
    private String type;

    @Column(
            name = "expiryDate"
    )
    private Date expiryDate;

    @Column(
            name = "status"
    )
    private String status;

    @Column(
            name = "price"
    )
    private String price;

    //define the relationship
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch= FetchType.EAGER,
            optional = false
    )
    @JsonIgnore
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

//    @OneToMany(
//            cascade = CascadeType.ALL
//    )
//    @JoinColumn(
//            //this is the name of attribute you want to call in another table
//            name = "card_id",
//            //this is the name of attribute in this table
//            referencedColumnName = "cardId"
//    )
//    private List<Invoice> invoices;

}
