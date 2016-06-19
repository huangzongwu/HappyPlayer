package com.happy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 *歌词
 *
 */
@Entity
@Table(name = "ksc_info")
public class KscInfo {
	// 歌词id
	private String kid;
	// 歌手名称
	private String artist;
	// 歌曲歌名
	private String songName;
	private long size; // 大小
	private String sizeStr; // 大小字符串
	private byte[] data;// ksc数据
	private String createTime;// 添加时间
	private String updateTime;// 更新时间
	private String type;// 文件类型

	@Id
	@Column(name = "kid", unique = true, nullable = false, length = 20)
	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	@Column(name = "artist", nullable = false, length = 100)
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Column(name = "song_name", nullable = false, length = 100)
	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
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
