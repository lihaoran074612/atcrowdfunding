package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfunding.bean.TRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface TRoleService {
    PageInfo<TRole> listRolePage(Map<String, Object> paramMap);

    void saveRole(TRole role);

    TRole getRoleById(Integer id);
}
