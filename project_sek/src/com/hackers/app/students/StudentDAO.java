package com.hackers.app.students;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.hackers.app.common.DAO;
import oracle.sql.DATE;

public class StudentDAO extends DAO {

	// 싱글톤

	private static StudentDAO studentDAO = null;

	private StudentDAO() {
	}

	public static StudentDAO getInstance() {
		if (studentDAO == null) {
			studentDAO = new StudentDAO();
		}
		return studentDAO;
	}

	//////////////////// CRUD/////////////////////////

	// insert

	public void insert(Student student) {
		try {
			connect();
			String sql = "INSERT INTO students VALUES(?,?, 1, ?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getMemberId());
			pstmt.setString(2, student.getMemberPassword());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getStudentGender());
			pstmt.setString(5, student.getStudentBirth());
			pstmt.setString(6, student.getStudentAddress());
			pstmt.setString(7, student.getStudentPhone());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println();
				System.out.println("회원가입이 완료되었습니다.");
				System.out.println();
				
			} else {
				System.out.println("등록되지 않았습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// update - 주소, 연락처 (management에서 구현?)

	public void updateInfo(Student student) {

		try {
			connect();
			String sql = "UPDATE students SET member_id =?, student_address=?, student_phone=? WHERE student_name =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getMemberId());
			pstmt.setString(2, student.getStudentAddress());
			pstmt.setString(3, student.getStudentPhone());
			pstmt.setString(4, student.getStudentName());


			int result = pstmt.executeUpdate();
			if (result > 0) {
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
	
	//회원정보 수정 - 유저화면 (비밀번호, 주소지 수정 가능)
	
	public void updateInfo2(Student student) {

		try {
			connect();
			String sql = "UPDATE students SET member_password =?, student_address=? WHERE member_id =?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getMemberPassword());
			pstmt.setString(2, student.getStudentAddress());
			pstmt.setString(3, student.getMemberId());

			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("회원정보 수정이 완료되었습니다.");
				System.out.println();
			} else {
				System.out.println("정상적으로 수정되지 않았습니다.");
				System.out.println();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// delete -
	public void delete(String memberName) {
		try {
			connect();
			String sql = "DELETE FROM students WHERE student_name = '" + memberName + "'";
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);

			if (result > 0) {
				System.out.println("회원정보가 삭제되었습니다.");
			} else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	// 단건조회 - 이름

	public Student selectOne(String studentName) {

		Student student = null;

		try {
			connect();
			String sql = "SELECT * FROM students WHERE student_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, studentName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				student = new Student();
				student.setMemberId(rs.getString("member_id"));;
				student.setMemberPassword(rs.getString("member_password"));
				student.setMemberRole(rs.getInt("member_role"));
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
	
	// 단건조회 - 아이디

		public Student selectId(String memberId) {

			Student student = null;

			try {
				connect();
				String sql = "SELECT * FROM students WHERE member_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberId);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					student = new Student();
					student.setMemberId(rs.getString("member_id"));;
					student.setMemberPassword(rs.getString("member_password"));
					student.setMemberRole(rs.getInt("member_role"));
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

	// 전체조회

	public List<Student> selectAll() {
		List<Student> list = new ArrayList<>();

		try {
			connect();
			String sql = "SELECT * FROM students ORDER BY 2";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student student = new Student();

				student.setMemberId(rs.getString("member_id"));;
				student.setMemberPassword(rs.getString("member_password"));
				student.setMemberRole(rs.getInt("member_role"));
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

	// 로그인 시도

	public Student loginTry(Student student) {
		Student loginInfo = null;

		try {
			connect();

			String sql = "SELECT * FROM students WHERE member_id = '" + student.getMemberId() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				// 아이디 존재
				if (rs.getString("member_password").equals(student.getMemberPassword())) {
					loginInfo = new Student();
					loginInfo.setMemberId(rs.getString("member_id"));
					loginInfo.setMemberPassword(rs.getString("member_password"));
					loginInfo.setMemberRole(rs.getInt("member_role"));
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			} else {
				System.out.println("아이디가 존재하지 않습니다.");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return loginInfo;
	}
	
	

}
