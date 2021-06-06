/**
 * 
 */
package com.work.exception;

/**
 * <pre>
 * 일반 예외 클래스
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class RecordNotFoundException extends Exception {
	public RecordNotFoundException() {
		super("데이터 검색 예외");
	}
	
	public RecordNotFoundException(String message) {
		super("데이터 검색 예외 : " + message);
	}	
}
