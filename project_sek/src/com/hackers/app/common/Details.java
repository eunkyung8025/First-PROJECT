package com.hackers.app.common;

public class Details {
	/*
	 * 
	 * private :해당클래스에서만 사용 가능 
	 * protected : 해당클래스와 해당클래스를 상속받는 자식 클래스들만 가능 
	 * default : 같은 패키지 내에서만 사용 가능 
	 * public : 모두 사용 가능

--student 클래스 시퀀스
CREATE SEQUENCE students_num_seq
INCREMENT BY 1                   --10씩 증가..?
                START WITH 1000         --250에서 9999까지,,?
                MAXVALUE 5000
                NOCACHE                     --NOCACHE는 기본값 20
                NOCYCLE;  

--course 클래스 시퀀스
CREATE SEQUENCE hac_class_seq
INCREMENT BY 11                    --10씩 증가..?
                START WITH 10110          --250에서 9999까지,,?
                MAXVALUE 50000
                NOCACHE                     --NOCACHE는 기본값 20
                NOCYCLE;  
                
DROP SEQUENCE students_num_seq;                
                
UPDATE courses SET class_name= WHERE class_num=10110;

ALTER TABLE students
Modify student_num PRIMARY KEY;
ALTER TABLE students
Modify student_name VARCHAR2(100) NOT NULL;

CREATE TABLE students (
	member_id VARCHAR2(100) PRIMARY KEY,
    member_password VARCHAR2(100),
    member_role VARCHAR2(100),
    student_name VARCHAR2(100) NOT NULL, 
    student_gender VARCHAR2(100) NOT NULL, 
    student_birth VARCHAR2(100) NOT NULL,             
    student_address VARCHAR2(100) NOT NULL,
    student_phone VARCHAR2(100) NOT NULL
    );
    
CREATE TABLE registrations (
	member_id VARCHAR2(100),
    student_name VARCHAR2(100),
    class_num NUMBER,
    class_schedule VARCHAR2(100) NOT NULL, 
    class_name VARCHAR2(100) NOT NULL,             
    regi_date DATE DEFAULT SYSDATE,
    capacity NUMBER,
    occupied NUMBER
);
    
 CREATE TABLE courses(
class_num NUMBER(10) PRIMARY KEY,
class_schedule VARCHAR2(100),
class_teacher VARCHAR2(100),
class_name VARCHAR2(100),
capacity NUMBER,
occupied NUMBER
);


ALTER TABLE registrations
 ADD CONSTRAINT stu_fk_regi
 FOREIGN KEY (member_id)
 REFERENCES students (member_id);
 
 
ALTER TABLE registrations
 ADD CONSTRAINT cou_fk_regi
 FOREIGN KEY (class_num)
 REFERENCES courses (class_num);
drop table courses purge;

DROP table students purge;

CREATE SEQUENCE hac_class_seq
INCREMENT BY 11                    --10씩 증가..?
                START WITH 10110          --250에서 9999까지,,?
                MAXVALUE 50000
                NOCACHE                     --NOCACHE는 기본값 20
                NOCYCLE;  



 ALTER TABLE students
 ADD CONSTRAINT stu_fk_course
 FOREIGN KEY (class_num)
 REFERENCES courses (class_num);
 
 select * from students;
 select * from registrations;
 desc registrations;
 select * from courses;
 DROP table registrations purge;
 DROP table courses purge;
 
 select constraint_name, constraint_type, search_condition
 from user_constraints
 where table_name ='REGISTRATIONS';
 
 
 INSERT INTO registrations VALUES (?,?,?,?,?,sysdate);
 SELECT * FROM registrations WHERE student_name ='신은경';
 
 commit;
 SELECT * FROM students WHERE student_name='신은경';
 SELECT * FROM registrations WHERE student_name ='신은경'; 
 
 select * from courses;
 
 update courses set occupied = 0;
 
	 */

}
