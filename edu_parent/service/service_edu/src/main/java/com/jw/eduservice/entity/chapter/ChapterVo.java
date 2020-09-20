package com.jw.eduservice.entity.chapter;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ChapterVo
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/16 4:02
 * @Version 1.0
 **/
@Data
public class ChapterVo {
    private String id;
    private String title;
    private Integer level=0;
    //小节
    private List<VideoVo> children;
}
