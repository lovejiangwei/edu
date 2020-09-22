package com.jw.vodservice.controller;

import com.jw.commonutils.R;
import com.jw.vodservice.service.VodVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
/**
 * @ClassName VodVideo
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/21 12:14
 * @Version 1.0
 **/
@Api(description = "阿里云视频点播服务")
@RestController
@CrossOrigin   //跨域
@RequestMapping("/eduvod/video")
public class VodVideoController {

    @Autowired
    VodVideoService vodVideoService;

    @ApiOperation("视频上传接口")
    @PostMapping("/upload")
    public R uploadVideo(
            @ApiParam(value = "视频",name = "file",required = true)
            @RequestParam("file") MultipartFile file) throws Exception{
        String videoId=vodVideoService.uploadVideo(file);
        return R.ok().data("data",videoId);
    }

    @ApiOperation("根据视频资源id删除视频")
    @DeleteMapping("{videoId}")
    public R deleteVideoById(
            @ApiParam(name = "videoId",value = "视频资源id",required = true)
            @PathVariable String videoId){
        vodVideoService.deleteVideoById(videoId);
        return R.ok().message("删除视频成功");
    }


}
