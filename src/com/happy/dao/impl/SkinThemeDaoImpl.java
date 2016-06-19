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

import com.happy.dao.SkinThemeDao;
import com.happy.model.SkinTheme;
import com.happy.util.PageNoUtil;

@Component("skinThemeDao")
public class SkinThemeDaoImpl implements SkinThemeDao {
	private Logger logger = Logger.getLogger(SkinThemeDaoImpl.class.getName());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean add(SkinTheme skinTheme) {
		try {
			hibernateTemplate.save(skinTheme);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(SkinTheme skinTheme) {
		try {
			hibernateTemplate.update(skinTheme);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		try {
			SkinTheme skinTheme = (SkinTheme) hibernateTemplate.get(
					SkinTheme.class, id);
			hibernateTemplate.delete(skinTheme);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public int getCount() {
		String hql = "select count(*) from SkinTheme";
		long count = (Long) hibernateTemplate.find(hql).listIterator().next();
		return (int) count;
	}

	@Override
	public SkinTheme getSkinThemePreviewImageByID(String id) {
		SkinTheme skinTheme = (SkinTheme) hibernateTemplate.get(
				SkinTheme.class, id);
		return skinTheme;
	}

	@Override
	public SkinTheme getSkinThemeDataByID(String id) {
		SkinTheme skinTheme = (SkinTheme) hibernateTemplate.get(
				SkinTheme.class, id);
		return skinTheme;
	}

	@Override
	public SkinTheme getSkinThemeByID(String id) {
		SkinTheme skinTheme = (SkinTheme) hibernateTemplate.get(
				SkinTheme.class, id);
		return skinTheme;
	}

	@Override
	public List<SkinTheme> getSkinThemePage(final int offset, final int length,
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
	public List<SkinTheme> loadMoreSkinThemeByCreateTime(String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from SkinTheme " + " order by createTime desc";
		} else {
			hql = "from SkinTheme where createTime <'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<SkinTheme> list = hibernateTemplate.find(hql);

		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}

	@Override
	public List<SkinTheme> loadNewSkinThemeByCreateTime(String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from SkinTheme " + " order by createTime desc";
		} else {
			hql = "from SkinTheme where createTime >'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<SkinTheme> list = hibernateTemplate.find(hql);

		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}

}
