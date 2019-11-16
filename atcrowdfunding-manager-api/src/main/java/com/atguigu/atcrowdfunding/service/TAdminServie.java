package com.atguigu.atcrowdfunding.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.github.pagehelper.PageInfo;

public interface TAdminServie {

	TAdmin getTAdminByLogin(Map<String, String> paramMap);

    PageInfo<TAdmin> listAdminPage(Map<String,Object> map);
}
