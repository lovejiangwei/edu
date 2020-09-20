package com.jw.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName ExcelData
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/11 23:16
 * @Version 1.0
 **/
@Data
public class ExcelData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
