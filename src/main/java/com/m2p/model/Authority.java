package com.m2p.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="authrotities")
public class Authority {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    int id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    String name;
}
