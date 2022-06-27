package com.hackers.app.common;

import java.util.List;
import java.util.Scanner;

import com.hackers.app.course.Course;
import com.hackers.app.course.CourseDAO;
import com.hackers.app.register.Regi;
import com.hackers.app.register.RegiDAO;
import com.hackers.app.students.Student;
import com.hackers.app.students.StudentDAO;

public class UserModeManagement extends RegiManagement{

	
	//회원으로 로그인했을때 넘어가는화면
	
	Scanner sc = new Scanner(System.in);
	protected StudentDAO sDAO=StudentDAO.getInstance();
	protected RegiDAO rDAO = RegiDAO.getInstance();
	protected CourseDAO cDAO = CourseDAO.getInstance();
	
	public UserModeManagement() {}
	
	public void run () {
		while (true) {
			menuPrint();
			int menuNo = menuSelect();
			
			if(menuNo ==1) {
				//1-1. 개설된 강의목록 출력
				printAll();
				//1-2. 수강신청
				registerClass();
			} else if (menuNo==2) {
				//2. 회원정보 수정 (비밀번호, 주소)
				updateStudentInfo();
			
			} else if (menuNo==3) {
				//3. 수강내역 조회
		
			} else if (menuNo ==9) {
				//9. 뒤로가기
				back();
				break;
		} else {
			showInputError();
		}
		
	}
	}
	
	protected void menuPrint() {

		System.out.println("--------------------------");
		System.out.println("1.수강신청 2.회원정보 수정 ");
		System.out.println("3.수강신청 내역조회 9.back");
		System.out.println("--------------------------");
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

	private void back() {
		System.out.println("메인으로 돌아갑니다.");
	}

	protected void exit() {
		System.out.println("프로그램을 종료합니다.");
	}

	protected void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}

	@Override
	protected Regi inputClassInfo() {
		Regi regi = new Regi();


		regi.setStudentName(LoginControl.getLoginInfo().getStudentName());
		System.out.println("강의번호>");
		regi.setClassNum(Integer.parseInt(sc.nextLine()));
		return regi;
	}
	
	//1. 수강신청 
	
	protected void registerClass() {
		Student stu = LoginControl.getLoginInfo();
		
		Regi regi = new Regi();

		
		//스튜던트테이블에서 아이디로 이름을 찾아와야함
		
		String id = stu.getMemberId();
		regi.setStudentName(sDAO.selectId(id).getStudentName());
		
		System.out.println("강의번호>");
		regi.setClassNum(Integer.parseInt(sc.nextLine()));
		
		Course course = cDAO.selectOne(regi.getClassNum());
		if (course == null) {
			System.out.println("개설된 강의가 아닙니다.");
			return;
		}
		
		List<Regi> list = rDAO.selectOne(regi.getStudentName(), regi.getClassNum());

		if (list.size() > 0) {
			System.out.println("이미 신청한 강의입니다.");
			return;
		}
		
		if (course.getOccupied() >= course.getCapacity()) {
			System.out.println("마감된 강의입니다.");
			return;
		}
		
		System.out.println(course.getClassName() + "를 신청하시겠습니까? (1:YES/2:NO)");

		if (Integer.parseInt(sc.nextLine()) == 2) {
			return;
		}
		
		regi.setMemberId(LoginControl.getLoginInfo().getMemberId());
		regi.setClassSchedule(course.getClassSchedule());
		regi.setClassName(course.getClassName());


		int num = course.getOccupied() + 1;
		course.setOccupied(num);		
		cDAO.updateOccupy(course);
		
		
		regi.setCapacity(course.getCapacity());
		regi.setOccupied(num);

		rDAO.insert(regi);
		
	}
	
	//2.회원정보 수정 - 비밀번호, 주소만 수정
	
	protected void updateStudentInfo() {
		
		String  stuInfo = LoginControl.getLoginInfo().getMemberId();
		Student stu = sDAO.selectId(stuInfo);
		
		System.out.print("기존 비밀번호 입력>");
		String mypage = sc.nextLine();
		
		
		if(!mypage.equals(stu.getMemberPassword())) {
			System.out.println("비밀번호 오류입니다.");
			return;
		} 

		System.out.print("수정(수정하지 않을경우 0)>");

		String password = sc.nextLine();

		if (!password.equals("0")) {
			stu.setMemberPassword(password);
		}
		
		System.out.println("주소 변경");
		System.out.println("  기존>"+stu.getStudentAddress());
		System.out.print("  수정(수정하지 않을경우 0)>");
		
		String address = sc.nextLine();

		if (!address.equals("0")) {
			stu.setStudentAddress(address);
		}
		
		sDAO.updateInfo2(stu);
		
	}
}