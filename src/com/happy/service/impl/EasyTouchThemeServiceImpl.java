package com.happy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.happy.dao.EasyTouchThemeDao;
import com.happy.model.EasyTouchTheme;
import com.happy.service.EasyTouchThemeService;

@Component("easyTouchThemeService")
public class EasyTouchThemeServiceImpl implements EasyTouchThemeService {

	private EasyTouchThemeDao dao;

	public EasyTouchThemeDao getDao() {
		return dao;
	}

	@Resource(name = "easyTouchThemeDao")
	public void setDao(EasyTouchThemeDao dao) {
		this.dao = dao;
	}

	@Override
	public Object add(EasyTouchTheme easyTouchTheme) {
		boolean result = dao.add(easyTouchTheme);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object edit(EasyTouchTheme easyTouchTheme) {
		boolean result = dao.edit(easyTouchTheme);

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
	public EasyTouchTheme getEasyTouchThemePreviewImageByID(String id) {
		EasyTouchTheme easyTouchTheme = dao
				.getEasyTouchThemePreviewImageByID(id);
		return easyTouchTheme;
	}

	@Override
	public EasyTouchTheme getEasyTouchThemeDataByID(String id) {
		EasyTouchTheme easyTouchTheme = dao.getEasyTouchThemeDataByID(id);
		return easyTouchTheme;
	}

	@Override
	public Object getEasyTouchThemeByID(String id) {
		EasyTouchTheme easyTouchTheme = dao.getEasyTouchThemeByID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		mapEasyTouchTheme(easyTouchTheme, map);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object getEasyTouchThemePage(int offset, int length, String sort,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String hql = "from EasyTouchTheme  order by " + sort + " " + order;
		List<EasyTouchTheme> lists = dao.getEasyTouchThemePage(offset, length,
				hql);
		int count = getCount();
		int size = lists.size();

		map.put("size", size);
		map.put("count", count);

		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				EasyTouchTheme easyTouchTheme = lists.get(i);
				mapEasyTouchTheme(easyTouchTheme, re);

				res.add(re);
			}
		}

		map.put("total", count);// total键 存放总记录数，必须的
								// ,EasyUI根据这个参数，可以计算page和number的值，这个值不是users的长度
		map.put("rows", res);// rows键 存放每页记录 list

		return gson.toJson(map);
	}

	private void mapEasyTouchTheme(EasyTouchTheme easyTouchTheme,
			Map<String, Object> map) {
		map.put("sid", easyTouchTheme.getSid());
		map.put("themeName", easyTouchTheme.getThemeName());
		map.put("size", easyTouchTheme.getSize());
		map.put("sizeStr", easyTouchTheme.getSizeStr());
		map.put("createTime", easyTouchTheme.getCreateTime());
		map.put("updateTime", easyTouchTheme.getUpdateTime());
		map.put("type", easyTouchTheme.getType());
	}

	@Override
	public Object loadMoreEasyTouchThemeByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<EasyTouchTheme> lists = dao
				.loadMoreEasyTouchThemeByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				EasyTouchTheme easyTouchTheme = lists.get(i);
				mapEasyTouchTheme(easyTouchTheme, re);

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
	public Object loadNewEasyTouchThemeByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<EasyTouchTheme> lists = dao
				.loadNewEasyTouchThemeByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				EasyTouchTheme easyTouchTheme = lists.get(i);
				mapEasyTouchTheme(easyTouchTheme, re);

				res.add(re);
			}
		}
		map.put("size", size);
		map.put("rows", res);

		return gson.toJson(map);
	}

}
