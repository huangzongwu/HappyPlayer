package com.happy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.happy.dao.SingerPhotoDao;
import com.happy.model.SingerPhoto;
import com.happy.service.SingerPhotoService;

@Component("singerPhotoService")
public class SingerPhotoServiceImpl implements SingerPhotoService {

	private SingerPhotoDao dao;

	public SingerPhotoDao getDao() {
		return dao;
	}

	@Resource(name = "singerPhotoDao")
	public void setDao(SingerPhotoDao dao) {
		this.dao = dao;
	}

	@Override
	public Object add(SingerPhoto singerPhoto) {
		boolean result = dao.add(singerPhoto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	@Override
	public Object edit(SingerPhoto singerPhoto) {
		boolean result = dao.edit(singerPhoto);
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
	public SingerPhoto getSingerPhotoImageByID(String id) {
		SingerPhoto singerPhoto = dao.getSingerPhotoImageByID(id);
		return singerPhoto;
	}

	@Override
	public Object getSingerPhotoByID(String id) {
		SingerPhoto singerPhoto = dao.getSingerPhotoByID(id);
		Map<String, Object> map = new HashMap<String, Object>();
		mapSingerPhoto(singerPhoto, map);
		Gson gson = new Gson();
		return gson.toJson(map);
	}

	private void mapSingerPhoto(SingerPhoto singerPhoto, Map<String, Object> map) {
		map.put("sid", singerPhoto.getSid());
		map.put("singer", singerPhoto.getSinger());
		map.put("createTime", singerPhoto.getCreateTime());
		map.put("updateTime", singerPhoto.getUpdateTime());
	}

	@Override
	public Object getSingerPhotoBySinger(String singer) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		List<SingerPhoto> lists = dao.getSingerPhotoBySinger(singer);
		int size = lists.size();
		map.put("size", size);
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SingerPhoto singerPhoto = lists.get(i);
				mapSingerPhoto(singerPhoto, re);

				res.add(re);
			}
		}
		// ,EasyUI根据这个参数，可以计算page和number的值，这个值不是users的长度
		map.put("rows", res);// rows键 存放每页记录 list

		return gson.toJson(map);
	}

	@Override
	public Object getSingerPhotoPage(int offset, int length, String sort,
			String order, String singer) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		Gson gson = new Gson();
		String hql = "";
		if (singer == null || singer.equals("")) {
			hql = "from SingerPhoto  order by " + sort + " " + order;
		} else {
			hql = "from SingerPhoto s where s.singer like '%" + singer + "%'"
					+ " order by " + sort + " " + order;
		}
		List<SingerPhoto> lists = dao.getSingerPhotoPage(offset, length, hql);
		int count = getCount();
		int size = lists.size();

		map.put("size", size);
		map.put("count", count);

		if (size != 0) {
			for (int i = 0; i < size; i++) {
				Map<String, Object> re = new HashMap<String, Object>();
				SingerPhoto singerPhoto = lists.get(i);
				mapSingerPhoto(singerPhoto, re);

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
