package com.happy.service;

import com.happy.model.SingerAvatar;

public interface SingerAvatarService {

	public Object add(SingerAvatar singerAvatar);

	public Object edit(SingerAvatar singerAvatar);

	public Object delete(String id);

	public SingerAvatar getSingerAvatarImageByID(String id);

	public Object getSingerAvatarByID(String id);

	public Object getSingerAvatarBySinger(String singer);

	public Object getSingerAvatarPage(int offset, int length, String sort,
			String order, String singer);

	public int getCount();

}
