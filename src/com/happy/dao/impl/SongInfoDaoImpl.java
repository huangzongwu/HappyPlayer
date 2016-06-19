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

import com.happy.dao.SongInfoDao;
import com.happy.model.SingerAvatar;
import com.happy.model.SongInfo;
import com.happy.util.PageNoUtil;

@Component("songInfoDao")
public class SongInfoDaoImpl implements SongInfoDao {
	private Logger logger = Logger.getLogger(SongInfoDaoImpl.class.getName());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public boolean add(SongInfo songInfo) {
		try {
			hibernateTemplate.save(songInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean edit(SongInfo songInfo) {
		try {
			hibernateTemplate.update(songInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(String id) {
		try {
			SongInfo songInfo = (SongInfo) hibernateTemplate.get(
					SongInfo.class, id);
			hibernateTemplate.delete(songInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
			return false;
		}
		return true;
	}

	@Override
	public SongInfo getSongInfoDataByID(String id) {
		SongInfo songInfo = (SongInfo) hibernateTemplate
				.get(SongInfo.class, id);
		return songInfo;
	}

	@Override
	public SongInfo getSongInfoByID(String id) {
		SongInfo songInfo = (SongInfo) hibernateTemplate
				.get(SongInfo.class, id);
		return songInfo;
	}

	@Override
	public List<SongInfo> getSongInfoPage(final int offset, final int length,
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
		String hql = "select count(*) from SongInfo";
		long count = (Long) hibernateTemplate.find(hql).listIterator().next();
		return (int) count;
	}

	@Override
	public List<SongInfo> loadMoreSongInfoByCreateTime(String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from SongInfo " + " order by createTime desc";
		} else {
			hql = "from SongInfo where createTime <'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<SongInfo> list = hibernateTemplate.find(hql);
		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}

	@Override
	public List<SongInfo> loadNewSongInfoByCreateTime(String createTime) {
		String hql = "";
		if (createTime == null || createTime.equals("")) {
			hql = "from SongInfo " + " order by createTime desc";
		} else {
			hql = "from SongInfo where createTime >'" + createTime + "'"
					+ " order by createTime desc";
		}
		List<SongInfo> list = hibernateTemplate.find(hql);

		if (list.size() > 10) {
			return list.subList(0, 10);
		}
		return list;
	}

	@Override
	public List<SongInfo> getSongInfoByKey(String key) {
		StringBuffer hql = new StringBuffer();
		hql.append("from SongInfo s where ");
		if (null != key && !"".equals(key)) {
			hql.append("  s.title like '%" + key + "%'");
			hql.append("  or s.singer like '%" + key + "%'");
			hql.append("  or s.key like '%" + key + "%'");
		}

		List<SongInfo> list = hibernateTemplate.find(hql.toString()
				+ " order by createTime desc");
		if (list == null) {
			list = new ArrayList<SongInfo>();
		}
		return list;
	}

}
