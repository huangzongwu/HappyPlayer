package com.happy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.happy.dao.KscDao;
import com.happy.model.KscInfo;
import com.happy.service.KscService;

@Component("kscService")
public class KscServiceImpl implements KscService {

	private KscDao dao;

	public KscDao getDao() {
		return dao;
	}

	@Resource(name = "kscDao")
	public void setDao(KscDao dao) {
		this.dao = dao;
	}

	@Override
	public Object add(KscInfo kscInfo) {
		boolean result = dao.add(kscInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object edit(KscInfo kscInfo) {
		boolean result = dao.edit(kscInfo);
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
	public KscInfo getKscInfoDataByID(String id) {
		KscInfo kscInfo = dao.getKscInfoByID(id);
		return kscInfo;
	}

	@Override
	public Object getKscInfoByID(String id) {
		KscInfo kscInfo = dao.getKscInfoByID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		mapKscInfo(kscInfo, map);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	private void mapKscInfo(KscInfo kscInfo, Map<String, Object> map) {
		map.put("kid", kscInfo.getKid());
		map.put("artist", kscInfo.getArtist());
		map.put("songName", kscInfo.getSongName());
		map.put("size", kscInfo.getSize());
		map.put("sizeStr", kscInfo.getSizeStr());
		map.put("createTime", kscInfo.getCreateTime());
		map.put("updateTime", kscInfo.getUpdateTime());
		map.put("type", kscInfo.getType());
	}

	@Override
	public Object getKscInfoPage(int offset, int length, String sort,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String hql = "from KscInfo  order by " + sort + " " + order;
		List<KscInfo> lists = dao.getKscInfoPage(offset, length, hql);
		int count = getCount();
		int size = lists.size();

		map.put("size", size);
		map.put("count", count);

		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				KscInfo kscInfo = lists.get(i);
				mapKscInfo(kscInfo, re);

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
	public Object getKscInfoByOther(String songName, String artist) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<KscInfo> lists = dao.getKscInfoByOther(songName, artist);
		int size = lists.size();
		map.put("size", size);
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				KscInfo kscInfo = lists.get(i);
				mapKscInfo(kscInfo, re);

				res.add(re);
			}
		}
		// ,EasyUI根据这个参数，可以计算page和number的值，这个值不是users的长度
		map.put("rows", res);// rows键 存放每页记录 list

		return gson.toJson(map);
	}

}
