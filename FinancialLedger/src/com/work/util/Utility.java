/**
 * 
 */
package com.work.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * <pre>
 * 유틸리티
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class Utility {

	/** 기본 생성자 */
	public Utility() {

	}
	
	/**
	 * 현재 날짜를 기본 날짜 형식 년도 4자리, 월 2자리, 일 2자리 형식의 문자열
	 * @return 기본 형식의 현재 날짜 문자열
	 */
	public String getCurrentDate() {
		return getCurrentDate("yyyy-MM-dd");
	}
	
	/**
	 * 아규먼트로 전달받은 날짜 형식의 현재날짜 변환 반환
	 * @param pattern 날짜형식
	 * @return 날짜형식의 현재날짜 문자열
	 */
	public String getCurrentDate(String pattern) {
		return getCurrentDate(pattern, Locale.KOREA);
	}
	
	/**
	 * <pre>
	 * 아규먼트로 전달받은 날짜 형식, 로케일 형식의 현재 날짜 변환 반환 메서드
	 * locale : java.util.Locale => FIELD 참고
	 * java.util.SimpleDateFormat 생성자 중복정의 => public SimpleDateFormat(String, Locale)
	 * 
	 * </pre>
	 * @see java.util.Locale
	 * @see java.util.SimpleDateFormat
	 * @param pattern 날짜 형식 및 시간 형식
	 * @param locale 로케일 형식
	 * @return 날짜 형식, 로케일 형식의 현재 날짜 문자열
	 */
	private String getCurrentDate(String pattern, Locale locale) {
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}
	
	/**
	 * 보안문자 4자리 숫자 반환
	 * @return 4자리숫자 보안문자
	 */
	public String getSecureNumberString() {
		return getSecureNumberString(4);
	}
	
	/**
	 * 보안문자 지정길이 숫자 반환
	 * @param length 보안문자 길이
	 * @return 지정길이숫자 보안문자
	 */
	public String getSecureNumberString(int length) {
		Random random = new Random((long)(Math.random() * System.nanoTime()));
		StringBuilder secureString = new StringBuilder();
		
		for (int index = 0; index < length; index++) {
			secureString.append(random.nextInt(10));
		}
		return secureString.toString();
	}
	
	/**
	 * 보안문자 영문 대문자 기본 4자리 반환
	 * @return 보안문자 영문대문자 4자리
	 */
	public String getSecureAlphabetString() {
		return getSecureAlphabetString(4);
	}
	
	/**
	 * 보안 영문 대문자 지정길이 반환
	 * @param length 보안문자 길이
	 * @return 지정길이숫자 영문대문자 보안문자 
	 */
	public String getSecureAlphabetString(int length) {
		Random random = new Random((long)(Math.random()*System.nanoTime()));
		StringBuilder secureString = new StringBuilder();
		
		for(int index = 0; index < length ; index++) {
			secureString.append((char)(random.nextInt(26)+'A'));
		}
		return secureString.toString();
	}
	
	/**
	 * 보안 영문 대/소문자 지정길이 반환
	 * @param length 보안문자 길이
	 * @param isUpper 영문 대소문자 여부
	 * @return 지정길이숫자 영문대/소문자 보안문자 
	 */
	public static String getSecureAlphabetString(int length, boolean isUpper) {
		Random random = new Random((long)(Math.random()* System.nanoTime()));
		StringBuilder secureString = new StringBuilder();
		
		for (int index = 0; index < length ; index++) {
			if(isUpper) {
				secureString.append((char)(random.nextInt(26)+'A'));
			} else {
				secureString.append((char)(random.nextInt(26)+'a'));
			}
		}
		return secureString.toString();
	}
	
	/**
	 * 보안 영문 대/소문자, 숫자 혼합 지정길이 반환
	 * @param length 보안문자 길이
	 * @param isUpper 영문 대/소문자 여부
	 * @param isMixed 영문 대/소문자, 숫자 혼합 여부, true 영문대/소문자 숫자 혼잡, false 영문 대/소문자
	 * @return
	 */
	public static String getSecureAlphabetString(int length, boolean isUpper, boolean isMixed) {
		if (isMixed) {
			Random random = new Random((long)(Math.random() * System.nanoTime()));
			StringBuilder secureString = new StringBuilder();
			
			//사용자가 발생시키고 싶어하는 길이만큼
			for (int index = 0; index < length; index++) {
				if (random.nextBoolean()) { //true 숫자 랜덤으로 발생, false이면 문자로 넘어감.
					secureString.append(random.nextInt(10));
				} else { // false이면 문자 
					if (isUpper) { // true이면 기존 문자열에 대문자하나 더 함.
						secureString.append((char)(random.nextInt(26) + 'A'));
					} else { //false이면 기존 문자열에 소문자 하나 더 함.
						secureString.append((char)(random.nextInt(26) + 'a'));
					}
				}
			}
			return secureString.toString();
			
		} else {
			return getSecureAlphabetString(length, isUpper);
		}
		
	}




}
