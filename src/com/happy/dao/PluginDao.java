package com.happy.dao;

import java.util.List;

import com.happy.model.PluginInfo;
import com.happy.model.SongInfo;

/**
 * 
 * @author zhangliangming
 * 
 */
public interface PluginDao {

	public boolean add(PluginInfo pluginInfo);

	public boolean edit(PluginInfo pluginInfo);

	public boolean delete(String ppid);

	public PluginInfo getPluginInfoDataByID(String id);

	public PluginInfo getPluginInfoByID(String id);

	public List<PluginInfo> getPluginInfoPage(int offset, int length, String hql);

	public List<PluginInfo> loadMorePluginInfoByCreateTime(String createTime);

	public List<PluginInfo> loadNewPluginInfoByCreateTime(String createTime);

	public int getCount();

}
