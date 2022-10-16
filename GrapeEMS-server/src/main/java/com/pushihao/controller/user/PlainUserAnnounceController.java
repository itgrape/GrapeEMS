package com.pushihao.controller.user;

import com.alibaba.fastjson.JSON;
import com.pushihao.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plainUser")
public class PlainUserAnnounceController {
    @Autowired
    private AnnounceService announceService;

    @GetMapping("/getAllAnnounceByUserId/{id}")
    public String getAllAnnounceByUserId(@PathVariable("id") Long id) {
        if (id == null)
            return null;
        return JSON.toJSONString(announceService.getAllAnnounceByUserId(id));
    }

    @GetMapping("/getAnnounceById/{id}")
    public String getAnnounceById(@PathVariable("id") Long id) {
        return JSON.toJSONString(announceService.getAnnounceById(id));
    }
}
