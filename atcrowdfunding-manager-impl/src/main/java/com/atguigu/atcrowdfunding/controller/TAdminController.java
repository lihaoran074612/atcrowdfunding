package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.service.TAdminServie;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TAdminController {

    Logger logger = LoggerFactory.getLogger(TAdminController.class);
    @Autowired
    TAdminServie adminServie;

    @Autowired
    TRoleService roleService;

    @RequestMapping("/admin/index")
    public String index(@RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize",required = false,defaultValue = "5") int pageSize,
                        Model model,
                        @RequestParam(value = "condition",required = false,defaultValue = "") String condition){
        logger.info(condition);
        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("condition",condition);
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

    @PreAuthorize("hasRole('PM - 项目经理')")
    @RequestMapping("admin/doAdd")
    public String doAdd(TAdmin admin){
        adminServie.saveTAdmin(admin);
        return "redirect:/admin/index?pageNum="+Integer.MAX_VALUE;
    }

    @RequestMapping("admin/doUpdate")
    public String doUpdate(TAdmin admin, Integer pageNum){
        adminServie.updateTAdmin(admin);
        return "redirect:/admin/index?pageNum="+pageNum;
    }

    @RequestMapping("/admin/doDelete")
    public String doDelete(TAdmin admin,int pageNum){
        adminServie.deleteAdmin(admin);
        return "redirect:/admin/index?pageNum="+pageNum;
    }

    @RequestMapping("/admin/doDeleteBatch")
    public String doDeleteBatch(String ids,int pageNum){
        String[] split = ids.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s:split){
            list.add(Integer.valueOf(s));
        }
        adminServie.batchDeleteAdmin(list);
        return "redirect:/admin/index?pageNum="+pageNum;
    }

    @RequestMapping("/admin/doAssign")
    @ResponseBody
    public String doAssign(Integer[] roleId,int adminId){
        roleService.saveAdminAndRoleRelationship(roleId,adminId);
        return "ok";
    }

    @RequestMapping("/admin/doUnAssign")
    @ResponseBody
    public String doUnAssign(Integer[] roleId,int adminId){
        roleService.deleteAdminAndRoleRelationship(roleId,adminId);
        return "ok";
    }

    @RequestMapping("/admin/toAssign")
    public String toAssign(String id, Model model){

        //1.查询所有角色
        List<TRole> roleList =  roleService.listAllRole();

        //2.根据用户id查询已经拥有的角色id
        List<Integer> roleIdList = roleService.getRoleByAdminId(id);

        //已经分配的角色
        List<TRole> assignRoles = new ArrayList<>();
        //未分配的角色
        List<TRole> unAssignRoles = new ArrayList<>();

        model.addAttribute("assignRoles",assignRoles);
        model.addAttribute("unAssignRoles",unAssignRoles);

        //3.将所有角色划分，
        roleList.stream().forEach((role) -> {
            if (roleIdList.contains(role.getId())){
                assignRoles.add(role);
            }else {
                unAssignRoles.add(role);
            }
        });
        return "admin/assignRole";
    }
}
