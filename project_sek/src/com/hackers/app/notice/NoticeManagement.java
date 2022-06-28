package com.hackers.app.notice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;
import com.hackers.app.course.CourseDAO;
import com.hackers.app.register.Regi;

public class NoticeManagement {

	Scanner sc = new Scanner(System.in);
	protected NoticeDAO nDAO = NoticeDAO.getInstance();

	InputStream is = System.in;
	Reader reader = new InputStreamReader(is);
	BufferedReader br = new BufferedReader(reader);

	public NoticeManagement() {
	}

	public void run() {

		while (true) {
			menuPrint();

			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 1.공지글 작성
				newNotice();
			} else if (menuNo == 2) {
				// 2.공지글 수정
				updateNoti();
			} else if (menuNo == 3) {
				// 3.게시글 삭제
				deleteNoti();

			} else if (menuNo == 4) {
				// 4.게시글 보기
				selectAllNoti();
				break;
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
		System.out.println("------- NOTICE EDIT MODE -------");
		System.out.println();
		System.out.println("1.공지글 작성 2.공지글 수정 3.공지글 삭제     ");
		System.out.println("4.전체게시글 확인   9.back          ");
		System.out.println("--------------------------------");
	}

	protected int menuSelect() {
		System.out.print("SELECT MEMU > ");
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

	// 1.공지글 작성

	public void newNotice() {

		Notice noti = new Notice();

		System.out.println("구분 (0:공지사항/1:반별게시판) ");
		noti.setNotiType(Integer.parseInt(sc.nextLine()));

		System.out.print("게시글 제목 > ");
		noti.setNotiTitle(sc.nextLine());

		System.out.println("게시글 내용 > ");
		String str = "";
		while (true) {
			String lineStr;
			try {
				lineStr = br.readLine();
				if (lineStr.equals("q") || lineStr.equals("quit"))
					break;
//			noti.setNotiContent(sc.nextLine());
				str = str + lineStr + "\n";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// System.out.println(str);
		noti.setNotiContent(str);
		nDAO.insert(noti);
	}

	// 2.공지 수정

	public void updateNoti() {

		int notiNum = selectNotiNum();
		Notice notice = nDAO.selectOne(notiNum);

		if (notice == null) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}

		notice = inputUpdateInfo(notice);
		nDAO.update(notice);

	}

	public int selectNotiNum() {
		int notiNum = 0;
		try {
			System.out.println("게시글번호 > ");
			notiNum = Integer.parseInt(sc.nextLine());

		} catch (NumberFormatException e) {
			e.printStackTrace();

		}
		return notiNum;

	}

	public Notice inputUpdateInfo(Notice notice) {
		System.out.println("기존>" + notice.getNotiContent());

		System.out.println("수정할 내용 입력>");
		String content = sc.nextLine();
		notice.setNotiContent(content);
		return notice;
	}

	// 3.게시글 삭제

	public void deleteNoti() {
		int notiNum = selectNotiNum();
		Notice notice = nDAO.selectOne(notiNum);

		if (notice == null) {
			System.out.println("존재하지 않는 게시글입니다.");
			return;

		}
		nDAO.delete(notiNum);
	}

	// 게시글 리스트 보기

	public void selectAllNoti() {
		
		System.out.println();
		List<Notice> list = nDAO.selectAll();

		for (Notice not : list) {
			System.out.println(not);
		}
		showContent();
		

	}

	// 유저 -> 공지사항만 보기

	public void checkNoti() {
		
		List<Notice> list = nDAO.selectBoardNoti();

		for (Notice notice : list) {
			System.out.println(notice);
		}
		showContent();


	}

	// 반별게시판만 보기

	public void checkClassNoti() {
		List<Notice> list = nDAO.selectClassNoti();

		for (Notice notice : list) {
			System.out.println(notice);
		}
		showContent();
	}

	// 게시물 내용 보기

	public void showContent() {
		
		try {
		System.out.println();
		System.out.println("확인하고자 하는 게시물 번호를 입력하세요.");
		System.out.println("ENTER > ");
		int number = (Integer.parseInt(sc.nextLine()));
		Notice notice = nDAO.selectOne(number);
		
		if(notice ==null) {
			System.out.println("");
			System.out.println("게시글 번호를 다시 확인해주세요.");
			System.out.println("");
			return;

		} else {
			System.out.println();
			System.out.println("[제목] :"+notice.getNotiTitle());
			System.out.println(notice.getNotiContent());
			System.out.println();

		}
		
	} catch (NullPointerException e) {
		e.printStackTrace();
	}

}
}
