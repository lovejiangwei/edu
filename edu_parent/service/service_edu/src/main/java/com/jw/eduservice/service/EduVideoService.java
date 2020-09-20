package com.jw.eduservice.service;

import com.jw.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-14
 */
public interface EduVideoService extends IService<EduVideo> {

    void deleteVideo(String videoId);

    void updateVideo(EduVideo eduVideo);

    EduVideo getVideoById(String videoId);

    void addVideo(EduVideo eduVideo);
}
