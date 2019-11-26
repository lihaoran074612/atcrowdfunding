package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TRolePermission;
import com.atguigu.atcrowdfunding.bean.TRolePermissionExample;
import com.atguigu.atcrowdfunding.mapper.TRolePermissionMapper;
import com.atguigu.atcrowdfunding.service.TRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TRolePermissionServiceImpl implements TRolePermissionService {

    @Autowired
    TRolePermissionMapper rolePermissionMapper;

    @Override
    public void doAssignPermissionToRoleDelete(List<Integer> permissionIds, Integer roleId) {

        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria().andRoleidEqualTo(roleId);
        rolePermissionMapper.deleteByExample(example);
    }
}
