package com.happy.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.happy.dao.APPDao;
import com.happy.model.AppInfo;
import com.happy.util.PageNoUtil;

@Component("appDao")
public class APPDaoImpl implements APPDao {
	private Logger logger = Logger.getLogger(APPDaoImpl.class.getName());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean add(AppInfo appInfo) {
		try {
			hibernateTemplate.save(appInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(AppInfo appInfo) {
		try {
			hibernateTemplate.update(appInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		try {
			AppInfo appInfo = (AppInfo) hibernateTemplate
					.get(AppInfo.class, id);
			hibernateTemplate.delete(appInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public AppInfo getAppInfoDataByID(String id) {
		AppInfo appInfo = (AppInfo) hibernateTemplate.get(AppInfo.class, id);
		return appInfo;
	}

	@Override
	public AppInfo getAppInfoByID(String id) {
		AppInfo appInfo = (AppInfo) hibernateTemplate.get(AppInfo.class, id);
		return appInfo;
	}

	@Override
	public List<AppInfo> getAppInfoPage(final int offset, final int length,
			final String hql) {
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
		String hql = "select count(*) from AppInfo";
		long count = (Long) hibernateTemplate.find(hql).listIterator().next();
		return (int) count;
	}

	@Override
	public AppInfo getAppInfoMessage(String versionName, float versionCode) {
		List<AppInfo> list = hibernateTemplate
				.find("from AppInfo where versionCode>=" + versionCode + ""
						+ " order by createTime desc");
		List<AppInfo> tempList = new ArrayList<AppInfo>();
		if (list.size() != 0) {
			for (AppInfo appInfo : list) {
				if (Float.parseFloat(appInfo.getVersionCode()) == versionCode) {
					if (appInfo.getVersionName().equals(versionName)) {

					} else {
						tempList.add(appInfo);
					}

				} else {
					tempList.add(appInfo);
				}
			}
			if (tempList.size() != 0) {
				return tempList.get(0);
			}
		}

		return null;
	}
}
