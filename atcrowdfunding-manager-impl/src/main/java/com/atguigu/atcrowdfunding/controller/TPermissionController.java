package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TPermissionController {

    @Autowired
    TPermissionService permissionService;

    @RequestMapping("/permission/index")
    public String index(){
        return "permission/index";
    }



    @RequestMapping("permission/loadTree")
    @ResponseBody
    public List<TPermission> loadTree(){
        List<TPermission> permissionrs = permissionService.listMenuAllTree();
        return permissionrs;
    }

}
