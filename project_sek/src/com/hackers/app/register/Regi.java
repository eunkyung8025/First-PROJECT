package com.hackers.app.register;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
}
