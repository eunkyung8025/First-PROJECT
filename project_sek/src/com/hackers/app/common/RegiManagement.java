package com.hackers.app.common;

import java.util.Scanner;

import com.hackers.app.course.Course;
import com.hackers.app.course.CourseDAO;

public class RegiManagement {
	
	Scanner sc = new Scanner(System.in);
	
	protected CourseDAO cDAO = CourseDAO.getInstance();
	
	public RegiManagement() {
		
		while (true) {
			int menuNo = menuSelect();
			
			if(menuNo ==1) {
				//1. 강의개설
				regiClassInfo();
			} else if(menuNo ==2) {
				//2. 강의삭제 -강의번호 기준
				deleteClassInfo();
			} else if (menuNo ==3) {
				//3.전체강의 조회
				//selectAllCourse();
			} else if (menuNo == 9) {
				back();
				break;
			} else {
				showInputError();
			}
			
		}
	}

	//1.강의개설 2.강의삭제 3.전체강의조회 
	
	protected void menuPrint() {
		
		System.out.println("==========HACKER TOEIC==========");
		System.out.println("1.강의개설 2.강의삭제 3.강의조회 9.뒤로가기");
		System.out.println("================================");
	}
	protected int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return menuNo;
	}
	private void back () {
		System.out.println("메인으로 돌아갑니다.");
	}
	protected void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}
	

    //1.강의개설

	public void regiClassInfo() {
		Course course = inputAll();
		cDAO.insert(course);
	}
	
	public Course inputAll() {
		Course course = new Course();
		
		System.out.print("개설강의월>");
		course.setClassSchedule(sc.nextLine());
		System.out.print("선생님>");
		course.setClassTeacher(sc.nextLine());
		System.out.print("강의명>");
		course.setClassName(sc.nextLine());
		
		return course;
	}
	
	//2.강의삭제
	
	private void deleteClassInfo() {
		int classNum = inputClassNum();
		
		Course course = cDAO.selectOne(classNum);
		
		if(course==null) {
			System.out.println("등록된 강의가 아닙니다.");
			return;
		}
		cDAO.delete(classNum);

	}
	
	private int inputClassNum() {
		System.out.println("강의번호>");
		return Integer.parseInt(sc.nextLine());
	}
	
	
	//class_num NUMBER(10),
	//class_schedule VARCHAR2(100),
	//class_teacher VARCHAR2(100),
	//class_name VARCHAR2(100)
}
	


	
	
