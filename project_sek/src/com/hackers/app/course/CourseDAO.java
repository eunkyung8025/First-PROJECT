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

		
	//insert (강의개설)
	
	public void insert(Course course) {
		try {
			connect();
			String sql = "INSERT INTO courses VALUES(hac_class_seq.nextVal,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course.getClassSchedule());
			pstmt.setString(2, course.getClassTeacher());
			pstmt.setString(3, course.getClassName());
			pstmt.setInt(4, course.getCapacity());
			pstmt.setInt(5, course.getOccupied());
			
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
	
	//강의 전체조회 : 리스트에 담을 때 학사일정 별 노출 구분하기 위해 두 개의 메소드 활용 
	
	public List <Course> selectAll1() {
		List<Course> list = new ArrayList<> ();
		
		try {
			connect();
			String sql = "SELECT * FROM courses WHERE class_schedule = '22.07' ORDER BY 4";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Course course = new Course();
				course.setClassNum(rs.getInt("class_num"));
				course.setClassSchedule(rs.getString("class_schedule"));
				course.setClassTeacher(rs.getString("class_teacher"));
				course.setClassName(rs.getString("class_name"));
				course.setCapacity(rs.getInt("capacity"));
				course.setOccupied(rs.getInt("occupied"));

				list.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//강의 전체조회 : 리스트에 담을 때 학사일정 별 노출 구분하기 위해 두 개의 메소드 활용 
	
		public List <Course> selectAll2() {
			List<Course> list = new ArrayList<> ();
			
			try {
				connect();
				String sql = "SELECT * FROM courses WHERE class_schedule = '22.08' ORDER BY 4";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Course course = new Course();
					course.setClassNum(rs.getInt("class_num"));
					course.setClassSchedule(rs.getString("class_schedule"));
					course.setClassTeacher(rs.getString("class_teacher"));
					course.setClassName(rs.getString("class_name"));
					course.setCapacity(rs.getInt("capacity"));
					course.setOccupied(rs.getInt("occupied"));

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
				course.setCapacity(rs.getInt("capacity"));
				course.setOccupied(rs.getInt("occupied"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return course;
	}
	
	//현재인원을 업데이트 해주는 기능
	
	public void updateOccupy (Course course) {
		
		try {
			connect();
			String sql = "UPDATE courses SET occupied = ? WHERE class_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, course.getOccupied());
			pstmt.setInt(2, course.getClassNum());
			
			rs = pstmt.executeQuery();
			
			rs.next();
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		
	}
	
}


