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

import com.happy.dao.SplashDao;
import com.happy.model.Splash;
import com.happy.util.PageNoUtil;

@Component("splashDao")
public class SplashDaoImpl implements SplashDao {
	private Logger logger = Logger.getLogger(SplashDaoImpl.class.getName());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 添加
	 * 
	 * @param splash
	 * @return
	 */
	public boolean add(Splash splash) {
		try {
			hibernateTemplate.save(splash);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	/**
	 * 通过当前的时间来获取要显示的启动页面
	 * 
	 * @param dateStr
	 * @return
	 */
	@Override
	public Splash getSplashMessageByDate(String dateStr) {
		List<Splash> list = hibernateTemplate
				.find("from Splash where startTime<='" + dateStr + "'"
						+ " and endTime>='" + dateStr + "'"
						+ " order by createTime desc");

		if (list.size() != 0) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * 通过id来获取图片数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Splash getSplashImageByID(String id) {
		Splash splash = (Splash) hibernateTemplate.get(Splash.class, id);
		return splash;
	}

	/**
	 * 分页获取启动页面的列表数据
	 * 
	 * @param offset
	 * @param length
	 * @param hql
	 * @return
	 */
	@Override
	public List<Splash> getSplashPage(final int offset, final int length,
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

	/**
	 * 获取数据总条数
	 * 
	 * @return
	 */
	@Override
	public int getCount() {
		String hql = "select count(*) from Splash";
		long count = (Long) hibernateTemplate.find(hql).listIterator().next();
		return (int) count;
	}

	/**
	 * 通过id来获取启动页面的数据
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Splash getSplashByID(String id) {
		Splash splash = (Splash) hibernateTemplate.get(Splash.class, id);
		return splash;
	}

	/**
	 * 编辑
	 * 
	 * @param splash
	 * @return
	 */
	@Override
	public boolean edit(Splash splash) {
		try {
			hibernateTemplate.update(splash);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean delete(String id) {
		try {
			Splash splash = (Splash) hibernateTemplate.get(Splash.class, id);
			hibernateTemplate.delete(splash);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public List<Splash> loadMoreSplashByCreateTime(String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from Splash " + " order by createTime desc";
		} else {
			hql = "from Splash where createTime <'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<Splash> list = hibernateTemplate.find(hql);

		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}

	@Override
	public List<Splash> loadNewSplashByCreateTime(String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from Splash " + " order by createTime desc";
		} else {
			hql = "from Splash where createTime >'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<Splash> list = hibernateTemplate.find(hql);

		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}
}
