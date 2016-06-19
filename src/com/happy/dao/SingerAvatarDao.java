package com.happy.dao;

import java.util.List;

import com.happy.model.SingerAvatar;

public interface SingerAvatarDao {
	/**
	 * 添加
	 * 
	 * @return
	 */
	public boolean add(SingerAvatar singerAvatar);

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public boolean edit(SingerAvatar singerAvatar);

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
	public SingerAvatar getSingerAvatarImageByID(String id);

	/**
	 * 通过id来获取数据
	 * 
	 * @param id
	 * @return
	 */
	public SingerAvatar getSingerAvatarByID(String id);

	/**
	 * 通过歌手名称获取歌手头像
	 * 
	 * @param singer
	 * @return
	 */
	public List<SingerAvatar> getSingerAvatarBySinger(String singer);

	/**
	 * 分页获取列表数据
	 * 
	 * @param offset
	 * @param length
	 * @param hql
	 * @return
	 */
	public List<SingerAvatar> getSingerAvatarPage(int offset, int length,
			String hql);

	/**
	 * 获取数据总条数
	 * 
	 * @return
	 */
	public int getCount();

}
