package com.happy.service;

import com.happy.model.EasyTouchTheme;

public interface EasyTouchThemeService {
	/**
	 * 添加
	 * 
	 * @param
	 * @return
	 */
	public Object add(EasyTouchTheme easyTouchTheme);

	/**
	 * 编辑
	 * 
	 * @param
	 * @return
	 */
	public Object edit(EasyTouchTheme easyTouchTheme);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public Object delete(String id);

	/**
	 * 获取数据总条数
	 * 
	 * @return
	 */
	public int getCount();

	/**
	 * 根据id来获取预览图片的数据
	 * 
	 * @param id
	 * @return
	 */
	public EasyTouchTheme getEasyTouchThemePreviewImageByID(String id);

	/**
	 * 通过id获取SkinTheme的数据包
	 * 
	 * @param id
	 * @return
	 */
	public EasyTouchTheme getEasyTouchThemeDataByID(String id);

	/**
	 * 通过id来获取SkinTheme的数据
	 * 
	 * @param id
	 * @return
	 */
	public Object getEasyTouchThemeByID(String id);

	/**
	 * 分页获取SkinTheme的数据
	 * 
	 * @param offset
	 * @param length
	 * @param hql
	 * @return
	 */
	public Object getEasyTouchThemePage(int offset, int length, String sort,
			String order);

	/**
	 * 加载更多的SkinTheme数据
	 * 
	 * @param createTime
	 * @return
	 */
	public Object loadMoreEasyTouchThemeByCreateTime(String createTime);

	/**
	 * 加载新的SkinTheme数据
	 * 
	 * @param createTime
	 * @return
	 */
	public Object loadNewEasyTouchThemeByCreateTime(String createTime);
}
