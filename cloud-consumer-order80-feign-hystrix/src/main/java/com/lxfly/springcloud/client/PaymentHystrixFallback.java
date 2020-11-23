package com.lxfly.springcloud.client;

import org.springframework.stereotype.Component;

/**
 * @author lxfly
 * @create 2020-11-21 23:18
 */
@Component
public class PaymentHystrixFallback implements PaymentHystrixClient {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_OK , (┬＿┬)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut , (┬＿┬)";
    }

}
