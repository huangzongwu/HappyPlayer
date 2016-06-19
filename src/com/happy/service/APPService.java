package com.happy.service;

import com.happy.model.AppInfo;

public interface APPService {
	/**
	 * 添加
	 * 
	 * @param
	 * @return
	 */
	public Object add(AppInfo appInfo);

	/**
	 * 编辑
	 * 
	 * @param
	 * @return
	 */
	public Object edit(AppInfo appInfo);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public Object delete(String id);

	/**
	 * 通过id来获取图片数据
	 * 
	 * @param id
	 * @return
	 */
	public AppInfo getAppInfoDataByID(String id);

	/**
	 * 通过id来获取启动页面的数据
	 * 
	 * @param id
	 * @return
	 */
	public Object getAppInfoByID(String id);

	/**
	 * 通过版本名和版本号，判断是否要下载新版本的apk
	 * 
	 * @param versionName
	 * @param versionCode
	 * @return
	 */
	public Object getAppInfoMessage(String versionName, float versionCode);

	/**
	 * 分页获取列表数据
	 * 
	 * @param offset
	 * @param length
	 * @param hql
	 * @return
	 */
	public Object getAppInfoPage(int offset, int length, String sort,
			String order);

	/**
	 * 获取数据总条数
	 * 
	 * @return
	 */
	public int getCount();
}
