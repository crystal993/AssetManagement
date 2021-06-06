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
public class CommonException extends Exception {
	
	/** 기본 생성자 */
	public CommonException() {
	}
	
	public CommonException(String message) {
			super(message);
	}
}
