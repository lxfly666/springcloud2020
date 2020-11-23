package com.lxfly.springcloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "CLOUD-PROVIDER-PAYMENT-HYSTRIX",fallback = PaymentHystrixFallback.class, configuration = FeignConfiguration.class)
//configuration = FeignConfiguration.class经测试内中的connectiontimeout与readtimeout对默认（hystrix=1s）事件不起作用，类似ribbon的连接与读取超时时间
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT-HYSTRIX",fallback = PaymentHystrixFallback.class)
public interface PaymentHystrixClient {
  @GetMapping("/payment/hystrix/ok/{id}")
  public String paymentInfo_OK(@PathVariable("id") Integer id);

  @GetMapping("/payment/hystrix/timeout/{id}")
  public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}