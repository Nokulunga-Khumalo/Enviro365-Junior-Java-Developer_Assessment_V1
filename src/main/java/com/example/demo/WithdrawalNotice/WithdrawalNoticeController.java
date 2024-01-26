package com.example.demo.WithdrawalNotice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawalNoticeController {
    @Autowired
    WithdrawalNoticeService withdrawalNoticeService;
}
