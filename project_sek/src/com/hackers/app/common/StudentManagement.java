package com.hackers.app.common;

import java.util.List;
import java.util.Scanner;

import com.hackers.app.students.Student;
import com.hackers.app.students.StudentDAO;

public class StudentManagement {
	
	Scanner sc = new Scanner(System.in);
	protected StudentDAO sDAO = StudentDAO.getInstance();
	
	public StudentManagement () {
		
		while (true) {
			menuPrint();
			
			int menuNo = menuSelect();
			
			if (menuNo == 1) {
				//1.회원등록
				regiStudentInfo();
			} else if(menuNo == 2) {
				//2.회원수정
				updateStudentInfo();
			} else if(menuNo == 3) {
				//3.회원삭제
				deleteStudentInfo();
				
			} else if(menuNo == 4) {
				//4.전체조회
				selectAllStudent();
			} else if(menuNo == 5) {
				//5.단건조회
				selectStudent();
			} else if(menuNo == 6) {
				//6.수강신청
 			} else if(menuNo ==9) {
 				back();
 				//exit();
 				break;
 			} else {
 				showInputError();
 			}
		}
	}
	//1.회원등록 2. 회원수정 3. 회원삭제 4. 전제조회 5.단건조회
    //6. 강의 개설 - 년월, 과정, 강의명,,
    // 6.수강신청 
	
	protected void menuPrint() {

		System.out.println("********외 국 어 학 원 1 위********");
		System.out.println("1.회원등록 2.회원수정 3.회원삭제    ");
		System.out.println("4.전체조회 5.단건조회 6.수강신청 9.back");
		System.out.println("*********H A C K E R S*********");	
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
	
	protected void exit() {
		System.out.println("프로그램을 종료합니다.");
	}
	
	protected void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}
	
	
	//1.회원등록
	
	public void regiStudentInfo() {
		Student student = inputAll();
		sDAO.insert(student);
	}
	
	public Student inputAll() {
		Student student = new Student();
		System.out.print("이름>");
		student.setStudentName(sc.nextLine());
		System.out.print("성별>");
		student.setStudentGender(sc.nextLine());
		System.out.print("생년월일>");
		student.setStudentBirth(sc.nextLine());
		System.out.print("주소>");
		student.setStudentAddress(sc.nextLine());
		System.out.print("연락처>");
		student.setStudentPhone(Integer.parseInt(sc.nextLine()));

		return student;
	}
	
	
	
	//2. 회원수정 - 주소, 연락처
	
	private void updateStudentInfo() {
		String studentName = inputName();
		
		Student student = sDAO.selectOne(studentName);
		
		if(student ==null) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		}
		student = inputUpdateInfo(student);
		sDAO.updateInfo(student);
	}
	
	public Student inputUpdateInfo (Student student) {
		System.out.print("기존>"+student.getStudentAddress());
		System.out.print("수정(수정하지 않을경우 0)>");
		String address = sc.nextLine();
		
		if(!address.equals("0")) {
			student.setStudentAddress(address);
		}
		
		System.out.print("기존>"+student.getStudentPhone());
		System.out.print("수정(수정하지 않을 경우 -1입력) >");
		int phoneNum = Integer.parseInt(sc.nextLine());
		if(phoneNum >-1) {
			student.setStudentPhone(phoneNum);
		}
		
		return student;		
	}

	//3.회원삭제
	
	private void deleteStudentInfo() {
		
		String studentName = inputName();
		
		Student student = sDAO.selectOne(studentName);
		
		if(student==null) {
			System.out.println("등록된 정보가 없습니다.");
			return;
		} 		
		sDAO.delete(studentName);
		
	}
	

	private String inputName() { 
		System.out.println("회원이름>");
		return sc.nextLine();
	}
	
	//4.전체조회
	
	private void selectAllStudent() {
		List<Student> list = sDAO.selectAll();
		
		for(Student stu : list) {
			System.out.println(stu);
		}
	}
	
	//5.단건조회
	
	private void selectStudent() {
		String studentName = inputName();
		
		Student stu = sDAO.selectOne(studentName);
		
		if(stu != null) {
			System.out.println(stu);
		} else  {
			System.out.println("회원정보가 없습니다.");
		}
	}
}
