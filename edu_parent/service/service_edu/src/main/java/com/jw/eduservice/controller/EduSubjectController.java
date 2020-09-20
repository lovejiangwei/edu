package com.jw.eduservice.controller;


import com.jw.commonutils.R;
import com.jw.eduservice.entity.subject.OneSubject;
import com.jw.eduservice.entity.subject.TreeSubject;
import com.jw.eduservice.service.EduSubjectService;
import com.jw.eduservice.utils.SubjectTreeUtil;
import io.swagger.annotations.Api;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-11
 */
@Api(description = "课程管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    EduSubjectService eduSubjectService;

    @GetMapping("/getTree")
    public R getTree(){
        List<TreeSubject> list=eduSubjectService.getTree();
        list = SubjectTreeUtil.buildTree(list);
        return R.ok().data("data",list);
    }

    //课程分类列表（树形）
    @GetMapping("getList")
    public R getList(){
       List<OneSubject> list= eduSubjectService.getList();
        return R.ok().data("data",list);
    }



    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file) throws IOException {
        boolean excelFile = this.isExcelFile(file.getInputStream(),file);
        System.out.println(excelFile);
        if (!excelFile){
            return R.error().message("上传的不是Excel文件！");
        }else {
            eduSubjectService.addSubject(file,eduSubjectService);
            return R.ok();
        }
    }



    //判断文件是否是Excel文件  需要poi 3.1以上版本
    private  boolean isExcelFile(InputStream inputStream,MultipartFile file){
        boolean result = false;
        try {
            inputStream=FileMagic.prepareToCheckMagic(inputStream);
            FileMagic fileMagic = FileMagic.valueOf(inputStream);
            if(Objects.equals(fileMagic,FileMagic.OLE2) || Objects.equals(fileMagic,FileMagic.OOXML)){
                String name = file.getOriginalFilename();
                System.out.println(name);
                String suffix =name.substring(name.lastIndexOf(".")+1);
                System.out.println(suffix);
                if(suffix.equals("xls") || suffix.equals("xlsx")){
                    result = true;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

}

