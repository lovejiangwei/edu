package com.jw.eduservice.entity.subject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jw.eduservice.entity.EduSubject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TreeSubject
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/17 14:30
 * @Version 1.0
 **/
@Data
public class TreeSubject extends EduSubject implements Serializable {
    private List<TreeSubject> children = new ArrayList<>();
}
