package com.hackers.app.common;

import java.util.Scanner;
import com.hackers.app.course.CourseDAO;
import com.hackers.app.students.StudentDAO;

public class Management {

	protected Scanner sc = new Scanner(System.in);

	protected StudentDAO sDAO = StudentDAO.getInstance();
	protected CourseDAO cDAO = CourseDAO.getInstance();

	public void run() {
		
	 while (true) {
		 
	 
		menuPrint();
		
		int menuNo = menuSelect();
		
		if(menuNo==1) {
			new StudentManagement();
		} else if(menuNo ==2) {
			new CourseManagement();
		} else if (menuNo ==3) {
			new RegiManagement();
		} else if (menuNo ==9) {
			exit();
			break;
		} else {
			showInputError();
		}
				
	  }
	}

	protected void menuPrint() {
		System.out.println();
		System.out.println("******** 외 국 어 학 원 1 위 ********");
		System.out.println();
		System.out.println("1.학생정보관리 2.강의개설관리 3.수강신청 9.종료");
		System.out.println();
		System.out.println("********* H A C K E R S *********");
		System.out.println();

	}

	protected int menuSelect() {
		System.out.println("메뉴선택>");

		int menuNo = 0;

		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return menuNo;
	}

	protected void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	protected void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}

}