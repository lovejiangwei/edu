package com.jw.eduservice.mapper;

import com.jw.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jw.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-14
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**发布课程信息回显*/
    CoursePublishVo selectCoursePublishVoById(String courseId);

}
