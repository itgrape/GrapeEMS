package com.pushihao.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Producer;
import com.pushihao.pojo.ActiveCode;
import com.pushihao.pojo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/img")
public class KaptchaController {

    @Autowired
    private Producer kaptchaProducer;

    @RequestMapping("/getActiveCode")
    public String getActiveCode() {
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);
        System.out.println(text);

        ResponseResult<ActiveCode> responseResult = new ResponseResult<ActiveCode>();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ActiveCode activeCode = new ActiveCode();
            ImageIO.write(image, "png", outputStream);
            byte[] imgByte = outputStream.toByteArray();

            String md5Text = DigestUtils.md5DigestAsHex(text.getBytes(StandardCharsets.UTF_8));

            activeCode.setImgByte(imgByte);
            activeCode.setImgText(md5Text);

            responseResult.setCode(200);
            responseResult.setData(activeCode);
        } catch (IOException e) {
            e.printStackTrace();
            responseResult.setCode(400);
            responseResult.setData(null);
        }

        return JSON.toJSONString(responseResult);
    }
}
