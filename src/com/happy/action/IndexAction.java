package com.happy.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	Logger logger = Logger.getLogger(IndexAction.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 论坛页面
	public String index() {
		logger.info("现在开始跳转到主页面--------->");
		return "index";
	}
}
