/**
 * 
 */
package com.work.model.service;

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
}
