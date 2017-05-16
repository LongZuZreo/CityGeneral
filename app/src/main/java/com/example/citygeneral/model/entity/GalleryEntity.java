package com.example.citygeneral.model.entity;

import java.io.Serializable;

public class GalleryEntity implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path,name;
	  private int id,allid,num;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAllid() {
		return allid;
	}
	public void setAllid(int allid) {
		this.allid = allid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	  
}
