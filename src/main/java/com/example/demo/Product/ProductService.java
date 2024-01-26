package com.example.demo.Product;


import com.example.demo.Consumer.Investor;
import com.example.demo.NotificationService;
import com.example.demo.WithdrawalNotice.WithdrawalNotice;
//import com.example.demo.WithdrawalNotice.WithdrawalNoticeDTO;
import com.example.demo.WithdrawalNotice.WithdrawalNoticeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
//import com.example.demo.//.ConsumerDTO;
//import com.example.demo.EmailNotificationService;

import java.util.Optional;

//@Service
//public class ProductService {
//
//    @Autowired
//    Investor investor;
////    WithdrawalNotice withdrawalNotice;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//
//    @Autowired
//    private WithdrawalNoticeRepository withdrawalNoticeRepository;
//
//    @Autowired
//    private NotificationService notificationService;
//
//    public WithdrawalNotice createWithdrawalNotice(Long productId, WithdrawalNotice withdrawalNotice) throws ChangeSetPersister.NotFoundException {
//        Optional<Product> optionalProduct = productRepository.findById(String.valueOf(productId));
//        if (optionalProduct.isPresent()) {
//            Product product = optionalProduct.get();
//
//            if ("RETIREMENT".equalsIgnoreCase(product.getType()) && investor.getAge() <= 65) {
//                System.out.println("You need to be older than 65 for the retirement option");
//
//
//            } else if (investor.getAAmount() > (0.9* product.getCurrentBalance())) {
//                System.out.println("Investors cannot withdraw an AMOUNT more than 90% of the current BALANCE");
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final WithdrawalNoticeRepository withdrawalNoticeRepository;
    private final NotificationService notificationService;
    private final Investor investor;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          WithdrawalNoticeRepository withdrawalNoticeRepository,
                          NotificationService notificationService,
                          Investor investor) {
        this.productRepository = productRepository;
        this.withdrawalNoticeRepository = withdrawalNoticeRepository;
        this.notificationService = notificationService;
        this.investor = investor;
    }

    public WithdrawalNotice createWithdrawalNotice(Long productId, WithdrawalNotice withdrawalNotice) throws ChangeSetPersister.NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(String.valueOf(productId));

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            if ("RETIREMENT".equalsIgnoreCase(product.getType()) && investor.getAge() <= 65) {
                throw new IllegalArgumentException("You need to be older than 65 for the retirement option");
            } else if (investor.getAmount() > (0.9 * product.getCurrentBalance())) {
                throw new IllegalArgumentException("Investors cannot withdraw an amount more than 90% of the current balance");

            } else {

                // Create withdrawal notice
                withdrawalNotice.setProduct(product);
                withdrawalNotice.setWithdrawalAmount(withdrawalNotice.getWithdrawalAmount());
                withdrawalNotice.setWithdrawalDate(withdrawalNotice.getWithdrawalDate());
                withdrawalNotice.setBankingDetails(withdrawalNotice.getBankingDetails());

                // Calculate balances
                double balanceBeforeWithdrawal = product.getCurrentBalance();
                double withdrawalAmount = withdrawalNotice.getWithdrawalAmount();
                double closingBalance = balanceBeforeWithdrawal - withdrawalAmount;

                // Update product balance
                product.setCurrentBalance(closingBalance);

                // Save withdrawal notice and update product
                withdrawalNoticeRepository.save(withdrawalNotice);
                productRepository.save(product);

                // Send notification to investor
                sendWithdrawalNotification(product.getInvestor(), balanceBeforeWithdrawal, withdrawalAmount, closingBalance);

                return convertToWithdrawalNoticeDTO(withdrawalNotice);
            }

        } else {
            throw new EntityNotFoundException("Product not found with ID: " + productId);
        }
    }


    private void sendWithdrawalNotification(Investor investor, double initialBalance, double withdrawalAmount, double closingBalance) {
        String notificationMessage = String.format(
                "Withdrawal Notice:\n\n" +
                        "Initial Balance: %.2f\n" +
                        "Withdrawal Amount: %.2f\n" +
                        "Closing Balance: %.2f\n",
                initialBalance, withdrawalAmount, closingBalance);

        notificationService.sendNotification(investor.getEmail(), "Withdrawal Notice", notificationMessage);
    }

    private WithdrawalNotice convertToWithdrawalNoticeDTO(WithdrawalNotice withdrawalNotice) {

        WithdrawalNotice withdrawalNoticeDTO = new WithdrawalNotice();
        withdrawalNoticeDTO.setId(withdrawalNotice.getId());
        withdrawalNoticeDTO.setWithdrawalAmount(withdrawalNotice.getWithdrawalAmount());
        withdrawalNoticeDTO.setWithdrawalDate(withdrawalNotice.getWithdrawalDate());
        withdrawalNoticeDTO.setBankingDetails(withdrawalNotice.getBankingDetails());

        return withdrawalNoticeDTO;
    }

    public com.example.demo.Product.Product getProductDetails(Long productId) {
        return null;
    }

    public void checkProductExistence(Long productId) {
    }
}

