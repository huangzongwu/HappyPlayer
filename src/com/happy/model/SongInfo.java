package com.happy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 歌曲文件信息
 * 
 */
@Entity
@Table(name = "song_info")
public class SongInfo {
	private String sid; // 编号id
	private String title; // 歌曲名称
	private String singer; // 歌手名称
	private String key;// 关键字
	private long duration; // 歌曲时长
	private String durationStr;// 歌曲时长
	private long size; // 文件大小
	private String sizeStr; // 文件大小字符串
	private String createTime;// 添加时间
	private String updateTime;// 更新时间
	private byte[] data;// 数据
	private String type;// 文件类型

	@Id
	@Column(name = "sid", unique = true, nullable = false, length = 20)
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Column(name = "title", nullable = false, length = 255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "singer", nullable = false, length = 255)
	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Column(name = "key_word", nullable = false, length = 255)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "duration", nullable = false)
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Column(name = "duration_str", nullable = false)
	public String getDurationStr() {
		return durationStr;
	}

	public void setDurationStr(String durationStr) {
		this.durationStr = durationStr;
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
