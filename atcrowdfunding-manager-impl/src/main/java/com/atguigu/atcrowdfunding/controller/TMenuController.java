package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.service.TMenuService;
import com.atguigu.atcrowdfunding.service.TPermissionService;

@Controller
public class TMenuController {

    @Autowired
    TMenuService menuService ;

    @Autowired
    TPermissionService permissionService;

    @ResponseBody
    @GetMapping("/menu/menu_permission")
    public List<TPermission> getPermissionByMenuid(@RequestParam("menuid") Integer mid) {
        // 查询出当前菜单能被哪些权限（自定义标识）操作
        List<TPermission> permissionByMenuid = permissionService.getPermissionByMenuid(mid);
        return permissionByMenuid;
    }


    /**
     * 为菜单分配权限 {mid: "3", perIds: "1,2,4,5,6"}
     */
    @ResponseBody
    @PostMapping("/menu/assignPermissionToMenu")
    public String assignPermissionToMenu(@RequestParam("mid") Integer mid, @RequestParam("perIds") String perIds) {
        // 权限id的集合
        List<Integer> perIdArray = new ArrayList<>();
        String[] split = perIds.split(",");
        for (String str : split) {
            int id;
            try {
                id = Integer.parseInt(str);
                perIdArray.add(id);
            } catch (NumberFormatException e) {
            }
        }
        // 1、将菜单和权限id集合的关系保存起来
        permissionService.assignPermissionToMenu(mid, perIdArray);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/menu/doDelete")
    public String doDelete(Integer id) {
        menuService.deleteTMenu(id);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/menu/doUpdate")
    public String doUpdate(TMenu menu) {
        menuService.updateTMenu(menu);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/menu/getMenuById")
    public TMenu getMenuById(Integer id) {
        TMenu menu = menuService.getMenuById(id);
        return menu;
    }


    @ResponseBody
    @RequestMapping("/menu/doAdd")
    public String doAdd(TMenu menu) {
        menuService.saveTMenu(menu);
        return "ok";
    }

    @RequestMapping("/menu/index")
    public String index() {
        return "menu/index";
    }

    @ResponseBody
    @RequestMapping("/menu/loadTree")
    public List<TMenu> loadTree() {
        return menuService.listMenuAllTree();
    }


}
