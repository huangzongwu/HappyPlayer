package com.happy.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.happy.model.Splash;
import com.happy.service.SplashService;
import com.happy.util.DateUtil;
import com.happy.util.IDGenerate;
import com.opensymphony.xwork2.ActionSupport;

public class SplashAction extends ActionSupport {

	private SplashService service;

	public SplashService getService() {
		return service;
	}

	@Resource(name = "splashService")
	public void setService(SplashService service) {
		this.service = service;
	}

	private Logger logger = Logger.getLogger(SplashAction.class.getName());

	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 添加启动页
	 */
	public void add() {
		HttpServletRequest request = ServletActionContext.getRequest();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);

		Gson gson = new Gson();

		if (file == null) {
			printResponse(gson.toJson(map));
		}

		try {
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int b = 0;
			while ((b = is.read()) != -1) {
				baos.write(b);
			}

			String title = request.getParameter("title");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String createTime = DateUtil.dateToString(new Date());

			Splash splash = new Splash();
			splash.setSid(IDGenerate.getId());
			splash.setTitle(title);
			splash.setCreateTime(createTime);
			splash.setUpdateTime(createTime);
			splash.setStartTime(startTime);
			splash.setEndTime(endTime);
			splash.setImage(baos.toByteArray());

			Object result = service.add(splash);

			printResponse(result);

		} catch (Exception e) {
			printResponse(gson.toJson(map));
			logger.error(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 删除数据
	 */
	public void delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");
		Object result = service.delete(sid);
		printResponse(result);
	}

	/**
	 * 编辑数据
	 */
	public void edit() {
		HttpServletRequest request = ServletActionContext.getRequest();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		Gson gson = new Gson();

		String sid = request.getParameter("sid");
		Splash splash = service.getSplashImageByID(sid);

		String title = request.getParameter("title");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String updateTime = DateUtil.dateToString(new Date());

		splash.setTitle(title);
		splash.setUpdateTime(updateTime);
		splash.setStartTime(startTime);
		splash.setEndTime(endTime);

		byte[] images = null;

		if (file != null) {
			try {
				InputStream is = new FileInputStream(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int b = 0;
				while ((b = is.read()) != -1) {
					baos.write(b);
				}
				images = baos.toByteArray();
			} catch (Exception e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
		if (file != null && images == null) {
			printResponse(gson.toJson(map));
		} else if (images != null) {
			splash.setImage(images);
		}

		Object result = service.edit(splash);
		printResponse(result);

	}

	/**
	 * 获取启动页列表
	 */
	public void list() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String page = request.getParameter("page");// 当前第几页
		String rows = request.getParameter("rows");// 每页显示的条数
		// 当前页,page由分页工具负责传过来
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		// 每页的开始记录 第一页为1 第二页为number +1
		int offset = (intPage - 1) * number;

		String sort = request.getParameter("sort");// 'itemid';
		String order = request.getParameter("order");// 'asc';

		Object result = service.getSplashPage(offset, number, sort, order);
		printResponse(result);
	}

	/**
	 * 通过id来获取详情
	 */
	public void getSplashByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");// splash的id编号
		Object result = service.getSplashByID(sid);
		printResponse(result);
	}

	/**
	 * 返回结果给服务器
	 * 
	 * @param result
	 */
	public void printResponse(Object result) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");

		PrintWriter out;
		try {
			out = response.getWriter();
			out.write(result.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
