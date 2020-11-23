package com.lxfly.springcloud.entities;

import lombok.*;

/**
 * @author lxfly
 * @create 2020-11-11 21:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult <T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }

}
