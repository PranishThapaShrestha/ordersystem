package com.buddha.ordersystem.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "products")
    private String productName;

    @Column(name = "product_availability")
    private boolean productsAvailable;

    @Column(name = "product_code")
    private String productCode;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderProducts = new ArrayList<>();

    private Long price;

    public Product() {

    }

    public Product(String productName, boolean productAvailable, String productCode) {
        this.productsAvailable = productAvailable;
        this.productName = productName;
        this.productCode = productCode;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public boolean isProductsAvailable() {
        return productsAvailable;
    }

    public void setProductsAvailable(boolean productsAvailable) {
        this.productsAvailable = productsAvailable;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
