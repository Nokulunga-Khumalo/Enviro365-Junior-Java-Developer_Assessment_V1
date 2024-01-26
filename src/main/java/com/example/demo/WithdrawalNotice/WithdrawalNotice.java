package com.example.demo.WithdrawalNotice;


import com.example.demo.Product.Product;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

//import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "withdrawal_notices")
public class WithdrawalNotice {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private double withdrawalAmount;
        private Date withdrawalDate;
        private String bankingDetails;

        // Many withdrawal notices can be associated with one product
        @ManyToOne
        @JoinColumn(name = "productId", nullable = false)
        private Product product;

        // Getters and setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public double getWithdrawalAmount() {
            return withdrawalAmount;
        }

        public void setWithdrawalAmount(double withdrawalAmount) {
            this.withdrawalAmount = withdrawalAmount;
        }

        public Date getWithdrawalDate() {
            return withdrawalDate;
        }

        public void setWithdrawalDate(Date withdrawalDate) {
            this.withdrawalDate = withdrawalDate;
        }

        public String getBankingDetails() {
            return bankingDetails;
        }

        public void setBankingDetails(String bankingDetails) {
            this.bankingDetails = bankingDetails;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

    @Override
    public String toString() {
        return "Product{id=" + id + ", withdrawalDate='" + withdrawalDate + "', withdrawalAmount='" + withdrawalAmount + "', bankingDetails=" + bankingDetails + '}';
    }

}



