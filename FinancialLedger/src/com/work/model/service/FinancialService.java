///**
// * 
// */
//package com.work.model.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map.Entry;
//import java.util.Set;
//
//import com.work.model.dto.Member;
//
///**
// * <pre>
// * 수입 내역 관리 서비스
// * </pre>
// * @author 김수정
// * @version ver.1.0
// * @param <E>
// * @since jdk1.8
// */
//public class FinancialService<E> {
//	
//	//지출 
//	ArrayList<String> incomeDates = new ArrayList<String>();
//	ArrayList<Integer> income = new ArrayList<Integer>();
//	ArrayList<Integer> spend = new ArrayList<Integer>();
//	ArrayList<String> sources = new ArrayList<String>();
//	
//	
////	ArrayList<Member> members = new ArrayList<Member>();
////	MemberService memberService = new MemberService();
////	
//	int balance = 0;
//	int totalInMoney = 0;
//	int totalOutMoney = 0;
//	
//	
//	/**테스트용*/
//	public void initFinancial() {
//		members.initMembers();
//	}
//	
//	/**
//	 * <pre>
//	 * 회원 존재 유무 조회 메서드
//	 * -- 처리 절차
//	 * 1. members 크기만큼 반복
//	 * 2. members에 존재하는 memberId 와 전달받은 memberId가 같은지 비교
//	 * 3. 존재하면 해당 객체의 인덱스 반환, 존재하지 않으면 -1 반환
//	 * </pre>
//	 * @param dto
//	 * @return 존재하면 해당 객체의 인덱스 반환, 존재하지 않으면 -1 반환
//	 */
//	public int exist(String memberId) {
//		for(int index = 0; index < members.size() ; index++) {
//			if(members.get(index).getMemberId().equals(memberId)) {
//				return index;
//			}
//		}
//		return -1;
//	}
//	
//	/**수입 내역 등록 메서드*/
//	public void addIncome(String date, int revenue, String source) {
//				int index = exist(memberService.currentMemberId);
//		
//				incomeDates.add(date);
//				income.add(revenue);
//				sources.add(source);
//				
//				members.get(index).setIncomeDates(incomeDates);
//				members.get(index).setIncome(income);
//				members.get(index).setSources(sources);
//				System.out.println("수입내역이 등록 되었습니다.");
//	}
//
//	/**수입 내역 삭제 메서드*/
//	public void removeIncome(String date, int revenue, String source) {
//				incomeDates.remove(date);
//				income.remove(revenue);
//				sources.remove(source);
//				System.out.println("수입내역이 삭제 되었습니다.");
//	}
//	
//	//전체 조회
//	public void getIncome() {
//		int currentIndex = exist(memberService.currentMemberId);
//		
//		for(int index = 0; index <income.size() ; index++ ) {
//			System.out.println();
//			System.out.println(" 날짜 : " + members.get(currentIndex).getIncomeDates(index));
//			System.out.printf(" 수입 : %,d원%n", members.get(currentIndex).getIncome(index));
//			System.out.printf(" 출처 : "+members.get(currentIndex).getSources(index));
//			System.out.println();
//		}
//	}
//	
//	//상세조회 1. 기간별 조회
//	public void getDateIncome(String currentDate, int revenue, String source) {
//		
//	}
//	
//	//상세 조회 2. 항목별 조회
//	public void getItemIncome(String currentDate, int revenue, String source) {
//		
//	}
//	
////	지출
////		/**항목과 지출을 같이 관리하는 저장 구조 : 필수*/
////		private HashMap<String, Integer> itemSpend = new HashMap<String, Integer>();
////		
////		/**날짜와 지출을 같이 관리하는 저장 구조 : 필수*/
////		private HashMap<String, Integer> dateSpend = new HashMap<String, Integer>();
////		
////		/**결제수단과 지출을 같이 관리하는 저장 구조 : 필수*/
////		private HashMap<String, Integer> typeSpend = new HashMap<String, Integer>();
////		
////		//수입
////		/**항목과 수입을 같이 관리하는 저장 구조 : 필수*/
////		private HashMap<String, Integer> itemIncome = new HashMap<String, Integer>();
////		
////		/**날짜와 수입을 같이 관리하는 저장 구조 : 필수*/
////		private HashMap<String, Integer> dateIncome = new HashMap<String, Integer>();
////	
////	
////    ArrayList<Member> members = new ArrayList<Member>();
////	
////	/** 기본 생성자 */
////	public FinancialService() {
////		
////	}
////	
////	/**테스트용*/
////	public void initFinancial() {
////		itemIncome.put("용돈", 3000);
////		itemIncome.put("월급", 20000);
////	}
////	
////	public void addIncome(String date, int revenue, String source) {
////		
////		Integer money = new Integer(revenue);
////		itemIncome.put(source, money);
////		dateIncome.put(date,money);
////		System.out.println(">> 입력 되었습니다.");
////		
////	}
////
////
////	public void removeIncome(String currentDate, int revenue, String source) {
////		// TODO Auto-generated method stub
////		Integer money = new Integer(revenue);
////		itemIncome.remove(source, revenue);
////		dateIncome.remove(currentDate, revenue);
////		System.out.println(">> 삭제 되었습니다.");
////	}
////	
////	//전체 조회
////	public void getIncome() {
////		Iterator<String> iterator = itemIncome.keySet().iterator();
////		while(iterator.hasNext()) {
////			String key = iterator.next(); 
////			
////			if(key.equals("용돈")) {
////			Integer value = itemIncome.get(key);
////			int Money = Integer.valueOf(value); 
////			}
////			System.out.println("[key] : "+key+" [value] : "+value);
////		}
////	}
////	
////	//상세조회 1. 기간별 조회
////	public Set<Entry<String, Integer>> getDateIncome(String currentDate, int revenue, String source) {
////		return dateIncome.entrySet();
////	}
////	
////	//상세 조회 2. 항목별 조회
////	public Set<Entry<String, Integer>> getItemIncome(String currentDate, int revenue, String source) {
////		return itemIncome.entrySet();
////	}
//	
//	
//}
