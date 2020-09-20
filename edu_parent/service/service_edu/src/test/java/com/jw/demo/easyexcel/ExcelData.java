package com.jw.demo.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName ExcelData
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/11 9:40
 * @Version 1.0
 **/
@Data
public class ExcelData {

    //设置excel表头
    @ExcelProperty("学生编号")
    private Integer id;
    @ExcelProperty("学生姓名")
    private String name;
}
