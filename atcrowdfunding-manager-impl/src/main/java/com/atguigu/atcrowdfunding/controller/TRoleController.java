package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TRolePermission;
import com.atguigu.atcrowdfunding.bean.TRolePermissionExample;
import com.atguigu.atcrowdfunding.mapper.TRolePermissionMapper;
import com.atguigu.atcrowdfunding.service.TRolePermissionService;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.atguigu.atcrowdfunding.util.Datas;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TRoleController {

    @Autowired
    TRoleService roleService;

    @Autowired
    TRolePermissionMapper tRolePermissionMapper;
    @Autowired
    TRolePermissionService rolePermissionService;

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
    @RequestMapping("role/listPermissionIdByRoleId")
    @ResponseBody
    public List<Integer> listPermissionIdByRoleId(Integer roleId){
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria().andRoleidEqualTo(roleId);
        List<TRolePermission> rolePermissions = tRolePermissionMapper.selectByExample(example);
        List<Integer> list = new ArrayList<>();
        rolePermissions.forEach(rolePemission->{
            list.add(rolePemission.getPermissionid());
        });
        return list;
    }

    /**
     * 取消分配权限
     * @param data
     * @param roleId
     * @return
     */
    @RequestMapping("role/doAssignPermissionToRole")
    @ResponseBody
    public String doAssignPermissionToRole(Datas data, Integer roleId){
        List<Integer> permissionIds = data.getIds();
        rolePermissionService.doAssignPermissionToRoleDelete(permissionIds,roleId);
        List<TRolePermission> rolePermissions = new ArrayList<>();
        permissionIds.forEach(permissionId -> {
            TRolePermission tRolePermission = new TRolePermission();
            tRolePermission.setRoleid(roleId);
            tRolePermission.setPermissionid(permissionId);
            rolePermissions.add(tRolePermission);
            tRolePermissionMapper.insertSelective(tRolePermission);
        });
        return "ok";
    }
}
