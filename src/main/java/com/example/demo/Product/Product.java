package com.example.demo.Product;


import com.example.demo.Consumer.Investor;
import jakarta.persistence.*;



@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private double currentBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investorEmail")
    private Investor investor;

    public Product(Long id, String type, String name, double currentBalance) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.currentBalance = currentBalance;
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", type='" + type + "', name='" + name + "', currentBalance=" + currentBalance + '}';
    }


    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }
}

