package com.m2p.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

@GetMapping("/myAccount")
    public String getAccountDetails(){
        return "Here is the Account details of User";
    }
}
