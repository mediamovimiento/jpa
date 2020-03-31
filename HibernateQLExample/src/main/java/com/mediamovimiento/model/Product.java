package com.mediamovimiento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
    private long id;
    private String name;
    private String description;
    private float price;

    private Category category;

    public Product() {
    }

    public Product(String name, String description, float price,
                   Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue
    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    public Category getCategory() {
        return category;
    }

    // other getters and setters
}