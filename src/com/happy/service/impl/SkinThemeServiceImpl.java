package com.happy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.happy.dao.SkinThemeDao;
import com.happy.model.SkinTheme;
import com.happy.service.SkinThemeService;

@Component("skinThemeService")
public class SkinThemeServiceImpl implements SkinThemeService {

	private SkinThemeDao dao;

	public SkinThemeDao getDao() {
		return dao;
	}

	@Resource(name = "skinThemeDao")
	public void setDao(SkinThemeDao dao) {
		this.dao = dao;
	}

	@Override
	public Object add(SkinTheme skinTheme) {
		boolean result = dao.add(skinTheme);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object edit(SkinTheme skinTheme) {
		boolean result = dao.edit(skinTheme);

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
	public int getCount() {
		return dao.getCount();
	}

	@Override
	public SkinTheme getSkinThemePreviewImageByID(String id) {
		SkinTheme skinTheme = dao.getSkinThemePreviewImageByID(id);
		return skinTheme;
	}

	@Override
	public SkinTheme getSkinThemeDataByID(String id) {
		SkinTheme skinTheme = dao.getSkinThemeDataByID(id);
		return skinTheme;
	}

	@Override
	public Object getSkinThemeByID(String id) {
		SkinTheme skinTheme = dao.getSkinThemeByID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		mapSkinTheme(skinTheme, map);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object getSkinThemePage(int offset, int length, String sort,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String hql = "from SkinTheme  order by " + sort + " " + order;
		List<SkinTheme> lists = dao.getSkinThemePage(offset, length, hql);
		int count = getCount();
		int size = lists.size();

		map.put("size", size);
		map.put("count", count);

		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SkinTheme skinTheme = lists.get(i);
				mapSkinTheme(skinTheme, re);

				res.add(re);
			}
		}

		map.put("total", count);// total键 存放总记录数，必须的
								// ,EasyUI根据这个参数，可以计算page和number的值，这个值不是users的长度
		map.put("rows", res);// rows键 存放每页记录 list

		return gson.toJson(map);
	}

	private void mapSkinTheme(SkinTheme skinTheme, Map<String, Object> map) {
		map.put("sid", skinTheme.getSid());
		map.put("themeName", skinTheme.getThemeName());
		map.put("size", skinTheme.getSize());
		map.put("sizeStr", skinTheme.getSizeStr());
		map.put("createTime", skinTheme.getCreateTime());
		map.put("updateTime", skinTheme.getUpdateTime());
		map.put("type", skinTheme.getType());
	}

	@Override
	public Object loadMoreSkinThemeByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<SkinTheme> lists = dao.loadMoreSkinThemeByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SkinTheme skinTheme = lists.get(i);
				mapSkinTheme(skinTheme, re);

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
	public Object loadNewSkinThemeByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<SkinTheme> lists = dao.loadNewSkinThemeByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SkinTheme skinTheme = lists.get(i);
				mapSkinTheme(skinTheme, re);

				res.add(re);
			}
		}
		map.put("size", size);
		map.put("rows", res);

		return gson.toJson(map);
	}

}
