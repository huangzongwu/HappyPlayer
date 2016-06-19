package com.happy.service;

import com.happy.model.KscInfo;

/**
 * 
 * @author zhangliangming
 * 
 */
public interface KscService {

	public Object add(KscInfo kscInfo);

	public Object edit(KscInfo kscInfo);

	public Object delete(String id);

	public KscInfo getKscInfoDataByID(String id);

	public Object getKscInfoByID(String id);

	public Object getKscInfoPage(int offset, int length, String sort,
			String order);

	public int getCount();

	public Object getKscInfoByOther(String songName, String artist);
}
