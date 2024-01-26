package com.example.demo.Product;

import com.example.demo.Consumer.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByEmail(String email);

        List<Product> findByInvestor(Investor investor);

    }

