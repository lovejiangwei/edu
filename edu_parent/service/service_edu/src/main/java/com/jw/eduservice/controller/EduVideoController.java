package com.jw.eduservice.controller;


import com.jw.commonutils.R;
import com.jw.eduservice.entity.EduVideo;
import com.jw.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-14
 */
@Api(description = "课程小节")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    EduVideoService eduVideoService;

    @ApiOperation("添加课时")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.addVideo(eduVideo);
        return R.ok();
    }
    @ApiOperation("获取一条数据")
    @GetMapping("/getVideoById/{videoId}")
    public R getVideoById(
            @ApiParam(value = "小节ID",name = "videoId",required = true)
            @PathVariable String videoId){
        EduVideo eduVideo=eduVideoService.getVideoById(videoId);
        return R.ok().data("data",eduVideo);
    }

    @ApiOperation("更新课时")
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateVideo(eduVideo);
        return R.ok();
    }

    @ApiOperation("删除课时小节")
    @DeleteMapping("/deleteVideo/{videoId}")
    public R deleteVideo(
            @ApiParam(value = "小节ID",name = "videoId",required = true)
            @PathVariable String videoId){
        eduVideoService.deleteVideo(videoId);
        return R.ok();
    }

}

