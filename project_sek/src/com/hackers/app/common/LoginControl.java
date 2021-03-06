package com.hackers.app.common;

import java.util.Scanner;

import com.hackers.app.students.Student;
import com.hackers.app.students.StudentDAO;
import com.hackers.app.students.StudentManagement;

public class LoginControl {

	private Scanner sc = new Scanner(System.in);
	private static Student LoginInfo = null;

	public static Student getLoginInfo() {
		return LoginInfo;
	}

	public LoginControl() {

		while (true) {
			menuPrint();

			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 회원가입 - 학생관리 프로그램 메소드 구분하여 사용
				new StudentManagement().regiStudentInfo2();
			} else if (menuNo == 2) {
				//로그인
				login();
				if (getLoginInfo() != null) {
					if (selectRole()) {
						// role이 0이면 관리자 모드인 일반 매니지먼트 실행
						new Management().run();
					} else {
						// role이 1이면 유저 모드인 유저 매니지먼트 실행
						new UserModeManagement().run();
					}
				}

			} else if (menuNo == 3) {
				exit();
				break;
			} else {
				showInputError();
			}

		}
	}

	private int menuSelect() {
		int menuNo = 0;

		try {
			menuNo = Integer.parseInt(sc.nextLine());

		} catch (NumberFormatException e) {
			System.out.println("숫자형식으로 입력해주세요.");
		}
		return menuNo;

	}

	protected void exit() {
		System.out.println();
		System.out.println("프로그램을 종료합니다.");
		System.out.println("토익은 1위 해커스 ♥");
		System.out.println();
		System.out.println(" ##   ##    ##       ####   ##   ##  #######  ######    #####  ");
		System.out.println(" ##   ##    ##      ##  ##  ##  ##   ##       ##   ##  ##   ## ");
		System.out.println(" ##   ##   ####    ##       ## ##    ##       ##   ##  ##      ");
		System.out.println(" #######   ## #    ##       ####     #####    ######    #####  ");
		System.out.println(" #######   ## #    ##       ####     #####    ######    #####  ");
		System.out.println(" ##   ##  ##   #    ##  ##  ##  ##   ##       ##  ##   ##   ## ");
		System.out.println(" ##   ## ###   ##    ####   ##   ##  #######  ##   ##   ##### ");
		System.out.println();
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	//유저모드 초기 메뉴 화면
	private void menuPrint() {
		
		System.out.println();
		System.out.println("    ######     ###### ");
		System.out.println("     ####       ####  ");
		System.out.println("     ####       ####  ");
		System.out.println("     ###############  ");
		System.out.println("     ###############  ");
		System.out.println("     ####       ####  ");
		System.out.println("     ####       ####  ");
		System.out.println("     ####       ####  ");
		System.out.println("    ######     ###### ");
		System.out.println();
		System.out.println("    HACKERS ACADEMIA ");
		System.out.println("   외국어학원 1위 해커스 ");
		System.out.println(" * * * * * * * * * * * *");
		System.out.println(" 1.JOIN  2.LOGIN  3.EXIT");
		System.out.println(" * * * * * * * * * * * *");
		System.out.print("SELECT MEMU > ");
	}

	private void showInputError() {
		System.out.println("메뉴를 확인해주시기 바랍니다.");
	}

	private void login() {
		// 아이디 비밀번호 입력

		Student inputInfo = inputMember();

		// 로그인 시도
		LoginInfo = StudentDAO.getInstance().loginTry(inputInfo);
		// 실패할 경우 그대로 메소드 종료
		if (LoginInfo == null)
			return;

	}

	//로그인을 위해 아이디와 비밀번호 받음
	private Student inputMember() {
		Student info = new Student();
		System.out.println();
		System.out.print("ID > ");
		info.setMemberId(sc.nextLine());
		System.out.print("PASSWORD > ");
		info.setMemberPassword(sc.nextLine());
		System.out.println();

		return info;
	}

	//관리자와 일반유저 구분을 위해 role로 구분
	protected boolean selectRole() {
		int memberRole = LoginControl.getLoginInfo().getMemberRole();
		if (memberRole == 0) {
			return true;
		} else {
			return false;
		}
	}

}
