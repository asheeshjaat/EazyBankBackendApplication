package com.m2p.controller;

import com.m2p.model.Customer;
import com.m2p.repository.CustomerRepository;
import com.sun.jdi.event.StepEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;
    @PostMapping("/register/customer")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
        Customer savedCustomer=null;
        log.info("receive request of customer "+customer.getName());
        ResponseEntity response=null;
        try{
            String hashed=bCryptPasswordEncoder.encode(customer.getPassword());
            customer.setPassword(hashed);
            savedCustomer=customerRepository.save(customer);
if (savedCustomer.getId()>0){
    return ResponseEntity.status(HttpStatus.CREATED).body("successfully added");

}
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
        return  null;
    }
}
