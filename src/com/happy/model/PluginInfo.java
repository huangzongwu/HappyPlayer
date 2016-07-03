package com.happy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 插件信息
 * 
 * @author zhangliangming
 * 
 */
@Entity
@Table(name = "plugin_info")
public class PluginInfo {
	private String pid;// 插件id
	private String pName;// 插件名
	private String pDetail;// 插件简介
	private String fileType;// 文件类型
	private int pType;// 插件类型
						// 0是原生有页面apk插件，1是原生无页面apk插件，2是有页面web插件，3是无页面web插件，4是混合有页面插件
	private long size; // 大小
	private String sizeStr; // 大小字符串
	private byte[] data;// 插件数据
	private String createTime;// 添加时间
	private String updateTime;// 更新时间

	@Id
	@Column(name = "pid", unique = true, nullable = false, length = 20)
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "pName", nullable = false, length = 255)
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	@Column(name = "pDetail", nullable = false, length = 255)
	public String getpDetail() {
		return pDetail;
	}

	public void setpDetail(String pDetail) {
		this.pDetail = pDetail;
	}

	@Column(name = "fileType", nullable = false, length = 255)
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Column(name = "pType", nullable = false)
	public int getpType() {
		return pType;
	}

	public void setpType(int pType) {
		this.pType = pType;
	}

	@Column(name = "size", nullable = false)
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Column(name = "sizeStr", nullable = false)
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

	@Column(name = "createTime", nullable = false, length = 19)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "updateTime", nullable = false, length = 19)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
