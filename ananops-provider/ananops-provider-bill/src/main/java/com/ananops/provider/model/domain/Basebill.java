package com.ananops.provider.model.domain;

import lombok.Data;

import javax.persistence.*;
@Data
@Table(name = "basebill")
public class Basebill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "`payment method`")
    private String paymentMethod;

    @Column(name = "`transaction method`")
    private String transactionMethod;

    @Column(name = "Amount")
    private Float amount;

    private String userid;

    private Long time;

    private String supplier;

    private String workorderid;

    private String state;


}