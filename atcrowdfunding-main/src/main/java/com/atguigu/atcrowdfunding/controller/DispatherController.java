package com.atguigu.atcrowdfunding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.atguigu.atcrowdfunding.bean.TMenu;
import com.atguigu.atcrowdfunding.service.TMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.atcrowdfunding.bean.TAdmin;
import com.atguigu.atcrowdfunding.service.TAdminServie;
import com.atguigu.atcrowdfunding.util.Const;

@Controller
public class DispatherController {
	
	Logger log = LoggerFactory.getLogger(DispatherController.class);
	
	@Autowired
	TAdminServie adminService;

	@Autowired
	TMenuService tMenuService;
	
	
	@RequestMapping("/index")
	public String index() {
		log.debug("系统跳转到首页");
		return "index";
	}
	
	@RequestMapping("/toLogin")
	public String login() {
		log.debug("系统跳转到登陆页面");
		return "login";
	}
	
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//
//		if(session!=null) {
//			session.removeAttribute(Const.LOGIN_ADMIN);
//		}
//		log.debug("用户注销了");
//		return "redirect:index";
//	}
//
//	@RequestMapping("/doLogin")
//	public String doLogin(String loginacct, String userpswd, HttpSession session,Model model) {
//		log.debug("开始登陆");
//		Map<String,String> paramMap = new HashMap();
//		paramMap.put("loginacct",loginacct);
//		paramMap.put("userpswd",userpswd);
//		try {
//			TAdmin admin= adminService.getTAdminByLogin(paramMap);
//			session.setAttribute(Const.LOGIN_ADMIN, admin);
//			log.debug("登陆成功");
//			return "redirect:/main";
//		}catch(Exception e) {
//			e.printStackTrace();
//			log.debug("登陆失败",e.getMessage());
//			model.addAttribute("message",e.getMessage());
//			return "login";
//		}
//	}
	
	@RequestMapping("/main")
	public String main(HttpSession session) {
        log.debug("跳转到后台系统main页面...");

        if(session==null) {
            return "redirect:/toLogin";
        }
        //存放父菜单
        List<TMenu> menuList = (List<TMenu>)session.getAttribute("menuList") ;

        log.debug("menuList={}",menuList);

        if(menuList==null) {

            log.debug("menuList=null ===》》》》查询侧边栏 菜单树");

            menuList = tMenuService.listMenuAll();
            session.setAttribute("menuList", menuList);
        }


        return "main";
	}
	
}
