package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    public Product(String name, String description, double price, Group group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }
}
