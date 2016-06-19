package com.happy.dao;

import java.util.List;

import com.happy.model.SingerPhoto;

public interface SingerPhotoDao {
	/**
	 * 添加
	 * 
	 * @return
	 */
	public boolean add(SingerPhoto singerPhoto);

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public boolean edit(SingerPhoto singerPhoto);

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
	public SingerPhoto getSingerPhotoImageByID(String id);

	/**
	 * 通过id来获取数据
	 * 
	 * @param id
	 * @return
	 */
	public SingerPhoto getSingerPhotoByID(String id);

	/**
	 * 通过歌手名称获取歌手头像
	 * 
	 * @param singer
	 * @return
	 */
	public List<SingerPhoto> getSingerPhotoBySinger(String singer);

	/**
	 * 分页获取列表数据
	 * 
	 * @param offset
	 * @param length
	 * @param hql
	 * @return
	 */
	public List<SingerPhoto> getSingerPhotoPage(int offset, int length,
			String hql);

	/**
	 * 获取数据总条数
	 * 
	 * @return
	 */
	public int getCount();

}
