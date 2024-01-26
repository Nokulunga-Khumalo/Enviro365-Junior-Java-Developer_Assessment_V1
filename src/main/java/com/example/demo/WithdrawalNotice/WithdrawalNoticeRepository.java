package com.example.demo.WithdrawalNotice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long> {

    // Custom query method to find withdrawal notices by product ID
    List<WithdrawalNotice> findByProductId(Long productId);


}

