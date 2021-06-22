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
import com.work.model.dto.Spend;

/**
 * <pre>
 * 지출 DAO 클래스

 * ## Singleton Pattern
 * ## DAO Pattern
 *
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class SpendDao {
	
	/**FactoryDao 객체 멤버변수 선언 및 할당 : 구현*/
	private FactoryDao factory = FactoryDao.getInstance();
	
	/**MemberDao instance 객체로 생성 */
	private static SpendDao instance = new SpendDao(); 
	
	private SpendDao() {
	}
	
	public static SpendDao getInstance() {
		return instance;
	}
	
	/**
	 * 지출 내역 등록 메서드
	 * @param date 날짜
	 * @param revenue 수입
	 * @param source 수입 출처
	 * @return 등록되면 true, 아니면 false
	 */
	public boolean addSpend(String memberId, int spend, String spendType, String spendMethod) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
	
			String sql = "insert into spend values(?,sysdate,?,?,?)";
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,spend);
			stmt.setString(3,spendType);
			stmt.setString(4,spendMethod);
			
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
	
	/**
	 * 지출 내역 전체 조회
	 * @param memberId
	 * @return
	 */
	public ArrayList<Spend> getSpend(String memberId) {
		ArrayList<Spend> spends = new ArrayList<Spend>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
			try {
				//2. db 서버 연결
				conn = factory.getConnection();

				// 
				String sql = "select * from spend where memberId=? order by spenddates";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memberId);
				
				//4. 통로이용 sql 실행 요청
				rs = stmt.executeQuery();
				
				
				//5. 
				while(rs.next()) {
					
					String date = rs.getString("spenddates");
					int spend = rs.getInt("spend");
					String spendtype = rs.getString("spendtype");
					String spendmethod = rs.getString("spendmethod");
					
					// select 결과로 가져온 회원의 
					Spend dto = new Spend(memberId , date, spend, spendtype, spendmethod);
					spends.add(dto);
					
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
			return spends;
	}
	
	/**
	 * 지출 항목별 총 지출액 조회
	 * @param spendType
	 * @return
	 */
	public int getTypeSpend(String memberId, String spendType) {
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		conn = factory.getConnection();
		
		String sql = "select sum(spend) from spend where memberid =? and spendType=?";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		stmt.setString(2, spendType);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		if(rs.next()) {
			int sum = rs.getInt(1);
			return sum;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 지출 출처 조회");
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
	public String getSpendStartDates(String memberId) {
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		conn = factory.getConnection();
		
		String sql = "SELECT to_char(spenddates,'yy/mm/dd') FROM spend WHERE ROWNUM = 1 and memberid=? order by spenddates";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		if(rs.next()) {
			String spenddates = rs.getString(1);
			return spenddates;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 지출 출처 조회");
			e.printStackTrace();
			
		} finally {
			
			factory.close(conn, stmt, rs);
		}
		
		return null;
	}
	
	/**
	 * 등록 지출 중 마지막날짜 조회
	 * @param memberId
	 * @return
	 */
	public String getSpendFinishDates(String memberId) {
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		conn = factory.getConnection();
		
		String sql = "SELECT to_char(spenddates,'yy/mm/dd') FROM (select * from spend order by spenddates desc) WHERE ROWNUM = 1 and memberid=?";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		if(rs.next()) {
			String spenddates = rs.getString(1);
			return spenddates;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 지출 출처 조회");
			e.printStackTrace();
			
		} finally {
			
			factory.close(conn, stmt, rs);
		}
		
		return null;
	}
	
	/**
	 * 기간별 조회
	 * @param memberId
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	public ArrayList<Spend> getDateSpend(String memberId, String startDate, String finishDate) {
		ArrayList<Spend> spends = new ArrayList<Spend>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
			try {
				//2. db 서버 연결
				conn = factory.getConnection();

				// 
				String sql = "select * from spend where memberid = ? and spenddates between ? and ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memberId);
				stmt.setString(2, startDate);
				stmt.setString(3, finishDate);
				
				//4. 통로이용 sql 실행 요청
				rs = stmt.executeQuery();
				
				
				//5. 
				while(rs.next()) {
					
					String date = rs.getString("spenddates");
					int spend = rs.getInt("spend");
					String spendType = rs.getString("spendType");
					String spendMethod = rs.getString("spendMethod");
					
					// select 결과로 가져온 회원의 
					Spend dto = new Spend(memberId , date, spend, spendType, spendMethod);
					spends.add(dto);
					
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
			return spends;
	}
	
	/**
	 * 지출 수단별 조회
	 * @param memberId
	 * @param spendMethod
	 * @return
	 */
	public int getMethodSpend(String memberId, String spendMethod) {
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		conn = factory.getConnection();
		
		String sql = "select sum(spend) from spend where memberid=? and spendmethod = ?";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		stmt.setString(2, spendMethod);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		while(rs.next()) {
			int sum = 0;
			sum += rs.getInt(1);
			return sum;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 지출 출처 조회");
			e.printStackTrace();
			
		} finally {
			
			factory.close(conn, stmt, rs);
		}
		
		return 0;
	}

	public boolean removeSpend(String memberId, int num) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
	
			String sql = "delete spend where memberId = ? and rownum = ?";
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,num);
			
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] 지출 내역 삭제 ");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}

	public int getSpend(String memberId, int num) {
		/** Connection DB 서버 연결 */
		Connection conn = null;
		
		/** PreparedStatement 연결된 서버와 통로 개설 */
		PreparedStatement stmt = null;
		
		/** ResultSet 실행결과 처리*/
		ResultSet rs = null;
		
		try {
			
		//1,2. driver로딩 ,db서버 연결
		conn = factory.getConnection();
		
		String sql = "select spend from spend where memberId = ? and rownum=?";
		
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
