package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TRoleController {

    @Autowired
    TRoleService roleService;

    @RequestMapping("role/index")
    public String index(){

        return "role/index";
    }
}
