package com.hackers.app.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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


	
}
