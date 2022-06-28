package com.hackers.app.notice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hackers.app.common.DAO;

public class NoticeDAO extends DAO {

	// 싱글톤

	private static NoticeDAO noticeDAO = null;

	private NoticeDAO() {
	}

	public static NoticeDAO getInstance() {
		if (noticeDAO == null) {
			noticeDAO = new NoticeDAO();
		}
		return noticeDAO;
	}

	// insert

	public void insert(Notice notice) {
		try {
			connect();
			String sql = "INSERT INTO notice VALUES (hac_notice_seq.nextVal,?,?,?)";

			pstmt = conn.prepareStatement(sql);


			pstmt.setInt(1, notice.getNotiType());
			pstmt.setString(2, notice.getNotiTitle());
			pstmt.setString(3, notice.getNotiContent());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("게시글 등록이 완료되었습니다.");
			} else {
				System.out.println("게시글 등록이 완료되지 않았습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// update 게시글 수정

	public void update(Notice notice) {
		try {
			connect();
			String sql = "UPDATE notice SET noti_content =? WHERE noti_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNotiContent());
			pstmt.setInt(2, notice.getNotiNum());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("게시글 수정이 완료되었습니다.");
			} else {
				System.out.println("게시글 수정이 완료되지 않았습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// delete 게시글 삭제

	public void delete(int notiNum) {
		try {
			connect();
			String sql = "DELETE FROM notice WHERE noti_num = '" + notiNum + "'";
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);

			if (result > 0) {
				System.out.println("게시글 삭제가 완료되었습니다.");
			} else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	// 게시글 전체 조회

	public List<Notice> selectAll() {
		List<Notice> list = new ArrayList<>();

		try {
			connect();
			String sql = "SELECT * FROM notice ORDER BY 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Notice notice = new Notice();
				notice.setNotiNum(rs.getInt("noti_num"));
				notice.setNotiType(rs.getInt("noti_type"));
				notice.setNotiTitle(rs.getString("noti_title"));
				notice.setNotiContent(rs.getString("noti_content"));

				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//게시글 부분 조회 - 0: 공지사항 1: 반별게시판
	
	public List<Notice> selectBoardNoti() {
		List<Notice> list = new ArrayList<>();

		try {
			connect();
			String sql = "SELECT * FROM notice WHERE noti_type = 0 ORDER BY 1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Notice notice = new Notice();
				notice.setNotiNum(rs.getInt("noti_num"));
				notice.setNotiType(rs.getInt("noti_type"));
				notice.setNotiTitle(rs.getString("noti_title"));
				notice.setNotiContent(rs.getString("noti_content"));

				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//게시글 부분 조회 - 0: 공지사항 1: 반별게시판
	
		public List<Notice> selectClassNoti() {
			List<Notice> list = new ArrayList<>();

			try {
				connect();
				String sql = "SELECT * FROM notice WHERE noti_type = 1 ORDER BY 1";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					Notice notice = new Notice();
					notice.setNotiNum(rs.getInt("noti_num"));
					notice.setNotiType(rs.getInt("noti_type"));
					notice.setNotiTitle(rs.getString("noti_title"));
					notice.setNotiContent(rs.getString("noti_content"));

					list.add(notice);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
		}
		
	
	//단건조회 - 게시글 번호
	
	public Notice selectOne (int notiNum) {
		Notice notice = null;
		
		try {
			connect();
			String sql = "SELECT * FROM notice WHERE noti_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notiNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new Notice();
				
				notice.setNotiNum(rs.getInt("noti_num"));
				notice.setNotiType(rs.getInt("noti_type"));
				notice.setNotiTitle(rs.getString("noti_title"));
				notice.setNotiContent(rs.getString("noti_content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return notice;
	}

}
