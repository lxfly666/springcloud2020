package com.lxfly.springcloud.service;

import com.lxfly.springcloud.entities.Payment;

/**
 * @author lxfly
 * @create 2020-11-11 22:02
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}


