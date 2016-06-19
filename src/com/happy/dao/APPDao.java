package com.happy.dao;

import java.util.List;

import com.happy.model.AppInfo;

public interface APPDao {
	/**
	 * 添加
	 * 
	 * @param
	 * @return
	 */
	public boolean add(AppInfo appInfo);

	/**
	 * 编辑
	 * 
	 * @param
	 * @return
	 */
	public boolean edit(AppInfo appInfo);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

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
	public AppInfo getAppInfoByID(String id);

	/**
	 * 通过版本名和版本号，判断是否要下载新版本的apk
	 * 
	 * @param versionName
	 * @param versionCode
	 * @return
	 */
	public AppInfo getAppInfoMessage(String versionName, float versionCode);

	/**
	 * 分页获取列表数据
	 * 
	 * @param offset
	 * @param length
	 * @param hql
	 * @return
	 */
	public List<AppInfo> getAppInfoPage(int offset, int length, String hql);

	/**
	 * 获取数据总条数
	 * 
	 * @return
	 */
	public int getCount();

}
