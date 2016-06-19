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

import com.happy.dao.KscDao;
import com.happy.model.KscInfo;
import com.happy.util.PageNoUtil;

@Component("kscDao")
public class KscDaoImpl implements KscDao {
	private Logger logger = Logger.getLogger(KscDaoImpl.class.getName());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean add(KscInfo kscInfo) {
		try {
			hibernateTemplate.save(kscInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(KscInfo kscInfo) {
		try {
			hibernateTemplate.update(kscInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		try {
			KscInfo kscInfo = (KscInfo) hibernateTemplate
					.get(KscInfo.class, id);
			hibernateTemplate.delete(kscInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public KscInfo getKscInfoDataByID(String id) {
		KscInfo kscInfo = (KscInfo) hibernateTemplate.get(KscInfo.class, id);
		return kscInfo;
	}

	@Override
	public KscInfo getKscInfoByID(String id) {
		KscInfo kscInfo = (KscInfo) hibernateTemplate.get(KscInfo.class, id);
		return kscInfo;
	}

	@Override
	public List<KscInfo> getKscInfoPage(final int offset, final int length,
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
		String hql = "select count(*) from KscInfo";
		long count = (Long) hibernateTemplate.find(hql).listIterator().next();
		return (int) count;
	}

	@Override
	public List<KscInfo> getKscInfoByOther(String songName, String artist) {
		StringBuffer hql = new StringBuffer();
		hql.append("from KscInfo k where ");
		if (null != songName && !"".equals(songName)) {
			hql.append("  k.songName like '%" + songName + "%'");
		}
		if (null != artist && !"".equals(artist)) {
			hql.append(" and k.artist like '%" + artist + "%'");
		}
		List<KscInfo> list = hibernateTemplate.find(hql.toString()  + " order by createTime desc");
		if (list == null) {
			list = new ArrayList<KscInfo>();
		}
		return list;
	}
}
