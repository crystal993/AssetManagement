/**
 * 
 */
package com.work.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Income;
import com.work.model.dto.Member;
import com.work.model.service.ManagementService;
import com.work.util.Utility;

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
 * 1.수입
 * 2.지출
 * 3.예산
 * 4.내정보
 * 5.로그아웃 
 * 
 * <3> 수입 내역 관리 화면
 * 1.수입 내역 등록	      
 * 2.수입 내역 조회	      
 * 3.수입 내역 삭제
 * 
 * <4> 지출 내역 관리 화면
 * 1.지출 내역 등록	      
 * 2.지출 내역 조회	      
 * 3.지출 내역 삭제
 * 
 * <5> 예산 내역 관리 화면
 * 1.초기 예산 등록	      
 * 2.현재 예산 조회	      
 * 3.전체 예산 삭제	
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class IntroMenu {

	Utility util = new Utility();
	
	ManagementService service = new ManagementService();

	
	Scanner scanner = new Scanner(System.in);
	
	
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
	 */
	public void introMenu() {
		printTitle("가계부 프로그램 초기 메뉴");
		
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
	 */
	public void loginMenu() {
		printTitle("로그인 메뉴");
		
		print("아이디 : ");
		String memberId = scanner.next();
		print("비밀번호 :");
		String memberPw = scanner.next();
		

		boolean check = service.login(memberId,memberPw);
		if(check) {
			System.out.println("[성공]"+ memberId + "님 로그인 되었습니다!");	
			mainMenu(memberId);
		} else {	
			System.out.println("[실패] 입력된 정보가 잘못되었거나 존재하지 않습니다.");
			introMenu();
		}
		scanner.close();
	}
	
	/**
	 * <pre>
	 * <1> 초기 화면
	 * 2. 회원 가입 메뉴
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
		
		boolean result = service.addMember(memberId, memberPw, name, mobile, email);
		if(result) {
			System.out.println("[회원가입 성공]"+memberId+"님 회원가입 되었습니다!");
			loginMenu();
		}
		System.out.println("[오류] 회원가입");
		scanner.close();
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
			scanner.close();
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
		
		
		String memberId = service.findId1(mobile);
		if (memberId != null) {	
			System.out.println("▶ 회원님의 아이디는 "+memberId+" 입니다.");
		} else {
			System.out.println("존재하지 않거나 잘못 입력된 번호입니다.");
		}
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			introMenu();
		}
			scanner.close();

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
		
		String memberId = service.findId2(email);
		if (memberId != null) {	
			System.out.println("▶ 회원님의 아이디는 "+memberId+" 입니다.");
		} else {
			System.out.println("존재하지 않거나 잘못 입력된 번호입니다.");
		}
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			introMenu();
		}
			scanner.close();
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
		
		System.out.println(">> 비밀번호를 찾을 수단을 입력바랍니다.");
		System.out.println("\t1. 휴대폰");
		System.out.println("\t2. 이메일");
		
		print(">>  메뉴번호 입력 : ");
		
		int menuNo = scanner.nextInt();
		
		switch(menuNo) {
		
		case 1 :
			findPwMobileMenu();
			break;
		
		case 2 : 
			findPwEmailMenu();
			break;
		default :	
			System.out.println("[입력 형식 오류] : 메뉴 번호는 숫자만 입력하기 바랍니다.");
			introMenu();
		}
			scanner.close();
	}
	
	/**
	 * <pre>
	 * <1> 초기 화면
	 * 4. 비밀번호 찾기 메뉴
	 * 4-1. 비밀번호찾기 - 모바일
	 * </pre>
	 */
	public void findPwMobileMenu() {
		String memberPw = null;
		
		printTitle("비밀번호 찾기");
		
		System.out.println(">> 아이디를 입력바랍니다.");
		print("▷ 아이디 : ");
		String memberId = scanner.next();
		
		System.out.println(">> 이름을 입력바랍니다.");
		print("▷ 이름 : ");
		String name = scanner.next();
		
		System.out.println(">> 휴대폰 번호를 입력바랍니다.");
		print("▷ 휴대폰 : ");
		String mobile = scanner.next();
		
		String tempMemberPw = service.findMemerPwByMobile(memberId, name, mobile);
		if(tempMemberPw != null) {
			System.out.println("▶ 임시 비밀번호 "+tempMemberPw+ "가 발급되었습니다. \n로그인 후 변경 바랍니다.");
			introMenu();
		} else {
			System.out.println("존재하지 않거나 잘못 입력된 번호입니다.");
		}
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			introMenu();
		}
			scanner.close();
	}
	
	/**
	 * <pre>
	 * <1> 초기 화면
	 * 4. 비밀번호 찾기 메뉴
	 * 4-1. 비밀번호찾기 - 이메일
	 * </pre>
	 */
	public void findPwEmailMenu() {
		String memberPw = null;
		
		printTitle("비밀번호 찾기");
		
		System.out.println(">> 아이디를 입력바랍니다.");
		print("▷ 아이디 : ");
		String memberId = scanner.next();
		
		System.out.println(">> 이름을 입력바랍니다.");
		print("▷ 이름 : ");
		String name = scanner.next();
		
		System.out.println(">> 이메일을 입력바랍니다.");
		print("▷ 이메일 : ");
		String email = scanner.next();
		
		String tempMemberPw = service.findMemerPwByEmail(memberId, name, email);
		
		if(tempMemberPw != null) {
			System.out.println("▶ 임시 비밀번호 "+tempMemberPw+ "가 발급되었습니다. \n로그인 후 변경 바랍니다.");
			introMenu();
		} else {
			System.out.println("존재하지 않거나 잘못 입력된 번호입니다.");
		}	
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			introMenu();
		}
			scanner.close();
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
	public void mainMenu(String memberId) {
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
			incomeMainMenu(memberId);
			break;
		case 2 : 
			spendMainMenu(memberId);
			break;
		case 3 : 
			budgetMainMenu(memberId);
			break;
		case 4 : 
			myInfoMenu(memberId);
			break;	
		case 5 : 
			introMenu();
			break;			
		default : 
			System.out.println("메뉴번호 오류");
			introMenu();
			break;
		}
		scanner.close();
	}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 1. 수입내역 관리 메인화면
	 */
	public void incomeMainMenu(String memberId) {
		printTitle("수입 내역 관리 화면");
		
		printMenuItem("1.수입 내역 등록");
		printMenuItem("2.수입 내역 조회");
		printMenuItem("3.수입 내역 삭제");
		printMenuItem("0.이전 화면으로 가기");
		printLine();
		
		System.out.print(">>  메뉴번호 입력 :");
		int menuNo = scanner.nextInt();
		
		switch(menuNo) {
		case 1 : 
			registerIncomeMenu(memberId);
			break;
		case 2 : 
			getIncomeMainMenu(memberId);
			break;
		case 3 : 
			removeIncomeMenu(memberId);
			break;
		case 0 : 
			mainMenu(memberId);
			break;		
		default : 
			System.out.println("메뉴번호 오류");
			break;
		}
		scanner.close();
	}
	
	/**
	 * 수입 등록 화면
	 */
	public void registerIncomeMenu(String memberId) {
		printTitle("수입 내역 등록");
		System.out.println("<"+util.getCurrentDate()+">");
		print("\n▶ 내 수입 : ");
		int revenue = scanner.nextInt();
		
		System.out.println();
		System.out.println("\n>> 출처 - 1.월급 2.용돈 3.배당금 4.기타 ");
		System.out.println("출처는 \"문자\"로 입력해주시길 바랍니다.");
		print("\n▶ 출처 : ");
		String source = scanner.next();
		
	
		boolean result = service.addIncome(memberId, revenue, source);
		if(result) {
		System.out.println("수입 내역이 등록되었습니다.");
		}
		System.out.println("[오류]수입 내역 등록");
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			incomeMainMenu(memberId);
		}
			scanner.close();
			System.exit(0);
		
	}
		
	
	
	/**
	 * 수입 삭제 화면
	 */
	public void removeIncomeMenu(String memberId) {
		printTitle("수입 내역 삭제");
		
		service.getIncome(memberId);
		ArrayList<Income> list = service.getIncome(memberId);
		int count=1;
		for(Income income : list) {
			System.out.print(count++);
			System.out.println(income);
		}
		
		System.out.println("<"+util.getCurrentDate()+">");
		System.out.println("\n>> 삭제할 수입내역 목록 번호를 입력하세요.");
		print("\n▶ 삭제할 수입 목록 번호 : ");
		int num = scanner.nextInt();
		boolean result = service.removeIncome(memberId, num);
		
		if(result) {
			System.out.println("해당 내역이 삭제되었습니다.");
		}
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			incomeMainMenu(memberId);
		}
			scanner.close();
			System.exit(0);
	}
	

	
	/**
	 * <pre>
	 * 수입 조회 화면
	 * 1. 수입 전체 조회 화면
	 * 2. 수입 상세 조회 화면  
	 * </pre>
	 */
	public void getIncomeMainMenu(String memberId) {
			printTitle("수입 조회 관리 화면");
			
			printMenuItem("1. 수입 전체 조회 화면");
			printMenuItem("2. 수입 상세 조회 화면");
			printMenuItem("0. 이전 화면");
			printLine();
			
			System.out.print(">>  메뉴번호 입력 :");
			int menuNo = scanner.nextInt();
			
			switch(menuNo) {
			case 1 : 
				getAllIncomeMenu(memberId);
				break;
			case 2 : 
				getDetailIncomeMenu(memberId);
				break;
			case 0 : 
				incomeMainMenu(memberId);
				break;
			default : 
				System.out.println("메뉴번호 오류");
				break;
			}
			scanner.close();
	}
	
	/**
	 * <pre>
	 * 수입 조회 화면
	 * 1. 수입 전체 조회 화면
	 * </pre>
	 */
	public void getAllIncomeMenu(String memberId) {
		printTitle("수입 전체 조회 화면");
		
		System.out.println(memberId+" 님 수입 전체 조회 목록\n");
		
		ArrayList<Income> list = service.getIncome(memberId);
		int count=1;
		for(Income income : list) {
			System.out.print(count++);
			System.out.println(income);
		}
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			incomeMainMenu(memberId);
		}
			scanner.close();
			System.exit(0);
	}
	
	
	
	/**
	 * <pre>
	 * 수입 상세 조회 화면
	 * 2. 수입 상세 조회 화면 
	 * </pre> 
	 */
	public void getDetailIncomeMenu(String memberId) {
		printTitle("수입 상세 조회 관리 화면");
		
		printMenuItem("1. 수입출처별 상세 조회 화면");
		printMenuItem("2. 수입출처별 전체 비율 조회 화면");
		printMenuItem("3. 기간별 상세 조회 화면");
		printMenuItem("0. 이전 화면");
		printLine();
		
		System.out.print(">>  메뉴번호 입력 :");
		int menuNo = scanner.nextInt();
		
		switch(menuNo) {
		case 1 : 
			getItemDetailIncomeMenu(memberId);
			break;
		case 2 : 
			getItemIncomePortionMenu(memberId);
			break;	
		case 3 : 
			getPeiodDetailIncomeMenu(memberId);
			break;
		case 0 : 
			getIncomeMainMenu(memberId);
			break;
		default : 
			System.out.println("메뉴번호 오류");
			break;
		}
		scanner.close();
		
	}
	


	/**
	 * <pre>
	 * 수입 상세 조회 화면
	 * 2. 수입 상세 조회 화면 
	 * 		(1) 수입출처별 항목 전체 화면
	 * </pre> 
	 */
	public void getItemDetailIncomeMenu(String memberId) {
		printTitle("수입출처별 수입 상세 조회");
		
		System.out.println("\n>> 출처 - 1.월급 2.용돈 3.배당금 4.기타 ");
		System.out.println("출처는 \"문자\"로 입력해주시길 바랍니다.");
		
		print("\n▶ 조회할 출처 : ");
		String source = scanner.next();
		
		//service.getItemIncome(source) 검사 위한 try
	
			int sumIncome = service.getItemIncome(memberId, source);
				if(sumIncome > 0) {
					System.out.println("[수입 출처]"+source+" 총 수입 :" +sumIncome);
				}
				else {
					System.out.println("현재 등록된 수입이 없습니다.");
				}
	
			System.out.println("현재 등록된 수입이 없습니다.");
		
		
		System.out.println("\n>> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			incomeMainMenu(memberId);
		}
			scanner.close();
			System.exit(0);
	}
	
	/**
	 * <pre>
	 * 수입 상세 조회 화면
	 * 2. 수입 상세 조회 화면 
	 * 		(1) 수입출처별 전체 비율 화면
	 * </pre> 
	 */
	private void getItemIncomePortionMenu(String memberId) {
		printTitle("수입출처별 전체 비율 조회");
		service.getItemIncomePortion(memberId);
		
		System.out.println("\n>> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			getDetailIncomeMenu(memberId);
		}
			scanner.close();
			System.exit(0);
	}
	
	/**
	 * <pre>
	 * 수입 조회 화면
	 * 2. 수입 상세 조회 화면 
	 * 		(2) 기간별 조회 전체 화면
	 * </pre> 
	 */
	public void getPeiodDetailIncomeMenu(String memberId) {
		printTitle("기간별 조회 전체 화면");
		
		print("\n▶ 현재 등록된 기간 ["+service.getIncomeStartDates(memberId)+" ~ "+service.getIncomeFinishDates(memberId)+"]");
		
		System.out.println("\n\n>>날짜는 현재 등록된 기간 내에서만 쓰시길 바랍니다.");
		System.out.println(">>날짜는 yyyy/MM/dd 형식 지켜주시길 바랍니다.");
		print("\n▶ 시작 기간 : ");
		String startDate = scanner.next();
		
		print("\n▶ 끝 기간 : ");
		String finishDate = scanner.next();
		
		System.out.println("\n \t <<조회>>");
		
		
		ArrayList<Income> list = service.getDateIncome(memberId, startDate, finishDate);
		int count=1;
		if(list != null) {	
			for(Income income : list) {
				System.out.print(count++);
				System.out.println(income);
			}
		} else { 
			System.out.println("[오류] 현재 등록된 수입이 없거나 기간을 잘못 입력하였습니다.");
		}
		
		System.out.println("\n>> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			getDetailIncomeMenu(memberId);
		}
			scanner.close();
			System.exit(0);
	}
	
	
	/**
	 * <pre>
	 * <2>지출내역 관리 메인화면
	 * 	1. 지출 내역 등록
	 *  2. 지출 내역 조회
	 *  3. 지출 내역 삭제
	 *  </pre>
	 */
	private void spendMainMenu(String memberId) {
		printTitle("지출 내역 관리 화면");
		
		printMenuItem("1.지출 내역 등록");
		printMenuItem("2.지출 내역 조회");
		printMenuItem("3.지출 내역 삭제");
		printMenuItem("0.이전 화면으로 가기");
		printLine();
		
		System.out.print(">>  메뉴번호 입력 :");
		int menuNo = scanner.nextInt();
		
		switch(menuNo) {
		case 1 : 
			addSpendMenu(memberId);
			break;
		case 2 : 
			getSpendMainMenu(memberId);
			break;
		case 3 : 
			removeSpendMenu(memberId);
			break;
		case 0 : 
			mainMenu(memberId);
			break;	
		default : 
			System.out.println("메뉴번호 오류");
			break;
		}
		scanner.close();
	}
	
	/**
	 * <pre>
	 * <2>지출내역 관리 메인화면
	 * 1. 지출 내역 등록
	 *  </pre>
	 */
	private void addSpendMenu(String memberId) {
		printTitle("지출 내역 등록");
		System.out.println("<"+util.getCurrentDate()+">");
		print("\n▶ 내 지출 : ");
		int spend = scanner.nextInt();
		
		System.out.println();
		System.out.println("\n>> 지출 항목 - 1.식비 2.주거비 3.의류비 4.교통비 5.문화비 6.기타 ");
		System.out.println("지출 항목는 \"문자\"로 입력해주시길 바랍니다.");
		print("\n▶ 지출 항목 : ");
		String spendType = scanner.next();
		
		System.out.println();
		System.out.println("\n>> 결제 수단 - 1.카드 2.현금 3.이체 ");
		System.out.println("결제 수단은 \"문자\"로 입력해주시길 바랍니다.");
		print("\n▶ 결제 수단 : ");
		String spendMethod = scanner.next();
		
	
			service.addSpend(util.getCurrentDate(), spend, spendType, spendMethod);
			
			// TODO Auto-generated catch block
			System.out.println("지출 내역이 존재하지 않습니다.");
		
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			spendMainMenu(memberId);
		}
			scanner.close();
			System.exit(0);
		
		
	}
	
	
	
	/**
	 * <pre>
	 * <2> 지출내역 관리 메인화면
	 *  2. 지출 내역 조회
	 *  </pre>
	 */
	private void getSpendMainMenu(String memberId) {
		printTitle("지출 조회 관리 화면");
		
		printMenuItem("1. 지출 전체 조회 화면");
		printMenuItem("2. 지출 상세 조회 화면");
		printMenuItem("0. 이전 화면");
		printLine();
		
		System.out.print(">>  메뉴번호 입력 :");
		int menuNo = scanner.nextInt();
		
		switch(menuNo) {
		case 1 : 
			getAllSpendMenu(memberId);
			break;
		case 2 : 
			getDetailSpendMenu(memberId);
			break;
		case 0 : 
			spendMainMenu(memberId);
			break;
		default : 
			System.out.println("메뉴번호 오류");
			break;
		}
		scanner.close();
}

	/**
	 * <pre>
	 * <2> 지출내역 관리 메인화면
	 *  2. 지출 내역 조회
	 * 2-1. 지출 전체 조회 화면
	 * </pre>
	 */
	public void getAllSpendMenu(String memberId) {
	printTitle("지출 전체 조회 화면");
	service.getSpend();
	
	System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
	int no = scanner.nextInt();
	
	if(no == 0)
	{
		spendMainMenu(memberId);
	}
		scanner.close();
		System.exit(0);
}



	/**
	 * <pre>
	 * <2> 지출내역 관리 메인화면
	 *  2. 지출 내역 조회
	 * 2-2. 지출 상세 조회 화면
	 * </pre> 
	 */
	public void getDetailSpendMenu(String memberId) {
		printTitle("지출 상세 조회 관리 화면");
		
		printMenuItem("1. 지출항목별 상세조회 화면");
		printMenuItem("2. 지출항목별 전체 비율 화면");
		printMenuItem("3. 기간별 상세조회 화면");
		printMenuItem("4. 결제수단별 상세조회 화면");
		
		printMenuItem("0. 이전 화면");
		printLine();
		
		System.out.print(">>  메뉴번호 입력 :");
		int menuNo = scanner.nextInt();
		
		switch(menuNo) {
		case 1 : 
			getTypeDetailSpendMenu(memberId);
			break;
		case 2 : 
			 getSpendTypePortionMenu(memberId);
			break;
		case 3 : 
			getPeiodDetailSpendMenu(memberId);
			break;
		case 4 : 
			getMethodDetailSpendMenu(memberId);
			break;
		case 0 : 
			getSpendMainMenu(memberId);
			break;
		default : 
			System.out.println("메뉴번호 오류");
			break;
		}
		scanner.close();
		
	}
	


	/**
	 * <pre>
	 * <2> 지출내역 관리 메인화면
	 *  2. 지출 내역 조회
	 * 		2-2. 지출 상세 조회 화면
	 *  		(1) 지출 항목별 상세 조회
	 * </pre> 
	 */
	public void getTypeDetailSpendMenu(String memberId) {
		printTitle("지출 항목별 상세 조회");
		
		System.out.println("\n>> 지출 항목 - 1.식비 2.주거비 3.의류비 4.교통비 5.문화비 6.기타 ");
		System.out.println("지출 항목는 \"문자\"로 입력해주시길 바랍니다.");
		print("\n▶ 조회할 지출 항목 : ");
		
		String spendType = scanner.next();
		
		//service.getItemSpend(spendType) 검사 위한 try
		
			int sumSpend = service.getTypeSpend(spendType);
				if(sumSpend > 0) {
					System.out.println("[지출 항목]"+spendType+" 총 지출 :" +sumSpend);
				}

			System.out.println("현재 등록된 지출이 없습니다.");
		
		
		System.out.println("\n>> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			getDetailSpendMenu( memberId);
		}
			scanner.close();
			System.exit(0);
	}
	
	/**
	 * <pre>
	 * <2> 지출내역 관리 메인화면
	 *  2. 지출 내역 조회
	 * 		2-2. 지출 상세 조회 화면
	 *  		(2) 기간별 조회
	 * </pre> 
	 */
	public void getPeiodDetailSpendMenu(String memberId) {
		printTitle("기간별 조회 전체 화면");
		
		print("\n▶ 현재 등록된 기간 ["+service.getSpendStartDates()+" ~ "+service.getSpendFinishDates()+"]");
		
		System.out.println("\n\n>>날짜는 현재 등록된 기간 내에서만 쓰시길 바랍니다.");
		System.out.println(">>날짜는 yyyy-MM-dd 형식 지켜주시길 바랍니다.");
		print("\n▶ 시작 기간 : ");
		String startDate = scanner.next();
		
		print("\n▶ 끝 기간 : ");
		String finishDate = scanner.next();
		
		System.out.println("\n \t <<조회>>");
		
		
				service.getDateSpend(startDate, finishDate);
			
		
			System.out.println("");
		
			System.out.println("[오류] 현재 등록된 지출이 없거나 기간을 잘못 입력하였습니다.");
		
		
		System.out.println("\n>> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			getDetailSpendMenu( memberId);
		}
			scanner.close();
			System.exit(0);
	}
	
	/**
	 * <pre>
	 * <2> 지출내역 관리 메인화면
	 *  2. 지출 내역 조회
	 * 		2-2. 지출 상세 조회 화면
	 *  		(3) 지출 수단별 조회 상세 항목 메뉴
	 * </pre> 
	 */
	private void getMethodDetailSpendMenu(String memberId) {
		printTitle("지출 수단별 상세 조회");
		
		System.out.println("\n>> 지출 수단 - 1.카드 2.현금 3.이체 4.기타");
		System.out.println("지출 수단은 \"문자\"로 입력해주시길 바랍니다.");
		print("\n▶ 조회할 지출 수단 : ");
		
		String spendMethod = scanner.next();
		
		//service.getItemSpend(spendType) 검사 위한 try
		
			int sumSpend = service.getMethodSpend(spendMethod);
				if(sumSpend > 0) {
					System.out.println("[지출 항목]"+spendMethod+" 총 지출 :" +sumSpend);
				}
		
			System.out.println("현재 등록된 지출이 없습니다.");
		
		
		System.out.println("\n>> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			getDetailSpendMenu( memberId);
		}
			scanner.close();
			System.exit(0);
		
	}
	
	/**
	 * <pre>
	 * 지출 상세 조회 화면
	 * 2. 지출 상세 조회 화면 
	 * 		(4) 지출항목별 전체 비율 화면
	 * </pre> 
	 */
	private void getSpendTypePortionMenu(String memberId) {
		printTitle("지출항목별 전체 비율 조회");
		service.getSpendTypePortion();
		
		System.out.println("\n>> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			getDetailSpendMenu( memberId);
		}
			scanner.close();
			System.exit(0);
	}
	
	/**
	 * <pre>
	 * <2> 지출내역 관리 메인화면
	 *  3. 지출 내역 삭제
	 *  </pre>
	 */
	private void removeSpendMenu(String memberId) {
		printTitle("지출 내역 삭제");
		System.out.println("<"+util.getCurrentDate()+">");
		System.out.println("\n>> 삭제할 지출은 숫자만 입력하세요.");
		print("\n▶ 삭제할 지출 : ");
		int spend = scanner.nextInt();
		
		
		System.out.println();
		System.out.println("\n>> 지출 항목 - 1.식비 2.주거비 3.의류비 4.교통비 5.문화비 6.기타 ");
		System.out.println("지출 항목은 \"문자\"로 입력해주시길 바랍니다.");
		print("\n▶ 삭제할 지출 항목 : ");
		String spendType = scanner.next();
		
		
		System.out.println();
		System.out.println("\n>> 지출 수단 - 1.카드 2.현금 3.이체 4.기타 ");
		System.out.println("출처는 \"문자\"로 입력해주시길 바랍니다.");
		print("\n▶ 삭제할 지출 수단 : ");
		String spendMethod = scanner.next();
		
		
		System.out.println();
		System.out.println("\n>>날짜는 yyyy-MM-dd 형식 지켜주시길 바랍니다.");
		print("\n▶ 삭제할 날짜 : ");
		String date = scanner.next();
		
		service.removeSpend(date, spend, spendType, spendMethod);
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			spendMainMenu(memberId);
		}
			scanner.close();
			System.exit(0);
		
	}

	

	/**
	 * <pre>
	 * <2>메인 화면
	 * 3. 예산내역 관리 메인화면
	 * 	3-1.초기 예산 등록
	 * 	3-2.현재 예산 조회
	 *  3-3.전체 예산 삭제
	 *  3-4.예산 내역 변경
	 *  </pre>
	 */
	private void budgetMainMenu(String memberId) {
		printTitle("예산 내역 관리 화면");
		
		printMenuItem("1.초기 예산 등록");
		printMenuItem("2.현재 예산 조회");
		printMenuItem("3.전체 예산 삭제");
		printMenuItem("0.이전 화면으로 가기");
		printLine();
		
		System.out.print(">>  메뉴번호 입력 :");
		int menuNo = scanner.nextInt();
		
		switch(menuNo) {
		case 1 : 
			addBudgetMenu(memberId);
			break;
		case 2 : 
			getBudgetMenu(memberId);
			break;
		case 3 : 
			removeBudgetMenu(memberId);
			break;
		case 0 : 
			mainMenu(memberId);
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
	 * 3. 예산내역 관리 메인화면
	 * 	3-1.초기 예산 등록
	 * </pre>
	 */
	private void addBudgetMenu(String memberId) {
		printTitle("1.초기 예산 등록");
		
		int budget = service.getBudget(memberId);
		
		if(budget == 0) {
		
			System.out.print("▶ 예산 입력 :");
			budget = scanner.nextInt();
	
			service.addBudget(memberId,budget);

			System.out.println("");
			
		}
		else {
			System.out.println(">> 예산 데이터가 이미 등록되어 있습니다. ");
			System.out.println("▶ 내 예산 : "+service.getBudget(memberId)+"원");
			System.out.println(">> 이미 등록된 상태에서는 예산 조회, 변경, 삭제만 가능합니다.\n");
		}
		
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			budgetMainMenu(memberId);
		}
			scanner.close();
			System.exit(0);
	}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 3. 예산내역 관리 메인화면
	 * 	3-2.현재 예산 조회
	 * </pre>
	 */
	private void getBudgetMenu(String memberId) {
		printTitle("2.현재 예산 조회");
		
		int budget = service.getBudget(memberId);
		System.out.println("▶ 예산 :" + budget+"\n");
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0)
		{
			budgetMainMenu(memberId);
		}
			scanner.close();
			System.exit(0);
		
	}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 3. 예산내역 관리 메인화면
	 * 	3-3.전체 예산 삭제
	 * </pre>
	 */
	private void removeBudgetMenu(String memberId) {
		printTitle("3.전체 예산 삭제");
		
		System.out.println(">> [경고] 예산을 삭제하면 지출과 수입 등 모든 내역이 사라집니다.");
		System.out.println(">> [경고] 그래도 삭제하겠습니까? 1. 네 2. 아니오");
		print(">> 번호 입력 : ");
		int removeNo = scanner.nextInt();
		
		if (removeNo == 1) {
			service.removeBudget(memberId);
			System.out.println(">> 예산 데이터가 존재하지 않습니다.\n");
		} else {
			System.out.println("▶ 예산 :" + service.getBudget(memberId)+"\n");
			System.out.println("");
		}

		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
		
		if(no == 0) {
			budgetMainMenu(memberId);
		}
			scanner.close();
			System.exit(0);
	}
	

	/**
	 * <pre>
	 * <2>메인 화면
	 * 4.내 정보 메뉴
	 * 	4-1. 내 정보 조회
	 * 	4-2. 비밀번호 변경
	 * </pre>
	 */
	private void myInfoMenu(String memberId) {
		printTitle("내 정보");
		
		System.out.println("\t 1.내 정보 조회");
		System.out.println("\t 2.내 정보 변경");
		System.out.println("\t 3.비밀번호 변경");
		System.out.println("\t 4.회원 탈퇴");
		
		System.out.println(">> 번호를 입력바랍니다.");
		print("▷ 메뉴 번호 : ");
		int menuNo = scanner.nextInt();
		
		System.out.println("");
		
		switch(menuNo) {
			case 1 : 
				getMyInfoMenu(memberId);
				break;
			case 2 : 
				setMyInfoMenu(memberId);
				break;	
			case 3:
				setMemberPwMenu(memberId);
				break;
			case 4:
				removeMemberMenu(memberId);
				break;	
		}		
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
					
		if(no == 0) {
			mainMenu(memberId);
		}
		introMenu();
		scanner.close();
	}


	/**
	 * <pre>
	 * <2>메인 화면
	 * 4. 내 정보 메뉴
	 * 	4-1. 내 정보 조회
	 * </pre>
	 */
	public void getMyInfoMenu(String memberId) {
		
		printTitle("내정보 조회");
		System.out.print(memberId+"님");	
//		System.out.print("▶ 아이디 : ");
//		String memberId = scanner.next();
		
		System.out.print("회원님의 비밀번호를 입력 바랍니다.");	
		System.out.print("▶ 비밀번호 : ");
		String memberPw = scanner.next();
			
		Member dto = service.getMember(memberId, memberPw);
		if(dto != null) {
			System.out.println(dto);
		} else {
			System.out.println("조회 실패 : 회원정보가 존재하지 않습니다.");
		}
			
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
			
		if(no == 0) {
			mainMenu(memberId);
		}
		
		introMenu();
		
		System.out.println("[오류] 존재하지 않는 회원입니다.");
		
		scanner.close();
	}
	
	/**
	 * <pre>
	 * <2> 메인 화면
	 * 4. 내 정보 메뉴
	 * 	4-2. 내 정보 변경 (아이디/비밀번호 인증 화면)
	 * </pre>
	 */
	private void setMyInfoMenu(String memberId) {
		printTitle("내정보 변경");
		
		System.out.println(">> "+memberId+"님");
//		System.out.print("▶ 아이디 : ");
//		String memberId = scanner.next();
			
		System.out.print("▶ 비밀번호 : ");
		String memberPw = scanner.next();
			
		Member dto = service.getMember(memberId, memberPw);
		if(dto != null) {
			setMyInfoMenu2(memberId);
		} else {
			System.out.println("조회 실패 : 회원정보가 존재하지 않습니다.");
		}
			
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
			
		if(no == 0) {
			mainMenu(memberId);
		}
		
		introMenu();
		
		System.out.println("[오류] 존재하지 않는 회원입니다.");
		
		scanner.close();
		
	}
	
	/**
	 * <pre>
	 * <2> 메인 화면
	 * 4. 내 정보 메뉴
	 * 	4-2. 내 정보 변경
	 * 		- 휴대폰
	 * 		- 이메일	
	 * </pre>
	 */
	private void setMyInfoMenu2(String memberId) {
		printTitle("내정보 변경");
		
		System.out.println("\t 1.휴대폰 변경");
		System.out.println("\t 2.이메일 변경");
		
		System.out.println(">> 번호를 입력바랍니다.");
		print("▷ 메뉴 번호 : ");
		int menuNo = scanner.nextInt();
		
		System.out.println("");
		
		switch(menuNo) {
			case 1 : 
				setMyInfoMobileMenu(memberId);
				break;
			case 2 : 
				setMyInfoMobileEmail(memberId);
				break;	
		}		
		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
					
		if(no == 0) {
			mainMenu(memberId);
		}
		introMenu();
		scanner.close();
		
	}


	/**
	 * <pre>
	 * <2> 메인 화면
	 * 4. 내 정보 메뉴
	 * 	4-2. 내 정보 변경
	 * 		- 휴대폰
	 * </pre>
	 * @param memberId 아이디
	 */
	private void setMyInfoMobileMenu(String memberId) {
		printTitle("휴대폰 변경");
		System.out.println(">> "+memberId+"님");
//		print("▷ 아이디 : ");
//		String memberId = scanner.next();
		
		System.out.println(">> 새로운 휴대폰 번호를 입력하세요.");
		print("▷ 변경 휴대폰 : ");
		String mobile = scanner.next();
		
	
		boolean result = service.setMemberMobile(memberId,mobile);
		if(result) {
			System.out.println("휴대폰이 변경되었습니다.");
		} else {
			System.out.println("[오류] 휴대폰 변경");
		}		
			System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
			int no = scanner.nextInt();
				
			if(no == 0)
			{
				mainMenu( memberId);
			}
			
			introMenu();
				
		
		
		scanner.close();
	}
	
	/**
	 * <pre>
	 * <2> 메인 화면
	 * 4. 내 정보 메뉴
	 * 	4-2. 내 정보 변경
	 * 		- 휴대폰
	 * </pre>
	 * @param memberId 아이디
	 */		
	
	private void setMyInfoMobileEmail(String memberId) {
		printTitle("휴대폰 변경");
		System.out.println(">> "+memberId+"님");
//		print("▷ 아이디 : ");
//		String memberId = scanner.next();
		
		System.out.println(">> 새로운 이메일을 입력하세요.");
		print("▷ 변경 이메일 : ");
		String email = scanner.next();
		
	
		boolean result = service.setMemberMobile(memberId,email);
		if(result) {
			System.out.println("이메일이 변경되었습니다.");
		} else {
			System.out.println("[오류] 이메일 변경");
		}		
			System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
			int no = scanner.nextInt();
				
			if(no == 0)
			{
				mainMenu( memberId);
			}
			
			introMenu();
				
		
		
		scanner.close();
		
	}

	/**
	 * <pre>
	 * <2>메인 화면
	 * 4.내 정보 메뉴
	 * 	4-3. 비밀번호 변경
	 * </pre>
	 */
	public void setMemberPwMenu (String memberId)  {
		printTitle("비밀번호 변경");
		System.out.println(">> "+memberId+"님");
//		print("▷ 아이디 : ");
//		String memberId = scanner.next();
		
		System.out.println(">> 현재 비밀번호를 입력하세요.");
		print("▷ 비밀번호 : ");
		String memberPw = scanner.next();
		
		System.out.println(">> 변경하고자 하는 비밀번호를 입력하세요.");
		print("▷ 변경 비밀번호 : ");
		String modifyMemberPw = scanner.next();
		
	
		boolean result = service.setMemberPw(memberId,memberPw,modifyMemberPw);
		if(result) {
			System.out.println("비밀번호가 변경되었습니다.");
		} else {
			System.out.println("[오류] 존재하지 않는 아이디 혹은 비밀번호입니다.");
		}		
			System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
			int no = scanner.nextInt();
				
			if(no == 0)
			{
				mainMenu( memberId);
			}
			
			introMenu();
				
		
		
		scanner.close();
	}
	
	/**
	 * <pre>
	 * <2>메인 화면
	 * 4.내 정보 메뉴
	 * 	4-4. 회원 탈퇴
	 * </pre>
	 */
	private void removeMemberMenu(String memberId) {
		printTitle("회원 탈퇴");
		System.out.println(">>[경고] 회원 탈퇴를 하면 계정이 복구가 안됩니다.");
		System.out.println(">> 회원탈퇴를 하시겠습니까? \n 예 - 1, 아니오 - 그 외 번호를 입력바랍니다. ");
		int removeNo = scanner.nextInt();
	
		if(removeNo == 1) {
			boolean result = service.removeMember(memberId);
			if(result) {
				System.out.println("[성공] 회원 탈퇴");
			} else {
				System.out.println("[오류] 회원 탈퇴");
			}
		} 		
		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
		int no = scanner.nextInt();
				
		if(no == 0) {
			mainMenu(memberId);
		}
		introMenu();
				
		scanner.close();
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
		System.out.println(""+"\t"+item+"\t"+"      ");
	}
	
	/**
	 * 문자열 출력 메서드 - 한줄 띄움 없음.
	 */
	public void print(String text) {
		System.out.print(text);
	}
}


