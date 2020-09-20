package com.jw.eduservice.service.impl;

import com.jw.eduservice.entity.EduVideo;
import com.jw.eduservice.mapper.EduVideoMapper;
import com.jw.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.servicebase.excaption.MyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-14
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    /**
     * 删除小节
     *   TODO 同时删除远程服务器上面的视频
     * */
    @Override
    public void deleteVideo(String videoId) {
        int i = baseMapper.deleteById(videoId);
        if(i==0){
            throw new MyException(20001,"删除失败");
        }
    }

    @Override
    public void updateVideo(EduVideo eduVideo) {
        boolean b = this.updateById(eduVideo);
        if(!b){
            throw new MyException(20001,"更新失败");
        }
    }

    @Override
    public EduVideo getVideoById(String videoId) {
        EduVideo eduVideo = baseMapper.selectById(videoId);
        return eduVideo;
    }

    /**
     * 添加小节
     *  TODO 上传视频到远程服务器
     * */
    @Override
    public void addVideo(EduVideo eduVideo) {
        int insert = baseMapper.insert(eduVideo);
        if (insert == 0){
            throw new MyException(20001,"添加失败");
        }
    }
}
