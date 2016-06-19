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
import com.happy.model.SingerAvatar;
import com.happy.service.SingerAvatarService;
import com.happy.util.DateUtil;
import com.happy.util.IDGenerate;
import com.opensymphony.xwork2.ActionSupport;

public class SingerAvatarAction extends ActionSupport {

	private SingerAvatarService service;

	public SingerAvatarService getService() {
		return service;
	}

	@Resource(name = "singerAvatarService")
	public void setService(SingerAvatarService service) {
		this.service = service;
	}

	private Logger logger = Logger
			.getLogger(SingerAvatarAction.class.getName());

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

			String singer = request.getParameter("singer");
			String createTime = DateUtil.dateToString(new Date());

			SingerAvatar singerAvatar = new SingerAvatar();
			singerAvatar.setSid(IDGenerate.getId());
			singerAvatar.setSinger(singer);
			singerAvatar.setCreateTime(createTime);
			singerAvatar.setUpdateTime(createTime);
			singerAvatar.setImage(baos.toByteArray());

			Object result = service.add(singerAvatar);

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
		SingerAvatar singerAvatar = service.getSingerAvatarImageByID(sid);

		String singer = request.getParameter("singer");
		String updateTime = DateUtil.dateToString(new Date());

		singerAvatar.setSinger(singer);
		singerAvatar.setUpdateTime(updateTime);

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
			singerAvatar.setImage(images);
		}

		Object result = service.edit(singerAvatar);
		printResponse(result);

	}

	/**
	 * 获取启动页列表
	 */
	public void list() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String singer = request.getParameter("singer");
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

		Object result = service.getSingerAvatarPage(offset, number, sort,
				order, singer);
		printResponse(result);
	}

	/**
	 * 通过id来获取详情
	 */
	public void getSingerAvatarByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");// splash的id编号
		Object result = service.getSingerAvatarByID(sid);
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
