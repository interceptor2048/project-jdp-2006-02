package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "T_USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Setter
    @Column(name = "STATUS")
    private String status;

    @Setter
    @Column(name = "USER_KEY")
    private int userKey;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = com.kodilla.ecommercee.domain.Product.class)
    @JoinTable(
            name = "CART",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> productList = new ArrayList<>();

    public User(String username, String status, int userKey) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }

    public User(Long id, String username, String status, int userKey) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }
}
