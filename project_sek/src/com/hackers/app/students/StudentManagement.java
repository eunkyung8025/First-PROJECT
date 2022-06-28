package com.hackers.app.students;

import java.util.List;
import java.util.Scanner;

public class StudentManagement {

	Scanner sc = new Scanner(System.in);
	protected StudentDAO sDAO = StudentDAO.getInstance();

	public StudentManagement() {
	}

	public void run() {
		while (true) {
			menuPrint();

			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 1.회원등록
				regiStudentInfo();
			} else if (menuNo == 2) {
				// 2.회원수정
				updateStudentInfo();
			} else if (menuNo == 3) {
				// 3.회원삭제
				deleteStudentInfo();

			} else if (menuNo == 4) {
				// 4.전체조회
				selectAllStudent();
			} else if (menuNo == 5) {
				// 5.단건조회
				selectStudent();
			} else if (menuNo == 9) {
				back();
				// exit();
				break;
			} else {
				showInputError();
			}
		}
	}
	// 1.회원등록 2. 회원수정 3. 회원삭제 4. 전제조회 5.단건조회
	// 6. 강의 개설 - 년월, 과정, 강의명,,
	// 6.수강신청

	protected void menuPrint() {

		System.out.println("---- HACKERS ACADEMIA ----");
		System.out.println("------- MEMBERSHIP -------");
		System.out.println();
		System.out.println("1.회원등록 2.회원수정 3.회원삭제  ");
		System.out.println("4.전체조회 5.단건조회 9.back");
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

	// 1.회원등록

	public void regiStudentInfo() {

		Student student = null;

		while (true) {
			System.out.print("아이디> ");

			String id = sc.nextLine();
			student = sDAO.selectId(id);

			if (student != null) {
				System.out.println();
				System.out.println("이미 사용중인 아이디입니다.");
				System.out.println("새로운 아이디를 입력해주세요.");

				System.out.println();

			} else {

				student = new Student();
				student.setMemberId(id);
				break;
			}
		}

		System.out.print("비밀번호> ");
		student.setMemberPassword(sc.nextLine());

		// while (true) {
		// System.out.print("비밀번호> ");
		// student.setMemberPassword(sc.nextLine());
		// System.out.println("비밀번호 재입력> ");
		// String pwdCheck = sc.nextLine();
		// if (!pwdCheck.equals(student.getMemberPassword())) {
		// } else {
		// break;
		// }
		// }

		System.out.print("이름>");
		student.setStudentName(sc.nextLine());
		System.out.print("성별(남/여)>");
		student.setStudentGender(sc.nextLine());
		System.out.print("생년월일(YYMMDD)>");
		student.setStudentBirth(sc.nextLine());
		System.out.print("주소>");
		student.setStudentAddress(sc.nextLine());
		System.out.print("연락처>");
		student.setStudentPhone(sc.nextLine());

		sDAO.insert(student);
	}

	// 2. 회원수정 - 주소, 연락처

	private void updateStudentInfo() {
		String studentName = inputName();

		Student student = sDAO.selectOne(studentName);

		if (student == null) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		}
		student = inputUpdateInfo(student);
		sDAO.updateInfo(student);
	}

	public Student inputUpdateInfo(Student student) {
		System.out.print("기존>" + student.getStudentAddress());
		System.out.print("수정(수정하지 않을경우 0)>");
		String address = sc.nextLine();

		if (!address.equals("0")) {
			student.setStudentAddress(address);
		}

		System.out.print("기존>" + student.getStudentPhone());
		System.out.print("수정(수정하지 않을 경우 -1입력) >");
		String phoneNum = sc.nextLine();
		if (!phoneNum.equals("-1")) {
			student.setStudentPhone(phoneNum);
		}

		return student;
	}

	// 3.회원삭제

	private void deleteStudentInfo() {

		String studentName = inputName();

		Student student = sDAO.selectOne(studentName);

		if (student == null) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		}
		sDAO.delete(studentName);

	}

	private String inputName() {
		System.out.println("회원이름>");
		return sc.nextLine();
	}

	// 4.전체조회

	private void selectAllStudent() {
		List<Student> list = sDAO.selectAll();

		for (Student stu : list) {
			System.out.println(stu);
		}
	}

	// 5.단건조회

	private void selectStudent() {
		String studentName = inputName();

		Student stu = sDAO.selectOne(studentName);

		if (stu != null) {
			System.out.println(stu);
		} else {
			System.out.println("회원정보가 없습니다.");
		}
	}
}
