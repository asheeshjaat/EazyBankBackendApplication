package com.m2p.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private int id ;

    private String name ;
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @JsonIgnore
    private String pwd;

    private String role;

    @Column(name ="create_dt")
    private String create_dt;

}
