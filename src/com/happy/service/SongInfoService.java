package com.happy.service;

import java.util.List;

import com.happy.model.SongInfo;

public interface SongInfoService {

	public Object add(SongInfo songInfo);

	public Object edit(SongInfo songInfo);

	public Object delete(String id);

	public SongInfo getSongInfoDataByID(String id);

	public Object getSongInfoByID(String id);

	public Object getSongInfoPage(int offset, int length, String sort,
			String order);

	public int getCount();

	public Object loadMoreSongInfoByCreateTime(String createTime);

	public Object loadNewSongInfoByCreateTime(String createTime);
	
	public Object getSongInfoByKey(String key);
}
