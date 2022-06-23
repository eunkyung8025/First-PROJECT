package com.hackers.app.students;

import java.sql.Date; //년월일까지 나오는게 sql.date

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Student {
	
	private int studentNum;
	private String studentName;
	private String studentGender;
	private String studentBirth;
	private String studentAddress;
	private int studentPhone;
	private int classNum;
	
	
	
	//name, gender, birth, address,classHistory(classNum)

	@Override
	
	public String toString() {
		return "이름 : "+studentName
				+ ", 성별 : "+studentGender
			    + ", 생년월일 : "+studentBirth
			    + ", 주소 : "+studentAddress
			    + ", 수강이력 : "+classNum;
	}
	
	
	
}
