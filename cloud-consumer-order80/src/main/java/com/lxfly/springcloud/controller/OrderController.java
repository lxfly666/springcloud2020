package com.lxfly.springcloud.controller;

import com.lxfly.springcloud.entities.CommonResult;
import com.lxfly.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

  //public static final String PAYMENT_URL = "http://localhost:8001";
  public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";


  @Autowired
  RestTemplate restTemplate;

  @GetMapping(value = "/consumer/payment/get/{id}")
  public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
    return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
  }

  @GetMapping(value = "/consumer/payment/getForEntity/{id}")
  public CommonResult<Payment> getPaymentById2(@PathVariable("id") Long id) {
    ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    if(entity.getStatusCode().is2xxSuccessful()){
        return entity.getBody();
    }else{
        return new CommonResult(444,"操作失败");
    }
  }

  @GetMapping("/consumer/payment/create")
  public CommonResult<Payment> create(Payment payment) {
    return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
  }

  @GetMapping("/consumer/payment/createForEntity")
  public CommonResult<Payment> create2(Payment payment) {
    ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    if(entity.getStatusCode().is2xxSuccessful()){
        return entity.getBody();
    }else{
      return new CommonResult(444,"操作失败");
    }
  }
  // ====================> zipkin+sleuth
  @GetMapping("/consumer/payment/zipkin")
  public String paymentZipkin() {
    return restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
  }

}
