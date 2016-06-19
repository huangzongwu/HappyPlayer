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

import com.happy.dao.EasyTouchThemeDao;
import com.happy.model.EasyTouchTheme;
import com.happy.util.PageNoUtil;

@Component("easyTouchThemeDao")
public class EasyTouchThemeDaoImpl implements EasyTouchThemeDao {
	private Logger logger = Logger.getLogger(EasyTouchThemeDaoImpl.class
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
	public boolean add(EasyTouchTheme easyTouchTheme) {
		try {
			hibernateTemplate.save(easyTouchTheme);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(EasyTouchTheme easyTouchTheme) {
		try {
			hibernateTemplate.update(easyTouchTheme);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		try {
			EasyTouchTheme easyTouchTheme = (EasyTouchTheme) hibernateTemplate
					.get(EasyTouchTheme.class, id);
			hibernateTemplate.delete(easyTouchTheme);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public int getCount() {
		String hql = "select count(*) from EasyTouchTheme";
		long count = (Long) hibernateTemplate.find(hql).listIterator().next();
		return (int) count;
	}

	@Override
	public EasyTouchTheme getEasyTouchThemePreviewImageByID(String id) {
		EasyTouchTheme easyTouchTheme = (EasyTouchTheme) hibernateTemplate.get(
				EasyTouchTheme.class, id);
		return easyTouchTheme;
	}

	@Override
	public EasyTouchTheme getEasyTouchThemeDataByID(String id) {
		EasyTouchTheme easyTouchTheme = (EasyTouchTheme) hibernateTemplate.get(
				EasyTouchTheme.class, id);
		return easyTouchTheme;
	}

	@Override
	public EasyTouchTheme getEasyTouchThemeByID(String id) {
		EasyTouchTheme easyTouchTheme = (EasyTouchTheme) hibernateTemplate.get(
				EasyTouchTheme.class, id);
		return easyTouchTheme;
	}

	@Override
	public List<EasyTouchTheme> getEasyTouchThemePage(final int offset,
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
	public List<EasyTouchTheme> loadMoreEasyTouchThemeByCreateTime(
			String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from EasyTouchTheme " + " order by createTime desc";
		} else {
			hql = "from EasyTouchTheme where createTime <'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<EasyTouchTheme> list = hibernateTemplate.find(hql);

		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}

	@Override
	public List<EasyTouchTheme> loadNewEasyTouchThemeByCreateTime(
			String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from EasyTouchTheme " + " order by createTime desc";
		} else {
			hql = "from EasyTouchTheme where createTime >'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<EasyTouchTheme> list = hibernateTemplate.find(hql);

		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}

}
