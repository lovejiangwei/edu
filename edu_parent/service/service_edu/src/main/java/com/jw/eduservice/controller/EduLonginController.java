package com.jw.eduservice.controller;

import com.jw.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName EduLonginController
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/8 17:09
 * @Version 1.0
 **/
@Api(description = "登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLonginController {

    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R info(){
        return R.ok().data("name","蒋伟").data("roles","管理员").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
