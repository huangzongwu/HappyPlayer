package com.happy.dao;

import java.util.List;

import com.happy.model.SongInfo;

public interface SongInfoDao {
	public boolean add(SongInfo songInfo);

	public boolean edit(SongInfo songInfo);

	public boolean delete(String id);

	public SongInfo getSongInfoDataByID(String id);

	public SongInfo getSongInfoByID(String id);

	public List<SongInfo> getSongInfoPage(int offset, int length, String hql);

	public int getCount();

	public List<SongInfo> loadMoreSongInfoByCreateTime(String createTime);

	public List<SongInfo> loadNewSongInfoByCreateTime(String createTime);

	public List<SongInfo> getSongInfoByKey(String key);
}
