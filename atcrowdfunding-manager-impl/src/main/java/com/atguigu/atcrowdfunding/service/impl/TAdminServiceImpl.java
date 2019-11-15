package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.bean.TAdminExample;
import com.atguigu.atcrowdfunding.exception.LoginException;
import com.atguigu.atcrowdfunding.mapper.TAdminMapper;
import com.atguigu.atcrowdfunding.service.TAdminServie;
import com.atguigu.atcrowdfunding.util.Const;

@Service
public class TAdminServiceImpl implements TAdminServie {

	@Autowired
	TAdminMapper adminMapper;

	@Override
	public TAdmin getTAdminByLogin(Map<String, String> paramMap) {
		
		String loginacct = paramMap.get("loginacct");
		
		String userpswd = paramMap.get("userpswd");
		
		TAdminExample example = new TAdminExample();
		
		example.createCriteria().andLoginacctEqualTo(loginacct);
		
		List<TAdmin> list = adminMapper.selectByExample(example);
		
		if (list == null || list.size()==0) {
			throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
		}
		
		TAdmin admin = list.get(0);
		
		if (!admin.getUserpswd().equals(userpswd)) {
			throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
		}
		
		return admin;
	}
}
