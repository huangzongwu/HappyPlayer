package com.happy.service;

import com.happy.model.PluginInfo;

/**
 * 
 * @author zhangliangming
 * 
 */
public interface PluginService {

	public Object add(PluginInfo pluginInfo);

	public Object edit(PluginInfo pluginInfo);

	public Object delete(String pid);

	public PluginInfo getPluginInfoDataByID(String pid);

	public Object getPluginInfoByID(String pid);

	public Object getPluginInfoPage(int offset, int length, String sort,
			String order, int pType);

	public Object loadMorePluginInfoByCreateTime(String createTime);

	public Object loadNewPluginInfoByCreateTime(String createTime);

	public int getCount();
}
