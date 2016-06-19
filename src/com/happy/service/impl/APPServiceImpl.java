package com.happy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.happy.dao.APPDao;
import com.happy.model.AppInfo;
import com.happy.service.APPService;

@Component("appService")
public class APPServiceImpl implements APPService {

	private APPDao dao;

	public APPDao getDao() {
		return dao;
	}

	@Resource(name = "appDao")
	public void setDao(APPDao dao) {
		this.dao = dao;
	}

	@Override
	public Object add(AppInfo appInfo) {
		boolean result = dao.add(appInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object edit(AppInfo appInfo) {
		boolean result = dao.edit(appInfo);
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
	public AppInfo getAppInfoDataByID(String id) {
		AppInfo appInfo = dao.getAppInfoDataByID(id);
		return appInfo;
	}

	@Override
	public Object getAppInfoByID(String id) {
		AppInfo appInfo = dao.getAppInfoByID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		mapAppInfo(appInfo, map);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	private void mapAppInfo(AppInfo appInfo, Map<String, Object> map) {
		map.put("aid", appInfo.getAid());
		map.put("name", appInfo.getName());
		map.put("title", appInfo.getTitle());
		map.put("versionName", appInfo.getVersionName());
		map.put("versionCode", appInfo.getVersionCode());
		map.put("size", appInfo.getSize());
		map.put("sizeStr", appInfo.getSizeStr());
		map.put("createTime", appInfo.getCreateTime());
		map.put("updateTime", appInfo.getUpdateTime());
		map.put("type", appInfo.getType());
	}

	@Override
	public Object getAppInfoMessage(String versionName, float versionCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		AppInfo appInfo = dao.getAppInfoMessage(versionName, versionCode);
		if (appInfo != null) {
			map.put("result", 1);
			mapAppInfo(appInfo, map);
		} else {
			map.put("result", 0);
		}
		return gson.toJson(map);
	}

	@Override
	public Object getAppInfoPage(int offset, int length, String sort,
			String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String hql = "from AppInfo  order by " + sort + " " + order;
		List<AppInfo> lists = dao.getAppInfoPage(offset, length, hql);
		int count = getCount();
		int size = lists.size();

		map.put("size", size);
		map.put("count", count);

		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				AppInfo appInfo = lists.get(i);
				mapAppInfo(appInfo, re);

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

}
