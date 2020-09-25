package com.jw.eduservice.client;

import com.jw.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @ClassName VodFileFeignClientFalllback
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/25 10:27
 * @Version 1.0
 **/
@Component
public class VodFileFeignClientFalllback implements VodClient {

    @Override
    public R deleteVideoById(String videoId) {
        return R.error().message("删除视频出错了");
    }
}
