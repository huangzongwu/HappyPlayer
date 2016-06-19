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

import com.happy.dao.SingerPhotoDao;
import com.happy.model.SingerPhoto;
import com.happy.util.PageNoUtil;

@Component("singerPhotoDao")
public class SingerPhotoDaoImpl implements SingerPhotoDao {
	private Logger logger = Logger
			.getLogger(SingerPhotoDaoImpl.class.getName());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean add(SingerPhoto singerPhoto) {
		try {
			hibernateTemplate.save(singerPhoto);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(SingerPhoto singerPhoto) {
		try {
			hibernateTemplate.update(singerPhoto);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		try {
			SingerPhoto singerPhoto = (SingerPhoto) hibernateTemplate.get(
					SingerPhoto.class, id);
			hibernateTemplate.delete(singerPhoto);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public SingerPhoto getSingerPhotoImageByID(String id) {
		SingerPhoto singerPhoto = (SingerPhoto) hibernateTemplate.get(
				SingerPhoto.class, id);
		return singerPhoto;
	}

	@Override
	public SingerPhoto getSingerPhotoByID(String id) {
		SingerPhoto singerPhoto = (SingerPhoto) hibernateTemplate.get(
				SingerPhoto.class, id);
		return singerPhoto;
	}

	@Override
	public List<SingerPhoto> getSingerPhotoBySinger(String singer) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SingerPhoto s where ");
		if (null != singer && !"".equals(singer)) {
			hql.append("  s.singer like '%" + singer + "%'");
		}

		List<SingerPhoto> list = hibernateTemplate.find(hql.toString()
				+ " order by createTime desc");
		if (list == null) {
			list = new ArrayList<SingerPhoto>();
		}
		return list;
	}

	@Override
	public List<SingerPhoto> getSingerPhotoPage(final int offset,
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
		String hql = "select count(*) from SingerPhoto";
		long count = (Long) hibernateTemplate.find(hql).listIterator().next();
		return (int) count;
	}

}
