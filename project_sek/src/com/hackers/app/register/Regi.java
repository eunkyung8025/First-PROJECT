package com.hackers.app.register;

import java.sql.Date;

public class Regi {

	private int studentNum; //수강생번호
	private String studentName; //수강생이름
	private int classNum; //강의번호
	private String classSchedule; //신청년월
	private String className; //강의명
	private Date regiDate; //등록일
	private int capacity; //수용가능인원
	private int occupied; //수강인원
	
	
	@Override
	
	public String toString() {
		String result = "수강생이름 : "+studentName
				+ ", 수강 월 : "+classSchedule
				+ ", 강의명 : "+className
				+ ", 신청일 : "+regiDate;
		return result;
	}


	public int getStudentNum() {
		return studentNum;
	}


	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public int getClassNum() {
		return classNum;
	}


	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}


	public String getClassSchedule() {
		return classSchedule;
	}


	public void setClassSchedule(String classSchedule) {
		this.classSchedule = classSchedule;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public Date getRegiDate() {
		return regiDate;
	}


	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public int getOccupied() {
		return occupied;
	}


	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}
	
	
	
	
	
}
