package com.example.demo.Product;


import com.example.demo.WithdrawalNotice.WithdrawalNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

        @Autowired
        private ProductService productService;

        @GetMapping("/{productId}")
        public ResponseEntity<Product> getProductDetails(@PathVariable Long productId) {
            Product productDTO = productService.getProductDetails(productId);
            return ResponseEntity.ok(productDTO);
        }

    @PostMapping("/{productId}/withdrawal")
    public ResponseEntity<Object> createWithdrawalNotice(
            @PathVariable Long productId,
            @RequestBody WithdrawalNotice withdrawalNotice) {
        try {
            WithdrawalNotice withdrawalNoticeDTO = productService.createWithdrawalNotice(productId, withdrawalNotice);
            return ResponseEntity.ok(withdrawalNoticeDTO);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with ID: " + productId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating withdrawal notice: " + e.getMessage());
        }
    }


}


