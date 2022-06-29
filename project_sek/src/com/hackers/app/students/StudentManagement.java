package com.hackers.app.students;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hackers.app.register.Regi;
import com.hackers.app.register.RegiDAO;

public class StudentManagement {

	Scanner sc = new Scanner(System.in);
	protected StudentDAO sDAO = StudentDAO.getInstance();
	protected RegiDAO rDAO = RegiDAO.getInstance();

	public StudentManagement() {
	}

	public void run() {
		while (true) {
			menuPrint();

			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 1.회원등록
				regiStudentInfo1();
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
				break;
			} else {
				showInputError();
			}
		}
	}

	protected void menuPrint() {

		System.out.println();
		System.out.println("------- MEMBER MANAGEMENT -------");
		System.out.println();
		System.out.println("   1.회원등록 2.회원수정 3.회원삭제  ");
		System.out.println("   4.전체조회 5.개별조회 9.back");
		System.out.println();
		System.out.println("---------------------------------");
		System.out.print("SELECT MEMU > ");
	
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

	protected void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}

	// 1-1.회원등록 - 관리자 화면 
	// 비밀번호 재확인 기능 없음, 초기 비밀번호 아이디와 동일하게 설정하고 유저에게 안내할 것 안내

	public void regiStudentInfo1() {

		Student student = null;

		while (true) {
			System.out.print("아이디 > ");

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
				student.setMemberPassword(id);
				break;
			}
		}
		System.out.println("★★초기 비밀번호는 아이디와 동일합니다.");
		System.out.println("★★회원정보 수정에서 변경가능함을 안내해주세요"); //📌


		System.out.print("이름 > ");
		student.setStudentName(sc.nextLine());
		System.out.print("성별(남/여) > ");
		student.setStudentGender(sc.nextLine());
		System.out.print("생년월일(YYMMDD) > ");
		student.setStudentBirth(sc.nextLine());
		System.out.print("주소 > ");
		student.setStudentAddress(sc.nextLine());
		System.out.print("연락처(-)없이 숫자만 입력 >");
		student.setStudentPhone(sc.nextLine());

		sDAO.insert1(student);
	}
	
	
	// 1-2. 회원등록 - 유저 화면 : 비밀번호까지 입력하게 하고, 비밀번호 재확인 기능도 추가 ★

		public void regiStudentInfo2() {

			Student student = null;

			while (true) {
				System.out.print("아이디 > ");

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

			 while (true) {
			 System.out.print("비밀번호> ");
			 student.setMemberPassword(sc.nextLine());
			 System.out.println("비밀번호 재입력> ");
			 String pwdCheck = sc.nextLine();
			 if (!pwdCheck.equals(student.getMemberPassword())) {
			 } else {
			 break;
			 }
			 }

			System.out.print("이름 > ");
			student.setStudentName(sc.nextLine());
			System.out.print("성별(남/여) > ");
			student.setStudentGender(sc.nextLine());
			System.out.print("생년월일(YYMMDD) > ");
			student.setStudentBirth(sc.nextLine());
			System.out.print("주소 > ");
			student.setStudentAddress(sc.nextLine());
			System.out.print("연락처(-)없이 숫자만 입력 >");
			student.setStudentPhone(sc.nextLine());

			sDAO.insert2(student);
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
		
		
		//회원삭제 전 수강중일 경우 삭제가 불가하도록 안내
		List <Regi> list = rDAO.selectOne(studentName);
		
		if (student == null) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		} 

		if (list.size() > 0 ) {
				System.out.println("수강 중인 강의가 있어 회원정보 삭제가 불가합니다.");
				return;
		} else {
			sDAO.delete(studentName);
			
		}

	}

	private String inputName() {
		System.out.println("회원이름>");
		return sc.nextLine();
	}

	
	
	// 4.전체조회

	private void selectAllStudent() {
		List<Student> list = sDAO.selectAll();

		System.out.println();
		for (Student stu : list) {
			System.out.println(stu);
		}
		System.out.println();
	}

	// 5.단건조회

	private void selectStudent() {
		String studentName = inputName();

		Student stu = sDAO.selectOne(studentName);

		if (stu != null) {
			System.out.println(stu);
		} else {
			System.out.println("회원정보가 존재하지 않습니다.");
		}
	}
}
