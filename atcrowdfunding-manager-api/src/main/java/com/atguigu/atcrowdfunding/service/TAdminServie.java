package com.atguigu.atcrowdfunding.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.TAdmin;

public interface TAdminServie {

	TAdmin getTAdminByLogin(Map<String, String> paramMap);

}
