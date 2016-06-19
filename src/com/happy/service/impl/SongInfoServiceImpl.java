package com.happy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.happy.dao.SongInfoDao;
import com.happy.model.SongInfo;
import com.happy.service.SongInfoService;

@Component("songInfoService")
public class SongInfoServiceImpl implements SongInfoService {

	private SongInfoDao dao;

	public SongInfoDao getDao() {
		return dao;
	}

	@Resource(name = "songInfoDao")
	public void setDao(SongInfoDao dao) {
		this.dao = dao;
	}

	@Override
	public Object add(SongInfo songInfo) {
		boolean result = dao.add(songInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object edit(SongInfo songInfo) {
		boolean result = dao.edit(songInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object delete(String id) {
		boolean result = dao.delete(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public SongInfo getSongInfoDataByID(String id) {
		SongInfo songInfo = dao.getSongInfoDataByID(id);
		return songInfo;
	}

	@Override
	public Object getSongInfoByID(String id) {
		SongInfo songInfo = dao.getSongInfoDataByID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		mapSongInfo(songInfo, map);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	private void mapSongInfo(SongInfo songInfo, Map<String, Object> map) {
		map.put("sid", songInfo.getSid());
		map.put("title", songInfo.getTitle());
		map.put("singer", songInfo.getSinger());
		map.put("key", songInfo.getKey());
		map.put("duration", songInfo.getDuration());
		map.put("durationStr", songInfo.getDurationStr());
		map.put("size", songInfo.getSize());
		map.put("sizeStr", songInfo.getSizeStr());
		map.put("createTime", songInfo.getCreateTime());
		map.put("updateTime", songInfo.getUpdateTime());
		map.put("type", songInfo.getType());
	}

	@Override
	public Object getSongInfoPage(int offset, int length, String sort,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String hql = "from SongInfo  order by " + sort + " " + order;
		List<SongInfo> lists = dao.getSongInfoPage(offset, length, hql);
		int count = getCount();
		int size = lists.size();

		map.put("size", size);
		map.put("count", count);

		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SongInfo songInfo = lists.get(i);
				mapSongInfo(songInfo, re);

				res.add(re);
			}
		}

		map.put("total", count);// total键 存放总记录数，必须的
								// ,EasyUI根据这个参数，可以计算page和number的值，这个值不是users的长度
		map.put("rows", res);// rows键 存放每页记录 list

		return gson.toJson(map);
	}

	@Override
	public int getCount() {
		return dao.getCount();
	}

	@Override
	public Object loadMoreSongInfoByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<SongInfo> lists = dao.loadMoreSongInfoByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SongInfo songInfo = lists.get(i);
				mapSongInfo(songInfo, re);

				res.add(re);
			}
		}
		if (size < 10) {
			map.put("hasmore", false);
		} else {
			map.put("hasmore", true);
		}
		map.put("size", size);
		map.put("rows", res);

		return gson.toJson(map);
	}

	@Override
	public Object loadNewSongInfoByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<SongInfo> lists = dao.loadNewSongInfoByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SongInfo songInfo = lists.get(i);
				mapSongInfo(songInfo, re);

				res.add(re);
			}
		}
		map.put("size", size);
		map.put("rows", res);

		return gson.toJson(map);
	}

	@Override
	public Object getSongInfoByKey(String key) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<SongInfo> lists = dao.getSongInfoByKey(key);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SongInfo songInfo = lists.get(i);
				mapSongInfo(songInfo, re);

				res.add(re);
			}
		}
		map.put("size", size);
		map.put("rows", res);

		return gson.toJson(map);
	}

}
