package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TRoleExample;
import com.atguigu.atcrowdfunding.mapper.TRoleMapper;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TRoleServiceImpl implements TRoleService {
    @Autowired
    TRoleMapper roleMapper;

    @Override
    public PageInfo<TRole> listRolePage(Map<String, Object> paramMap) {
        TRoleExample example = new TRoleExample();

        List<TRole> roles = roleMapper.selectByExample(example);
        PageInfo<TRole> pageInfo = new PageInfo<>(roles,5);
        return pageInfo;
    }
}
