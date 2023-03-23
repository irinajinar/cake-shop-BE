package com.project.CakeShop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private ProductType productType;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "cartProducts")
    @JsonBackReference
    Set<Cart> carts;

    @OneToMany(mappedBy = "product")
    private Set<OrderedProduct> orderedProducts;

}
