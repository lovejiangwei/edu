package com.jw.ossservice.service.imp;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.jw.ossservice.service.OssService;
import com.jw.ossservice.utils.ConstPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


/**
 * @ClassName OssServiceImp
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/10 17:54
 * @Version 1.0
 **/
@Service
public class OssServiceImp implements OssService {
    @Override
    public String uploadeFileAvatar(MultipartFile file) {
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClient(ConstPropertiesUtils.ENDPOINT
                    ,ConstPropertiesUtils.KEYID, ConstPropertiesUtils.KEYSECRET);
            // 上传文件流。
            InputStream inputStream = file.getInputStream();
            String f = file.getOriginalFilename();
            String fileName =UUID.randomUUID().toString().replaceAll("-","")+f;
            String avaterFileName = new DateTime().toString("yyyy-MM-dd");
            ossClient.putObject(ConstPropertiesUtils.BUCKETNAME,avaterFileName+"/"+fileName , inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //需要手动拼接上传后的url地址
            String url = "https://"+ConstPropertiesUtils.BUCKETNAME+"."+ConstPropertiesUtils.ENDPOINT+
                    "/"+avaterFileName+"/"+fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
