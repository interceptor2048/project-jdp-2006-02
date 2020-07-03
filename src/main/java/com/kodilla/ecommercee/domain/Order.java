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

    @Column(name = "ORDER_NAME")
    private String orderName;

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

    public Order(String orderName, User user) {
        this.orderName = orderName;
        this.user = user;
    }
}
