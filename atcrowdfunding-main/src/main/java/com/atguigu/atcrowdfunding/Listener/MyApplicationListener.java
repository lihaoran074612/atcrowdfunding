package com.atguigu.atcrowdfunding.Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atguigu.atcrowdfunding.util.Const;

public class MyApplicationListener implements ServletContextListener {

	Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		logger.debug("当前应用上下文"+contextPath);
		application.setAttribute(Const.PATH, contextPath);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.debug("当前对象被销毁");
	}

}
