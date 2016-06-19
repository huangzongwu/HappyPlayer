package com.happy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * EasyTouchTheme主题
 * @author zhangliangming
 *
 */
@Entity
@Table(name = "easytouch_theme")
public class EasyTouchTheme {

	/**
	 * 编号
	 */
	private String sid;
	/**
	 * 皮肤主题名称
	 */
	private String themeName;

	/**
	 * 预览图片的数据
	 */
	private byte[] previewImage;

	/**
	 * 皮肤文件大小
	 */
	private long size;
	/**
	 * 皮肤文件大小字符串
	 */
	private String sizeStr;
	/**
	 * 添加时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 皮肤数据
	 */
	private byte[] data;
	/**
	 * 文件类型
	 */
	private String type;

	@Id
	@Column(name = "sid", unique = true, nullable = false, length = 20)
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Column(name = "theme_name", nullable = false, length = 100)
	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	@Column(name = "preview_image", nullable = false)
	public byte[] getPreviewImage() {
		return previewImage;
	}

	public void setPreviewImage(byte[] previewImage) {
		this.previewImage = previewImage;
	}

	@Column(name = "size", nullable = false)
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Column(name = "size_str", nullable = false)
	public String getSizeStr() {
		return sizeStr;
	}

	public void setSizeStr(String sizeStr) {
		this.sizeStr = sizeStr;
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

	@Column(name = "data", nullable = false)
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Column(name = "type", nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
