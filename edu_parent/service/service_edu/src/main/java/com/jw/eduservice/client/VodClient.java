package com.jw.eduservice.client;

import com.jw.commonutils.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * description: 远程调用上传视频微服务接口  OpenFeign组件
 * */
@Component //给Spring管理
@FeignClient(name="service-vod",fallback = VodFileFeignClientFalllback.class)  //调用哪个微服务
public interface VodClient {
    //删除视频
    @DeleteMapping("/eduvod/video/{videoId}")
    R deleteVideoById(@PathVariable("videoId") String videoId);

}
