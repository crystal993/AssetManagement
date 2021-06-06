/**
 * 
 */
package com.work.exception;

/**
 * 중복 예외 클래스
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class DuplicateException extends Exception {
	public DuplicateException() {
		super("중복 예외");
	}
	
	public DuplicateException(String message) {
		super("중복 예외 : " + message);
	}	
}
