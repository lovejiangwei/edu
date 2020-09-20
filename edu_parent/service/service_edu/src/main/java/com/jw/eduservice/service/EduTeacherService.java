package com.jw.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jw.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.eduservice.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-07
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
