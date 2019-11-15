package com.atguigu.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jTest{

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(Slf4jTest.class);
		
		log.debug("debug!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.info("info!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.warn("warn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		log.error("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
	}
	
}
