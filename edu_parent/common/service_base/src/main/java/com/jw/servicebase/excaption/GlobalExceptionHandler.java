package com.jw.servicebase.excaption;


import com.jw.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理  @ControllerAdvice
 * @Author Jiang wei
 * @Date 2020/9/7 14:50
 * @Version 1.0
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("服务器内部错误！");
    }
    //特定异常处理  除数为0
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理器！");
    }
    //自定义异常
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R error(MyException e){
        log.error(e.getMsg());
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
