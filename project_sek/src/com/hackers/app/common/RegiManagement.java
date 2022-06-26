package com.hackers.app.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hackers.app.course.Course;
import com.hackers.app.register.Regi;
import com.hackers.app.register.RegiDAO;
import com.hackers.app.students.Student;

public class RegiManagement extends Management {

	Scanner sc = new Scanner(System.in);

	protected RegiDAO rDAO = RegiDAO.getInstance();

	public RegiManagement() {

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
	
	private void printAll() {
		String str = "마감임박";
		
		System.out.println("▼ ▼ ▼ 개설된 강의목록 ▼ ▼ ▼");
		List <Course> list = cDAO.selectAll();
		for(Course cr : list) {
			if(course.getOccupy()>course.getAccommodate()*0.9) {
				System.out.print(str);
			}
			System.out.println(cr);
		}
		
	}

	protected void menuPrint() {

		System.out.println("---- HACKERS ACADEMIA ----");
		System.out.println("------- RESTRATION -------");
		System.out.println();
		System.out.println("1.수강신청 2.수강취소 3.신청내역조회     ");
		System.out.println("4.전체수강신청 내역조회 9.back");
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
	
	private void back () {
		System.out.println("메인으로 돌아갑니다.");
	}
	
	protected void exit() {
		System.out.println("프로그램을 종료합니다.");
	}
	
	protected void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}

	//1.수강신청
		
	private void registerClass() {
		Regi regi = inputClassInfo();

		Student student = sDAO.selectOne(regi.getStudentName());
		if(student==null) {
			System.out.println("회원정보가 없습니다.");
			return;
		} 
		
		Course course = cDAO.selectOne(regi.getClassNum());
		if(course==null) {
			System.out.println("개설된 강의가 아닙니다.");
			return;
		} 
		
		//이미 수강신청한 강의일 경우, "이미 신청한 강의입니다."
		
		List<Regi> list = rDAO.selectOne(regi.getStudentName(), regi.getClassNum());
		
		if(list.size()>0) {
			System.out.println("이미 신청한 강의입니다.");
			return;
		}
		
		//수강인원에 넘치지 않는 지 조회
		
		if(course.getOccupy() >= course.getCapacity()) {
			System.out.println("마감된 강의입니다.");
			return;
		}
	
		//회원정보, 강의 있음 -> 나머지 정보를 담아줄것
		System.out.println(course.getClassName()+"를 신청하시겠습니까? (1:YES/2:NO)");
		
		if(Integer.parseInt(sc.nextLine())==2) {
			return;
		}
		
		regi.setStudentNum(student.getStudentNum());
		regi.setClassSchedule(course.getClassSchedule());
		regi.setClassName(course.getClassName());
		
		//수강하고 있는 인원을 데려와서 +1을 해줌
		int num = course.getOccupy()+1;
		course.setOccupy(num);
		cDAO.updateOccupy(course);
		regi.setAccommodate(course.getCapacity());
		regi.setOccupy(num);
		
		
		rDAO.insert(regi);
		
	}
	
	private Regi inputClassInfo() {
		Regi regi = new Regi();
		
		System.out.println("이름>");
		regi.setStudentName(sc.nextLine());
		System.out.println("강의번호>");
		regi.setClassNum(Integer.parseInt(sc.nextLine()));
		return regi;
		
	}

	//2.수강취소
	
	private void refundClass() {
			
		Regi regi = inputClassInfo();

		Student student = sDAO.selectOne(regi.getStudentName());
		if(student==null) {
			System.out.println("회원정보가 없습니다.");
			return;
		} 
		
		Course course = cDAO.selectOne(regi.getClassNum());
		if(course==null) {
			System.out.println("등록한 강의가 아닙니다.");
			return;
		} 
		
		//수강하고 있는 인원을 데려와서 -1을 해줌
		int num = course.getOccupy()-1;
		course.setOccupy(num);
		cDAO.updateOccupy(course);
				
		rDAO.delete(course.getClassNum());
		
	}
	
	
	
	//3.신청내역조회

	private void showRegiInfo() {
		
		System.out.print("이름>");
		String studentName = sc.nextLine();
		
		List<Regi> list = rDAO.selectOne(studentName);
		
		if(list ==null)  {
			System.out.println("등록한 강의가 없습니다.");
			return;
		} else {
			for(Regi regi :list) {
				System.out.println(regi);
			}
		}
	}
	

	//4.전체수강신청 내역조회
	
	private void showAllClassInfo() {
		List<Regi> list = rDAO.selectAll();
		
		for(Regi regi : list) {
			System.out.println(regi);
		}
	}
	
}
