/**
 * 
 */
package com.work.view;

/**
 * <pre> 
 * 수입 내역 관리 화면
 * </pre>
 * @author Playdata
 *
 */
public class IncomeMenu {
	
	/**기본 생성자*/
	public IncomeMenu() {

	}
	
	/**
	 * 수입 등록 화면
	 */
	public void registerIncomeMenu() {
		
	}
	
	/**
	 * 수입 삭제 화면
	 */
	public void removeIncomeMenu() {
		
	}
	
	/**
	 * 수입 변경 화면
	 */
	public void setIncomeMenu() {
		
	}
	
	/**
	 * <pre>
	 * 수입 조회 화면
	 * 1. 수입 전체 조회 화면
	 * 2. 수입 상세 조회 화면  
	 * </pre>
	 */
	public void getIncomeMainMenu() {
		
	}
	
	/**
	 * <pre>
	 * 수입 조회 화면
	 * 1. 수입 전체 조회 화면
	 * </pre>
	 */
	public void getAllIncomeMenu() {
		
	}
	
	/**
	 * <pre>
	 * 수입 조회 화면
	 * 2. 수입 상세 조회 화면 
	 * 		(1) 기간별 조회 전체 화면
	 * </pre> 
	 */
	public void getDetailIncomeMenu() {
		
	}
	
	/**
	 * <pre>
	 * 수입 조회 화면
	 * 2. 수입 상세 조회 화면 
	 * 		(2) 항목별 항목 전체 화면
	 * </pre> 
	 */
	public void getPeiodDetailIncomeMenu() {
		
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
	
}
