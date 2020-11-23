package com.lxfly.springcloud.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

  //超时时间设置
  @Bean
  public Request.Options options() {
    return new Request.Options(4000, 4000);
  }

  @Bean
  public Logger.Level feignLoggerLevel(){
    return Logger.Level.FULL;
  }
}