package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class TPermissionController {

    @Autowired
    TPermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "permission/index";
    }



    @RequestMapping("/listAllPermissionTree")
    @ResponseBody
    public List<TPermission> loadTree(){
        List<TPermission> permissionrs = permissionService.listMenuAllTree();
        return permissionrs;
    }

    @ResponseBody
    @PostMapping("/add")
    public String addPermission(TPermission permission) {
        permissionService.savePermission(permission);
        return "ok";
    }

    @ResponseBody
    @DeleteMapping("/delete")
    public String deletePermission(Integer id) {
        permissionService.deletePermission(id);
        return "ok";
    }

    @ResponseBody
    @PostMapping("/edit")
    public String editPermission(TPermission permission) {
        permissionService.editPermission(permission);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/get")
    public TPermission getPermission(Integer id) {
        return permissionService.getPermissionById(id);
    }

}
