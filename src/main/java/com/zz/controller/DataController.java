package com.zz.controller;

import com.zz.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DataController {

    @Resource
    private DataService dataService;

    @GetMapping("/data/getData")
    public String getData() {
        return dataService.getData();
    }
}
