package com.jw.eduservice.service;

import com.jw.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jw.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-14
 */
public interface EduChapterService extends IService<EduChapter> {


    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    EduChapter getChapterById(String chapterId);

    void updateChapter(EduChapter eduChapter);


    void addChapter(EduChapter eduChapter);

    void deleteChapter(String chapterId);
}
