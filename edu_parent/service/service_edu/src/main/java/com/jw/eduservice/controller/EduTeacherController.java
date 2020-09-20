package com.jw.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jw.commonutils.R;
import com.jw.eduservice.entity.EduTeacher;
import com.jw.eduservice.entity.vo.TeacherQuery;
import com.jw.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-07
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询表中所有数据
    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public R findAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }
    //逻辑删除
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){
        boolean b= eduTeacherService.removeById(id);
        if (b){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 分页查询
     * */
    @ApiOperation("讲师分页条件查询")
    @PostMapping("/pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable Integer current,
            @ApiParam(name = "limit",value = "每页显示条数",required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "teacherQuery",value = "需要条件过滤的参数",required = false)
            @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.pageQuery(page,teacherQuery);
        long total = page.getTotal();
        List<EduTeacher> data = page.getRecords();
        return R.ok().data("total",total).data("rows",data);
    }

    /**
     * 添加讲师
     * */
    @ApiOperation("添加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        return save==true ? R.ok() : R.error();
    }

    /**
     * 根据讲师ID查询
     * */
    @ApiOperation("ID查询讲师")
    @GetMapping("/getTeacherById/{id}")
    public R getTeacherById(
            @ApiParam(value = "讲师ID",name = "id",required = true)
            @PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    /**
     * 根据id修改讲师
     * */
    @ApiOperation("修改讲师")
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        return b==true ? R.ok() : R.error();
    }


}

