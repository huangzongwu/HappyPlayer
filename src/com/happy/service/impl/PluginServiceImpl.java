package com.happy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.happy.dao.PluginDao;
import com.happy.model.PluginInfo;
import com.happy.service.PluginService;

@Component("pluginService")
public class PluginServiceImpl implements PluginService {

	private PluginDao dao;

	@Resource(name = "pluginDao")
	public void setDao(PluginDao dao) {
		this.dao = dao;
	}

	@Override
	public Object add(PluginInfo pluginInfo) {
		boolean result = dao.add(pluginInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object edit(PluginInfo pluginInfo) {
		boolean result = dao.edit(pluginInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object delete(String pid) {
		boolean result = dao.delete(pid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public PluginInfo getPluginInfoDataByID(String pid) {
		PluginInfo pluginInfo = dao.getPluginInfoByID(pid);
		return pluginInfo;
	}

	@Override
	public Object getPluginInfoByID(String pid) {
		PluginInfo pluginInfo = dao.getPluginInfoByID(pid);
		Map<String, Object> map = new HashMap<String, Object>();
		mapPluginInfo(pluginInfo, map);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	private void mapPluginInfo(PluginInfo pluginInfo, Map<String, Object> map) {
		map.put("pid", pluginInfo.getPid());
		map.put("pName", pluginInfo.getpName());
		map.put("pDetail", pluginInfo.getpDetail());
		map.put("fileType", pluginInfo.getFileType());
		int pType = pluginInfo.getpType();
		map.put("pType", pType);
		if (pType == 0) {
			map.put("pTypeText", "原生有页面apk插件");
		} else if (pType == 1) {
			map.put("pTypeText", "原生无页面apk插件");
		} else if (pType == 2) {
			map.put("pTypeText", "有页面web插件");
		} else if (pType == 3) {
			map.put("pTypeText", "无页面web插件");
		} else if (pType == 4) {
			map.put("pTypeText", "混合有页面插件");
		}
		map.put("size", pluginInfo.getSize());
		map.put("sizeStr", pluginInfo.getSizeStr());
		map.put("createTime", pluginInfo.getCreateTime());
		map.put("updateTime", pluginInfo.getUpdateTime());
	}

	@Override
	public Object getPluginInfoPage(int offset, int length, String sort,
			String order, int pType) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String hql = "";
		if (pType == -1) {
			hql = "from PluginInfo  order by " + sort + " " + order;
		} else {
			hql = "from PluginInfo where pType=" + pType + " order by " + sort
					+ " " + order;
		}
		List<PluginInfo> lists = dao.getPluginInfoPage(offset, length, hql);
		int count = getCount();
		int size = lists.size();

		map.put("size", size);
		map.put("count", count);

		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				PluginInfo pluginInfo = lists.get(i);
				mapPluginInfo(pluginInfo, re);

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
	public Object loadMorePluginInfoByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<PluginInfo> lists = dao.loadMorePluginInfoByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				PluginInfo pluginInfo = lists.get(i);
				mapPluginInfo(pluginInfo, re);

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
	public Object loadNewPluginInfoByCreateTime(String createTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<PluginInfo> lists = dao.loadNewPluginInfoByCreateTime(createTime);
		int size = lists.size();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				PluginInfo pluginInfo = lists.get(i);
				mapPluginInfo(pluginInfo, re);

				res.add(re);
			}
		}
		map.put("size", size);
		map.put("rows", res);

		return gson.toJson(map);
	}
}
