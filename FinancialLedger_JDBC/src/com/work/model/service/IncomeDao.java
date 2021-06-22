/**
 * 
 */
package com.work.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.work.model.dto.Income;
import com.work.model.dto.Member;

/**
 * <pre>
 * 수입 DAO 클래스

 * ## Singleton Pattern
 * ## DAO Pattern
 *
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class IncomeDao {

	
	/**FactoryDao 객체 멤버변수 선언 및 할당 : 구현*/
	private static FactoryDao factory = FactoryDao.getInstance();
	
	/**MemberDao instance 객체로 생성 */
	private static IncomeDao instance = new IncomeDao(); 
	
	private IncomeDao() {
	}
	
	public static IncomeDao getInstance() {
		return instance;
	}

	/**
	 * 수입 내역 등록 메서드
	 * @param date 날짜
	 * @param revenue 수입
	 * @param source 수입 출처
	 * @return 등록되면 true, 아니면 false
	 */
	public boolean addIncome(String memberId, int revenue, String source) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
	
			String sql = "insert into income values(?,sysdate,?,?)";
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,revenue);
			stmt.setString(3,source);
			
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 수입 내역 등록 ");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}

	public static ArrayList<Income> getIncome(String memberId) {
		ArrayList<Income> incomes = new ArrayList<Income>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
			try {
				//2. db 서버 연결
				conn = factory.getConnection();

				// 
				String sql = "select * from income where memberId=? order by incomedates";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memberId);
				
				//4. 통로이용 sql 실행 요청
				rs = stmt.executeQuery();
				
				
				//5. 
				while(rs.next()) {
					
					String date = rs.getString("incomedates");
					int income = rs.getInt("income");
					String sources = rs.getString("sources");
					
					// select 결과로 가져온 회원의 
					Income dto = new Income(memberId , date, income, sources);
					incomes.add(dto);
					
				} 
				
				//return members;
				
			} catch (SQLException e) {
				System.out.println("[오류] 조회 오류");
				e.printStackTrace();
			} finally {
				//6. 자원해제 : finally 구문으로 변경 수정
				// 공장에게 위임
				factory.close(conn, stmt, rs);
			}
			return incomes;
	}
	/**
	 * 
	 * @param memberId
	 * @param num
	 * @return
	 */
	public boolean removeIncome(String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
	
			String sql = "delete income where memberId = ?";
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 수입 내역 삭제 ");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}
	
	/**
	 * 상세 조회 - 수입 출처 
	 * @param memberId
	 * @param source
	 * @return
	 */
	public static int getItemIncome(String memberId, String source) {
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		conn = factory.getConnection();
		
		String sql = "select sum(income) from income where memberid =? and sources=?";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		stmt.setString(2, source);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		if(rs.next()) {
			int sumIncome = rs.getInt(1);
			return sumIncome;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 수입 출처 조회");
			e.printStackTrace();
			
		} finally {
			
			factory.close(conn, stmt, rs);
		}
		
		return 0;
	}

	/**
	 * 
	 * @param memberId
	 * @return
	 */
	public String getIncomeStartDates(String memberId) {
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		conn = factory.getConnection();
		
		String sql = "SELECT to_char(incomedates,'yy/mm/dd') FROM income WHERE ROWNUM = 1 and memberid=? order by incomedates";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		if(rs.next()) {
			String incomedates = rs.getString(1);
			return incomedates;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 수입 출처 조회");
			e.printStackTrace();
			
		} finally {
			
			factory.close(conn, stmt, rs);
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param memberId
	 * @return
	 */
	public String getIncomeFinishDates(String memberId) {
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		conn = factory.getConnection();
		
		String sql = "SELECT to_char(incomedates,'yy/mm/dd') FROM (select * from income order by incomedates desc) WHERE ROWNUM = 1 and memberid=?";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		if(rs.next()) {
			String incomedates = rs.getString(1);
			return incomedates;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 수입 출처 조회");
			e.printStackTrace();
			
		} finally {
			
			factory.close(conn, stmt, rs);
		}
		
		return null;
	}
	
	/**
	 * 상세조회 1. 기간별 조회 
	 * @param memberId 아이디
	 * @param startDate 시작날짜
	 * @param finishDate 끝날짜
	 * @return
	 */
	public ArrayList<Income> getDateIncome(String memberId, String startDate, String finishDate) {
		ArrayList<Income> incomes = new ArrayList<Income>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
			try {
				//2. db 서버 연결
				conn = factory.getConnection();

				// 
				String sql = "select * from income where memberid = ? and incomedates between ? and ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memberId);
				stmt.setString(2, startDate);
				stmt.setString(3, finishDate);
				
				//4. 통로이용 sql 실행 요청
				rs = stmt.executeQuery();
				
				
				//5. 
				while(rs.next()) {
					
					String date = rs.getString("incomedates");
					int income = rs.getInt("income");
					String sources = rs.getString("sources");
					
					// select 결과로 가져온 회원의 
					Income dto = new Income(memberId , date, income, sources);
					incomes.add(dto);
					
				} 
				
				//return members;
				
			} catch (SQLException e) {
				System.out.println("[오류] 조회 오류");
				e.printStackTrace();
			} finally {
				//6. 자원해제 : finally 구문으로 변경 수정
				// 공장에게 위임
				factory.close(conn, stmt, rs);
			}
			return incomes;
			
			
	}

	public static int getIncome(String memberId, int num) {
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		conn = factory.getConnection();
		
		String sql = "select income from income where memberId = ? and rownum=?";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		stmt.setInt(2, num);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		if(rs.next()) {
			int incomedates = rs.getInt(1);
			return incomedates;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 수입 조회");
			e.printStackTrace();
			
		} finally {
			
			factory.close(conn, stmt, rs);
		}
		
		return 0;
	}
	
	
	

}
