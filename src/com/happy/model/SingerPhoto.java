package com.happy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 歌手写真图片
 * 
 */
@Entity
@Table(name = "singer_photo")
public class SingerPhoto {
	/**
	 * id
	 */
	private String sid;

	/**
	 * 歌手名
	 */
	private String singer;

	/**
	 * 添加时间
	 */
	private String createTime;

	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 图片数据
	 */
	private byte[] image1;
	/**
	 * 图片数据
	 */
	private byte[] image2;
	/**
	 * 图片数据
	 */
	private byte[] image3;

	@Id
	@Column(name = "sid", unique = true, nullable = false, length = 20)
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Column(name = "singer", nullable = false, length = 100)
	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Column(name = "create_time", nullable = false, length = 19)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", nullable = false, length = 19)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "image1")
	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	@Column(name = "image2")
	public byte[] getImage2() {
		return image2;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	@Column(name = "image3")
	public byte[] getImage3() {
		return image3;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

}
