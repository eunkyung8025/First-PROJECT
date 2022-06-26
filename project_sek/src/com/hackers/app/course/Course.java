package com.hackers.app.course;

public class Course {

	
	private int classNum;
	private String classSchedule;
	private String classTeacher;
	private String className;
	private int capacity; //수용가능인원
	private int occupied; //현재인원
	
	
	@Override
	
	public String toString() {
		return "강의번호 : "+ classNum
				+"｜개설년월 : "+ classSchedule
				+"｜ 선생님 : "+ classTeacher
				+"｜ 강의명 : "+ className
				+"｜ 수강가능인원 : "+ capacity
				+"｜ 현재인원 : "+ occupied;
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


	public String getClassTeacher() {
		return classTeacher;
	}


	public void setClassTeacher(String classTeacher) {
		this.classTeacher = classTeacher;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
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
