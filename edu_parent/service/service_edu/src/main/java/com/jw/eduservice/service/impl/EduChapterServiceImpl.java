package com.jw.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jw.commonutils.R;
import com.jw.eduservice.client.VodClient;
import com.jw.eduservice.entity.EduChapter;
import com.jw.eduservice.entity.EduVideo;
import com.jw.eduservice.entity.chapter.ChapterVo;
import com.jw.eduservice.entity.chapter.VideoVo;
import com.jw.eduservice.mapper.EduChapterMapper;
import com.jw.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.eduservice.service.EduVideoService;
import com.jw.servicebase.excaption.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-14
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService eduVideoService;

    //远程调用视频微服务
    @Autowired
    VodClient vodClient;

    //根据课程id查询
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id查询所有章节
        QueryWrapper<EduChapter> queryEduChapter = new QueryWrapper<>();
        queryEduChapter.eq("course_id",courseId);
        queryEduChapter.orderByAsc("sort");
        List<EduChapter> eduChaptersList = baseMapper.selectList(queryEduChapter);
        //查询所有小节
        QueryWrapper<EduVideo> eduVideoQueryWrapper =new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        eduVideoQueryWrapper.orderByAsc("sort");
        List<EduVideo> eduVideoList = eduVideoService.list(eduVideoQueryWrapper);
        //创建一个最终返回list
        List<ChapterVo> resultList = new ArrayList<>();
        for (int i = 0; i < eduChaptersList.size(); i++) {
            EduChapter eduChapter = eduChaptersList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            resultList.add(chapterVo);
            //遍历章节里面的小节
            List<VideoVo> video = new ArrayList<>();
            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    video.add(videoVo);
                }
            }
            chapterVo.setChildren(video);
        }
        return resultList;
    }


    @Override
    public EduChapter getChapterById(String chapterId) {
        EduChapter eduChapter = baseMapper.selectById(chapterId);
        return eduChapter;
    }

    @Override
    public void updateChapter(EduChapter eduChapter) {
        int i = baseMapper.updateById(eduChapter);
        if(i==0){
            throw new MyException(20001,"更新失败");
        }
    }

    @Override
    public void addChapter(EduChapter eduChapter) {
        int insert = baseMapper.insert(eduChapter);
        if(insert ==0){
            throw new MyException(20001,"添加失败");
        }
    }

    /**
     * 删除章节  删除章节时需要删除章节下面的所有小节
     * 添加事务管理 防止删除了某一个而另一个没有删除
     * */
    @Override
    @Transactional
    public void deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        //获取需要删除的视频资源id
        List<EduVideo> videoList = eduVideoService.list(queryWrapper);
        String videoIds = "";
        for (EduVideo eduVideo : videoList) {
            if(!StringUtils.isEmpty(eduVideo.getVideoSourceId())){
                videoIds+=eduVideo.getVideoSourceId()+",";
            }
        }
        //删除小节
        boolean remove = eduVideoService.remove(queryWrapper);
        if(!remove){
            throw new MyException(20001,"删除小节失败");
        }
        //删除章节
        boolean b = this.removeById(chapterId);
        if(!StringUtils.isEmpty(videoIds)){
            videoIds = videoIds.substring(0,videoIds.length()-1);
            //删除远程服务器上的视频
            R r = vodClient.deleteVideoById(videoIds);
            if(!r.getSuccess()){
                throw new MyException(20001,"删除视频失败");
            }
        }
        if(!b){
            throw new MyException(20001,"删除章节失败");
        }
    }
}
