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
	
	//등록 
	
	public void insert (Regi regi) {
		try {
			connect();
			String sql = "INSERT INTO registrations VALUES (?,?,?,?,?,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, regi.getStudentNum());
			pstmt.setString(2, regi.getStudentName());
			pstmt.setInt(3, regi.getClassNum());
			pstmt.setString(4, regi.getClassSchedule());
			pstmt.setString(5, regi.getClassName());
			pstmt.setInt(6, regi.getAccommodate());
			pstmt.setInt(7, regi.getOccupy());

			
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
	
	//삭제
	
	public void delete (int classNum) {
		try {
			connect();
			String sql = "DELETE FROM registrations WHERE class_num = '"+classNum+"'";
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result >0) {
				System.out.println("수강취소가 완료되었습니다.");
			} else {
				System.out.println("정상적으로 취소되지 않았습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//전체조회 
	
	public List <Regi> selectAll() {
		List<Regi> list = new ArrayList<> ();
		try {
			connect();
			String sql = "SELECT * FROM registrations ORDER BY 1";
		    stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Regi regi = new Regi();
				
				regi.setStudentNum(rs.getInt("student_num"));
				regi.setStudentName(rs.getString("student_name"));
				regi.setClassNum(rs.getInt("class_num"));
				regi.setClassSchedule(rs.getString("class_schedule"));
				regi.setClassName(rs.getString("class_name"));
				regi.setRegiDate(rs.getDate("regi_date"));
				regi.setAccommodate(rs.getInt("accommodate"));
				regi.setOccupy(rs.getInt("occupy"));
				
				
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
				regi.setStudentNum(rs.getInt("student_num"));
				regi.setStudentName(rs.getString("student_name"));
				regi.setClassNum(rs.getInt("class_num"));
				regi.setClassSchedule(rs.getString("class_schedule"));
				regi.setClassName(rs.getString("class_name"));
				regi.setRegiDate(rs.getDate("regi_date"));
				regi.setAccommodate(rs.getInt("accommodate"));
				regi.setOccupy(rs.getInt("occupy"));
				
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
				regi.setStudentNum(rs.getInt("student_num"));
				regi.setClassName(rs.getString("student_name"));
				regi.setClassNum(rs.getInt("class_num"));
				regi.setClassSchedule(rs.getString("class_schedule"));
				regi.setClassName(rs.getString("class_name"));
				regi.setRegiDate(rs.getDate("regi_date"));
				regi.setAccommodate(rs.getInt("accommodate"));
				regi.setOccupy(rs.getInt("occupy"));
				
				list.add(regi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		} return list;
	}



	

}
 