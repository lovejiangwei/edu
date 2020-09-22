package com.jw.vodservice.utlis;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;
import com.jw.commonutils.R;
import com.jw.servicebase.excaption.MyException;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

/**
 * @ClassName AliYunVideoUploadUtil
 * @Description 阿里云视频点播工具
 * @Author Jiang wei
 * @Date 2020/9/21 12:39
 * @Version 1.0
 **/
public class AliYunVideoUploadUtil {

    private static DefaultAcsClient client;
    static {
        //创建初始化对象
        try {
             client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //根据资源id获取视频播放地址
    public static String getVideoUrl(String videoId) throws Exception {
        //创建获取视频地址的request 和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        //向request设置资源id
        request.setVideoId(videoId);
        //调用初始化对象传递request获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList =response.getPlayInfoList();
        if (playInfoList.size() !=0){
            return playInfoList.get(0).getPlayURL();
        }
        return null;
    }

    //根据资源id获取凭证
    public static String getVideoAuth(String videoId) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId(videoId);
        response=client.getAcsResponse(request);
        return response.getPlayAuth();
    }

    /**
     * 根据资源id删除视频
     * @param videoId
     * */
    public static void deleteVideo(String videoId) throws Exception {
        if(StringUtils.isEmpty(videoId)){
            throw new MyException(20001,"视频资源id不存在");
        }
        DeleteVideoRequest request = new DeleteVideoRequest();
        DeleteVideoResponse response = new DeleteVideoResponse();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        response = client.getAcsResponse(request);
    }

    /**
     * 本地文件上传接口
     * @param title  文件上传后的名称  aaa
     * @param fileName  本地文件路径 c:\\a.mp4
     */
    public static R LocalUploadVideo(String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        if (response.isSuccess()) {
            return R.ok().data("videoId",response.getVideoId());
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            return R.ok().message(response.getMessage()).data("videoId",response.getVideoId()).code(Integer.parseInt(response.getCode()));
        }
    }

    /**
     * 流式上传接口
     *
     * @param title
     * @param fileName
     * @param inputStream
     */
    public static R UploadStream( String title, String fileName, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID,
                ConstantPropertiesUtil.ACCESS_KEY_SECRET, title, fileName, inputStream);
        /* 是否使用默认水印(可选)，指定模板组ID时，根据模板组配置确定是否使用默认水印*/
        //request.setShowWaterMark(true);
        /* 设置上传完成后的回调URL(可选)，建议通过点播控制台配置消息监听事件，参见文档 https://help.aliyun.com/document_detail/57029.html */
        //request.setCallback("http://callback.sample.com");
        /* 自定义消息回调设置，参数说明参考文档 https://help.aliyun.com/document_detail/86952.html#UserData */
        //request.setUserData(""{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackURL\":\"http://test.test.com\"}}"");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 工作流ID(可选) */
        //request.setWorkflowId("d4430d07361f0*be1339577859b0177b");
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        // request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        /* 设置应用ID*/
        //request.setAppId("app-1000000");
        /* 点播服务接入点 */
        //request.setApiRegionId("cn-shanghai");
        /* ECS部署区域*/
        // request.setEcsRegionId("cn-shanghai");
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        //请求视频点播服务的请求ID
        //System.out.print("RequestId=" + response.getRequestId() + "\n");
        if (response.isSuccess()) {
            return R.ok().data("videoId",response.getVideoId());
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            return R.ok().message(response.getMessage()).data("videoId",response.getVideoId()).code(Integer.parseInt(response.getCode()));
        }
    }

}
