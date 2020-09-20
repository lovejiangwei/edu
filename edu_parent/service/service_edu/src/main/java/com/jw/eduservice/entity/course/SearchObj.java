package com.jw.eduservice.entity.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SearchObj
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/20 15:23
 * @Version 1.0
 **/
@Data
public class SearchObj implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;
}
