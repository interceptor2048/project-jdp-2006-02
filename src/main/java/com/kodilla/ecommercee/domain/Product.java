package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    @Column(name = "Name")
    private final String name;

    @Column(name = "Description")
    private final String description;

    @Column(name = "Price")
    private final double price;

    @Column(name = "Category")
    private final Long group_Id;
}
