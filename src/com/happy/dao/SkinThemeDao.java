package com.happy.dao;

import java.util.List;

import com.happy.model.SkinTheme;

public interface SkinThemeDao {
	/**
	 * 添加
	 * 
	 * @param
	 * @return
	 */
	public boolean add(SkinTheme skinTheme);

	/**
	 * 编辑
	 * 
	 * @param
	 * @return
	 */
	public boolean edit(SkinTheme skinTheme);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(String id);

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
	public SkinTheme getSkinThemePreviewImageByID(String id);

	/**
	 * 通过id获取SkinTheme的数据包
	 * 
	 * @param id
	 * @return
	 */
	public SkinTheme getSkinThemeDataByID(String id);

	/**
	 * 通过id来获取SkinTheme的数据
	 * 
	 * @param id
	 * @return
	 */
	public SkinTheme getSkinThemeByID(String id);

	/**
	 * 分页获取SkinTheme的数据
	 * 
	 * @param offset
	 * @param length
	 * @param hql
	 * @return
	 */
	public List<SkinTheme> getSkinThemePage(int offset, int length, String hql);

	/**
	 * 加载更多的SkinTheme数据
	 * 
	 * @param createTime
	 * @return
	 */
	public List<SkinTheme> loadMoreSkinThemeByCreateTime(String createTime);

	/**
	 * 加载新的SkinTheme数据
	 * 
	 * @param createTime
	 * @return
	 */
	public List<SkinTheme> loadNewSkinThemeByCreateTime(String createTime);
}
