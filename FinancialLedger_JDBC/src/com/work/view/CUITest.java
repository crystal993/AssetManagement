/**
 * 
 */
package com.work.view;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.service.*;

/**
 * <pre>
 * 가계부 시스템 CUI 초기 메뉴 화면 
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class CUITest {

	public static void main(String[] args) throws RecordNotFoundException, CommonException, DuplicateException {
		/* 초기 화면 메뉴 */
		IntroMenu view = new IntroMenu();
		

		/* 초기화 메뉴 수행 */
		while(true) {
			try {
				view.introMenu();
					
			} catch (IllegalStateException e) {
				break;
			}
		}	
	}

}
