package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TRoleController {

    @Autowired
    TRoleService roleService;

    @RequestMapping("role/index")
    public String index(){
        return "role/index";
    }

    @RequestMapping("role/loadData")
    @ResponseBody
    public PageInfo<TRole> loadData(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                    @RequestParam(value ="pageSize",required = false,defaultValue = "5") Integer pageSize,
                                    @RequestParam(value ="condition",required = false,defaultValue = "") String condition){

        PageHelper.startPage(pageNum,pageSize);
        Map<String ,Object> paramMap = new HashMap<>();
        paramMap.put("condition",condition);
        PageInfo<TRole> pageInfo = roleService.listRolePage(paramMap);

        return pageInfo;
    }

    @RequestMapping("role/doAdd")
    @ResponseBody
    public String doAdd(TRole role){
        roleService.saveRole(role);
        return "ok";
    }

    @RequestMapping("role/doUpdate")
    @ResponseBody
    public String doUpdate(TRole role){
        roleService.updateRole(role);
        return "ok";
    }

    @RequestMapping("role/doDelete")
    @ResponseBody
    public String doDelete(TRole role){
        roleService.deleteRole(role);
        return "ok";
    }

    @RequestMapping("role/getRoleById")
    @ResponseBody
    public TRole getRoleById(Integer id){
        TRole role = roleService.getRoleById(id);
        return role;
    }

}
