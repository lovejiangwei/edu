package com.jw.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jw.commonutils.R;
import com.jw.eduservice.entity.EduCourse;
import com.jw.eduservice.entity.course.CourseStatus;
import com.jw.eduservice.entity.course.SearchObj;
import com.jw.eduservice.entity.vo.CoursePublishVo;
import com.jw.eduservice.entity.vo.CourseSearchVo;
import com.jw.eduservice.service.EduCourseService;
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
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    EduCourseService eduCourseService;

    /**
     * 获取所有课程  分页
     * */
    @ApiOperation("分页查询所有课程列表")
    @PostMapping("/getCourseList/{page}/{limit}")
    public R getCourseList(
            @ApiParam(name = "page",value = "当前页",required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit",value = "显示条数",required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "searchObj",value = "条件过滤参数")
            @RequestBody(required = false) SearchObj searchObj){
        Page<EduCourse> pageQuery =new Page<>(page,limit);
        eduCourseService.getCourseList(pageQuery,searchObj);
        long total = pageQuery.getTotal();
        List<EduCourse> data = pageQuery.getRecords();
        return R.ok().data("total",total).data("data",data);
    }

    @ApiOperation("根据id删除课程")
    @DeleteMapping("/{id}")
    public R deleteCourseById(
            @ApiParam(value = "courseid" ,name = "id" ,required = true)
            @PathVariable String id){
        eduCourseService.deleteCourseById(id);
        return R.ok();
    }


    @ApiOperation("添加课程基本信息")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseSearchVo courseInfoVo){
        String courseId = eduCourseService.addCourseInfo(courseInfoVo);
        return R.ok().data("data",courseId);
    }

    @ApiOperation("通过id查询课程基本信息")
    @GetMapping("/getCourseInfo/{courseId}")
    public R getCourseInfoById(
            @ApiParam(value = "courseId",name = "courseId" ,required = true)
            @PathVariable String courseId){
        CourseSearchVo courseInfoVo=eduCourseService.getCourseInfoById(courseId);
        return R.ok().data("data",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("/updateCourse")
    public R updateCourse(@RequestBody CourseSearchVo courseInfoVo){
        eduCourseService.updateCourse(courseInfoVo);
        return R.ok();
    }

    /**
     * 发布课程回显
     */
    @GetMapping("/publish/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id",value = "课程id",required = true)
            @PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishVoById(id);
        return R.ok().data("data",coursePublishVo);
    }

    //发布课程  修改状态
    @PutMapping("/publish/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse e = new EduCourse();
        e.setId(id);
        e.setStatus(CourseStatus.NORMAL);
        eduCourseService.publishCourse(e);
        return R.ok();
    }

}

