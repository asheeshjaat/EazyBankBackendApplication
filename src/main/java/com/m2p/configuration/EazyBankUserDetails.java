//package com.m2p.configuration;
//
//import com.m2p.model.Customer;
//import com.m2p.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class EazyBankUserDetails implements UserDetailsService {
//
//    @Autowired
//    public CustomerRepository customerRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//String userName,password=null;
//        List<GrantedAuthority> authorities=null;
//
//        List<Customer> customerList=customerRepository.findByName(username);
//        if (customerList.size()==0){
//            throw new UsernameNotFoundException("user is not availabe of name "+username);
//        }
//        else {
//
//                userName = customerList.get(0).getName();
//                password = customerList.get(0).getPassword();
//                authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority(customerList.get(0).getRole()));
//
//
//        }
//        return new User(userName,password,authorities);
//    }
//}
