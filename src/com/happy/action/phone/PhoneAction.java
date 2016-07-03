package com.happy.action.phone;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.happy.action.SplashAction;
import com.happy.model.AppInfo;
import com.happy.model.EasyTouchTheme;
import com.happy.model.KscInfo;
import com.happy.model.PluginInfo;
import com.happy.model.SingerAvatar;
import com.happy.model.SingerPhoto;
import com.happy.model.SkinTheme;
import com.happy.model.SongInfo;
import com.happy.model.Splash;
import com.happy.service.APPService;
import com.happy.service.EasyTouchThemeService;
import com.happy.service.KscService;
import com.happy.service.PluginService;
import com.happy.service.SingerAvatarService;
import com.happy.service.SingerPhotoService;
import com.happy.service.SkinThemeService;
import com.happy.service.SongInfoService;
import com.happy.service.SplashService;
import com.happy.util.DateUtil;

@Component
public class PhoneAction {
	private SplashService splashService;

	public SplashService getSplashService() {
		return splashService;
	}

	@Resource(name = "splashService")
	public void setSplashService(SplashService splashService) {
		this.splashService = splashService;
	}

	private APPService appService;

	public APPService getAPPService() {
		return appService;
	}

	@Resource(name = "appService")
	public void setAPPService(APPService appService) {
		this.appService = appService;
	}

	private SkinThemeService skinThemeService;

	public SkinThemeService getSkinThemeService() {
		return skinThemeService;
	}

	@Resource(name = "skinThemeService")
	public void setSkinThemeService(SkinThemeService skinThemeService) {
		this.skinThemeService = skinThemeService;
	}

	private KscService kscService;

	public KscService getKscService() {
		return kscService;
	}

	@Resource(name = "kscService")
	public void setKscService(KscService kscService) {
		this.kscService = kscService;
	}

	private SingerAvatarService singerAvatarService;

	public SingerAvatarService getSingerAvatarService() {
		return singerAvatarService;
	}

	@Resource(name = "singerAvatarService")
	public void setSingerAvatarService(SingerAvatarService singerAvatarService) {
		this.singerAvatarService = singerAvatarService;
	}

	private SingerPhotoService singerPhotoService;

	public SingerPhotoService getSingerPhotoService() {
		return singerPhotoService;
	}

	@Resource(name = "singerPhotoService")
	public void setSingerPhotoService(SingerPhotoService singerPhotoService) {
		this.singerPhotoService = singerPhotoService;
	}

	private SongInfoService songInfoService;

	public SongInfoService getSongInfoService() {
		return songInfoService;
	}

	@Resource(name = "songInfoService")
	public void setSongInfoService(SongInfoService songInfoService) {
		this.songInfoService = songInfoService;
	}

	private EasyTouchThemeService easyTouchThemeService;

	public EasyTouchThemeService getEasyTouchThemeService() {
		return easyTouchThemeService;
	}

	@Resource(name = "easyTouchThemeService")
	public void setEasyTouchThemeService(
			EasyTouchThemeService easyTouchThemeService) {
		this.easyTouchThemeService = easyTouchThemeService;
	}

	private PluginService pluginService;

	public PluginService getPluginService() {
		return pluginService;
	}

	@Resource(name = "pluginService")
	public void setPluginService(PluginService service) {
		this.pluginService = service;
	}

	private Logger logger = Logger.getLogger(SplashAction.class.getName());

	/**
	 * 获取启动页面的信息
	 */
	public void getSplashMessageByDate() {
		Object result = splashService.getSplashMessageByDate(DateUtil
				.dateToOtherString(new Date()));
		printResponse(result);
	}

	/**
	 * 通过添加时间，加载更多的启动页面数据
	 */
	public void loadMoreSplashByCreateTime() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String createTime = request.getParameter("createTime");
		Object result = splashService.loadMoreSplashByCreateTime(createTime);
		printResponse(result);
	}

	/**
	 * 通过添加时间，获取最新的启动页面数据
	 */
	public void loadNewSplashByCreateTime() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String createTime = request.getParameter("createTime");
		Object result = splashService.loadNewSplashByCreateTime(createTime);
		printResponse(result);
	}

	/**
	 * 获取启动页面的图片数据
	 */
	public void getSplashImageByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		Splash splash = splashService.getSplashImageByID(id);
		if (splash == null) {
			return;
		}
		byte[] b = splash.getImage();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream();
			printImage(b, toClient);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 获取easyTouchTheme的预览图片
	 */
	public void getEasyTouchThemePreviewImageByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");
		EasyTouchTheme easyTouchTheme = easyTouchThemeService
				.getEasyTouchThemePreviewImageByID(sid);
		if (easyTouchTheme == null) {
			return;
		}
		byte[] b = easyTouchTheme.getPreviewImage();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream();
			printImage(b, toClient);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 获取皮肤预览图片
	 */
	public void getSkinThemePreviewImageByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");
		SkinTheme skinTheme = skinThemeService
				.getSkinThemePreviewImageByID(sid);
		if (skinTheme == null) {
			return;
		}
		byte[] b = skinTheme.getPreviewImage();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream();
			printImage(b, toClient);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 获取歌手头像图片
	 */
	public void getSingerAvatarImageByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");
		SingerAvatar singerAvatar = singerAvatarService
				.getSingerAvatarImageByID(sid);
		if (singerAvatar == null) {
			return;
		}
		byte[] b = singerAvatar.getImage();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream();
			printImage(b, toClient);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 获取写真图片
	 */
	public void getSingerPhotoImageByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String sid = request.getParameter("sid");
		String imageid = request.getParameter("imageid");
		SingerPhoto singerPhoto = singerPhotoService
				.getSingerPhotoImageByID(sid);
		if (singerPhoto == null) {
			return;
		}
		byte[] b = null;
		if (imageid.equals("1")) {
			b = singerPhoto.getImage1();
		} else if (imageid.equals("2")) {
			b = singerPhoto.getImage2();
		} else {
			b = singerPhoto.getImage3();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setContentType("image/*"); // 设置返回的文件类型
			OutputStream toClient = response.getOutputStream();
			printImage(b, toClient);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 通过歌手名称获取歌手写真图片数据
	 */
	public void getSingerPhotoBySinger() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String singer = request.getParameter("singer");
		Object result = singerPhotoService.getSingerPhotoBySinger(singer);
		printResponse(result);
	}

	/**
	 * 通过歌手名称获取歌手头像图片数据
	 */
	public void getSingerAvatarBySinger() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String singer = request.getParameter("singer");
		Object result = singerAvatarService.getSingerAvatarBySinger(singer);
		printResponse(result);
	}

	/**
	 * 输出图片
	 * 
	 * @param b
	 * @param out
	 */
	private void printImage(byte[] b, OutputStream out) {
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}
		try {
			out.write(b);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 返回结果给服务器
	 * 
	 * @param result
	 */
	public void printResponse(Object result) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

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
	 * 获取app的新版本信息
	 */
	public void getAppInfoMessage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String versionName = request.getParameter("versionName");
		String versionCodeStr = request.getParameter("versionCode");
		float versionCode = Float.parseFloat(versionCodeStr);
		Object result = appService.getAppInfoMessage(versionName, versionCode);
		printResponse(result);
	}

	/**
	 * 下载mp3文件
	 */
	public void getSongInfoDataByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String sid = request.getParameter("sid");
		SongInfo songInfo = songInfoService.getSongInfoDataByID(sid);
		if (songInfo == null) {
			return;
		}
		String fileName = songInfo.getSinger() + "-" + songInfo.getTitle()
				+ "." + songInfo.getType();
		try {
			service(request, response, fileName, songInfo.getSize(),
					songInfo.getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载更多歌曲
	 */
	public void loadMoreSongInfoByCreateTime() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String createTime = request.getParameter("createTime");
		Object result = songInfoService
				.loadMoreSongInfoByCreateTime(createTime);
		printResponse(result);
	}

	/**
	 * 加载新的歌曲
	 */
	public void loadNewSongInfoByCreateTime() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String createTime = request.getParameter("createTime");
		Object result = songInfoService.loadNewSongInfoByCreateTime(createTime);
		printResponse(result);
	}

	/**
	 * 搜索歌曲
	 */
	public void getSongInfoByKey() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String key = request.getParameter("key");
		Object result = songInfoService.getSongInfoByKey(key);
		printResponse(result);
	}

	/**
	 * 下载apk文件
	 */
	public void getAppInfoDataByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String id = request.getParameter("aid");
		AppInfo appInfo = appService.getAppInfoDataByID(id);
		if (appInfo == null) {
			return;
		}
		String fileName = appInfo.getAid() + "." + appInfo.getType();
		try {
			service(request, response, fileName, appInfo.getSize(),
					appInfo.getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载皮肤文件
	 */
	public void getSkinThemeDataByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String sid = request.getParameter("sid");
		SkinTheme skinTheme = skinThemeService.getSkinThemeDataByID(sid);
		if (skinTheme == null) {
			return;
		}
		String fileName = skinTheme.getSid() + "." + skinTheme.getType();
		try {
			service(request, response, fileName, skinTheme.getSize(),
					skinTheme.getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载EasyTouchTheme
	 */
	public void getEasyTouchThemeDataByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String sid = request.getParameter("sid");
		EasyTouchTheme easyTouchTheme = easyTouchThemeService
				.getEasyTouchThemeDataByID(sid);
		if (easyTouchTheme == null) {
			return;
		}
		String fileName = easyTouchTheme.getSid() + "."
				+ easyTouchTheme.getType();
		try {
			service(request, response, fileName, easyTouchTheme.getSize(),
					easyTouchTheme.getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载歌词文件
	 */
	public void getKscInfoDataByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String kid = request.getParameter("kid");
		KscInfo kscInfo = kscService.getKscInfoDataByID(kid);
		if (kscInfo == null) {
			return;
		}
		String fileName = kscInfo.getKid() + "." + kscInfo.getType();
		try {
			service(request, response, fileName, kscInfo.getSize(),
					kscInfo.getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询歌词
	 */
	public void getKscInfoByOther() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String songName = request.getParameter("songName");
		String artist = request.getParameter("artist");
		Object result = kscService.getKscInfoByOther(songName, artist);
		printResponse(result);
	}

	/**
	 * 通过添加时间，加载更多的皮肤数据
	 */
	public void loadMoreSkinThemeByCreateTime() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String createTime = request.getParameter("createTime");
		Object result = skinThemeService
				.loadMoreSkinThemeByCreateTime(createTime);
		printResponse(result);
	}

	/**
	 * 通过添加时间，获取最新的皮肤数据
	 */
	public void loadNewSkinThemeByCreateTime() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String createTime = request.getParameter("createTime");
		Object result = skinThemeService
				.loadNewSkinThemeByCreateTime(createTime);
		printResponse(result);
	}

	/**
	 * 下载插件文件
	 */
	public void getPluginInfoDataByID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String pid = request.getParameter("pid");
		PluginInfo pluginInfo = pluginService.getPluginInfoDataByID(pid);
		if (pluginInfo == null) {
			return;
		}
		String fileName = pluginInfo.getPid() + "." + pluginInfo.getFileType();
		try {
			service(request, response, fileName, pluginInfo.getSize(),
					pluginInfo.getData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载更多插件
	 */
	public void loadMorePluginInfoByCreateTime() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String createTime = request.getParameter("createTime");

		Object result = pluginService
				.loadMorePluginInfoByCreateTime(createTime);
		printResponse(result);
	}

	/**
	 * 加载新的插件
	 */
	public void loadNewPluginInfoByCreateTime() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String createTime = request.getParameter("createTime");

		Object result = pluginService.loadNewPluginInfoByCreateTime(createTime);
		printResponse(result);
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response, String fileName, long fSize,
			byte[] datas) throws IOException {

		String range = request.getHeader("RANGE");
		int start = 0, end = 0;
		if (null != range && range.startsWith("bytes=")) {
			String[] values = range.split("=")[1].split("-");
			start = Integer.parseInt(values[0]);
			end = Integer.parseInt(values[1]);
		}
		int requestSize = 0;
		if (end != 0 && end > start) {
			requestSize = end - start;
		} else {
			requestSize = (int) fSize;
		}

		byte[] buffer = new byte[10 * 1024];
		response.setHeader("Accept-Ranges", "bytes");
		response.addHeader("content-length", "" + (requestSize));
		response.setContentType("application/x-download");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(), "ISO8859_1") + "\"");
		ServletOutputStream os = response.getOutputStream();

		int needSize = requestSize;

		InputStream inputStream = new ByteArrayInputStream(datas);
		inputStream.skip(start);
		while (needSize > 0) {
			int len = inputStream.read(buffer);
			if (needSize < len) {
				os.write(buffer, 0, needSize);
			} else {
				os.write(buffer, 0, len);
			}
			needSize -= len;
		}

		inputStream.close();
		os.flush();
		os.close();
	}
}
