package com.jw.vodservice.service.imp;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.jw.commonutils.R;
import com.jw.servicebase.excaption.MyException;
import com.jw.vodservice.service.VodVideoService;
import com.jw.vodservice.utlis.AliYunVideoUploadUtil;
import com.jw.vodservice.utlis.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @ClassName VodVideoServiceImp
 * @Description TODO
 * @Author Jiang wei
 * @Date 2020/9/21 12:18
 * @Version 1.0
 **/
@Service
public class VodVideoServiceImp implements VodVideoService {

    /**
     * 阿里云vod视频上传
     * */
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            InputStream in = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            R r = AliYunVideoUploadUtil.UploadStream(title, fileName, in);
            Object videoId = r.getData().get("videoId");
                return videoId.toString();
        }catch (Exception e){
            throw new MyException(20001,"视频上传失败！");
        }
    }

    /**
     * 根据视频资源id删除视频
     * */
    @Override
    public void deleteVideoById(String videoId) {
        try {
            AliYunVideoUploadUtil.deleteVideo(videoId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败！");
        }
    }

}
