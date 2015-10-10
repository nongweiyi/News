package com.nong.news.entities;

import android.graphics.drawable.Drawable;

public class News {
	private String title;
	private String description;
	private String hottime;
	private String picUrl;
	private String newUrl;
	private Drawable imageDrawable;

	public News() {
		super();

	}
	
	public News(String title, String description, String hottime, String picUrl, String newUrl,Drawable imageDrawable) {
		super();
		this.title = title;
		this.description = description;
		this.hottime = hottime;
		this.picUrl = picUrl;
		this.newUrl = newUrl;
		this.imageDrawable=imageDrawable;
	}
	public Drawable getImageDrawable() {
		return imageDrawable;
	}

	public void setImageDrawable(Drawable imageDrawable) {
		this.imageDrawable = imageDrawable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHottime() {
		return hottime;
	}

	public void setHottime(String hottime) {
		this.hottime = hottime;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getNewUrl() {
		return newUrl;
	}

	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}

}
