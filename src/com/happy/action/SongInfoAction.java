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
import java.util.logging.Level;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.ID3v23Tag;

import com.google.gson.Gson;
import com.happy.model.SongInfo;
import com.happy.service.SongInfoService;
import com.happy.util.DateUtil;
import com.happy.util.IDGenerate;
import com.opensymphony.xwork2.ActionSupport;

public class SongInfoAction extends ActionSupport {

	private SongInfoService service;

	public SongInfoService getService() {
		return service;
	}

	@Resource(name = "songInfoService")
	public void setService(SongInfoService service) {
		this.service = service;
	}

	private Logger logger = Logger.getLogger(SongInfoAction.class.getName());

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

			AudioFileIO.logger.setLevel(Level.SEVERE);
			ID3v23Frame.logger.setLevel(Level.SEVERE);
			ID3v23Tag.logger.setLevel(Level.SEVERE);
			MP3File mp3file = new MP3File(file);
			MP3AudioHeader header = mp3file.getMP3AudioHeader();
			if (header == null) {
				printResponse(gson.toJson(map));
				return;
			}
			String durationStr = header.getTrackLengthAsString();

			// 歌曲时长
			long duration = getTrackLength(durationStr);

			String title = request.getParameter("title");
			String singer = request.getParameter("singer");
			String key = request.getParameter("key");
			String createTime = DateUtil.dateToString(new Date());

			SongInfo songInfo = new SongInfo();

			songInfo.setSid(IDGenerate.getId());
			songInfo.setTitle(title);
			songInfo.setSinger(singer);
			songInfo.setKey(key);
			songInfo.setDuration(duration);
			songInfo.setDurationStr(durationStr);
			songInfo.setCreateTime(createTime);
			songInfo.setUpdateTime(createTime);
			songInfo.setData(baos.toByteArray());
			songInfo.setSize(file.length());
			songInfo.setSizeStr(getFileSize(file.length()));
			songInfo.setType(getFileExtension(fileFileName));

			Object result = service.add(songInfo);

			printResponse(result);

		} catch (Exception e) {
			printResponse(gson.toJson(map));
			logger.error(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * 获取歌曲长度
	 * 
	 * @param trackLengthAsString
	 * @return
	 */
	private static long getTrackLength(String trackLengthAsString) {

		if (trackLengthAsString.contains(":")) {
			String temp[] = trackLengthAsString.split(":");
			if (temp.length == 2) {
				int m = Integer.parseInt(temp[0]);// 分
				int s = Integer.parseInt(temp[1]);// 秒
				int currTime = (m * 60 + s) * 1000;
				return currTime;
			}
		}
		return 0;
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
		SongInfo songInfo = service.getSongInfoDataByID(sid);

		String title = request.getParameter("title");
		String singer = request.getParameter("singer");
		String key = request.getParameter("key");
		String updateTime = DateUtil.dateToString(new Date());

		songInfo.setTitle(title);
		songInfo.setSinger(singer);
		songInfo.setKey(key);
		songInfo.setUpdateTime(updateTime);

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

				AudioFileIO.logger.setLevel(Level.SEVERE);
				ID3v23Frame.logger.setLevel(Level.SEVERE);
				ID3v23Tag.logger.setLevel(Level.SEVERE);
				MP3File mp3file = new MP3File(file);
				MP3AudioHeader header = mp3file.getMP3AudioHeader();
				if (header == null) {
					printResponse(gson.toJson(map));
					return;
				}
				String durationStr = header.getTrackLengthAsString();

				// 歌曲时长
				long duration = getTrackLength(durationStr);
				songInfo.setDuration(duration);
				songInfo.setDurationStr(durationStr);
			} catch (Exception e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
		if (file != null && datas == null) {
			printResponse(gson.toJson(map));
		} else if (datas != null) {
			songInfo.setData(datas);
			songInfo.setSize(file.length());
			songInfo.setSizeStr(getFileSize(file.length()));
			songInfo.setType(getFileExtension(fileFileName));
		}

		Object result = service.edit(songInfo);
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

		Object result = service.getSongInfoPage(offset, number, sort, order);
		printResponse(result);
	}

	/**
	 * 通过id来获取详情
	 */
	public void getSongInfoByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");//
		Object result = service.getSongInfoByID(sid);
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
