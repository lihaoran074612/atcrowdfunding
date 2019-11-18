package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.mapper.TRoleMapper;
import com.atguigu.atcrowdfunding.service.TRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TRoleServiceImpl implements TRoleService {
    @Autowired
    TRoleMapper RoleMapper;
}
