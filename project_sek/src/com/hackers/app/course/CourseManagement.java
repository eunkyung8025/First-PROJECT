package com.hackers.app.course;

import java.util.List;
import java.util.Scanner;

import com.hackers.app.register.Regi;
import com.hackers.app.register.RegiDAO;

public class CourseManagement {
	
	Scanner sc = new Scanner(System.in);
	protected CourseDAO cDAO = CourseDAO.getInstance();
	protected RegiDAO rDAO = RegiDAO.getInstance();
	
	public CourseManagement() {
		
		while (true) {
			menuPrint();
			
			int menuNo = menuSelect();
			
			if(menuNo ==1) {
				//1. 강의개설
				regiClassInfo();
			} else if(menuNo ==2) {
				//2. 전체강의 조회
				selectAllCourse();
			} else if(menuNo ==3) {
				//3.강의수정 -강의번호기준 검색, 강의명 수정가능
				updateClassInfo();
			} else if (menuNo ==4) {
				//4.강의삭제 -강의번호 기준
				deleteClassInfo();
			} else if (menuNo == 9) {
				back();
				break;
			} else {
				showInputError();
			}
			
		}
	}

	//1.강의개설 2.강의삭제 3.전체강의조회 
	
	protected void menuPrint() {
		
		System.out.println("------- CLASS MANAGEMENT -------");
		System.out.println();		
		System.out.println("   1.강의개설   2.전체강의조회              ");
		System.out.println("   3.강의수정  4.강의삭제  9.back  ");
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
	private void back () {
		System.out.println("메인으로 돌아갑니다.");
	}
	protected void showInputError() {
		System.out.println("메뉴에서 입력해주시기 바랍니다.");
	}
	

    //1.강의개설

	public void regiClassInfo() {
		Course course = inputAll();
		cDAO.insert(course);
	}
	
	public Course inputAll() {
		Course course = new Course();
		
		System.out.print("개설강의월(YY.MM) > ");
		course.setClassSchedule(sc.nextLine());
		System.out.print("선생님 > ");
		course.setClassTeacher(sc.nextLine());
		System.out.print("강의명 > ");
		course.setClassName(sc.nextLine());
		System.out.println("수강인원 > ");
		int num = Integer.parseInt(sc.nextLine());
		course.setCapacity(num);
		course.setOccupied(0);
		
		return course;
	}
	
	
	//2.강의수정 -강의번호기준 검색, 강의명 수정가능
	
	private void updateClassInfo() {
		int classNum = inputClassNum();
		Course course = cDAO.selectOne(classNum);
		
		if(course ==null) {
			System.out.println("강의정보가 없습니다.");
			return;
		}
		
		course = inputUpdateInfo(course);
		cDAO.updateInfo(course);
		
	}
	
	// 수정 - 강의명 수정 가능
	
	private Course inputUpdateInfo(Course course) {
		
		System.out.println("기존 > "+course.getClassName());
		System.out.println("수정 > ");
		String className = sc.nextLine();
		course.setClassName(className);
		
		return course;
	}
	
	
	
	//3. 강의삭제 -강의번호 기준
	
	private void deleteClassInfo() {
		int classNum = inputClassNum();
		
		Course course = cDAO.selectOne(classNum);
		
		List <Regi> list = rDAO.selectClassNum(classNum);
		
		if(course==null) {
			System.out.println("등록된 강의가 아닙니다.");
			return;
		}
		
		//수강중이 있는 강의는 삭제 불가하도록 안내문구 노출
		if(list.size() > 0) {
			System.out.println("수강신청한 학생이 있어 강의삭제가 불가합니다.");
			System.out.println();
		} else {
			cDAO.delete(classNum);
		}

	}
	
	private int inputClassNum() {
		System.out.println("강의번호>");
		return  menuSelect();
	}
	
	//강의 조회 -> for문으로 돌때 학사일정을 구분해서 보기 위해 두개의 메소드 활용
	private void selectAllCourse() {
		
		System.out.println();
		List <Course> list1 = cDAO.selectAll1();
		
		for(Course cou : list1) {
			System.out.println(cou);
		}
		
		System.out.println();
		List <Course> list2 = cDAO.selectAll2();
		
		for(Course cou : list2) {
			System.out.println(cou);
		}
		
		System.out.println();

	}
	
	
}

