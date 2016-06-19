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
import com.happy.model.SingerPhoto;
import com.happy.service.SingerPhotoService;
import com.happy.util.DateUtil;
import com.happy.util.IDGenerate;
import com.opensymphony.xwork2.ActionSupport;

public class SingerPhotoAction extends ActionSupport {

	private SingerPhotoService service;

	public SingerPhotoService getService() {
		return service;
	}

	@Resource(name = "singerPhotoService")
	public void setService(SingerPhotoService service) {
		this.service = service;
	}

	private Logger logger = Logger.getLogger(SingerPhotoAction.class.getName());

	private File file1;
	private File file2;
	private File file3;

	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public File getFile2() {
		return file2;
	}

	public void setFile2(File file2) {
		this.file2 = file2;
	}

	public File getFile3() {
		return file3;
	}

	public void setFile3(File file3) {
		this.file3 = file3;
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

		if (file1 == null || file2 == null || file3 == null) {
			printResponse(gson.toJson(map));
		}

		try {
			InputStream is = new FileInputStream(file1);
			ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
			int b = 0;
			while ((b = is.read()) != -1) {
				baos1.write(b);
			}

			InputStream is2 = new FileInputStream(file2);
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			b = 0;
			while ((b = is2.read()) != -1) {
				baos2.write(b);
			}

			InputStream is3 = new FileInputStream(file3);
			ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
			b = 0;
			while ((b = is3.read()) != -1) {
				baos3.write(b);
			}

			String singer = request.getParameter("singer");
			String createTime = DateUtil.dateToString(new Date());

			SingerPhoto singerPhoto = new SingerPhoto();
			singerPhoto.setSid(IDGenerate.getId());
			singerPhoto.setSinger(singer);
			singerPhoto.setCreateTime(createTime);
			singerPhoto.setUpdateTime(createTime);
			singerPhoto.setImage1(baos1.toByteArray());
			singerPhoto.setImage2(baos2.toByteArray());
			singerPhoto.setImage3(baos3.toByteArray());

			Object result = service.add(singerPhoto);

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
		SingerPhoto singerPhoto = service.getSingerPhotoImageByID(sid);

		String singer = request.getParameter("singer");
		String updateTime = DateUtil.dateToString(new Date());

		singerPhoto.setSinger(singer);
		singerPhoto.setUpdateTime(updateTime);

		byte[] images = null;

		if (file1 != null) {
			try {
				InputStream is = new FileInputStream(file1);
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
		if (file1 != null && images == null) {
			printResponse(gson.toJson(map));
		} else if (images != null) {
			singerPhoto.setImage1(images);
		}

		if (file2 != null) {
			try {
				InputStream is = new FileInputStream(file2);
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
		if (file2 != null && images == null) {
			printResponse(gson.toJson(map));
		} else if (images != null) {
			singerPhoto.setImage2(images);
		}

		if (file3 != null) {
			try {
				InputStream is = new FileInputStream(file3);
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
		if (file3 != null && images == null) {
			printResponse(gson.toJson(map));
		} else if (images != null) {
			singerPhoto.setImage3(images);
		}
		Object result = service.edit(singerPhoto);
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

		Object result = service.getSingerPhotoPage(offset, number, sort, order,
				singer);
		printResponse(result);
	}

	/**
	 * 通过id来获取详情
	 */
	public void getSingerPhotoByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");//
		Object result = service.getSingerPhotoByID(sid);
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
