package com.lxfly.springcloud.controller;

import com.lxfly.springcloud.client.PaymentHystrixClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globTimeOutFallbackMethod",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3500")
})
public class OrderHystrixController {

  @Resource
  private PaymentHystrixClient paymentHystrixClient;

  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/consumer/payment/hystrix/ok/{id}")
  public String paymentInfo_OK(@PathVariable("id") Integer id){
    String result = paymentHystrixClient.paymentInfo_OK(id);
    log.info("*******result:"+result);
    return result;
  }

  @GetMapping("/consumer/payment/hystrix/timeout/{id}")
  @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
          @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
  })
  public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
    String result = paymentHystrixClient.paymentInfo_TimeOut(id);
    log.info("*******result:"+result);
    return result;
  }

  @GetMapping("/consumer/payment/hystrix/timeout1/{id}")
  public String paymentInfo_TimeOut1(@PathVariable("id") Integer id){
    String result = paymentHystrixClient.paymentInfo_TimeOut(id);
    log.info("*******result:"+result);
    return result;
  }

  //没有指定fallbackMethod ，则用全局的：@DefaultProperties(defaultFallback = "globTimeOutFallbackMethod")
  @GetMapping("/consumer/payment/hystrix/timeout2/{id}")
  @HystrixCommand
  public String paymentInfo_TimeOut2(@PathVariable("id") Integer id){
    String result = paymentHystrixClient.paymentInfo_TimeOut(id);
    log.info("*******result:"+result);
    return result;
  }

  //没有指定fallbackMethod ，则用全局的：@DefaultProperties(defaultFallback = "globTimeOutFallbackMethod")
  @HystrixCommand
  @GetMapping("/consumer/testException")
  public String testException(){
    return (1/0)+"";
  }

  public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
    return "80,()";
  }

  public String globTimeOutFallbackMethod() {
    return "服务器繁忙，请稍后重试";
  }

}