///**
// * 
// */
//package com.work.view;
//
//import java.util.Scanner;
//
//import com.work.exception.CommonException;
//import com.work.model.service.MemberService;
//import com.work.util.Utility;
//
///**
// * <pre> 
// * 수입 내역 관리 화면
// * </pre>
// * @author Playdata
// *
// */
//public class IncomeMenu {
//	
//	Scanner scanner = new Scanner(System.in);
//	Utility util = new Utility();
//	MemberService service = new MemberService();
//	/**기본 생성자*/
//	public IncomeMenu() {
//
//	}
//	
//	/**
//	 * <pre>
//	 * <2>메인 화면
//	 * 1. 수입내역 관리 메인화면
//	 */
//	public void incomeMainMenu() {
//		printTitle("수입 내역 관리 화면");
//		
//		printMenuItem("1.수입 내역 등록");
//		printMenuItem("2.수입 내역 조회");
//		printMenuItem("3.수입 내역 삭제");
//		printMenuItem("4.수입 내역 변경");
//		printMenuItem("0.이전 화면으로 가기");
//		printLine();
//		
//		System.out.print(">>  메뉴번호 입력 :");
//		int menuNo = scanner.nextInt();
//		
//		switch(menuNo) {
//		case 1 : 
//			registerIncomeMenu();
//			break;
//		case 2 : 
//			getIncomeMainMenu();
//			break;
//		case 3 : 
//			removeIncomeMenu();
//			break;
//		case 4 : 
//			setIncomeMenu();
//			break;
//		case 0 : 
//			//나중에 옮길때 꼭 쓰기
//			break;		
//		default : 
//			System.out.println("메뉴번호 오류");
//			break;
//		}
//		scanner.close();
//	}
//	
//	/**
//	 * 수입 등록 화면
//	 */
//	public void registerIncomeMenu() {
//		printTitle("수입 내역 등록");
//		System.out.println("<"+util.getCurrentDate()+">");
//		print("\n▶ 내 수입 : ");
//		int revenue = scanner.nextInt();
//		
//		System.out.println();
//		System.out.println("\n>> 출처 - 1.월급 2.용돈 3.배당금 4.기타 ");
//		System.out.println("출처는 \"문자\"로 입력해주시길 바랍니다.");
//		print("\n▶ 출처 : ");
//		String source = scanner.next();
//		
//		try {
//			service.addIncome(util.getCurrentDate(), revenue, source);
//		} catch (CommonException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
//		int no = scanner.nextInt();
//		
//		if(no == 0)
//		{
//			incomeMainMenu();
//		}
//			scanner.close();
//			System.exit(0);
//		
//	}
//		
//	
//	
//	/**
//	 * 수입 삭제 화면
//	 */
//	public void removeIncomeMenu() {
//		printTitle("수입 내역 삭제");
//		System.out.println("<"+util.getCurrentDate()+">");
//		System.out.println("\n>> 삭제할 수입은 숫자만 입력하세요.");
//		print("\n▶ 삭제할 수입 : ");
//		int revenue = scanner.nextInt();
//		
//		
//		System.out.println();
//		System.out.println("\n>> 출처 - 1.월급 2.용돈 3.배당금 4.기타 ");
//		System.out.println("출처는 \"문자\"로 입력해주시길 바랍니다.");
//		print("\n▶ 삭제할 출처 : ");
//		String source = scanner.next();
//		
//		System.out.println();
//		System.out.println("\n>>날짜는 yyyy-MM-dd 형식 지켜주시길 바랍니다.");
//		print("\n▶ 삭제할 날짜 : ");
//		String date = scanner.next();
//		
//		service.removeIncome(date, revenue, source);
//		
//		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
//		int no = scanner.nextInt();
//		
//		if(no == 0)
//		{
//			incomeMainMenu();
//		}
//			scanner.close();
//			System.exit(0);
//	}
//	
//
//	
//	/**
//	 * <pre>
//	 * 수입 조회 화면
//	 * 1. 수입 전체 조회 화면
//	 * 2. 수입 상세 조회 화면  
//	 * </pre>
//	 */
//	public void getIncomeMainMenu() {
//			printTitle("수입 조회 관리 화면");
//			
//			printMenuItem("1. 수입 전체 조회 화면");
//			printMenuItem("2. 수입 상세 조회 화면");
//			printMenuItem("0. 이전 화면");
//			printLine();
//			
//			System.out.print(">>  메뉴번호 입력 :");
//			int menuNo = scanner.nextInt();
//			
//			switch(menuNo) {
//			case 1 : 
//				getAllIncomeMenu();
//				break;
//			case 2 : 
//				getDetailIncomeMenu();
//				break;
//			case 0 : 
//				incomeMainMenu();
//				break;
//			default : 
//				System.out.println("메뉴번호 오류");
//				break;
//			}
//			scanner.close();
//	}
//	
//	/**
//	 * <pre>
//	 * 수입 조회 화면
//	 * 1. 수입 전체 조회 화면
//	 * </pre>
//	 */
//	public void getAllIncomeMenu() {
//		printTitle("수입 전체 조회 화면");
//		service.getIncome();
//		
//		System.out.println(">> 이전 메뉴로 돌아가려면 0번을 눌러주세요.");
//		int no = scanner.nextInt();
//		
//		if(no == 0)
//		{
//			incomeMainMenu();
//		}
//			scanner.close();
//			System.exit(0);
//	}
//	
//	
//	
//	/**
//	 * <pre>
//	 * 수입 상세 조회 화면
//	 * 2. 수입 상세 조회 화면 
//	 * </pre> 
//	 */
//	public void getDetailIncomeMenu() {
//		printTitle("수입 상세 조회 관리 화면");
//		
//		printMenuItem("1. 항목별 상세 조회 화면");
//		printMenuItem("2. 기간별 상세 조회 화면");
//		printMenuItem("0. 이전 화면");
//		printLine();
//		
//		System.out.print(">>  메뉴번호 입력 :");
//		int menuNo = scanner.nextInt();
//		
//		switch(menuNo) {
//		case 1 : 
//			getItemDetailIncomeMenu();
//			break;
//		case 2 : 
//			getPeiodDetailIncomeMenu();
//			break;
//		case 0 : 
//			getIncomeMainMenu();
//			break;
//		default : 
//			System.out.println("메뉴번호 오류");
//			break;
//		}
//		scanner.close();
//		
//	}
//	
//	/**
//	 * <pre>
//	 * 수입 상세 조회 화면
//	 * 2. 수입 상세 조회 화면 
//	 * 		(1) 항목별 항목 전체 화면
//	 * </pre> 
//	 */
//	public void getItemDetailIncomeMenu() {}
//	
//	/**
//	 * <pre>
//	 * 수입 조회 화면
//	 * 2. 수입 상세 조회 화면 
//	 * 		(2) 기간별 조회 전체 화면
//	 * </pre> 
//	 */
//	public void getPeiodDetailIncomeMenu() {
//		
//	}
//	
//	/**
//	 * 수입 변경 화면
//	 */
//	public void setIncomeMenu() {
//		
//	}
//	
//	/**
//	 * 구분 선 출력 메서드
//	 */
//	public void printLine() {
//		System.out.println("==*=========================*==");
//	}
//	
//	
//	/**
//	 * 화면 타이틀 출력 메서드
//	 * @param title 메뉴 타이틀
//	 */
//	public void printTitle(String title) {
//		System.out.println();
//		printLine();
//		System.out.println("\t"+title+"\t");
//		printLine();
//	}
//	
//	/**
//	 * 메뉴 항목 출력 메서드
//	 * @param item 메뉴 항목
//	 */
//	public void printMenuItem(String item) {
//		System.out.println("|"+"\t"+item+"\t"+"      |");
//	}
//	
//	/**
//	 * 문자열 출력 메서드 - 한줄 띄움 없음.
//	 */
//	public void print(String text) {
//		System.out.print(text);
//	}
//}
