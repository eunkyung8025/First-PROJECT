package com.hackers.app.students;

import java.sql.Date; //년월일까지 나오는게 sql.date


public class Student {
	
	private int studentNum;
	private String memberId;
	private String memberPassword;
	private int memberRole;
	private String studentName;
	private String studentGender;
	private String studentBirth;
	private String studentAddress;
	private String studentPhone;
	
	
	
	//name, gender, birth, address,classHistory(classNum)

	@Override
	
	public String toString() {
		return "아이디:"+memberId
				+ ", 이름:"+studentName
				+ ", 연락처:"+studentPhone
				+ ", 성별:"+studentGender
			    + ", 생년월일:"+studentBirth
			    + ", 주소:"+studentAddress;
		
	}



	public int getStudentNum() {
		return studentNum;
	}



	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public String getMemberPassword() {
		return memberPassword;
	}



	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}



	public int getMemberRole() {
		return memberRole;
	}



	public void setMemberRole(int memberRole) {
		this.memberRole = memberRole;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public String getStudentGender() {
		return studentGender;
	}



	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}



	public String getStudentBirth() {
		return studentBirth;
	}



	public void setStudentBirth(String studentBirth) {
		this.studentBirth = studentBirth;
	}



	public String getStudentAddress() {
		return studentAddress;
	}



	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}



	public String getStudentPhone() {
		return studentPhone;
	}



	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}



	
	
}
