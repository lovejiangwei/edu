package com.jw.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jw.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.eduservice.entity.course.SearchObj;
import com.jw.eduservice.entity.vo.CourseSearchVo;
import com.jw.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-14
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseSearchVo courseInfoVo);

    CourseSearchVo getCourseInfoById(String courseId);

    void updateCourse(CourseSearchVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String id);


    boolean publishCourse(EduCourse e);

    void getCourseList(Page<EduCourse> pageQuery, SearchObj searchObj);

    void deleteCourseById(String id);
}
