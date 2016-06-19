package com.happy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.happy.dao.SplashDao;
import com.happy.model.Splash;
import com.happy.service.SplashService;

@Component("splashService")
public class SplashServiceImpl implements SplashService {

	private SplashDao dao;

	public SplashDao getDao() {
		return dao;
	}

	@Resource(name = "splashDao")
	public void setDao(SplashDao dao) {
		this.dao = dao;
	}

	public Object add(Splash splash) {
		boolean result = dao.add(splash);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object getSplashMessageByDate(String dateStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		Splash splash = dao.getSplashMessageByDate(dateStr);
		if (splash != null) {
			map.put("result", 1);
			mapSplash(splash, map);
		} else {
			map.put("result", 0);
		}
		return gson.toJson(map);
	}

	@Override
	public Splash getSplashImageByID(String id) {
		Splash splash = dao.getSplashImageByID(id);
		return splash;
	}

	@Override
	public Object getSplashByID(String id) {
		Splash splash = dao.getSplashByID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		mapSplash(splash, map);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	private void mapSplash(Splash splash, Map<String, Object> map) {
		map.put("sid", splash.getSid());
		map.put("title", splash.getTitle());
		map.put("createTime", splash.getCreateTime());
		map.put("startTime", splash.getStartTime());
		map.put("endTime", splash.getEndTime());
		map.put("updateTime", splash.getUpdateTime());
	}

	@Override
	public Object getSplashPage(int offset, int length, String sort,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String hql = "from Splash  order by " + sort + " " + order;
		List<Splash> lists = dao.getSplashPage(offset, length, hql);
		int count = getCount();
		int size = lists.size();

		map.put("size", size);
		map.put("count", count);

		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				Splash splash = lists.get(i);
				mapSplash(splash, re);

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
	public Object edit(Splash splash) {
		boolean result = dao.edit(splash);

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
	public Object loadMoreSplashByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<Splash> lists = dao.loadMoreSplashByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				Splash splash = lists.get(i);
				mapSplash(splash, re);

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
	public Object loadNewSplashByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<Splash> lists = dao.loadNewSplashByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				Splash splash = lists.get(i);
				mapSplash(splash, re);

				res.add(re);
			}
		}
		map.put("size", size);
		map.put("rows", res);

		return gson.toJson(map);
	}

}
