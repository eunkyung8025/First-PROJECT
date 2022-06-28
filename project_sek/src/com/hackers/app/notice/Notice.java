package com.hackers.app.notice;

public class Notice {

	private int notiNum;
	private int notiType;
	private String notiTitle;
	private String notiContent;
	
	@Override
	
	public String toString () {
		return notiNum + "|" + notiTitle;
	}

	public int getNotiNum() {
		return notiNum;
	}

	public void setNotiNum(int notiNum) {
		this.notiNum = notiNum;
	}

	public int getNotiType() {
		return notiType;
	}

	public void setNotiType(int notiType) {
		this.notiType = notiType;
	}

	public String getNotiTitle() {
		return notiTitle;
	}

	public void setNotiTitle(String notiTitle) {
		this.notiTitle = notiTitle;
	}

	public String getNotiContent() {
		return notiContent;
	}

	public void setNotiContent(String notiContent) {
		this.notiContent = notiContent;
	}

	
	
	

}
