/**
 * 
 */
package com.work.model.service;

/**
 * <pre>
 * 예산 관리 DAO 클래스
1. jdbc driver 로딩 : 생성자에서 수행
		=> api보고 하기
		=> 객체를 생성해서 메모리에 올린다는 의미.
		
		생성자의 역할 1. 초기화 2. 서비스를 제공하기 전에 선행 처리 되어야할 것도 넣기
	
	2. db 서버연결 : url, user, password  => Connection
		
		
	3. 연결된 서버와 통로 개설 => Statement, PreparedStatement, CallableStatement
	4. 통로이용 sql 실행 요청
	5. 실행결과 처리
	6. 자원해제
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class BudgetDao {

}
