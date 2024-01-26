package com.example.demo.Consumer;

import com.example.demo.Product.Product;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "investors")
public class Investor {

    private String firstName;
    private String lastName;

    @Id
    private String email;

    public  double amount;

    private Long contacts;

    private String address;


     private  int age;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;


    public Investor(String firstName, String lastName, String email, List<Product> products, String address, Long contacts, int age, double amount) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        this.products = products;
        this.address= address;
        this.contacts= contacts;
        this.age = age;
        this.amount=amount;

    }

    public Investor() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getContacts() {
        return contacts;
    }

    public void setContacts(Long contacts) {
        this.contacts = contacts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;

    }

    public  int getAge() {
        return age;
    }

    public  void setAge(int age) {
        this.age=age;
    }
    public  double getAmount() {
        return amount;
    }

    public  void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Investor{ firstName='" + firstName + "', lastName='" + lastName + "', email='" + email + "'}";
    }
}




