package com.jw.ossservice;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/10 18:42
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\Administrator\\Desktop\\z.jpg");
        System.out.println(f.getName().substring(f.getName().lastIndexOf(".")+1));
        String id = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(id);
    }
}
