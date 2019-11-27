package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.bean.TPermissionMenu;
import com.atguigu.atcrowdfunding.bean.TPermissionMenuExample;
import com.atguigu.atcrowdfunding.mapper.TPermissionMapper;
import com.atguigu.atcrowdfunding.mapper.TPermissionMenuMapper;
import com.atguigu.atcrowdfunding.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TPermissionServiceImpl implements TPermissionService {
    @Autowired
    TPermissionMapper permissionMapper;

    @Autowired
    TPermissionMenuMapper permissionMenuMapper;

    @Override
    public List<TPermission> listMenuAllTree() {
        List<TPermission> tPermissions = permissionMapper.selectByExample(null);
        return tPermissions;
    }

    @Override
    public void savePermission(TPermission permission) {
        permissionMapper.insertSelective(permission);
    }

    @Override
    public void deletePermission(Integer id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void editPermission(TPermission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public TPermission getPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TPermission> getPermissionByMenuid(Integer mid) {
        return permissionMapper.getPermissionByMenuid(mid);
    }

    @Override
    public void assignPermissionToMenu(Integer mid, List<Integer> perIdArray) {
        // 1、删除之前菜单对应的权限
        TPermissionMenuExample example = new TPermissionMenuExample();
        example.createCriteria().andMenuidEqualTo(mid);
        permissionMenuMapper.deleteByExample(example);
        // 2、插入提交过来的新的权限集合
        perIdArray.forEach(permissionId->{
            TPermissionMenu permissionMenu = new TPermissionMenu();
            permissionMenu.setMenuid(mid);
            permissionMenu.setPermissionid(permissionId);
            permissionMenuMapper.insertSelective(permissionMenu);
        });

    }
}
