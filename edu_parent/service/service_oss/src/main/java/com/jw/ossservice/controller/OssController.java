package com.jw.ossservice.controller;

import com.jw.commonutils.R;
import com.jw.ossservice.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName OssController
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/10 17:53
 * @Version 1.0
 **/
@Api(description = "文件上传")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;
    /**
     * 上传头像方法
     * @parm MultipartFile spring自带文件上传对象  可以获取上传的文件
     * */
    @ApiOperation("文件上传")
    @PostMapping("/uploadefile")
    public R uploadeOssFile( MultipartFile file) throws IOException {
        if(file==null){
            return R.error().message("未上传文件!文件为空！");
        }
        String url = ossService.uploadeFileAvatar(file);
            return R.ok().data("url",url);
    }

}
