package com.hackers.app.register;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hackers.app.common.DAO;

public class RegiDAO extends DAO {
	
	private static RegiDAO rDAO = null;
	private RegiDAO () {}
	public static RegiDAO getInstance() {
		if(rDAO ==null) {
			rDAO = new RegiDAO();
		}
		return rDAO;
	}
	
	//등록 1 - 관리자모드
	
	public void insert (Regi regi) {
		try {
			connect();
			String sql = "INSERT INTO registrations (member_id, student_name, class_num, "
					+ "class_name, class_schedule, regi_date, capacity, occupied) VALUES "
					+ " (?,?,?,?,?,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, regi.getMemberId());
			pstmt.setString(2, regi.getStudentName());
			pstmt.setInt(3, regi.getClassNum());
			pstmt.setString(4, regi.getClassName());
			pstmt.setString(5, regi.getClassSchedule());
			
			pstmt.setInt(6, regi.getCapacity());
			pstmt.setInt(7, regi.getOccupied());

			
			int result = pstmt.executeUpdate();
			if(result >0) {
				System.out.println("수강신청이 완료되었습니다.");
			} else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			} 
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//등록 2 - 유저모드 
	//수강 신청 완료 후 안내 문구를 구분하기 위해 별도의 메소드로 사용
	public void insert2 (Regi regi) {
		try {
			connect();
			String sql = "INSERT INTO registrations (member_id, student_name, class_num, "
					+ "class_name, class_schedule, regi_date, capacity, occupied) VALUES "
					+ " (?,?,?,?,?,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, regi.getMemberId());
			pstmt.setString(2, regi.getStudentName());
			pstmt.setInt(3, regi.getClassNum());
			pstmt.setString(4, regi.getClassName());
			pstmt.setString(5, regi.getClassSchedule());
			
			pstmt.setInt(6, regi.getCapacity());
			pstmt.setInt(7, regi.getOccupied());

			
			int result = pstmt.executeUpdate();
			if(result >0) {
				System.out.println("수강신청이 완료되었습니다.");
				System.out.println(regi.getStudentName()+"님 1위 해커스와 최단기 목표달성!" );
			} else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			} 
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
	//삭제
	
	public void delete (int classNum) {
		try {
			connect();
			String sql = "DELETE FROM registrations WHERE class_num = '"+classNum+"'";
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result >0) {
				System.out.println("수강 삭제가 완료되었습니다.");
			} else {
				System.out.println("정상적으로 취소되지 않았습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//전체조회 : 학사일정에 따른 출력문 수정 (selectAll 1: 22.07)
	
	public List <Regi> selectAll1() {
		List<Regi> list = new ArrayList<> ();
		try {
			connect();
			String sql = "SELECT * FROM registrations WHERE class_schedule = '22.07' ORDER BY 1";
		    stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Regi regi = new Regi();
				
				regi.setMemberId(rs.getString("member_id"));
				regi.setStudentName(rs.getString("student_name"));
				regi.setClassNum(rs.getInt("class_num"));
				regi.setClassName(rs.getString("class_name"));
				regi.setClassSchedule(rs.getString("class_schedule"));
				regi.setRegiDate(rs.getDate("regi_date"));
				regi.setCapacity(rs.getInt("capacity"));
				regi.setOccupied(rs.getInt("occupied"));
				
				list.add(regi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		} return list;
	}
	
	//전체조회 : 학사일정에 따른 출력문 수정 (selectAll 2: 22.08)
	
	public List <Regi> selectAll2() {
		List<Regi> list = new ArrayList<> ();
		try {
			connect();
			String sql = "SELECT * FROM registrations WHERE class_schedule = '22.08' ORDER BY 1";
		    stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Regi regi = new Regi();
				
				regi.setMemberId(rs.getString("member_id"));
				regi.setStudentName(rs.getString("student_name"));
				regi.setClassNum(rs.getInt("class_num"));
				regi.setClassName(rs.getString("class_name"));
				regi.setClassSchedule(rs.getString("class_schedule"));
				regi.setRegiDate(rs.getDate("regi_date"));
				regi.setCapacity(rs.getInt("capacity"));
				regi.setOccupied(rs.getInt("occupied"));
				
				list.add(regi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		} return list;
	}
	
	//단건조회 - 수강생이름으로 검색 
	
	public List <Regi> selectOne(String studentName) {
		List<Regi> list = new ArrayList<> ();
		try {
			connect();
			String sql = "SELECT * FROM registrations WHERE student_name = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentName);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Regi regi = new Regi();
				regi.setMemberId(rs.getString("member_id"));
				regi.setStudentName(rs.getString("student_name"));
				regi.setClassNum(rs.getInt("class_num"));
				regi.setClassName(rs.getString("class_name"));
				regi.setClassSchedule(rs.getString("class_schedule"));
				regi.setRegiDate(rs.getDate("regi_date"));
				regi.setCapacity(rs.getInt("capacity"));
				regi.setOccupied(rs.getInt("occupied"));
				
				list.add(regi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		} return list;
	}
	
	//강의번호 조회
	
	public List <Regi> selectClassNum(int classNum) {
		List<Regi> list = new ArrayList<> ();
		try {
			connect();
			String sql = "SELECT * FROM registrations WHERE class_num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNum);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Regi regi = new Regi();
				regi.setMemberId(rs.getString("member_id"));
				regi.setStudentName(rs.getString("student_name"));
				regi.setClassNum(rs.getInt("class_num"));
				regi.setClassName(rs.getString("class_name"));
				regi.setClassSchedule(rs.getString("class_schedule"));
				regi.setRegiDate(rs.getDate("regi_date"));
				regi.setCapacity(rs.getInt("capacity"));
				regi.setOccupied(rs.getInt("occupied"));
				
				list.add(regi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		} return list;
	}
	
	
	public List <Regi> selectOne(String studentName, int classNum) {
		List<Regi> list = new ArrayList<> ();
		try {
			connect();
			String sql = "SELECT * FROM registrations WHERE student_name = ? and class_num =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentName);
			pstmt.setInt(2, classNum);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Regi regi = new Regi();
				regi.setMemberId(rs.getString("member_id"));
				regi.setStudentName(rs.getString("student_name"));
				regi.setClassNum(rs.getInt("class_num"));
				regi.setClassName(rs.getString("class_name"));
				regi.setClassSchedule(rs.getString("class_schedule"));
				regi.setRegiDate(rs.getDate("regi_date"));
				regi.setCapacity(rs.getInt("capacity"));
				regi.setOccupied(rs.getInt("occupied"));
				
				list.add(regi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		} return list;
	}



	

}
 