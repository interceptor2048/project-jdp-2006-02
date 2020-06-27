package com.kodilla.ecommercee.domain;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private List<User> users = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
