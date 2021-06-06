/**
 * 
 */
package com.work.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Member;
import com.work.model.service.MemberService;

/**
 * <pre>
 * 가계부 시스템 초기화면 메뉴 구성
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class IntroMenu {

	MemberService service = new MemberService();
	
	MainMenu main = new MainMenu();
	
	Scanner scanner = new Scanner(System.in);
	
	
	/** 기본 생성자 */
	public IntroMenu() {
	}
	
	/**
	 * 초기 화면
	 * @throws CommonException 
	 * @throws RecordNotFoundException 
	 * @throws DuplicateException 
	 */
	public void introMenu() {
		printTitle("회원관리시스템 메인메뉴");
		
		printMenuItem("1. 로그인\t");
		printMenuItem("2. 회원가입");
		printMenuItem("3. 아이디찾기");
		printMenuItem("4. 비밀번호찾기");
		printMenuItem("0. 프로그램 종료");
	
		printLine();
		
		print(">>  메뉴번호 입력 : ");
		
		try {
			int menuNo = scanner.nextInt();
			
			switch(menuNo) {
			case 1 :
				loginMenu();
				break;
			case 2 :
				signUpMenu();
				break;
			case 3 :
				findIdMenu();
				break;
			case 4 :
				findPwMenu();
				break;
			case 0 :
				exitMenu();
				break;
			default : 
				System.out.println("메뉴번호 오류");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("[입력 형식 오류] : 메뉴 번호는 숫자만 입력하기 바랍니다.");
			System.exit(0);
			scanner.close();
		}
		
	}
	


	/**
	 * 1. 로그인 메뉴
	 * @throws CommonException 
	 * @throws RecordNotFoundException 
	 * @throws DuplicateException 
	 */
	public void loginMenu() {
		printTitle("로그인 메뉴");
		
		print("아이디 : ");
		String memberId = scanner.next();
		print("비밀번호 :");
		String memberPw = scanner.next();
		
		try {
			service.login(memberId,memberPw);
			System.out.println("[성공]"+ memberId + "님 로그인 되었습니다!");	
			main.mainMenu();
			
		} catch (RecordNotFoundException | CommonException e) {
			System.out.println("[실패] 입력된 정보가 잘못되었거나 존재하지 않습니다.");
			introMenu();
		}
		
	}
	
	/**
	 * 2. 회원 가입 메뉴
	 * @throws DuplicateException 
	 * @throws CommonException 
	 * @throws RecordNotFoundException 
	 */
	public void signUpMenu() {
		printTitle("회원 가입");
		
		print("아이디 : ");
		String memberId = scanner.next();
		print("비밀번호 :");
		String memberPw = scanner.next();
		print("이름 : ");
		String name = scanner.next();
		print("휴대폰 번호 :");
		String mobile = scanner.next();
		print("이메일 : ");
		String email = scanner.next();
		
		boolean result;
		try {
			
			service.addMember(memberId,memberPw,name,mobile,email);
			System.out.println("[회원가입 성공]"+memberId+"님 회원가입 되었습니다!");
			loginMenu();
		} catch (DuplicateException e) {
			System.out.println("[회원가입 실패] 이미 등록된 아이디이거나 회원입니다.");
			introMenu();
		}	
		
	}
	
	
	/**
	 * 3. 아이디 찾기 메뉴
	 */
	public void findIdMenu() {
		
		String memberId = null;
		
		printTitle("아이디 찾기");
		
		System.out.println(">> 아이디 찾을 수단을 입력바랍니다.");
		System.out.println("\t1. 휴대폰");
		System.out.println("\t2. 이메일");
		
		print(">>  메뉴번호 입력 : ");
		
		int menuNo = scanner.nextInt();
		
		switch(menuNo) {
		
		case 1 :
			printLine();
			print("귀하의 휴대폰 번호 입력하세요.(숫자만)\n");
			print("▷ 휴대폰 : ");
			String mobile = scanner.next();
			
			try {
				memberId = service.findId1(mobile);
				System.out.println("▶ 회원님의 아이디는 "+memberId+" 입니다.");
			
			} catch (CommonException e) {
				System.out.println("존재하지 않거나 잘못 입력된 번호입니다.");
			}
			break;
		
		case 2 : 
			printLine();
			print("귀하의 이메일을 입력하세요.\n");
			print("▷ 이메일 : ");
			String email = scanner.next();
			try {
				memberId = service.findId2(email);
				System.out.println("▶ 회원님의 아이디는 "+memberId+" 입니다.");
			} catch (CommonException e) {
				System.out.println("존재하지 않거나 잘못 입력된 번호입니다.");
			}
			break;
		default :	
			System.out.println("[입력 형식 오류] : 메뉴 번호는 숫자만 입력하기 바랍니다.");
			introMenu();
		}
		
	}
	
	/**
	 * 4. 비밀번호 찾기 메뉴
	 */
	public void findPwMenu() {
		
	}
	
	/**
	 * 0. 프로그램 종료 메뉴
	 */
	private void exitMenu() {
		System.out.println("\n[종료] 프로그램이 종료되었습니다.");
		System.exit(0);
	}
	
	
	/**
	 * 구분 선 출력 메서드
	 */
	public void printLine() {
		System.out.println("==*=========================*==");
	}
	
	
	/**
	 * 화면 타이틀 출력 메서드
	 * @param title 메뉴 타이틀
	 */
	public void printTitle(String title) {
		System.out.println();
		printLine();
		System.out.println("\t"+title+"\t");
		printLine();
	}
	
	/**
	 * 메뉴 항목 출력 메서드
	 * @param item 메뉴 항목
	 */
	public void printMenuItem(String item) {
		System.out.println("|"+"\t"+item+"\t"+"      |");
	}
	
	/**
	 * 문자열 출력 메서드 - 한줄 띄움 없음.
	 */
	public void print(String text) {
		System.out.print(text);
	}
}


