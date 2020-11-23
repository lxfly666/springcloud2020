package com.lxfly.springcloud.controller;

import com.lxfly.springcloud.client.PaymentFeignClient;
import com.lxfly.springcloud.entities.CommonResult;
import com.lxfly.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lxfly
 * @create 2020-11-18 23:27
 */
@RestController
public class OrderController {

    @Autowired
    PaymentFeignClient paymentFeignClient;

    @GetMapping(value = "/consumer/payment/create")
    public CommonResult<Integer> create(Payment payment){
        return paymentFeignClient.create(payment);
    };

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignClient.getPaymentById(id);
    };


    @RequestMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeignClient.paymentFeignTimeout();
    }
    
}
