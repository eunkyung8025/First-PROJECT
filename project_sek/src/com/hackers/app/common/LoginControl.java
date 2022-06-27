package com.hackers.app.common;

import java.util.Scanner;

import com.hackers.app.students.Student;
import com.hackers.app.students.StudentDAO;

public class LoginControl {
	
	private Scanner sc = new Scanner(System.in);
	private static Student LoginInfo = null;
	public static Student getLoginInfo() {
		return LoginInfo;
	}
	
	public LoginControl () {
		
		while (true) {
			menuPrint();
			
			int menuNo = menuSelect();
			
			if(menuNo ==1) {
				//회원가입
				new StudentManagement().regiStudentInfo();
			} else if (menuNo==2) {
				login();
				if(getLoginInfo()!=null) {
					if(selectRole()) {
						//role이 0이면 매니지먼트 실행
						new Management().run();
					} else {
						//role이 1이면 유저 매니지먼트	
						new UserModeManagement().run();
					}
				}
				
			} else if (menuNo==3) {
				exit();
				break;
			} else {
				showInputError();
			}
			
		}
	}
	
	private int menuSelect() {
		int menuNo=0;
		
		try {
			menuNo= Integer.parseInt(sc.nextLine());
			
		} catch (NumberFormatException e) {
			System.out.println("숫자형식으로 입력해주세요.");
		} return menuNo;

	}
	
	private void exit() {
		System.out.println("프로그램을 종료합니다.");
	}
	
	private void menuPrint() {
		System.out.println("* * * * * * * * * * * *");
		System.out.println("1.JOIN  2.LOGIN  3.EXIT");
		System.out.println("* * * * * * * * * * * *");
	}
	
	private void showInputError() {
		System.out.println("메뉴를 확인해주시기 바랍니다.");
			}
	
	private void login() {
		//아이디 비밀번호 입력
		
		Student inputInfo = inputMember();
		
		//로그인 시도
		LoginInfo = StudentDAO.getInstance().loginTry(inputInfo);
		//실패할 경우 그대로 메소드 종료
		if(LoginInfo ==null) return;
	
		
	}
	
	private Student inputMember() {
		Student info = new Student();
		System.out.println("아이디>");
		info.setMemberId(sc.nextLine());
		System.out.println("비밀번호>");
		info.setMemberPassword(sc.nextLine());
		
		return info;
	}
	
	protected boolean selectRole() { 
		int memberRole = LoginControl.getLoginInfo().getMemberRole();
		if (memberRole ==0 ) {
			return true;
		} else {
			return false;
		}
	}

}

