/**
 * 
 */
package com.work.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <pre>
 * 모든 dao 클래스에서 사용하기 위한 클래스
 * --메소드1. Connection 반환
 * --메소드2. close() 자원해제를 담당하는 기능으로만 분리설계
 * => 모든 dao 클래스에서 getConnection, close(conn, stmt, rs) : 호출 사용
 * 
 * -- singleton pattern
 * 1. private 생성자
 * 2. private static 클래스이름 instance = new 클래스이름();
 * 3. public static 클래스이름 getInstance() {return instance;}
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class FactoryDao {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	String user = "crystal993";
	String password = "limemint01r";

	
	private static FactoryDao instance = new FactoryDao();
	
	private FactoryDao() {
		try {
			Class.forName(driver);
			System.out.println("[성공] 드라이버 로딩");
		} catch (ClassNotFoundException e) {
			System.out.println("[오류] 드라이버 로딩 오류");
			e.printStackTrace();
		}
	}
	
	public static FactoryDao getInstance() {
		return instance;
	}
	
	
	//1. DB 서버 연결 Connection 반환 메서드
	/**
	 * DB 연결 Connection 반환 메서드
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * <pre>
	 * 자원해제 : Select 수행에 대한 자원 해제
	 * Statement - 부모, PreparedStatement - 자식
	 * </pre>
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public void close(Connection conn, Statement stmt, ResultSet rs)
	{
		try {
			if(rs != null) // 오류가 발생할 때 nullPointerException 예방 가능
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(stmt != null)
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null)
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 자원해제 : C,U,D 수행에 대한 자원 해제
	 * Statement - 부모, PreparedStatement - 자식
	 * </pre> 
	 * @param conn
	 * @param stmt
	 */
	public void close(Connection conn, Statement stmt){
		close(conn,stmt,null);
	}
	
		
	
}
