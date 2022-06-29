package com.hackers.app.register;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hackers.app.common.Management;
import com.hackers.app.course.Course;
import com.hackers.app.students.Student;

public class RegiManagement extends Management {

	Scanner sc = new Scanner(System.in);

	protected RegiDAO rDAO = RegiDAO.getInstance();

	public RegiManagement() {
	}

	public void run() {

		while (true) {
			menuPrint();
			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 1.수강신청
				printAll();
				registerClass();
			} else if (menuNo == 2) {
				// 2.수강취소
				refundClass();
			} else if (menuNo == 3) {
				// 3.수강신청결과
				showRegiInfo();
			} else if (menuNo == 4) {
				// 4.전체조회
				showAllClassInfo();
			} else if (menuNo == 9) {
				back();
				break;
			} else {
				showInputError();
			}

		}
	}

	public void printAll() {
		String str = "★마감임박★ "; // 🔔🔔
		String ok = "ㅇ수강신청ㅇ "; // ✔️✔️

		System.out.println("▼ ▼ ▼ 개설된 강의목록 ▼ ▼ ▼");
		System.out.println();

		List<Course> list1 = cDAO.selectAll1();

		for (Course cr : list1) {

			if (cr.getOccupied() == cr.getCapacity()) {
				System.out.print("☆조기마감☆ ");
				System.out.println(printInfo(cr));
			} else if (cr.getOccupied() >= cr.getCapacity() * 0.7) {
				System.out.print(str);
				System.out.println(printInfo(cr));
			} else {
				System.out.println(ok + printInfo(cr));

			}

		}
		System.out.println();
		List<Course> list2 = cDAO.selectAll2();

		for (Course cr : list2) {

			if (cr.getOccupied() == cr.getCapacity()) {
				System.out.print("☆조기마감☆ "); //⚡
				System.out.println(printInfo(cr));
			} else if (cr.getOccupied() >= cr.getCapacity() * 0.7) {
				System.out.print(str);
				System.out.println(printInfo(cr));
			} else {
				System.out.println(ok + printInfo(cr));

			}

		}
		System.out.println();
	}

	// 수강신청 화면에서만 보이는 강의목록
	public String printInfo(Course course) {

		String str = "강의번호 : " + course.getClassNum() + "｜개설년월 : " + course.getClassSchedule() + "｜선생님 : "
				+ course.getClassTeacher() + "｜강의명 : " + course.getClassName();

		return str;
	}

	protected void menuPrint() {

		System.out.println();
		System.out.println("------- REGI MANAGEMENT -------");
		System.out.println();
		System.out.println("   1.수강신청  2.수강취소 3.신청내역조회     ");
		System.out.println("   4.전체수강신청 내역조회    9.back");
		System.out.println();
		System.out.println("---------------------------------");
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

	protected void back() {
		System.out.println("메인으로 돌아갑니다.");
	}

	protected void showInputError() {
		System.out.println("🚩 메뉴에서 입력해주시기 바랍니다 🚩");

	}

	// 1.수강신청 (관리자가 하는 수강신청)

	protected void registerClass() {
		Regi regi = inputClassInfo();

		Student student = sDAO.selectOne(regi.getStudentName());
		if (student == null) {
			System.out.println("회원정보가 없습니다.");
			return;
		}

		Course course = cDAO.selectOne(regi.getClassNum());
		if (course == null) {
			System.out.println("개설된 강의가 아닙니다.");
			System.out.println("강의번호를 다시 확인해주세요😃");
		}

		// 이미 수강신청한 강의일 경우, "이미 신청한 강의입니다."

		List<Regi> list = rDAO.selectOne(regi.getStudentName(), regi.getClassNum());

		if (list.size() > 0) {
			System.out.println("이미 신청한 강의입니다.");
			return;
		}

		// 수강인원에 넘치지 않는 지 조회

		if (course.getOccupied() >= course.getCapacity()) {
			System.out.println("해당 강의는 조기마감되었습니다.");
			System.out.println("스타강사 인기강의 또는 인강 수강을 추천합니다😃");
			return;
		}

		// 회원정보, 강의 있음 -> 나머지 정보를 담아줄것
		
		System.out.println(course.getClassName() + "를 신청하시겠습니까? (1:YES/2:NO)");

		if (Integer.parseInt(sc.nextLine()) == 2) {
			return;
		}

		regi.setMemberId(sDAO.selectOne(student.getStudentName()).getMemberId());
		regi.setClassSchedule(course.getClassSchedule());
		regi.setClassName(course.getClassName());

		// 수강하고 있는 인원을 데려와서 +1을 해줌
		
		int num = course.getOccupied() + 1;
		course.setOccupied(num);
		cDAO.updateOccupy(course);
		regi.setCapacity(course.getCapacity());
		regi.setOccupied(num);

		rDAO.insert(regi);

	}

	protected Regi inputClassInfo() {
		Regi regi = new Regi();

		System.out.println("이름>");
		regi.setStudentName(sc.nextLine());
		System.out.println("강의번호>");
		int cnum =  menuSelect();
		regi.setClassNum(cnum);
		return regi;

	}

	// 2.수강취소

	private void refundClass() {

		Regi regi = inputClassInfo();

		Student student = sDAO.selectOne(regi.getStudentName());
		if (student == null) {
			System.out.println("회원정보가 없습니다.");
			return;
		}

		Course course = cDAO.selectOne(regi.getClassNum());
		if (course == null) {
			System.out.println("등록한 강의가 아닙니다.");
			return;
		}

		// 수강하고 있는 인원을 데려와서 -1을 해줌
		
		int num = course.getOccupied() - 1;
		course.setOccupied(num);
		cDAO.updateOccupy(course);

		rDAO.delete(course.getClassNum());

	}

	// 3.신청내역조회

	private void showRegiInfo() {

		System.out.print("이름>");
		String studentName = sc.nextLine();

		List<Regi> list = rDAO.selectOne(studentName);

		if (list == null) {
			System.out.println("등록한 강의가 없습니다.");
			return;
		} else {
			for (Regi regi : list) {
				System.out.println(regi);
			}
		}
	}

	// 4.전체수강신청 내역조회 

	private void showAllClassInfo() {

		// selectAll1 : 22.07월 강의 출력
		List<Regi> list = rDAO.selectAll1();

		for (Regi regi : list) {
			System.out.println(regi);
		}

		// selectAll2 : 22.08월 강의 출력
		System.out.println();
		List<Regi> list1 = rDAO.selectAll2();

		for (Regi regi : list1) {
			System.out.println(regi);
		}
	}

}
