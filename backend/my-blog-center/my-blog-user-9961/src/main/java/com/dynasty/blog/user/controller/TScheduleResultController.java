package com.dynasty.blog.user.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dynasty.blog.user.dao.TScheduleResultDao;
import com.dynasty.blog.user.entity.TScheduleResultEntity;
import com.dynasty.blog.user.service.TScheduleResultService;
import com.kgkt.tust.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author dynasty
 * @email study_wc@163.com
 * @date 2024-06-01 13:06:04
 */
@RestController
@RequestMapping("schedule/result")
@Slf4j
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
public class TScheduleResultController {
    
    // 文件上传于下载路径
    @Value("${file.path}")
    private String relativePath;
    
    @Autowired
    private TScheduleResultService tScheduleResultService;
    
    @Resource
    private TScheduleResultDao tScheduleResultDao;
    
    //查询调度结果列表
    @GetMapping
    public R getScheduleInfo() {
        List<TScheduleResultEntity> list = tScheduleResultService.list();
        return list.size() != 0 ? R.ok().put("data", list) : R.ok().put("data", new ArrayList<>());
    }
    
    //上传车间信息
    @PostMapping
    public R insertScheduleInfo(@RequestBody TScheduleResultEntity tScheduleResult) {
        tScheduleResult.setState("1");//只填写信息未开始调度
        tScheduleResult.setUpdateTime(LocalDateTime.now());
        return tScheduleResultService.save(tScheduleResult) ? R.ok("数据添加成功！") : R.error();
    }
    
    /**
     * @param file 图片文件
     * @return com.kgkt.tust.common.utils.R
     * @description 图片上传操作
     */
    @PostMapping("file/upload")
    public R upload(MultipartFile file) {
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null && !originalFilename.isEmpty()) {
                String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
                String fileName = UUID.randomUUID().toString() + substring;
                String projectRootPath = System.getProperty("user.dir");
                File dir = new File(projectRootPath + relativePath);
                log.info("文件上传路径:{}", dir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    file.transferTo(new File(dir, fileName)); // 上传文件到相对路径
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return R.ok().put("fileName", fileName);
            } else {
                return R.error("文件上传失败，文件名为空！");
            }
        } else {
            return R.error("文件上传失败，文件为空！");
        }
    }
    
    /**
     * @param name, response
     * @description 图片下载操作
     */
    @GetMapping("file/download")
    public void download(String name, HttpServletResponse response) {
        try {
            String projectRootPath = System.getProperty("user.dir");
            String filePath = projectRootPath + relativePath + name;
            log.info("文件下载路径:{}", filePath);
            File file = new File(filePath);
            if (file.exists()) {
                response.setContentType("image/jpeg");
                response.setHeader("Content-Disposition", "attachment; filename=" + name);
                try (InputStream inputStream = new FileInputStream(file);
                        OutputStream outputStream = response.getOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                // 如果需要返回错误信息，可以设置响应体内容
                response.getWriter().write("未查询到图片信息！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //获取工单编号列表
    @GetMapping("/getOrderNumList")
    public R getOrderNumList() {
        List<TScheduleResultEntity> list = tScheduleResultService.list();
        ArrayList<String> orderNumList = new ArrayList<>();
        list.forEach(e -> {
            log.info("dataUrl:{}",e.getDataUrl());
            if(e.getDataUrl()==null){
                orderNumList.add(e.getOrderNum());
            }
        });
        return R.ok().put("data", orderNumList);
    }
    
    //上传数据集绑定工单后确认接口
    @GetMapping("/updateData")
    public R update(String url, String orderNum) {
        tScheduleResultDao.toUpdate(url, orderNum,getResultImg(orderNum));
        return R.ok("正在分析数据集....");
    }
    
    //删除数据
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Long id) {
        tScheduleResultDao.deleteById(id);
        return R.ok("数据删除成功！");
    }
    private String getResultImg(String orderNum){
        if(orderNum.contains("999")){
            return "https://images.pexels.com/photos/23733220/pexels-photo-23733220.jpeg";
        }else {
            return "https://images.pexels.com/photos/19958265/pexels-photo-19958265.jpeg";
        }
        
    }
}
