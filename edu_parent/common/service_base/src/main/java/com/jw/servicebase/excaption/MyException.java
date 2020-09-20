package com.jw.servicebase.excaption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MyException
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/7 15:16
 * @Version 1.0
 **/
@Data
@AllArgsConstructor //生成有参数的够着方法
@NoArgsConstructor  //无参构造器
public class MyException extends RuntimeException {
    private Integer code;
    private String msg;
}
