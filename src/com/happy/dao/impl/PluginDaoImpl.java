package com.happy.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.happy.dao.PluginDao;
import com.happy.model.PluginInfo;
import com.happy.util.PageNoUtil;

@Component("pluginDao")
public class PluginDaoImpl implements PluginDao {
	private Logger logger = Logger.getLogger(PluginDaoImpl.class.getName());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean add(PluginInfo pluginInfo) {
		try {
			hibernateTemplate.save(pluginInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(PluginInfo pluginInfo) {
		try {
			hibernateTemplate.update(pluginInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String pid) {
		try {
			PluginInfo pluginInfo = (PluginInfo) hibernateTemplate.get(
					PluginInfo.class, pid);
			hibernateTemplate.delete(pluginInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public PluginInfo getPluginInfoDataByID(String pid) {
		PluginInfo pluginInfo = (PluginInfo) hibernateTemplate.get(
				PluginInfo.class, pid);
		return pluginInfo;
	}

	@Override
	public PluginInfo getPluginInfoByID(String pid) {
		PluginInfo pluginInfo = (PluginInfo) hibernateTemplate.get(
				PluginInfo.class, pid);
		return pluginInfo;
	}

	@Override
	public List<PluginInfo> getPluginInfoPage(final int offset,
			final int length, final String hql) {
		List list1 = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List list2 = PageNoUtil.getList(session, hql, offset,
								length);
						return list2;
					}
				});
		return list1;
	}

	@Override
	public int getCount() {
		String hql = "select count(*) from PluginInfo";
		long count = (Long) hibernateTemplate.find(hql).listIterator().next();
		return (int) count;
	}

	@Override
	public List<PluginInfo> loadMorePluginInfoByCreateTime(String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from PluginInfo  order by createTime desc";
		} else {
			hql = "from PluginInfo where  createTime <'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<PluginInfo> list = hibernateTemplate.find(hql);
		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}

	@Override
	public List<PluginInfo> loadNewPluginInfoByCreateTime(String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from PluginInfo  order by createTime desc";
		} else {
			hql = "from PluginInfo where  createTime >'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<PluginInfo> list = hibernateTemplate.find(hql);

		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}
}
