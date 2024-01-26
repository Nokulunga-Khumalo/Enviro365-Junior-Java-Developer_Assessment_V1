package com.example.demo.Consumer;


import com.example.demo.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investors")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/{investorEmail}")
    public ResponseEntity<Object> getInvestorDetails(@PathVariable String investorEmail) throws Throwable {
        Investor investorDTO = consumerService.getInvestorDetails(investorEmail);
        return ResponseEntity.ok(investorDTO);
    }

    @GetMapping("/{investorEmail}/products")
    public ResponseEntity<Object> getProductsForInvestor(@PathVariable String investorEmail) {
        List<Product> products = consumerService.getProductsForInvestor(investorEmail);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/create")
    public ResponseEntity<String> newInvestor(@RequestBody Investor investor, @RequestParam String email) {

        try {
            consumerService.addNewInvestor(investor, email);
            return ResponseEntity.status(HttpStatus.CREATED).body("Investor created successfully!");
        } catch (Throwable e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating investor: " + e.getMessage());
        }
    }

}

