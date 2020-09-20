package com.jw.demo.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/11 19:10
 * @Version 1.0
 **/
public class ExcelListener  extends AnalysisEventListener<ExcelData> {
    //一行一行读取excel数据  data
    @Override
    public void invoke(ExcelData data, AnalysisContext analysisContext) {

        System.out.println(data+"======================");
    }
    //读取表头的方法
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头======>"+headMap);
    }

    //读取完成后的方法
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完成");

    }
}
