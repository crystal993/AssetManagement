/**
 * 
 */
package com.work.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.work.exception.CommonException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Member;
import com.work.util.Utility;

/**
 * <pre>
 * 회원 DAO 클래스
 * 
 * ## Singleton Pattern
 * ## DAO Pattern
 * - 회원가입
 * 
 * - 로그인
 * 
 * - 아이디 찾기1 - 휴대폰 
 * - 아이디 찾기2 - 이메일
 * 
 * - 비밀번호 찾기1 - 휴대폰 
 * - 비밀번호 찾기2- 이메일
 * 
 * - 내 정보 조회 
 * - 내 정보 변경1 - 휴대폰 변경
 * - 내 정보 변경2 - 이메일 변경
 * - 내 정보 변경3 - 비밀번호 변경
 * 
 * - 회원 탈퇴
 * 
 * - 전체 회원 수 조회 
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class MemberDao {
	
	
	
	
	/**FactoryDao 객체 멤버변수 선언 및 할당 : 구현*/
	private FactoryDao factory = FactoryDao.getInstance();
	
	/**MemberDao instance 객체로 생성 */
	private static MemberDao instance = new MemberDao(); 
	
	/**
	 * <pre>
	 * private 생성자
	 * >> 수행문이 없어도 지우면 안된다.
	 * >> jvm에서 생성자가 없으니 자동으로 기본생성자(public) 생성
	 * >> singleton패턴 규칙이 깨진다.
	 * </pre>
	 */
	private MemberDao() {
		
	} 
	
	/**
	 * getInstance 인스턴스 반환메서드 
	 * @return MemberDao
	 */
	public static MemberDao getInstance() {
		return instance;
	}
	


	

	/**
	 * <pre>
	 * 회원 가입
	 * 날짜는 쿼리문에서 sysdate 오늘 날짜로 자동 저장
	 * </pre>
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @param email 이메일
	 * @return 등록되면 true, 아니면 false
	 */
	public boolean addMember(String memberId, String memberPw, String name, String mobile, String email) {
				//1. 
				ResultSet rs = null;
				PreparedStatement stmt = null;
				Connection conn = null;
				try {
					//2. db 서버 연결
					conn = factory.getConnection();

					

					String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, sysdate)";
					stmt = conn.prepareStatement(sql);
					
					// 3.
					stmt.setString(1, memberId);
					stmt.setString(2, memberPw);
					stmt.setString(3, name);
					stmt.setString(4, mobile);
					stmt.setString(5, email);

					
					//4. 통로이용 sql 실행 요청 - sql구문이 Read - Select일 경우에 사용
					// ResultSet rs = stmt.executeQuery();
					
					//4. 통로이용 sql 실행 요청 - sql구문이 C, U, D - insert, update, delete일 때 사용.			
					
					// 업데이트 되면 : 1 , 업데이트 되지 않으면 : 0이 되는데 
					// 업데이트된 행이 1개 이상이면 업데이트가 된것이므로 
					// if(rows > 0) 을 써줍니다.
					
					int rows = stmt.executeUpdate();
					if(rows > 0) {

						return true;
					}
					
					
					//return members;
					
				} catch (SQLException e) {
					System.out.println("[오류] 회원 가입");
					e.printStackTrace();
				} finally {
					
					//6. 자원해제 : finally 구문으로 변경 수정
					// 공장에게 위임
					factory.close(conn, stmt);
				}
				return false;
	}
	
	/**
	 * 로그인
	 * 2. db 서버 ★연결 : url, user, password  => Connection
	 * 3. 연결된 서버와 ★통로 개설 => Statement, PreparedStatement, CallableStatement
	 * 4. 통로이용 sql 실행 요청
	 * 5. 실행결과 처리
	 * 6. 자원해제
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 성공시 true, 실패하면 false
	 */
	public boolean login(String memberId, String memberPw) {
		
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		//conn = DriverManager.getConnection(url, user, password);
		conn = factory.getConnection();
		
		String sql = "select memberid from member where memberid = ? and memberPw = ?";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		stmt.setString(2, memberPw);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		if(rs.next()) {
			String memberid = rs.getString("memberid");
			return true;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 로그인");
			e.printStackTrace();
			
		} finally {
			
			factory.close(conn, stmt, rs);
		}
		
		return false;
	}
	
	
	/**
	 * 아이디 찾기1 - 휴대폰
	 * @param mobile 휴대폰
	 * @return 휴대폰 번호가 존재하면 해당 인스턴스의 아이디를 반환
	 * @throws CommonException
	 */
	public String findId1(String mobile) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		//1,2. 드라이버 로딩, db와 연결
		conn = factory.getConnection();
		
		String sql ="select memberid from member where mobile=?";
		
		// 3. 서버와 연결할 통로 생성
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, mobile);
		
		// 4. 통로이용. sql 구문 실행 요청
		rs = stmt.executeQuery();
		
		//5. 실행결과 처리
		if(rs.next()) {
			String memberId = rs.getString("memberid");
			return memberId;
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 아이디 찾기 오류");
			e.printStackTrace();
		
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt, rs);
		}

		return null;

	}	

	
	/**
	 * 아이디 찾기2 - 이메일
	 * @param email 이메일
	 * @return 이메일이 존재하면 해당 인스턴스의 아이디를 반환
	 * @throws CommonException
	 */
	public String findId2(String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			//1,2.드라이버 로딩, db서버 연결
			conn = factory.getConnection();
			
			//3.db서버와 연결할 통로 생성
			String sql = "select memberid from member where email=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, email);
			
			//4. 통로 이용, sql 구문 실행 요청 ; 역할
			rs = stmt.executeQuery();
			
			//5. 실행 요청 결과 처리
			if(rs.next()) {
				String memberid = rs.getString("memberid");
				return memberid;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 아이디 찾기 오류");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 비밀번호 찾기 01 - 휴대폰
	 * @param member_id 아이디
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @return memberPw 가져오면 1 반환, 아니면 0 반환
	 */
	public boolean findMemerPwByMobile(String member_id, String name, String mobile) {
		//1. 
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
				try {
					//2. db 서버 연결
					conn = factory.getConnection();

					

					String sql = "select memberpw from member where mobile=? and name=? and memberid=?";
					 stmt = conn.prepareStatement(sql);
					
					// 3.
					stmt.setString(1, mobile);
					stmt.setString(2, name);
					stmt.setString(3, member_id);
					
					//4. 통로이용 sql 실행 요청
					rs = stmt.executeQuery();
					
					
					//5. 
					while(rs.next()) {
						
						String memberPw = rs.getString("memberpw");
						return true;
						
					} 
					
					
					//return members;
					
				} catch (SQLException e) {
					System.out.println("[오류] 비밀번호 찾기");
					e.printStackTrace();
				} finally {
					
					//6. 자원해제 : finally 구문으로 변경 수정
					// 공장에게 위임
					factory.close(conn, stmt, rs);
				}
				
				
				return false;
	}
	
	
	
	/**
	 * 비밀번호 찾기 02 - 이메일
	 * @param member_id 아이디
	 * @param name 이름
	 * @param email 이메일 
	 * @return memberPw 가져오면 1 반환, 아니면 0 반환	
	 */
	public boolean findMemerPwByEmail(String member_id, String name, String email) {
		//1. 
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			//2. db 서버 연결
			conn = factory.getConnection();

			

			String sql = "select memberpw from member where email=? and name=? and memberid=?";
			stmt = conn.prepareStatement(sql);
			
			// 3.
			stmt.setString(1, email);
			stmt.setString(2, name);
			stmt.setString(3, member_id);
			
			//4. 통로이용 sql 실행 요청
			rs = stmt.executeQuery();
			
			
			//5. 
			while(rs.next()) {
				
				String memberPw = rs.getString("memberpw");
				return true;
				
			} 
			
			
			//return members;
			
		} catch (SQLException e) {
			System.out.println("[오류] 비밀번호 찾기");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt, rs);
		}
		
		
		return false;
	} 
	

	/**
	 * 회원 비밀번호 변경 2
	 * @param memberId
	 * @param tmpMemberPw
	 */
	public boolean setMemberPw(String memberId, String tmpMemberPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			// 1,2. 드라이버 로딩, db 서버와 연결
			conn = factory.getConnection();
			
			// 3. 서버와 연결할 통로 생성, 비밀번호 변경
			String sql = "update member set memberpw=? where memberid=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, tmpMemberPw);
			stmt.setString(2, memberId);
			
			//4. 통로이용 sql 실행 요청 - sql구문이 c,u,d일 경우에 사용 stmt.executeUpdate()
			int rows = stmt.executeUpdate();
			if(rows > 0) {
				System.out.println(tmpMemberPw);
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 비밀번호(임시발급) 변경");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		return true;
	}

	
	/**
	 * 내 정보 조회 / 회원 상세 조회 
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 존재하면 회원 반환, 아니면 null
	 */
	public Member getMember(String memberId, String memberPw) {
		
		
		ResultSet rs = null;
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			//2. db 서버 연결
			conn = factory.getConnection();

			
			// 회원상세/내정보조회
			String sql = "select * from member where memberid = ? and memberpw = ?";
			 stmt = conn.prepareStatement(sql);
			
			// 3. 주의사항 : ?에 매핑되는 값을 설정
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			
			//4. 통로이용 sql 실행 요청
			 rs = stmt.executeQuery();
			
			//5. 
			if(rs.next()) {
				
				String name = rs.getString("name");
				String mobile = rs.getString("mobile");
				String email = rs.getString("email");
				String entryDate = rs.getString("entrydate");

				Member dto = new Member(memberId, memberPw, name, mobile, email, entryDate);
				return dto;
			}
			
			
		} catch (SQLException e) {
			System.out.println("[오류] 내 정보 조회");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt, rs);
		}
		
		
		return null;
	}
	
	/**
	 * 내 정보 변경 - 휴대폰
	 * @param mobile 휴대폰
	 * @return 변경되면 true, 아니면 false
	 */	
	public boolean setMemberMobile(String memberId, String mobile) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		// 1. 드라이버 로딩, db 서버와 연결
		conn = factory.getConnection();
		
		try {
			// 2. 통로 생성 , 서버와 통로 연결
			String sql = "update member set mobile=? where memberId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,mobile);
			stmt.setString(2,memberId);
			
			//3. sql구문 실행 요청, 통로 이용
			// c u d => stmt.executeUpdate()
			int rows = stmt.executeUpdate();

			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 내 정보 변경");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}
	
	/**
	 * 내 정보 변경 - 이메일
	 * @param email 이메일
	 * @return 변경되면 true, 아니면 false
	 */
	public boolean setMemberEmail(String memberId, String email) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		// 1. 드라이버 로딩, db 서버와 연결
		conn = factory.getConnection();
		
		try {
			// 2. 통로 생성 , 서버와 통로 연결
			String sql = "update member set email = ? where memberId = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, memberId);
			
			//3. sql구문 실행 요청, 통로 이용
			// c u d => stmt.executeUpdate()
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 내 정보 변경");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}
	
	/**
	 * 회원 비밀번호 변경2
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param modifyMemberPw 바꿀 비밀번호
	 * @return 아이디와 비밀번호가 존재하면 true, 아니면 오류 
	 */
	public boolean setMemberPw(String memberId, String memberPw, String modifyMemberPw) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		// 1. 드라이버 로딩, db 서버와 연결
		conn = factory.getConnection();
		
		try {
			// 2. 통로 생성 , 서버와 통로 연결
			String sql = "update member set memberpw=? where memberid=? and memberPw=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, modifyMemberPw);
			stmt.setString(2, memberId);
			stmt.setString(3, memberPw);
			
			//3. sql구문 실행 요청, 통로 이용
			// c u d => stmt.executeUpdate()
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}
	
	
	/**
	 * 회원 탈퇴
	 * @param dto 회원
	 * @return 회원이 존재하면 탈퇴 후 true, 존재하지 않으면 오류
	 * @throws RecordNotFoundException
	 */
	public boolean removeMember(String memberId) { 
		Connection conn = null;
		PreparedStatement stmt = null;
		
		// 1. 드라이버 로딩, db 서버와 연결
		conn = factory.getConnection();
		
		try {
			// 2. 통로 생성 , 서버와 통로 연결
			String sql = "delete member where memberid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			
			//3. sql구문 실행 요청, 통로 이용
			// c u d => stmt.executeUpdate()
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 회원 탈퇴");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}
	

	/**
	 * 현재 회원 등록 인원수 조회 
	 * @return 현재 등록 인원수
	 */
	public int getMemberSize( ) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
		//1,2. 드라이버 로딩, db에 연결
		conn = factory.getConnection();
		
		String sql = "select count(*) from member";
		
		//3. 연결된 서버와 통로 생성
		stmt = conn.prepareStatement(sql);
		
		//4. 통로 이용 sql 실행요청 , ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행결과 처리
		if(rs.next()) {
			int size = rs.getInt(1);
			return size;
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt, rs);
		}
		
		
		return 0;
	}


}
