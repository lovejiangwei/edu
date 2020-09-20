package com.jw.demo.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName TestEasyExcel
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/11 9:42
 * @Version 1.0
 **/
public class TestEasyExcel {
    public static void main(String[] args) {
//        //实现ecxel操作
//        //1、设置写入文件夹和文件名称C:\jiangwei
//        String filename = "C:\\jiangwei\\write.xlsx";
//        //2、调用easyExcel方法filename 文件路径名称   ExcelData.class实体类
//        EasyExcel.write(filename,ExcelData.class).sheet("学生列表").doWrite(getData());

        //读操作
        String filename = "C:\\jiangwei\\write.xlsx";
        EasyExcel.read(filename,ExcelData.class,new ExcelListener()).sheet().doRead();

    }

    //创建方法返回list
    private static List<ExcelData> getData(){
        List<ExcelData> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            ExcelData excelData = new ExcelData();
            excelData.setId(i+1);
            excelData.setName(UUID.randomUUID().toString().replaceAll("-",""));
            list.add(excelData);
        }
        return list;
    }
}
