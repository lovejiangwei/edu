package com.jw.eduservice.utils;

import com.jw.eduservice.entity.subject.TreeSubject;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SubjectTreeUtil
 * @Description 科目树形菜单
 * @Author Jiang wei
 * @Date 2020/9/17 14:44
 * @Version 1.0
 **/
public class SubjectTreeUtil {
    public static List<TreeSubject> buildTree(List<TreeSubject> list){
        List<TreeSubject> tree  = new ArrayList<>();
        for(TreeSubject t : list){
            if(t.getParentId().equals("0")){
                //1级目录
                tree.add(getChild(t,list));
            }
        }
        return tree;
    }

    public static TreeSubject getChild(TreeSubject parent,List<TreeSubject> list){
        for(TreeSubject t : list){
            if(t.getParentId().equals(parent.getId())){
                parent.getChildren().add(getChild(t,list));
            }
        }
        return parent;
    }
}
