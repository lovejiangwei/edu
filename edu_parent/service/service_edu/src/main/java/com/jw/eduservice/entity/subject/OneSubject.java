package com.jw.eduservice.entity.subject;

import lombok.Data;

import java.util.List;

/**
 * @ClassName OneSubject
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/13 22:50
 * @Version 1.0
 **/
@Data
public class OneSubject {

    private String id;

    private String title;

    private List<TwoSubject> children;
}
