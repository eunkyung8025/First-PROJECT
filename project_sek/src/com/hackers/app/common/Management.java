package com.hackers.app.common;

import java.util.Scanner;
import com.hackers.app.course.CourseDAO;
import com.hackers.app.course.CourseManagement;
import com.hackers.app.notice.NoticeDAO;
import com.hackers.app.notice.NoticeManagement;
import com.hackers.app.register.RegiManagement;
import com.hackers.app.students.StudentDAO;
import com.hackers.app.students.StudentManagement;
import com.hackers.app.common.LoginControl;

public class Management {

	protected Scanner sc = new Scanner(System.in);

	protected StudentDAO sDAO = StudentDAO.getInstance();
	protected CourseDAO cDAO = CourseDAO.getInstance();
	protected NoticeDAO nDAO = NoticeDAO.getInstance();
	
	
	public void run() {
		
	 while (true) {
		 
	 
		menuPrint();
		
		int menuNo = menuSelect();
		
		if(menuNo==1) {
			//회원관리 매니지먼트
			new StudentManagement().run();
		} else if(menuNo ==2) {
			//강의관리 매니지먼트
			new CourseManagement();
		} else if (menuNo ==3) {
			//수강신청 매니지먼트
			new RegiManagement().run();
		} else if (menuNo ==4) {
			//게시판 관리 매니지먼트
			new NoticeManagement().run();
		} else if (menuNo ==9) {
			back();
			break;
		} else {
			showInputError();
		}
				
	  }
	}

	//관리자 모드에서 보이는 메뉴 화면 (크게 회원관리.강의관리.수강신청.공지관리로 구분됨)
	protected void menuPrint() {
		System.out.println();
		

		System.out.println(" * H * A * C * K * E * R * S * ");
		System.out.println();
		System.out.println("   1.MEMBER_ 회원정보관리");
		System.out.println("   2.CLASS_ 강의개설,강의리스트조회 ");
		System.out.println("   3.REGI_ 수강신청");
		System.out.println("   4.NOTICE_ 공지글,반별게시판관리  ");
		System.out.println("   9.LOG-OUT ");
		System.out.println();
		System.out.println(" * * * T * O * E * I * C * * *  ");
		System.out.println();

	}

	protected int menuSelect() {
		System.out.print("SELECT MEMU > ");


		int menuNo = 0;

		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자를 입력해주시기 바랍니다.");
		}
		return menuNo;
	}

	protected void back() {
		System.out.println("메인으로 돌아갑니다.");
		System.out.println(" @══════@ ");
		System.out.println("   ║  ║  HACKERS");
		System.out.println("   ║  ║  ACADEMIA");
		System.out.println();

	}

	protected void showInputError() {
		System.out.println(" 메뉴에서 입력해주시기 바랍니다 ");

	}
	


}
