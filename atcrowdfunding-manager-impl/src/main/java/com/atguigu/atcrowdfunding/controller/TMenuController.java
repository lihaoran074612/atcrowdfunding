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

    @RequestMapping("/menu/doAdd")
    @ResponseBody
    public String doAdd(TMenu menu){
        menuService.saveTMenu(menu);
        return "ok";
    }

    @RequestMapping("/menu/loadTree")
    @ResponseBody
    public List<TMenu> loadTree(){
        List<TMenu> list = menuService.listMenuAllTree();
        return list;
    }

    @RequestMapping("menu/getMenuById")
    @ResponseBody
    public TMenu getMenuById(Integer id){
        TMenu menu = menuService.getMenuById(id);
        return menu;
    }


    @RequestMapping("menu/doUpdate")
    @ResponseBody
    public String doUpdate(TMenu menu){
        menuService.doUpdate(menu);
        return "ok";
    }

    @RequestMapping("menu/doDelete")
    @ResponseBody
    public String doDelete(int id){
        menuService.doDelete(id);
        return "ok";
    }

}
