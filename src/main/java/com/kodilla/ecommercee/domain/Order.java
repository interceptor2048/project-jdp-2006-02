package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "PRODUCT_ORDER",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> products;

    @Column(name = "STATUS")
    private String status;

    public Order(String status) {
        this.status = status;
    }

    public Order(String status, User user) {
        this.status = status;
        this.user = user;
    }

    public Order(Long id, User user, String status) {
        this.id = id;
        this.user = user;
        this.status = status;
    }
}
