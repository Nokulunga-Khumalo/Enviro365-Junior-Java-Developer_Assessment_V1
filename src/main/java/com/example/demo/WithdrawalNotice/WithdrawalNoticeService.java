package com.example.demo.WithdrawalNotice;


import com.example.demo.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WithdrawalNoticeService {

    @Autowired
    private WithdrawalNoticeRepository withdrawalNoticeRepository;

    @Autowired
    private ProductService productService; // Inject the ProductService for additional operations if needed

    public List<WithdrawalNotice> getWithdrawalNoticesForProduct(Long productId) {
        // Check if the product exists
        productService.checkProductExistence(productId);

        // Retrieve withdrawal notices for the given product
        List<WithdrawalNotice> withdrawalNotices = withdrawalNoticeRepository.findByProductId(productId);

        // Convert withdrawal notices to DTOs
        return withdrawalNotices.stream()
                .map(this::convertToWithdrawalNoticeDTO)
                .collect(Collectors.toList());
    }

    private WithdrawalNotice convertToWithdrawalNoticeDTO(WithdrawalNotice withdrawalNotice) {
        // Convert WithdrawalNotice entity to WithdrawalNoticeDTO
        // You can use a library like ModelMapper or manually map the fields
        WithdrawalNotice withdrawalNoticeDTO = new WithdrawalNotice();
        withdrawalNoticeDTO.setId(withdrawalNotice.getId());
        withdrawalNoticeDTO.setWithdrawalAmount(withdrawalNotice.getWithdrawalAmount());
        withdrawalNoticeDTO.setWithdrawalDate(withdrawalNotice.getWithdrawalDate());
        withdrawalNoticeDTO.setBankingDetails(withdrawalNotice.getBankingDetails());
        // ... other fields

        return withdrawalNoticeDTO;
    }


}

