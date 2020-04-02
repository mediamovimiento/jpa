package com.mediamovimiento.model;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "CATEGORY")
public class Category {

    private int id;
    private String name;

    private Set<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "CATEGORY_ID")
    public int getId() {
        return id;
    }


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public Set<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
// other getters and setters

    public void setId(int id) {
        this.id = id;
    }
}