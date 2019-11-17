package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.service.TAdminServie;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TAdminController {
    @Autowired
    TAdminServie adminServie;

    @RequestMapping("/admin/index")
    public String index(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize",required = false,defaultValue = "5") int pageSize,
                        Model model){
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map = new HashMap<>();
        PageInfo<TAdmin> pageInfo = adminServie.listAdminPage(map);
        model.addAttribute("page",pageInfo);
        return "admin/index";
    }

    @RequestMapping("admin/toAdd")
    public String toAdd(){
        return "admin/add";
    }

    @RequestMapping("admin/toUpdate")
    public String toUpdate(Integer id, Model model){
        TAdmin admin = adminServie.getAdminById(id);
        model.addAttribute("admin",admin);
        return "admin/update";
    }

    @RequestMapping("admin/doAdd")
    public String doAdd(TAdmin admin,int pageNum){
        adminServie.saveTAdmin(admin);
        return "redirect:/admin/index?pageNum="+pageNum;
    }

    @RequestMapping("admin/doUpdate")
    public String doUpdate(TAdmin admin){
        adminServie.updateTAdmin(admin);
        return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;
    }

    @RequestMapping("/admin/doDelete")
    public String doDelete(TAdmin admin,int pageNum){
        adminServie.deleteAdmin(admin);
        return "redirect:/admin/index?pageNum="+pageNum;
    }
}
