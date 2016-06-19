package com.happy.dao;

import java.util.List;

import com.happy.model.Splash;

public interface SplashDao {
	/**
	 * 添加
	 * 
	 * @param splash
	 * @return
	 */
	public boolean add(Splash splash);

	/**
	 * 编辑
	 * 
	 * @param splash
	 * @return
	 */
	public boolean edit(Splash splash);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

	/**
	 * 通过当前的时间来获取要显示的启动页面
	 * 
	 * @param dateStr
	 * @return
	 */
	public Splash getSplashMessageByDate(String dateStr);

	/**
	 * 通过id来获取图片数据
	 * 
	 * @param id
	 * @return
	 */
	public Splash getSplashImageByID(String id);

	/**
	 * 通过id来获取启动页面的数据
	 * 
	 * @param id
	 * @return
	 */
	public Splash getSplashByID(String id);

	/**
	 * 分页获取启动页面的列表数据
	 * 
	 * @param offset
	 * @param length
	 * @param hql
	 * @return
	 */
	public List<Splash> getSplashPage(int offset, int length, String hql);

	/**
	 * 获取数据总条数
	 * 
	 * @return
	 */
	public int getCount();

	public List<Splash> loadMoreSplashByCreateTime(String createTime);

	public List<Splash> loadNewSplashByCreateTime(String createTime);
}
