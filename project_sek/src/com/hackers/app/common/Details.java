package com.hackers.app.common;

public class Details {
	/*
	 * 
	 * private :해당클래스에서만 사용 가능 
	 * protected : 해당클래스와 해당클래스를 상속받는 자식 클래스들만 가능 
	 * default : 같은 패키지 내에서만 사용 가능 
	 * public : 모두 사용 가능
	 
	 
★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★               


//1. 테이블 생성
 
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

CREATE TABLE notice(
noti_num NUMBER PRIMARY KEY,
noti_type NUMBER,
noti_title VARCHAR2(1000),
noti_content VARCHAR2(1000)
);

//2.외래키 설정

ALTER TABLE registrations
 ADD CONSTRAINT stu_fk_regi
 FOREIGN KEY (member_id)
 REFERENCES students (member_id);
 
 
ALTER TABLE registrations
 ADD CONSTRAINT cou_fk_regi
 FOREIGN KEY (class_num)
 REFERENCES courses (class_num);
drop table courses purge;



//3. course 클래스 시퀀스 생성
CREATE SEQUENCE hac_class_seq
INCREMENT BY 11                    --10씩 증가..?
                START WITH 10110          --250에서 9999까지,,?
                MAXVALUE 50000
                NOCACHE                     --NOCACHE는 기본값 20
                NOCYCLE;  
                
                
CREATE SEQUENCE hac_notice_seq
INCREMENT BY 1 
START WITH 1
MAXVALUE 100
NOCACHE 
NOCYCLE;
          
                 
//4. students > 관리자 아이디 생성
insert into students values ('admin','admin',0,'관리자','여',000000,'NONE',0000000000);                
                
★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★      
         
--시퀀스 제거                
DROP SEQUENCE students_num_seq;                
                
--테이블 개별 제약조건 수정                
ALTER TABLE students
Modify student_num PRIMARY KEY;
ALTER TABLE students
Modify student_name VARCHAR2(100) NOT NULL;

--제약조건 확인
select constraint_name, constraint_type, search_condition
from user_constraints
where table_name ='REGISTRATIONS';

	System.out.println(" 1등에게 들어야 한 번에 끝낸다! " );
		System.out.println("     외국어학원 1위 해커스    "  );
		System.out.println(" @======================@ ");
		System.out.println("       ||        ||        ");
		System.out.println("       ||        ||        ");
		System.out.println("       ||        ||        ");
		System.out.println("       ||        ||        ");
		System.out.println("       ||        ||        ");
		System.out.println("       ||        ||        ");
		System.out.println("--------------------------");
		System.out.println("1.수강신청 2.회원정보 수정 ");
		System.out.println("3.수강신청 내역조회 9.back");
		System.out.println("--------------------------");
		
		System.out.println(" @======@ ");
		System.out.println("         ⅰ ⅰ     ⅰ ⅰ     HACKERS  ");
		System.out.println("         ⅰ ⅰ     ⅰ ⅰ     ACADEMIA  ");

		System.out.println(" @======@ ");
		System.out.println(" @══════@ ");
		System.out.println("   ║  ║  HACKERS");
		System.out.println("   ║  ║   ACADEMIA");

update courses set occupied = 7 where class_num=10231; 
update courses set occupied = 10 where class_num=10242; 

commit;

insert into students values ('stuid1','stuid1',1,'김민준','남',950415,'대구시 서구',01067814785);                
insert into students values ('stuid2','stuid2',1,'박서준','남',930315,'대구시 달서구',01061574785);                
insert into students values ('stuid3','stuid3',1,'이도윤','남',960401,'대구시 수성구',01013874785);                
insert into students values ('stuid4','stuid4',1,'김예준','여',980105,'대구시 수성구',01068941385);                
insert into students values ('stuid5','stuid5',1,'박하준','남',991205,'대구시 달서구',01048753654);                
insert into students values ('stuid6','stuid6',1,'서주원','남',960107,'대구시 서구',01041150101);                
insert into students values ('stuid7','stuid7',1,'김서연','여',990105,'서울시 서초구',01047840101);                
insert into students values ('stuid8','stuid8',1,'최지우','여',990105,'서울시 서초구',01047840101);                
insert into students values ('stuid9','stuid9',1,'윤서현','여',951109,'서울시 강서구',01047667401);                
insert into students values ('stuid10','stuid10',1,'임하은','여',930101,'대구시 동구',01015787401);  
insert into students values ('jimin','jimin',1,'홍지민','여',940121,'대구시 중구',01017451454);
insert into students values ('jiyoo','jiyoo',1,'김지유','여',971005,'대구시 북구',01048711001);
insert into students values ('hyunwoo','hyunwoo',1,'이현우','남',990201,'대구시 달서구',01015741001);
insert into students values ('eunwoo','eunwoo',1,'배은우','남',960115,'대구시 달서구',0101110 1001);

insert into courses values ( hac_class_seq.NextVal , '22.07' , '최석원' , '토익 550+ 입문 점수보장반 (14시)' , 10, 0);
insert into courses values ( hac_class_seq.NextVal , '22.07' , '최석원' , '토익 750+ 정규 점수보장반 (16시)' , 10, 0);



	 */

}
