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

import com.happy.dao.SingerAvatarDao;
import com.happy.model.SingerAvatar;
import com.happy.util.PageNoUtil;

@Component("singerAvatarDao")
public class SingerAvatarDaoImpl implements SingerAvatarDao {
	private Logger logger = Logger.getLogger(SingerAvatarDaoImpl.class
			.getName());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean add(SingerAvatar singerAvatar) {
		try {
			hibernateTemplate.save(singerAvatar);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(SingerAvatar singerAvatar) {
		try {
			hibernateTemplate.update(singerAvatar);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		try {
			SingerAvatar singerAvatar = (SingerAvatar) hibernateTemplate.get(
					SingerAvatar.class, id);
			hibernateTemplate.delete(singerAvatar);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public SingerAvatar getSingerAvatarImageByID(String id) {
		SingerAvatar singerAvatar = (SingerAvatar) hibernateTemplate.get(
				SingerAvatar.class, id);
		return singerAvatar;
	}

	@Override
	public SingerAvatar getSingerAvatarByID(String id) {
		SingerAvatar singerAvatar = (SingerAvatar) hibernateTemplate.get(
				SingerAvatar.class, id);
		return singerAvatar;
	}

	@Override
	public List<SingerAvatar> getSingerAvatarPage(final int offset,
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
		String hql = "select count(*) from SingerAvatar";
		long count = (Long) hibernateTemplate.find(hql).listIterator().next();
		return (int) count;
	}

	@Override
	public List<SingerAvatar> getSingerAvatarBySinger(String singer) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SingerAvatar s where ");
		if (null != singer && !"".equals(singer)) {
			hql.append("  s.singer like '%" + singer + "%'");
		}

		List<SingerAvatar> list = hibernateTemplate.find(hql.toString()
				+ " order by createTime desc");
		if (list == null) {
			list = new ArrayList<SingerAvatar>();
		}
		return list;
	}

}
