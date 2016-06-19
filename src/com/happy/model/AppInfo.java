package com.happy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 *应用版本
 * 
 */
@Entity
@Table(name = "app_info")
public class AppInfo {
	private String aid;// 编号id
	private String name; // 应用名称
	private String title;// 应用简介
	private String versionName; // 版本
	private String versionCode;// 数字版本
	private long size; // 大小
	private String sizeStr; // 大小字符串
	private byte[] data;// apk数据
	private String createTime;// 添加时间
	private String updateTime;// 更新时间
	private String type;// 文件类型

	@Id
	@Column(name = "aid", unique = true, nullable = false, length = 20)
	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "title", nullable = false, length = 255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "version_name", nullable = false, length = 100)
	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	@Column(name = "version_code", nullable = false, length = 100)
	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
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

	@Column(name = "data", nullable = false)
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
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

	public String getType() {
		return type;
	}

	@Column(name = "type", nullable = false)
	public void setType(String type) {
		this.type = type;
	}

}
