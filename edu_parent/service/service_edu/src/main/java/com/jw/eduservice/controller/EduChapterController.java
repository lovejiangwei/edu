package com.jw.eduservice.controller;


import com.jw.commonutils.R;
import com.jw.eduservice.entity.EduChapter;
import com.jw.eduservice.entity.chapter.ChapterVo;
import com.jw.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-14
 */
@Api(description = "章节管理")
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    EduChapterService eduChapterService;
    //课程大纲列表  根据课程id查询
    @ApiOperation("树形结构")
    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideo(
            @ApiParam(value = "课程id",name = "courseId",required = true)
            @PathVariable String courseId){
        List<ChapterVo> list =eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("data",list);
    }

    @ApiOperation("添加章节")
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.addChapter(eduChapter);
        return R.ok();
    }

    @ApiOperation("根据id获取一条数据")
    @GetMapping("/getChapterById/{chapterId}")
    public R getChapterById(
            @ApiParam(value = "章节id",name = "chapterId",required = true)
            @PathVariable String chapterId){
        EduChapter eduChapter=eduChapterService.getChapterById(chapterId);
        return R.ok().data("data",eduChapter);
    }

    @ApiOperation("更新数据")
    @PostMapping("/updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateChapter(eduChapter);
        return R.ok();
    }

    //删除章节
    @ApiOperation("删除章节及其下面所有小节")
    @DeleteMapping("/deleteChapter/{chapterId}")
    public R deleteChapter(
            @ApiParam(value = "章节id" ,name = "chapterId",required = true)
            @PathVariable String chapterId){
        eduChapterService.deleteChapter(chapterId);
        return R.ok();
    }





}

