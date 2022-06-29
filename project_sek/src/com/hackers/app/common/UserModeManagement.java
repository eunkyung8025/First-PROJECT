package com.hackers.app.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hackers.app.course.Course;
import com.hackers.app.course.CourseDAO;
import com.hackers.app.notice.Notice;
import com.hackers.app.register.Regi;
import com.hackers.app.register.RegiDAO;
import com.hackers.app.register.RegiManagement;
import com.hackers.app.students.Student;
import com.hackers.app.students.StudentDAO;

public class UserModeManagement extends RegiManagement {

	// 회원으로 로그인했을때 넘어가는화면

	Scanner sc = new Scanner(System.in);
	protected StudentDAO sDAO = StudentDAO.getInstance();
	protected RegiDAO rDAO = RegiDAO.getInstance();
	protected CourseDAO cDAO = CourseDAO.getInstance();
	private static int check;
	private String stuInfo;
	private Student stu;

	// 수강신청 완료 여부에 따라 출력되는 메뉴를 다르게 설정하기 위해 생성자에서 해당 내용 선언

	public UserModeManagement() {
		stuInfo = LoginControl.getLoginInfo().getMemberId();
		stu = sDAO.selectId(stuInfo);

		List<Regi> temp = rDAO.selectOne(stu.getStudentName());
		if (temp.size() > 0) {
			check = 1;
		} else {
			check = 0;
		}

		// notiType =0 이면 공지사항 1이면 반별게시판

	}

	public void run() {
		while (true) {
			if (check == 0) {
				menuPrint();
			}

			else if (check == 1) {
				menuPrint2();
			}
			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 1-1. 개설된 강의목록 출력
				printAll();
				// 1-2. 수강신청
				registerClass();
			} else if (menuNo == 2) {
				// 2. 회원정보 수정 (비밀번호, 주소)
				updateStudentInfo();
			} else if (menuNo == 3) {
				// 3. 수강내역 조회
				showRegiInfo1();
			} else if (menuNo == 4) {
				// 4. 공지사항
				checkNoti();
			} else if (menuNo == 5) {
				// 5. 반별게시
				checkClassNoti();
			} else if (menuNo == 9) {
				// 9. 뒤로가기
				back();
				break;
			} else {
				showInputError();
			}

		}
	}

	public void printAll() {
		String str = "★ 마감임박 ★ "; // 🔔🔔
		String ok = "ㅇ 수강신청 ㅇ "; // ✔️✔️

		System.out.println();
		System.out.println("♨ 해커스 인기강의 마감속출 ♨"); // 🚨🚨
		System.out.println("   마감이 빠른 1위 해커스   ");
		System.out.println();

		List<Course> list1 = cDAO.selectAll1();

		for (Course cr : list1) {

			if (cr.getOccupied() == cr.getCapacity()) {
				System.out.print("☆ 강의마감 ☆ "); //⚡⚡
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
				System.out.print("☆ 강의마감 ☆ ");
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

	// 수강신청하지 않은 신규유저에게 보이는 메뉴 내용 → check = 0

	protected void menuPrint() {

		List<Regi> list = rDAO.selectOne(stu.getStudentName());

		System.out.println();
		System.out.println("     W E L C O M E ♥	 ");
		System.out.println("     외국어학원 1위 해커스  ");
		System.out.println();
		System.out.println("--------------------------");
		System.out.println(" -1등에게 들어야 한 번에 끝낸다!- ");
		System.out.println("--지금 7월 인기강좌 수강신청 중!--");
		System.out.println();
		System.out.println("[" + stu.getStudentName() + "]님 인기강좌 마감 전 서두르세요! ");
		System.out.println();
		System.out.println("1.인기강좌 수강신청 2.개인정보 수정 ");
		System.out.println("3.수강신청 내역 조회           ");
		System.out.println("4.공지사항 5.반별게시판 9.log-out");
		System.out.println("--------------------------");
		System.out.print("SELECT MEMU > ");

	}

	// 수강신청 완료한 사람에게 보이는 메뉴 내용 → check = 1

	protected void menuPrint2() {

		String stuInfo = LoginControl.getLoginInfo().getMemberId();
		Student stu = sDAO.selectId(stuInfo);
		List<Regi> list = rDAO.selectOne(stu.getStudentName());

		System.out.println();

		System.out.println("(●'◡'●)	");
		System.out.println("[" + stu.getStudentName() + "]님 오늘도 해커스와 함께 좋은 하루!");
		System.out.println("     HACKERS ACADEMIA  ");
		System.out.println();
		System.out.println("--------------------------");
		System.out.println();
		System.out.println("1.인기강좌 수강신청 2.개인정보 수정 ");
		System.out.println("3.수강신청 내역 조회           ");
		System.out.println("4.공지사항 5.반별게시판 9.log-out");
		System.out.println("--------------------------");
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

	protected void back() {
		System.out.println();
		System.out.println(" @══════@ ");
		System.out.println("   ║  ║  HACKERS");
		System.out.println("   ║  ║  ACADEMIA");
		System.out.println();
		System.out.println("[" + stu.getStudentName() + "]님의 목표달성 그날까지!");
		System.out.println("1위 해커스가 응원합니다. ");
		System.out.println();
		System.out.println();

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

	// 1. 수강신청 -> 유저 모드에서는 로그인 기록으로 아이디를 찾아와서 수강신청 가능하도록 설정

	protected void registerClass() {

		Student stu = LoginControl.getLoginInfo();

		Regi regi = new Regi();

		// 스튜던트테이블에서 아이디로 이름을 찾아와야함
		String id = stu.getMemberId();
		regi.setStudentName(sDAO.selectId(id).getStudentName());

		System.out.println("강의번호 > ");
		int number =  menuSelect();
		regi.setClassNum(number);

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

		rDAO.insert2(regi);

		check = 1;
	}

	// 2.회원정보 수정 - 비밀번호, 주소 수정 가능

	protected void updateStudentInfo() {

		String stuInfo = LoginControl.getLoginInfo().getMemberId();

		Student stu = sDAO.selectId(stuInfo);

		System.out.print("기존 비밀번호 입력 > ");
		String mypage = sc.nextLine();

		if (!mypage.equals(stu.getMemberPassword())) {
			System.out.println("비밀번호 오류입니다.");
			return;
		}

		int num = 0;

		System.out.println();
		System.out.println("1.비밀번호 변경  / 2.주소 변경 ");
		System.out.print("SELECT MEMU > ");

		num = Integer.parseInt(sc.nextLine());

		if (num == 1) {
			System.out.print("변경하고자 하는 비밀번호 입력 > ");
			String password = sc.nextLine();
			stu.setMemberPassword(password);
			sDAO.updateInfo2(stu);
		} else if (num == 2) {
			System.out.println();
			System.out.println("  기존 등록된 주소 확인 후, 수정 내용을 입력해주세요");
			System.out.println();

			System.out.println("  기존 > " + stu.getStudentAddress());
			System.out.print("  변경 > ");
			String address = sc.nextLine();
			System.out.println();
			stu.setStudentAddress(address);
			sDAO.updateInfo2(stu);
		}
	}

	// 3.수강신청 내역 조회 -> 수강신청 완료한 사람만 이용 가능하도록 체크

	protected void showRegiInfo1() {

		String stuInfo = LoginControl.getLoginInfo().getMemberId();

		Student stu = sDAO.selectId(stuInfo);

		List<Regi> list = rDAO.selectOne(stu.getStudentName());

		if (list.size() == 0) {
			System.out.println("");
			System.out.println("등록한 강의가 없습니다.");
			System.out.println();
			System.out.println("마감이 빠른 해커스 인기강좌!");
			System.out.println("등록을 서둘러 주세요!:)");
			return;
		} else {
			for (Regi regi : list) {
				System.out.println(regi);
			}
		}
	}


	// 공지사항만 보기

	public void checkNoti() {

		List<Notice> list = nDAO.selectBoardNoti();

		List<Integer> plist = new ArrayList<>();

		// 게시물 번호를 모으는 리스트를 만들어 해당 게시판 내용만 보이게 구분
		System.out.println();
		for (Notice notice : list) {
			System.out.println(notice);
			plist.add(notice.getNotiNum());
		}
		System.out.println();
		int number = 0;
		System.out.println();
		System.out.print("게시물번호 > ");
		number = menuSelect();

		Notice notice = nDAO.selectOne(number);

		if (notice == null) {
			System.out.println("");
			System.out.println("게시글 번호를 다시 확인해주세요.");
			System.out.println("");
			return;

		} else if (!plist.contains(number)) {
			// 입력받은 값이 리스트 안에 있는지
			System.out.println("게시글 번호를 다시 확인해주세요.");
			return;
		} else {
			System.out.println();
			System.out.println("제목 : " + notice.getNotiTitle());
			System.out.println("내용 : " + notice.getNotiContent());

		}
		
		//공지글 확인 후, 다음 메뉴가 바로 뜨지 않게 3초 정도 sleep 되도록 설정
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// 반별게시판만 보기 -> 수강신청 완료한 사람만 이용 가능하도록 체크

	public void checkClassNoti() {

		if (check == 0) {
			
			System.out.println("");
			System.out.println("반별게시판은 수강생만 이용가능합니다.");
			System.out.println("수강신청 후 이용해주세요♥ ");
			System.out.println("");
			return;

		} else {

			List<Notice> list = nDAO.selectClassNoti();

			List<Integer> plist = new ArrayList<>();
			
			System.out.println();
			for (Notice notice : list) {
				System.out.println(notice);
				plist.add(notice.getNotiNum());
			}

			System.out.println();
			System.out.print("게시물번호 > ");

			int number = menuSelect();
			Notice notice = nDAO.selectOne(number);

			if (notice == null) {
				System.out.println("");
				System.out.println("게시글 번호를 다시 확인해주세요.");
				System.out.println("");
				return;

			} else if (!plist.contains(number)) {
				// 입력받은 값이 리스트 안에 있는지
				System.out.println("");
				System.out.println("게시글 번호를 다시 확인해주세요.");
				System.out.println("");

			} else {
				System.out.println("제목 : " + notice.getNotiTitle());
				System.out.println("내용 : " + notice.getNotiContent());
			}
		}
		
		//공지글 확인 후, 다음 메뉴가 바로 뜨지 않게 3초 정도 sleep 되도록 설정
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}