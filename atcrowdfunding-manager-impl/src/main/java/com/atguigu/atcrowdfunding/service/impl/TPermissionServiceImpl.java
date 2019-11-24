package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TPermission;
import com.atguigu.atcrowdfunding.mapper.TPermissionMapper;
import com.atguigu.atcrowdfunding.service.TPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TPermissionServiceImpl implements TPermissionService {
    @Autowired
    TPermissionMapper permissionMapper;

    @Override
    public List<TPermission> listMenuAllTree() {
        List<TPermission> tPermissions = permissionMapper.selectByExample(null);
        return tPermissions;
    }
}
