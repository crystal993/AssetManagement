/**
 * 
 */
package com.work.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <pre>
 * 예산 DAO 클래스

 * ## Singleton Pattern
 * ## DAO Pattern
 *
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class BudgetDao {
	
	/**FactoryDao 객체 멤버변수 선언 및 할당 : 구현*/
	private FactoryDao factory = FactoryDao.getInstance();
	
	/**MemberDao instance 객체로 생성 */
	private static BudgetDao instance = new BudgetDao(); 
	
	/**
	 * <pre>
	 * private 생성자
	 * >> 수행문이 없어도 지우면 안된다.
	 * >> jvm에서 생성자가 없으니 자동으로 기본생성자(public) 생성
	 * >> singleton패턴 규칙이 깨진다.
	 * </pre>
	 */
	private BudgetDao() {
		
	}
	
	/**
	 * getInstance 인스턴스 반환메서드 
	 * @return MemberDao
	 */
	public static BudgetDao getInstance() { 
		return instance;
	}

	/**
	* 예산 등록 메서드
	* @param memberId 아이디
	* @param budget 예산
	* @return 존재하면 true 반환, 아니면 false 반환
	*/
	public boolean addBudget(String memberId, int budget) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
	
			String sql = "insert into budget values(?,?)";
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			stmt.setInt(2,budget);
			
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] ");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}
	
	/**
	 * 현재 예산 조회 메서드
	 * @param memberId 아이디
	 * @return 존재하면 true 반환, 아니면 false 반환
	 */
	public int getBudget(String memberId) {
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
		
		String sql = "select budget from budget where memberid = ?";
		
		//3. 연결된 서버와 통로 개설 
		stmt = conn.prepareStatement(sql);
		
		//3.
		stmt.setString(1, memberId);
		
		//4. 통로이용 sql 실행 요청 ; 역할
		rs = stmt.executeQuery();
		
		//5. 실행 결과 처리
		if(rs.next()) {
			int budget = rs.getInt("budget");
			return budget;
					
		}
		
		
		} catch (SQLException e) {
			System.out.println("[오류] 로그인");
			e.printStackTrace();
			
		} finally {
			
			factory.close(conn, stmt, rs);
		}
		
		return 0;
	}
	
	/**
	 * 전체 예산 삭제 메서드
	 * @param memberId 아이디
	 * @return 삭제되면 true이고, 아니면 false
	 */
	public boolean removeBudget(String memberId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
	
			String sql = "delete budget where memberId = ?";
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,memberId);
			
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] ");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}

	/**
	 * <pre>
	 * 예산 수정 메서드
	 * 이 메서드는 수입등록, 지출등록 메서드에서만 쓰는 용도. 
	 * </pre>
	 * @param memberId 아이디
	 * @return 수정되면 true, 아니면 false
	 */
	public boolean updateBudget(String memberId, int money) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = factory.getConnection();
	
			String sql = "update budget set budget = ? where memberid=?";
		
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,money);
			stmt.setString(2,memberId);
			
			int rows = stmt.executeUpdate();
			
			if(rows > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("[오류] ");
			e.printStackTrace();
		} finally {
			
			//6. 자원해제 : finally 구문으로 변경 수정
			// 공장에게 위임
			factory.close(conn, stmt);
		}
		
		return false;
	}



	
	
	
	
	
}
