package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.service.TMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TMenuController {
    @Autowired
    TMenuService menuService;

    @RequestMapping("/menu/index")
    public String index(){
        return "menu/index";
    }

    @RequestMapping("/menu/loadTree")
    @ResponseBody
    public List<TMenu> loadTree(){
        List<TMenu> list = menuService.listMenuAllTree();
        return list;
    }


}
