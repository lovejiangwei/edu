package com.jw.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jw.eduservice.entity.EduSubject;
import com.jw.eduservice.entity.excel.ExcelData;
import com.jw.eduservice.service.EduSubjectService;
import com.jw.servicebase.excaption.MyException;

import java.util.Map;

/**
 * @ClassName SubjectListener
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/11 23:22
 * @Version 1.0
 **/
public class SubjectListener extends AnalysisEventListener<ExcelData> {

    public EduSubjectService eduSubjectService;

    public SubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }
    public SubjectListener() {
    }

    //每一行数据
    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        if(null == excelData){
            throw new MyException(20001,"文件数据为空");
        }
        //一行一行的读取 每次读取两个值 第一个一级分类  第二个二级分类
        //判断一级是否存在
        EduSubject eduSubject = this.existOneSubject(eduSubjectService, excelData.getOneSubjectName());
        if(null == eduSubject){
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(excelData.getOneSubjectName());
            eduSubjectService.save(eduSubject);
        }
        String pid=eduSubject.getId();
        //判断一级是否存在
        EduSubject eduSubjectTwo = this.existTwoSubject(eduSubjectService, excelData.getTwoSubjectName(), pid);
        if(null == eduSubjectTwo){
            eduSubjectTwo = new EduSubject();
            eduSubjectTwo.setParentId(pid);
            eduSubjectTwo.setTitle(excelData.getTwoSubjectName());
            eduSubjectService.save(eduSubjectTwo);
        }
    }

    /**
     * 判断一级分类不能重复添加
     * */
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0"); // 一级目录为0
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }
    /**
     * 判断二级分类不能重复添加
     * */
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid); // 一级目录为0
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }

    //表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
