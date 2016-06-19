package com.happy.dao;

import java.util.List;

import com.happy.model.KscInfo;

/**
 * 
 * @author zhangliangming
 * 
 */
public interface KscDao {

	public boolean add(KscInfo kscInfo);

	public boolean edit(KscInfo kscInfo);

	public boolean delete(String id);

	public KscInfo getKscInfoDataByID(String id);

	public KscInfo getKscInfoByID(String id);

	public List<KscInfo> getKscInfoPage(int offset, int length, String hql);

	public int getCount();

	public List<KscInfo> getKscInfoByOther(String songName, String artist);
}
