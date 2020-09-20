package com.jw.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jw.eduservice.entity.EduChapter;
import com.jw.eduservice.entity.EduCourse;
import com.jw.eduservice.entity.EduCourseDescription;
import com.jw.eduservice.entity.EduVideo;
import com.jw.eduservice.entity.course.SearchObj;
import com.jw.eduservice.entity.vo.CourseSearchVo;
import com.jw.eduservice.entity.vo.CoursePublishVo;
import com.jw.eduservice.mapper.EduCourseMapper;
import com.jw.eduservice.service.EduChapterService;
import com.jw.eduservice.service.EduCourseDescriptionService;
import com.jw.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jw.eduservice.service.EduVideoService;
import com.jw.servicebase.excaption.MyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-14
 */
@Service
public class  EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduChapterService eduChapterService;
    //添加课程表基本信息
    @Override
    @Transactional
    public String addCourseInfo(CourseSearchVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0){
            throw new MyException(20001,"添加课程信息失败");
        }
        //获取添加课程的id
        String cid = eduCourse.getId();
        //添加描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        eduCourseDescription.setId(cid);
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        if(!save){
            throw new MyException(20001,"添加课程详细信息失败");
        }
        return eduCourse.getId();

    }

    @Override
    public CourseSearchVo getCourseInfoById(String courseId) {
        //1查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        //2查询课程描述表
        EduCourseDescription byId = eduCourseDescriptionService.getById(courseId);
        CourseSearchVo courseInfoVo = new CourseSearchVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(byId.getDescription());
        return courseInfoVo;
    }

    @Override
    @Transactional
    public void updateCourse(CourseSearchVo courseInfoVo) {
        EduCourse eduCourse =new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if(i==0){
            throw new MyException(20001,"修改失败！");
        }
        //修改课程详细信息
        EduCourseDescription e = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,e);
        eduCourseDescriptionService.updateById(e);
    }

    //发布课程回显
    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourse(EduCourse e) {
        Integer i = baseMapper.updateById(e);
        return null != i && i>0;
    }

    /**
     * 分页查询所有课程
     * */
    @Override
    public void getCourseList(Page<EduCourse> pageQuery, SearchObj searchObj) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if(searchObj == null){
            baseMapper.selectPage(pageQuery,queryWrapper);
            return;
        }
        if(!StringUtils.isEmpty(searchObj.getTitle())){
            queryWrapper.like("title",searchObj.getTitle());
        }
        if(!StringUtils.isEmpty(searchObj.getSubjectId())){
            queryWrapper.eq("subject_id",searchObj.getSubjectId());
        }
        if(!StringUtils.isEmpty(searchObj.getSubjectParentId())){
            queryWrapper.eq("subject_parent_id",searchObj.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(searchObj.getTeacherId())){
            queryWrapper.eq("teacher_id",searchObj.getTeacherId());
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageQuery,queryWrapper);
    }

    /**
     * 删除课程  1、删除视频
     *          2、删除小节
     *          3、删除章节
     *          4、删除描述信息
     *          5、删除课程信息
     *          添加事务管理
     * */
    @Override
    @Transactional
    public void deleteCourseById(String id) {
        //1、删除视频 TODO

        //2、删除小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        eduVideoService.remove(videoQueryWrapper);
        //3、删除章节信息
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        eduChapterService.remove(chapterQueryWrapper);
        //4、删除描述信息
        eduCourseDescriptionService.removeById(id);
        //5、删除课程信息
        baseMapper.deleteById(id);
    }


}
