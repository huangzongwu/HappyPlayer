package com.happy.service;

import com.happy.model.Splash;

public interface SplashService {
	public Object add(Splash splash);

	public Object getSplashMessageByDate(String dateStr);

	public Splash getSplashImageByID(String id);

	public Object getSplashByID(String id);

	public Object getSplashPage(int offset, int length, String sort,
			String order);

	public int getCount();

	public Object edit(Splash splash);

	public Object delete(String id);

	public Object loadMoreSplashByCreateTime(String createTime);

	public Object loadNewSplashByCreateTime(String createTime);
}
