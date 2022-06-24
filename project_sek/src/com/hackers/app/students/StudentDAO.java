package com.hackers.app.students;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.hackers.app.common.DAO;
import oracle.sql.DATE;

public class StudentDAO extends DAO {
	
	//싱글톤
	
	private static StudentDAO studentDAO = null;
	
	private StudentDAO() {}
	
	public static StudentDAO getInstance() {
		if (studentDAO ==null) {
			studentDAO = new StudentDAO ();
		}
		return studentDAO;
	}
	
	////////////////////CRUD/////////////////////////
	
    //studentNum/studentName/studentGender
	//studentBirth/studentAddress/ studentPhone, classNum;
	
	//insert
	
	public void insert(Student student) {
		try {
			connect();
			String sql = "INSERT INTO students VALUES(students_num_seq.nextval,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getStudentName());
			pstmt.setString(2, student.getStudentGender());
			pstmt.setString(3, student.getStudentBirth());
			pstmt.setString(4, student.getStudentAddress());
			pstmt.setString(5, student.getStudentPhone());
			
			int result = pstmt.executeUpdate();
			
			if (result >0) {
				System.out.println("회원정보 등록완료");
			} else {
				System.out.println("등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	//update - 주소, 연락처 (management에서 구현?)

	public void updateInfo (Student student) {
		
		try {
			connect();
			String sql = "UPDATE students SET student_address='?', student_phone=?"
					+ "WHERE student_name =?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getStudentAddress());
			pstmt.setString(2, student.getStudentPhone());
			pstmt.setString(3, student.getStudentName());
			
			int result = pstmt.executeUpdate();
			if(result >0) {
				System.out.println("회원정보 수정이 완료되었습니다.");
			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	//delete - 
	public void delete (String studentName)  {
		try {
			connect();
			String sql = "DELETE FROM students WHERE student_name = '"+studentName+"'";
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result >0) {
				System.out.println("회원정보가 삭제되었습니다.");
			} else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	//단건조회 - 이름
	
	public Student selectOne(String studentName) {
		
		Student student = null;
		
		try {
			connect();
			String sql = "SELECT * FROM students WHERE student_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				student = new Student();
				student.setStudentNum(rs.getInt("student_num"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentPhone(rs.getString("student_phone"));
				student.setStudentGender(rs.getString("student_gender"));
				student.setStudentBirth(rs.getString("student_birth"));
				student.setStudentAddress(rs.getString("student_address"));

			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return student;
	}
	
	//전체조회
	
	public List<Student> selectAll() {
		List<Student> list = new ArrayList<>();
		
		try {
			connect();
			String sql = "SELECT * FROM students ORDER BY 2";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student student = new Student();
				
				student.setStudentNum(rs.getInt("student_num"));
				student.setStudentName(rs.getString("student_name"));
				student.setStudentPhone(rs.getString("student_phone"));
				student.setStudentGender(rs.getString("student_gender"));
				student.setStudentBirth(rs.getString("student_birth"));
				student.setStudentAddress(rs.getString("student_address"));
				
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			disconnect();
		}
		return list;
	}

}
