package com.lxfly.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lxfly
 * @create 2020-11-18 21:31
 */
@Component
public class MyLoadBalancerImpl implements MyLoadBalancer {

    private final AtomicInteger atomicInteger = new AtomicInteger();

    //坐标
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));  //第一个参数是期望值，第二个参数是修改值是
        System.out.println("*******第几次访问，次数next: " + next);
        return next;
    }


    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size(); //得到服务器的下标位置
        return serviceInstances.get(index);
    }

}
