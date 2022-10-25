package com.pushihao.controller;

import jdk.dynalink.linker.LinkerServices;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/epidemicData")
public class EpidemicDataController {

    @GetMapping("/provinceName")
    public String provinceName() {
        RestTemplate restTemplate = new RestTemplate();
        String reqUrl = "https://lab.isaaclin.cn/nCoV/api/provinceName?lang=zh";
        return restTemplate.getForObject(reqUrl, String.class);
    }

    @GetMapping("/epidemicData")
    public String epidemicData() {
        RestTemplate restTemplate = new RestTemplate();
        String reqUrl = "https://lab.isaaclin.cn/nCoV/api/area";
        return restTemplate.getForObject(reqUrl, String.class);
    }

}
