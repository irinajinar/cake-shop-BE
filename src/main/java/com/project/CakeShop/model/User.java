package com.project.CakeShop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String telephoneNumber;

    @Column(nullable = false)
    private String password;

    @JsonBackReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;


    @OneToMany(mappedBy="user")
    private Set<OrderedProduct> orderedProducts;


}
