package com.hackers.app.course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hackers.app.common.DAO;

public class CourseDAO extends DAO {
	
	//싱글톤
	
	private static CourseDAO courseDAO = null;
	
	private CourseDAO () {}
	
	public static CourseDAO getInstance() {
		if( courseDAO ==null) {
			courseDAO = new CourseDAO();
		}
		
		return courseDAO;
	}

	
	//class_num NUMBER(10),
	//class_schedule VARCHAR2(100),
	//class_teacher VARCHAR2(100),
	//class_name VARCHAR2(100)
	
	
	//insert (강의개설)
	
	public void insert(Course course) {
		try {
			connect();
			String sql = "INSERT INTO courses VALUES(hac_class_seq.nextVal,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course.getClassSchedule());
			pstmt.setString(2, course.getClassTeacher());
			pstmt.setString(3, course.getClassName());
			
			int result = pstmt.executeUpdate();
			if(result >0) {
				System.out.println("강의개설이 완료되었습니다.");
			} else {
				System.out.println("강의개설이 완료되지 않았습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			disconnect();
		}
	}
	
    //update - 강의명 업데이트
	
	public void updateInfo(Course course) {
		try {
			
			connect();
			String sql = "UPDATE courses SET class_name=? WHERE class_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString (1,course.getClassName());
			pstmt.setInt(2, course.getClassNum());
			
			int result = pstmt.executeUpdate();
			if(result >0) {
				System.out.println("강의명 수정이 완료되었습니다.");
			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
	}
	
	
	//delete (강의삭제)
	
	public void delete (int classNum) {
		try { 
			connect();
			String sql = "DELETE FROM courses WHERE class_num = '"+classNum+"'";
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result>0) {
				System.out.println("강의삭제가 완료되었습니다.");
			} else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
 		} finally {
 			disconnect();
 		}
	}
	
	//강의 전체조회
	
	public List <Course> selectAll() {
		List<Course> list = new ArrayList<> ();
		
		try {
			connect();
			String sql = "SELECT * FROM courses ORDER BY 3";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Course course = new Course();
				course.setClassNum(rs.getInt("class_num"));
				course.setClassSchedule(rs.getString("class_schedule"));
				course.setClassTeacher(rs.getString("class_teacher"));
				course.setClassName(rs.getString("class_name"));

				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//강의 단건조회
	
	public Course selectOne(int classNum) {
		Course course = null;
		
		try {
			connect();
			String sql = "SELECT * FROM courses WHERE class_num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classNum);
			
			rs= pstmt.executeQuery();
			
			if(rs.next())  {
				course = new Course();
				
				course.setClassNum(rs.getInt("class_num"));
				course.setClassSchedule(rs.getString("class_schedule"));
				course.setClassTeacher(rs.getString("class_teacher"));
				course.setClassName(rs.getString("class_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return course;
	}
	
	
}


