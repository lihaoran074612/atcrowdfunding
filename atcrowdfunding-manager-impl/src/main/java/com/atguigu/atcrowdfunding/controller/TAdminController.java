package com.atguigu.atcrowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TAdminController {
    @RequestMapping("/admin/index")
    public String index(){
        return "admin/index";
    }
}
