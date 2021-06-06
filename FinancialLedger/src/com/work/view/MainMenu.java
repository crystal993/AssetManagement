/**
 * 
 */
package com.work.view;

import java.util.Scanner;

/**
 * <pre>
 * CUI 메인 메뉴 화면 
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class MainMenu {

	Scanner scanner = new Scanner(System.in);
	
	/**
	 * 기본 생성자
	 */
	public MainMenu() {

	}
	
	/**
	 * 메인 화면
	 */
	public void mainMenu() {
		printTitle("메인 화면");
		
		printMenuItem("1.수입");
		printMenuItem("2.지출");
		printMenuItem("3.예산");
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
		default : 
			System.out.println("메뉴번호 오류");
			break;
		}
		scanner.close();
	}
	
	/**
	 * 수입내역 관리 메인화면
	 */
	public void incomeMainMenu() {
		
	}
	
	/**
	 * 지출내역 관리 메인화면
	 */
	public void spendMainMenu() {
		
	}
	
	/**
	 * 예산내역 관리 메인화면
	 */
	public void budgetMainMenu() {
		
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
		System.out.println("|"+"\t\t"+item+"\t"+"        |");
	}
	
	
}
