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
 * 
 * <1> 초기 화면
 * 1. 로그인
 * 2. 회원가입
 * 3. 아이디찾기
 * 4. 비밀번호 찾기
 * 5. 프로그램 종료
 * 
 * <2> 메인 화면
 * 
 * <3> 수입 내역 관리 화면
 * 
 * <4> 지출 내역 관리 화면
 * 
 * <5> 예산 내역 관리 화면
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class IntroMenu {

	MemberService service = new MemberService();
	
	Scanner scanner = new Scanner(System.in);
	
	String memberId = null;
	
	/** 기본 생성자 */
	public IntroMenu() {
	}
	
	/**
	 * <pre>
	 * <1> 초기 화면
	 * 1. 로그인
	 * 2. 회원가입
	 * 3. 아이디찾기
	 * 4. 비밀번호 찾기
	 * 5. 프로그램 종료
	 * </pre>
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
	 * <pre>
	 * <1> 초기 화면
	 * 	1. 로그인 메뉴
	 * </pre>
	 * @throws CommonException 
	 * @throws RecordNotFoundException 
	 * @throws DuplicateException 
	 */
	public void loginMenu() {
		printTitle("로그인 메뉴");
		
		print("아이디 : ");
		memberId = scanner.next();
		print("비밀번호 :");
		String memberPw = scanner.next();
		
		try {
			service.login(memberId,memberPw);
			System.out.println("[성공]"+ memberId + "님 로그인 되었습니다!");	
			mainMenu();
			
		} catch (RecordNotFoundException | CommonException e) {
			System.out.println("[실패] 입력된 정보가 잘못되었거나 존재하지 않습니다.");
			introMenu();
		}
		
	}
	
	/**
	 * <pre>
	 * <1> 초기 화면
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
	 * <pre>
	 * <1> 초기 화면
	 * 3. 아이디 찾기 메뉴
	 * 	3-1. 휴대폰
	 * 	3-2. 이메일
	 * </pre>
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
			findIdMobileMenu();
			break;
		
		case 2 : 
			findIdEmailMenu();
			break;
		default :	
			System.out.println("[입력 형식 오류] : 메뉴 번호는 숫자만 입력하기 바랍니다.");
			introMenu();
		}
		
	}
	
	/**
	 * <pre>
	 * <1> 초기 화면
	 * 3. 아이디 찾기 메뉴
	 * 	3-1. 휴대폰
	 * </pre>
	 */
	public void findIdMobileMenu() {
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
	}
	/**
	 * <pre>
	 * <1> 초기 화면
	 * 3. 아이디 찾기 메뉴
	 * 	3-2. 이메일
	 * </pre>
	 */
	public void findIdEmailMenu() {
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
	}
	
	/**
	 * <pre>
	 * <1> 초기 화면
	 * 4. 비밀번호 찾기 메뉴
	 * </pre>
	 */
	public void findPwMenu() {
		String memberPw = null;
		
		printTitle("비밀번호 찾기");
		
		System.out.println(">> 아이디를 입력바랍니다.");
		print("▷ 아이디 : ");
		String memberId = scanner.next();
		
		System.out.println(">> 휴대폰 번호를 입력바랍니다.");
		print("▷ 휴대폰 : ");
		String email = scanner.next();
		
		try {
			String tempMemberPw = service.findPw(memberId, email);
			System.out.println("▶ 임시 비밀번호 "+tempMemberPw+ "가 발급되었습니다. \n로그인 후 변경 바랍니다.");
			introMenu();
		} catch (CommonException e) {
			System.out.println("존재하지 않거나 잘못 입력된 번호입니다.");
		}
		
	}
	
	/**
	 * <pre>
	 * <1> 초기 화면
	 * 0. 프로그램 종료 메뉴
	 * </pre>
	 */
	private void exitMenu() {
		System.out.println("\n[종료] 프로그램이 종료되었습니다.");
		System.exit(0);
	}
	
	
	//<2> 메인 메뉴
	/**
	 * <pre>
	 * <2>메인 화면
	 * 1.수입
	 * 2.지출
	 * 3.예산
	 * 4.내정보
	 * 5.로그아웃
	 * </pre>
	 */
	public void mainMenu() {
		printTitle("메인 화면");
		
		printMenuItem("1.수입");
		printMenuItem("2.지출");
		printMenuItem("3.예산");
		printMenuItem("4.내 정보");
		printMenuItem("5.로그아웃");
		printLine();
		
		System.out.print(">>  메뉴번호 입력 :");
		int menuNo = scanner.nextInt();
		
		switch(menuNo) {
		case 1 : 
			incomeMainMenu();
			break;
		case 2 : 
			spendMainMenu();
			break;
		case 3 : 
			budgetMainMenu();
			break;
		case 4 : 
			myInfoMenu();
			break;	
		case 5 : 
			logoutMenu();
			break;		
		default : 
			System.out.println("메뉴번호 오류");
			break;
		}
		scanner.close();
	}
	

	/**
	 * <pre>
	 * <2>메인 화면
	 * 1. 수입내역 관리 메인화면
	 */
	private void incomeMainMenu() {
		printTitle("수입");
		
	}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 2. 지출내역 관리 메인화면
	 */
	private void spendMainMenu() {
		
	}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 3. 예산내역 관리 메인화면
	 */
	private void budgetMainMenu() {
		
	}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 4.내 정보 메뉴
	 * 	4-1. 내 정보 조회
	 * 	4-2. 비밀번호 변경
	 * </pre>
	 */
	private void myInfoMenu() {
		printTitle("내 정보");
		
		System.out.println("\t 1.내 정보 조회");
		System.out.println("\t 2.비밀번호 변경");
		
		System.out.println(">> 번호를 입력바랍니다.");
		print("▷ 메뉴 번호 : ");
		int menuNo = scanner.nextInt();
		
		System.out.println("");
		
		switch(menuNo) {
			case 1 : 
				getMyInfoMenu();
				break;
			case 2:
				setMemberPwMenu();
				break;
				
		}
		
	}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 4. 내 정보 메뉴
	 * 	4-1. 내 정보 조회
	 * </pre>
	 */
	public void getMyInfoMenu() {
		try {
			printTitle("내정보 조회");
			
			Member dto = service.getMember(service.currentMemberId);
			System.out.println(dto);
			
			System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
			int no = scanner.nextInt();
			
			if(no == 0)
			{
				mainMenu();
			}
				introMenu();
		} catch (RecordNotFoundException e) {
			System.out.println("[오류] 존재하지 않는 회원입니다.");
		}
		
		}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 4.내 정보 메뉴
	 * 	4-2. 비밀번호 변경
	 * </pre>
	 */
	public void setMemberPwMenu ()  {
		printTitle("비밀번호 변경");
		System.out.println(">> 아이디를 입력하세요.");
		print("▷ 아이디 : ");
		String memberId = scanner.next();
		
		System.out.println(">> 비밀번호를 입력하세요.");
		print("▷ 비밀번호 : ");
		String memberPw = scanner.next();
		
		System.out.println(">> 변경하고자 하는 비밀번호를 입력하세요.");
		print("▷ 변경 비밀번호 : ");
		String modifyMemberPw = scanner.next();
		
		try {
			boolean result = service.setMemberPw(memberId,memberPw,modifyMemberPw);
			if(result) {
				System.out.println("비밀번호가 변경되었습니다.");
				
				System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
				int no = scanner.nextInt();
				
				if(no == 0)
				{
					mainMenu();
				}
					introMenu();
				
			}
		} catch (RecordNotFoundException e) {
			System.out.println("[오류] 존재하지 않는 아이디 혹은 비밀번호입니다.");
		}
	}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 5. 로그아웃
	 * </pre>
	 */
	private void logoutMenu() {
		System.out.println("\n[로그아웃] 로그아웃 되었습니다.");
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


