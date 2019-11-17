package com.atguigu.atcrowdfunding.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;


import com.atguigu.atcrowdfunding.util.AppDateUtils;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.github.pagehelper.PageInfo;
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
		String m5pswd = MD5Util.digest(userpswd);
		
		TAdminExample example = new TAdminExample();
		
		example.createCriteria().andLoginacctEqualTo(loginacct);
		
		List<TAdmin> list = adminMapper.selectByExample(example);
		
		if (list == null || list.size()==0) {
			throw new LoginException(Const.LOGIN_LOGINACCT_ERROR);
		}
		
		TAdmin admin = list.get(0);
		
		if (!admin.getUserpswd().equals(m5pswd)&& !admin.getUserpswd().equals(userpswd)) {
			throw new LoginException(Const.LOGIN_USERPSWD_ERROR);
		}
		
		return admin;
	}

	@Override
	public PageInfo<TAdmin> listAdminPage(Map<String,Object> map) {
		TAdminExample adminExample = new TAdminExample();
		List<TAdmin> admins = adminMapper.selectByExample(adminExample);
		PageInfo<TAdmin> pageInfo = new PageInfo<>(admins,5);
		return pageInfo;
	}

	@Override
	public void saveTAdmin(TAdmin admin) {
		admin.setUserpswd(MD5Util.digest(Const.DEFAULT_USERPSWD));
		admin.setCreatetime(AppDateUtils.getFormatTime());
		adminMapper.insertSelective(admin);
	}

	@Override
	public TAdmin getAdminById(Integer id) {
		TAdmin admin = adminMapper.selectByPrimaryKey(id);
		return admin;
	}

	@Override
	public void updateTAdmin(TAdmin admin) {
		adminMapper.updateByPrimaryKeySelective(admin);
	}

	@Override
	public void deleteAdmin(TAdmin admin) {
		adminMapper.deleteByPrimaryKey(admin.getId());
	}
}
