package com.lxfly.springcloud.service.impl;

import com.lxfly.springcloud.dao.PaymentDao;
import com.lxfly.springcloud.entities.Payment;
import com.lxfly.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lxfly
 * @create 2020-11-11 22:04
 */
@Service
public class PaymentServiceImpl implements PaymentService{

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById( Long id){
        return paymentDao.getPaymentById(id);
    }
}
