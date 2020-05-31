package com.zz.controller;

import com.zz.service.HttpConnectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HttpConnectController {

    @Resource
    HttpConnectService httpConnectService;

    @GetMapping("/getContentByHttpClient")
    public String getContentByHttpClient(@RequestParam(value = "name", defaultValue = "World") String name) {
        return httpConnectService.get("https://www.baidu.com", String.class);
    }

}
