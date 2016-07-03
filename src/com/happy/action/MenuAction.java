package com.happy.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class MenuAction extends ActionSupport {
	Logger logger = Logger.getLogger(MenuAction.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String addSplash() {
		return "addSplash";
	}

	public String listSplash() {
		return "listSplash";
	}

	public String addAPP() {
		return "addAPP";
	}

	public String listAPP() {
		return "listAPP";
	}

	public String addSkinTheme() {
		return "addSkinTheme";
	}

	public String listSkinTheme() {
		return "listSkinTheme";
	}

	public String addKsc() {
		return "addKsc";
	}

	public String listKsc() {
		return "listKsc";
	}

	public String addSingerAvatar() {
		return "addSingerAvatar";
	}

	public String listSingerAvatar() {
		return "listSingerAvatar";
	}

	public String addSingerPhoto() {
		return "addSingerPhoto";
	}

	public String listSingerPhoto() {
		return "listSingerPhoto";
	}

	public String addSongInfo() {
		return "addSongInfo";
	}

	public String listSongInfo() {
		return "listSongInfo";
	}

	public String addEasyTouchTheme() {
		return "addEasyTouchTheme";
	}

	public String listEasyTouchTheme() {
		return "listEasyTouchTheme";
	}

	public String addPluginInfo() {
		return "addPluginInfo";
	}

	public String listPluginInfo() {
		return "listPluginInfo";
	}

}
