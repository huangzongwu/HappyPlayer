package com.happy.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.happy.model.PluginInfo;
import com.happy.service.PluginService;
import com.happy.util.DateUtil;
import com.happy.util.IDGenerate;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 插件
 * 
 * @author zhangliangming
 * 
 */
public class PluginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	private String fileFileName;
	private String fileContentType;

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	private Logger logger = Logger.getLogger(PluginAction.class.getName());

	private PluginService service;

	public PluginService getService() {
		return service;
	}

	@Resource(name = "pluginService")
	public void setService(PluginService service) {
		this.service = service;
	}

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

			String pName = request.getParameter("pName");
			String pDetail = request.getParameter("pDetail");
			String createTime = DateUtil.dateToString(new Date());
			String pTypeStr = request.getParameter("pType");
			int pType = -1;
			if (pTypeStr != null) {
				pType = Integer.parseInt(pTypeStr);
			}

			PluginInfo pluginInfo = new PluginInfo();
			pluginInfo.setPid(IDGenerate.getId());
			pluginInfo.setpName(pName);
			pluginInfo.setpDetail(pDetail);
			pluginInfo.setCreateTime(createTime);
			pluginInfo.setUpdateTime(createTime);
			pluginInfo.setData(baos.toByteArray());
			pluginInfo.setFileType(getFileExtension(fileFileName));
			pluginInfo.setSize(file.length());
			pluginInfo.setSizeStr(getFileSize(file.length()));
			pluginInfo.setpType(pType);

			Object result = service.add(pluginInfo);

			printResponse(result);

		} catch (Exception e) {
			printResponse(gson.toJson(map));
			logger.error(e.toString());
			e.printStackTrace();
		}
	}

	public void edit() {
		HttpServletRequest request = ServletActionContext.getRequest();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		Gson gson = new Gson();

		String pid = request.getParameter("pid");
		PluginInfo pluginInfo = service.getPluginInfoDataByID(pid);

		String pName = request.getParameter("pName");
		String pDetail = request.getParameter("pDetail");
		String updateTime = DateUtil.dateToString(new Date());
		String pTypeStr = request.getParameter("pType");

		int pType = -1;
		if (pTypeStr != null) {
			pType = Integer.parseInt(pTypeStr);
		}

		pluginInfo.setpName(pName);
		pluginInfo.setpDetail(pDetail);
		pluginInfo.setUpdateTime(updateTime);
		pluginInfo.setpType(pType);

		byte[] datas = null;

		if (file != null) {
			try {
				InputStream is = new FileInputStream(file);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int b = 0;
				while ((b = is.read()) != -1) {
					baos.write(b);
				}
				datas = baos.toByteArray();
			} catch (Exception e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
		if (file != null && datas == null) {
			printResponse(gson.toJson(map));
		} else if (datas != null) {
			pluginInfo.setData(datas);
			pluginInfo.setSize(file.length());
			pluginInfo.setSizeStr(getFileSize(file.length()));
			pluginInfo.setFileType(getFileExtension(fileFileName));
		}

		Object result = service.edit(pluginInfo);
		printResponse(result);

	}

	public void list() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pTypeStr = request.getParameter("pType");

		int pType = -1;
		if (pTypeStr != null) {
			pType = Integer.parseInt(pTypeStr);
		}
		
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

		Object result = service.getPluginInfoPage(offset, number, sort, order,
				pType);
		printResponse(result);
	}

	public void getPluginInfoByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pid = request.getParameter("pid");//
		Object result = service.getPluginInfoByID(pid);
		printResponse(result);
	}

	public void delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pid = request.getParameter("pid");
		Object result = service.delete(pid);
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

	/**
	 * 计算文件的大小，返回相关的m字符串
	 * 
	 * @param fileS
	 * @return
	 */
	private String getFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param file
	 * @return
	 */
	private String getFileExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return "";
		}
	}
}
