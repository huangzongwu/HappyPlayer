package com.happy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.happy.dao.SingerAvatarDao;
import com.happy.model.SingerAvatar;
import com.happy.service.SingerAvatarService;

@Component("singerAvatarService")
public class SingerAvatarServiceImpl implements SingerAvatarService {

	private SingerAvatarDao dao;

	public SingerAvatarDao getDao() {
		return dao;
	}

	@Resource(name = "singerAvatarDao")
	public void setDao(SingerAvatarDao dao) {
		this.dao = dao;
	}

	@Override
	public Object add(SingerAvatar singerAvatar) {
		boolean result = dao.add(singerAvatar);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object edit(SingerAvatar singerAvatar) {
		boolean result = dao.edit(singerAvatar);
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
	public SingerAvatar getSingerAvatarImageByID(String id) {
		SingerAvatar singerAvatar = dao.getSingerAvatarImageByID(id);
		return singerAvatar;
	}

	@Override
	public Object getSingerAvatarByID(String id) {
		SingerAvatar singerAvatar = dao.getSingerAvatarImageByID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		mapSingerAvatar(singerAvatar, map);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	private void mapSingerAvatar(SingerAvatar singerAvatar,
			Map<String, Object> map) {
		map.put("sid", singerAvatar.getSid());
		map.put("singer", singerAvatar.getSinger());
		map.put("createTime", singerAvatar.getCreateTime());
		map.put("updateTime", singerAvatar.getUpdateTime());
	}

	@Override
	public Object getSingerAvatarPage(int offset, int length, String sort,
			String order, String singer) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String hql = "";
		if (singer == null || singer.equals("")) {
			hql = "from SingerAvatar  order by " + sort + " " + order;
		} else {
			hql = "from SingerAvatar s where s.singer like '%" + singer + "%'"
					+ " order by " + sort + " " + order;
		}
		List<SingerAvatar> lists = dao.getSingerAvatarPage(offset, length, hql);
		int count = getCount();
		int size = lists.size();

		map.put("size", size);
		map.put("count", count);

		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SingerAvatar singerAvatar = lists.get(i);
				mapSingerAvatar(singerAvatar, re);

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
	public Object getSingerAvatarBySinger(String singer) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<SingerAvatar> lists = dao.getSingerAvatarBySinger(singer);
		int size = lists.size();
		map.put("size", size);
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SingerAvatar singerAvatar = lists.get(i);
				mapSingerAvatar(singerAvatar, re);

				res.add(re);
			}
		}
		// ,EasyUI根据这个参数，可以计算page和number的值，这个值不是users的长度
		map.put("rows", res);// rows键 存放每页记录 list

		return gson.toJson(map);
	}

}
