package com.pushihao.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.pushihao.pojo.UserUploadInfo;
import listener.UserUploadInfoListener;
import com.pushihao.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    private UserCenterService userCenterService;

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        userCenterService.downloadUser(response);
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        if (file != null) {
            EasyExcel.read(file.getInputStream(), UserUploadInfo.class, new UserUploadInfoListener(userCenterService)).sheet().doRead();
            return JSON.toJSONString(true);
        } else {
            return JSON.toJSONString(false);
        }
    }
}
