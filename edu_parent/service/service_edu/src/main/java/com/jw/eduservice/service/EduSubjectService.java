package com.jw.eduservice.service;

import com.jw.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.eduservice.entity.subject.OneSubject;
import com.jw.eduservice.entity.subject.TreeSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-11
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getList();


    List<TreeSubject> getTree();
}
