package com.pushihao.controller;


import com.alibaba.fastjson.JSON;
import com.pushihao.entity.Announce;
import com.pushihao.pojo.AddAnnounce;
import com.pushihao.pojo.EditAnnounce;
import com.pushihao.pojo.QueryAnnounce;
import com.pushihao.service.AnnounceService;
import com.pushihao.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announce")
public class AnnounceController {
    @Autowired
    private AnnounceService announceService;

    @Autowired
    private DeptService deptService;

    @PostMapping("/addOneAnnounce")
    public String addOneAnnounce(@RequestBody AddAnnounce announce) {
        return JSON.toJSONString(announceService.addOneAnnounce(announce));
    }

    @GetMapping("/getAllAnnounce")
    public String getAllAnnounce() {
        return JSON.toJSONString(announceService.getAllAnnounce());
    }

    @PostMapping("/editOneAnnounce")
    public String editOneAnnounce(@RequestBody EditAnnounce editAnnounce) {
        Announce announce = new Announce();
        announce.setAnnounceId(editAnnounce.getId());
        announce.setAnnounceTitle(editAnnounce.getTitle());
        announce.setAnnounceContent(editAnnounce.getContent());
        return JSON.toJSONString(announceService.editOneAnnounce(announce));
    }

    @GetMapping("/deleteOneAnnounce/{id}")
    public String deleteOneAnnounce(@PathVariable("id") Long id) {
        return JSON.toJSONString(announceService.deleteOneAnnounce(id));
    }

    @PostMapping("/queryAnnounce")
    public String queryAnnounce(@RequestBody QueryAnnounce queryAnnounce) {
        return JSON.toJSONString(announceService.queryAnnounce(queryAnnounce));
    }
}
