package com.jw.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jw.eduservice.entity.EduSubject;
import com.jw.eduservice.entity.excel.ExcelData;
import com.jw.eduservice.entity.subject.OneSubject;
import com.jw.eduservice.entity.subject.TreeSubject;
import com.jw.eduservice.entity.subject.TwoSubject;
import com.jw.eduservice.listener.SubjectListener;
import com.jw.eduservice.mapper.EduSubjectMapper;
import com.jw.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author jiangwei
 * @since 2020-09-11
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 添加课程分类
     * */
    @Override
    public void addSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelData.class,new SubjectListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 返回树形列表
     * */
    @Override
    public List<OneSubject> getList() {
        //1级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> listone = baseMapper.selectList(wrapperOne);
        //二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> listtwo = baseMapper.selectList(wrapperTwo);

        List<OneSubject> resultList = new ArrayList<>();
        for (int i = 0; i < listone.size(); i++) {
            EduSubject eduSubject = listone.get(i);
            OneSubject o = new OneSubject();
            BeanUtils.copyProperties(eduSubject,o);
            resultList.add(o);

            //二级分类
            List<TwoSubject> tworesult = new ArrayList<>();
            for (int m = 0; m < listtwo.size(); m++) {
                EduSubject tSubject = listtwo.get(m);
                if(tSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject t = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,t);
                    tworesult.add(t);
                }
            }
            o.setChildren(tworesult);
        }
        return resultList;
    }

    @Override
    public List<TreeSubject> getTree() {
        List<EduSubject> eduSubjects = baseMapper.selectList(null);
        List<TreeSubject> result = new ArrayList<>();
        for (EduSubject e : eduSubjects){
            TreeSubject t = new TreeSubject();
            BeanUtils.copyProperties(e,t);
            result.add(t);
        }
        return result;
    }

}
