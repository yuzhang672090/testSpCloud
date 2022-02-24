package com.buba.springcloud.service;


import com.buba.springcloud.pojo.Payment;

public interface PaymentService {

    int create(Payment payment);
    Payment queryById(Long id);
}
