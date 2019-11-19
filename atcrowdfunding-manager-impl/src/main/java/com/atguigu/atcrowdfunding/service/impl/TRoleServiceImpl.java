package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.atguigu.atcrowdfunding.bean.TRoleExample;
import com.atguigu.atcrowdfunding.mapper.TRoleMapper;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.atguigu.atcrowdfunding.util.StringUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class TRoleServiceImpl implements TRoleService {
    @Autowired
    TRoleMapper roleMapper;

    @Override
    public PageInfo<TRole> listRolePage(Map<String, Object> paramMap) {
        String condition = (String)paramMap.get("condition");
        List<TRole> roles;
        if (StringUtils.isEmpty(condition)){
            roles = roleMapper.selectByExample(null);
        }else {
            TRoleExample example = new TRoleExample();
            example.createCriteria().andNameLike("%"+condition+"%");
            roles = roleMapper.selectByExample(example);
        }

        PageInfo<TRole> pageInfo = new PageInfo<>(roles,5);
        return pageInfo;
    }

    @Override
    public void saveRole(TRole role) {
        roleMapper.insert(role);
    }
}
