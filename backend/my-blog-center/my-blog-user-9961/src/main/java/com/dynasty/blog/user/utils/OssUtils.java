package com.dynasty.blog.user.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 文件上传oss工具类
 * @Author: 王朝
 * @Date: 2023/12/17 14:44
 */
@Slf4j
public class OssUtils {
  // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
 private static final String ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";
  // 填写Bucket名称，例如examplebucket。
  private static final  String BUCKETNAME = "my-blog-dynasty";
  // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
  private static final String OSS_ACCESS_KEY_ID = "LTAI5tQoRzpXwTkRxRQgiY3b";
  private static final String OSS_ACCESS_KEY_SECRET = "TfwaE2MtOTgyWEACirUscWhN5X3JKW";
  public static  String OssUpload(  String objectName, InputStream in) {
    // 创建OSSClient实例。
    OSS ossClient = new OSSClientBuilder().build(ENDPOINT, OSS_ACCESS_KEY_ID,
        OSS_ACCESS_KEY_SECRET);
    String url = "";
    try {
      // 创建PutObjectRequest对象。
      PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, objectName,
         in);
      PutObjectResult result = ossClient.putObject(putObjectRequest);
      //url组成：https://bucket名称.区域节点/objectName
      url = "https://"+BUCKETNAME+"."+ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1)+"/"+objectName;
      log.info("url:{}",url);
    } catch (OSSException oe) {
      System.out.println("Caught an OSSException, which means your request made it to OSS, "
          + "but was rejected with an error response for some reason.");
      System.out.println("Error Message:" + oe.getErrorMessage());
      System.out.println("Error Code:" + oe.getErrorCode());
      System.out.println("Request ID:" + oe.getRequestId());
      System.out.println("Host ID:" + oe.getHostId());
    } catch (ClientException ce) {
      System.out.println("Caught an ClientException, which means the client encountered "
          + "a serious internal problem while trying to communicate with OSS, "
          + "such as not being able to access the network.");
      System.out.println("Error Message:" + ce.getMessage());
    } finally {
      if (ossClient != null) {
        ossClient.shutdown();
      }
    }
    return url;
  }
}


