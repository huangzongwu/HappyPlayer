package com.happy.service;

import com.happy.model.SingerPhoto;

public interface SingerPhotoService {

	public Object add(SingerPhoto singerPhoto);

	public Object edit(SingerPhoto singerPhoto);

	public Object delete(String id);

	public SingerPhoto getSingerPhotoImageByID(String id);

	public Object getSingerPhotoByID(String id);

	public Object getSingerPhotoBySinger(String singer);

	public Object getSingerPhotoPage(int offset, int length, String sort,
			String order, String singer);

	public int getCount();

}
