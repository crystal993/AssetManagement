/**
 * 
 */
package com.work.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Budget;
import com.work.model.dto.Member;
import com.work.util.*;

/**
 * <pre>
 * 회원과 가계부 관리 서비스 클래스
 * Collection API 활용 
 * -- ArrayList
 * -- Generic Collection 
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class MemberService {
	
	
	Utility util = new Utility();
	
	
	
	/**회원들을 관리하기 위한 저장 구조 - ArrayList*/
	private ArrayList<Member> members = new ArrayList<Member>();
	
	/**예산을 관리하기 위한 저장 구조 - ArrayList*/
	private ArrayList<Budget> budgets = new ArrayList<Budget>();
	
	/**프로그램이 실행 될 동안 식별을 위해 쓰일 현재 멤버 아이디 변수*/
	public String currentMemberId = null;

	
	/** 기본 생성자 */
	public MemberService() {

	}
	
	/**회원 초기화 - 테스트를 위함
	 * @throws DuplicateException 
	 * @throws CommonException */
	public int initMembers( ) throws DuplicateException, CommonException {
		Member dto1 = new Member("user01", "password01", "홍길동", "01012341000", "user01@work.com");
		Member dto2 = new Member("user02", "password02", "강감찬", "01012342000", "user02@work.com");
		Member dto3 = new Member("user03", "password03", "이순신", "01012343000", "user03@work.com");
		Member dto4 = new Member("user04", "password04", "김유신", "01012344000", "user04@work.com");
		Member dto5 = new Member("user05", "password05", "유관순", "01012345000", "user05@work.com");
		
		//회원 등록
		addMember(dto1);
		addMember(dto2);
		addMember(dto3);
		addMember(dto4);
		addMember(dto5);
		
		//dto1 테스트 용
		Budget budgetDto1 = new Budget("user01",60000);
		Budget budgetDto2 = new Budget("user02",60000);
		Budget budgetDto3 = new Budget("user03",60000);
		Budget budgetDto4 = new Budget("user04",60000);
		Budget budgetDto5 = new Budget("user05",60000);
		
		addIncome(dto1,"2021-06-04",5000,"배당금");
		addIncome(dto1,"2021-06-04",5000,"용돈");
		addIncome(dto1,"2021-06-04",5000,"기타");
		addIncome(dto1,"2021-06-05",3000,"기타");
		addIncome(dto1,"2021-06-05",2000,"용돈");
		addIncome(dto1,"2021-06-05",20000,"월급");
		addIncome(dto1,"2021-06-06",8000,"용돈");
		addIncome(dto1,"2021-06-07",3000,"배당금");
		addIncome(dto1,"2021-06-07",3000,"용돈");
		addIncome(dto1,"2021-06-08",3000,"기타");
		
		addSpend(dto1,"2021-06-04",6000,"식비","카드");
		addSpend(dto1,"2021-06-04",10000,"식비","카드");
		addSpend(dto1,"2021-06-05",30000,"문화비","현금");
		addSpend(dto1,"2021-06-05",3000,"문화비","현금");
		addSpend(dto1,"2021-06-06",2000,"교통비","카드");
		addSpend(dto1,"2021-06-06",2000,"기타","카드");
		addSpend(dto1,"2021-06-07",10000,"주거비","기타");
		addSpend(dto1,"2021-06-07",30000,"의류비","기타");
		addSpend(dto1,"2021-06-08",2000,"교통비","기타");
		
		//dto2 테스트 용
				
		addIncome(dto2,"2021-06-04",5000,"배당금");
		addIncome(dto2,"2021-06-04",5000,"용돈");
		addIncome(dto2,"2021-06-05",2000,"기타");
		addIncome(dto2,"2021-06-06",2000,"용돈");
		addIncome(dto2,"2021-06-06",50000,"월급");
		addIncome(dto2,"2021-06-07",3000,"배당금");
		addIncome(dto2,"2021-06-07",3000,"용돈");
		
		
		return members.size();
	}
	
	/**
	 * 현재 등록 인원수 조회 
	 * @return 현재 등록 인원수
	 */
	public int getSize( ) {
		return members.size();
	}
	
	
	/**
	 * <pre>
	 * 회원 존재 유무 조회 메서드
	 * -- 처리 절차
	 * 1. members 크기만큼 반복
	 * 2. members에 존재하는 memberId 와 전달받은 memberId가 같은지 비교
	 * 3. 존재하면 해당 객체의 인덱스 반환, 존재하지 않으면 -1 반환
	 * </pre>
	 * @param dto
	 * @return 존재하면 해당 객체의 인덱스 반환, 존재하지 않으면 -1 반환
	 */
	public int exist(String memberId) {
		for(int index = 0; index < members.size() ; index++) {
			if(members.get(index).getMemberId().equals(memberId)) {
				currentMemberId = memberId;
				return index;
			}
		}
		return -1;
	}
	
	
	/**
	 * <pre>
	 * 회원 등록 1
	 * 
	 * -- 처리 절차
	 * 1. 존재 유무 판별 
	 * 2. 존재하면 중복 오류
	 * 3. 존재하지 않으면 회원 등록
	 * </pre>
	 * @param dto 회원
	 * @throws DuplicateException 
	 */
	public boolean addMember(Member dto) throws DuplicateException {
		int index = exist(dto.getMemberId());
		if(index >= 0) {
			throw new DuplicateException(dto.getMemberId());
		}
		members.add(dto);
		return true;
	}
	
	/**
	 * <pre>
	 * 회원 등록 2
	 * - 필수 데이터 바로 초기화 가능한 메서드
	 * </pre>
	 * @param dto 회원
	 * @return 
	 * @throws DuplicateException 
	 */
	public boolean addMember(String memberId, String memberPw, String name, String mobile, String email) throws DuplicateException {
		Member dto = new Member(memberId, memberPw, name, mobile, email);
		
			addMember(dto);
			return true;
	}
	
	/**
	 * 회원 상세 조회 1
	 * @param dto 회원
	 * @return 존재하면 회원 반환, 존재하지 않으면 오류
	 * @throws RecordNotFoundException 
	 */
	public Member getMember(Member dto) throws RecordNotFoundException {
		int index = exist(dto.getMemberId());
		if(index == -1) {
			throw new RecordNotFoundException();
		}
		return members.get(index);
	}
	
	/**
	 * 회원 상세 조회 2
	 * @param dto 회원
	 * @return 존재하면 회원 반환, 존재하지 않으면 오류
	 * @throws RecordNotFoundException 
	 * @throws CommonException 
	 */
	public Member getMember(String memberId, String memberPw) throws RecordNotFoundException, CommonException {
		Member dto = getMember(memberId);
		if(dto.getMemberPw().equals(memberPw)) {
			return dto;
		}
		throw new CommonException("회원의 정보가 올바르지 않습니다.");
	}
	
	/**
	 * 회원 조회 3 - 로그인에 쓸 메서드
	 * @param memberId 아이디
	 * @return 존재하면 회원 반환, 존재하지 않으면 오류
	 * @throws RecordNotFoundException 
	 */
	public Member getMember(String memberId) throws RecordNotFoundException {
		int index = exist(memberId);
		if(index == -1) {
			throw new RecordNotFoundException();
		}
		return members.get(index);
	}
	
	
	/**
	 * 회원 전체 변경
	 * @param dto 회원
	 * @throws RecordNotFoundException 
	 */
	public boolean setMembers(Member dto) throws RecordNotFoundException {
		int index = exist(dto.getMemberId());
		if(index >= 0)		
		{
			members.set(index, dto); 
			return true;
		}
		throw new RecordNotFoundException();
		//return false;
	}
	
	/**
	 * 회원 비밀번호 변경
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param modifyMemberPw 바꿀 비밀번호
	 * @return 아이디와 비밀번호가 존재하면 true, 아니면 오류 
	 * @throws RecordNotFoundException 
	 */
	public boolean setMemberPw(String memberId, String memberPw, String modifyMemberPw) throws RecordNotFoundException {
		int index = exist(memberId);
		Member dto = members.get(index);
		
		if(index >= 0 && dto.getMemberPw().equals(memberPw) ) {
			dto.setMemberPw(modifyMemberPw);
			return true;
		}	
		//return false;
		throw new RecordNotFoundException();
	}
	
	
	/**
	 * 회원 탈퇴
	 * @param dto 회원
	 * @return 회원이 존재하면 탈퇴 후 true, 존재하지 않으면 오류
	 * @throws RecordNotFoundException
	 */
	public boolean removeMember(Member dto) throws RecordNotFoundException { 
		int index = exist(dto.getMemberId());
		
		if(index >= 0) {
			members.remove(index);
			return true;
		}
		//return false;
		throw new RecordNotFoundException();
	}
	
	
	/**
	 * 로그인
	 * @param memberId
	 * @param memberPw
	 * @return 성공시 true, 실패하면 false
	 * @throws RecordNotFoundException 
	 * @throws CommonException 
	 */
	public boolean login(String memberId, String memberPw) throws RecordNotFoundException, CommonException {
		
		Member dto = getMember(memberId);
		if(dto.getMemberPw().equals(memberPw)) {
			currentMemberId = memberId;
			return true;
		}
		throw new CommonException("회원의 정보가 올바르지 않습니다.");
		//return false;
	}
	
	
	/**
	 * 아이디 찾기1 - 휴대폰
	 * @param mobile 휴대폰
	 * @return 휴대폰 번호가 존재하면 해당 인스턴스의 아이디를 반환
	 * @throws CommonException
	 */
	public String findId1(String mobile) throws CommonException {
		for(int index = 0 ; index < members.size() ; index++) {
			if(members.get(index).getMobile().equals(mobile)) {
				return members.get(index).getMemberId();
			}
		}
		throw new CommonException("존재하지 않거나 잘못 입력된 번호입니다.");
	}
	
	/**
	 * 아이디 찾기2 - 이메일
	 * @param email 이메일
	 * @return 이메일이 존재하면 해당 인스턴스의 아이디를 반환
	 * @throws CommonException
	 */
	public String findId2(String email) throws CommonException {
		for(int index = 0 ; index < members.size() ; index++) {
			if(members.get(index).getEmail().equals(email)) {
				return members.get(index).getMemberId();
			}
		}
		throw new CommonException("존재하지 않거나 잘못 입력된 번호입니다.");
	}	
	
	
	/**
	 * 비밀번호 찾기
	 * @param memberId 아이디
	 * @param mobile 휴대폰
	 * @return 아이디와 휴대폰 번호가 맞으면(true) 임시 비밀번호를 기존 비밀번호에 업데이트 후 반환, false이면 오류메세지
	 * @throws CommonException
	 */
	public String findPw(String memberId, String mobile) throws CommonException {
		Utility util = new Utility();
		
		
		int index = exist(memberId);
			if(members.get(index).getMobile().equals(mobile)) {
				String tempMemberPw =  util.getSecureAlphabetString(8, false, true);
				members.get(index).setMemberPw(tempMemberPw);
				return tempMemberPw;
			}
		
		throw new CommonException("존재하지 않거나 잘못 입력된 번호입니다.");
	}
	

	
	
	// 수입 내역 관련 메서드
	
	/**
	 * 수입 내역 등록 메서드
	 * @param date 날짜
	 * @param revenue 수입
	 * @param source 수입 출처
	 * @return 회원 존재하면 날짜, 수입, 수입출처 등록되면 true, 아니면 false
	 * @throws CommonException
	 */
	public boolean addIncome(String date, int revenue, String source) throws CommonException {
				int currentIndex = exist(currentMemberId);
				int currentBudget = budgets.get(currentIndex).getBudget();
				int updateBudget = 0;
				
				if (currentIndex >= 0) {
					
					members.get(currentIndex).setIncomeDates(date);
					members.get(currentIndex).setIncome(revenue);
					members.get(currentIndex).setSources(source);
					
					updateBudget = currentBudget + revenue;
					members.get(currentIndex).setBudget(updateBudget);
					
					System.out.println("수입내역이 등록 되었습니다.");
					return true;
				} 
				throw new CommonException("수입 내역이 존재하지 않습니다.");
	}
	
	

	/**
	 * 수입 내역 등록 메서드 - 초기화용
	 * @param dto 객체
	 * @param date 날짜 
	 * @param revenue 수입
	 * @param source 수입 출처 
	 * @return 아이디가 존재하고, 수입이 등록되면 true 반환, 아니면 false 반환 
	 * @throws CommonException
	 */
	public boolean addIncome(Member dto, String date, int revenue, String source) throws CommonException {
				int index = exist(dto.getMemberId());
				int currentBudget = members.get(index).getBudget();
				int updateBudget = 0;
				
				if (index >= 0) {
					
					members.get(index).setIncomeDates(date);
					members.get(index).setIncome(revenue);
					members.get(index).setSources(source);
					
					updateBudget = currentBudget + revenue;
					members.get(index).setBudget(updateBudget);

					return true;
				} 
				throw new CommonException("수입 내역이 존재하지 않습니다.");
	}
	

	/**
	 * 수입 내역 삭제 메서드
	 * @param date 날짜
	 * @param revenue 수입
	 * @param source 수입 출처
	 */
	public void removeIncome(String date, int revenue, String source) {
				int currentIndex = exist(currentMemberId);
				int currentBudget = members.get(currentIndex).getBudget();
				int updateBudget = 0;
				
				members.get(currentIndex).removeIncomeDates(date);
				members.get(currentIndex).removeSources(source);
				members.get(currentIndex).removeIncome(revenue);
				
				
				updateBudget = currentBudget - revenue;
				members.get(currentIndex).setBudget(updateBudget);
				System.out.println("\n >> 수입내역이 삭제 되었습니다.");
	}
	
	/**
	 * 수입 전체 조회 
	 */
	public void getIncome() {
		int currentIndex = exist(currentMemberId);
		
		
		if(members.get(currentIndex).getBudget() != 0) {
		for(int index = 0; index < members.get(currentIndex).getIncomeSize() ; index++ ) {
			System.out.println();
			System.out.println(" 날짜 : " + members.get(currentIndex).getIncomeDates(index));
			System.out.printf(" 수입 : %,d원%n", members.get(currentIndex).getIncome(index));
			System.out.printf(" 출처 : "+members.get(currentIndex).getSources(index));
			
			System.out.println();
		}
		
			System.out.println("\n==*=========================*==");
			
			int sumIncomeMoney = 0;
			for(int index = 0; index < members.get(currentIndex).getIncomeSize() ; index++ ) {
				   sumIncomeMoney += members.get(currentIndex).getIncome(index);
			}
			
			System.out.println(" ▶ 총 수입 : " + sumIncomeMoney +"");
			System.out.println(" ▶ 현재 예산 : " + members.get(currentIndex).getBudget()+"\n");
		}
		else {
			System.out.println(">> 수입 내역이 존재하지 않습니다.\n");
			
		}
	}
	
	/**
	 * 상세조회 1. 기간별 조회
	 * @param startDate
	 * @param finishDate
	 * @throws RecordNotFoundException
	 */
	public void getDateIncome(String startDate, String finishDate) throws RecordNotFoundException {
		int currentIndex = exist(currentMemberId);
		int finishCount=0;
		int sumDateIncome = 0;
		
			if(currentIndex >=0) {
				if(members.get(currentIndex).getBudget() != 0) {
					
						int startDateIndex = members.get(currentIndex).getIncomeDates(startDate) ;
						int finishDateIndex = members.get(currentIndex).getIncomeDates(finishDate) ;
						int size = members.get(currentIndex).getIncomeSize();

						
						for(int index = startDateIndex; index < size ; index++ ) {
							if(members.get(currentIndex).getIncomeDates(index).equals(finishDate)) {
								
								finishCount++;
							}
						}
						
							for(int index = startDateIndex; index < finishDateIndex+finishCount; index++) {
								sumDateIncome = sumDateIncome + members.get(currentIndex).getIncome(index) ;
								System.out.print("["+members.get(currentIndex).getIncomeDates(index)+"] 수입 :");
								System.out.print(members.get(currentIndex).getIncome(index)+"원"
										+ "");
								System.out.println(", 출처 :"+members.get(currentIndex).getSources(index));
							}
							
							if(sumDateIncome > 0) {
								System.out.println("\n["+startDate+" ~ "+finishDate+"]\n");
								System.out.println("▶ 총 수입 :"+sumDateIncome);
								System.out.println("▶ 총 예산 :"+members.get(currentIndex).getBudget());
							} else {
							}
						
				} else {
					System.out.println(">> 수입 내역이 존재하지 않습니다.\n");
					throw new RecordNotFoundException();
					
				}
				throw new ArrayIndexOutOfBoundsException();
			}
		}
			
	/**
	 * 수입 시작날짜 조회 메서드
	 * @return 수입 시작날짜 
	 */
	public String getIncomeStartDates() {
		int currentIndex = exist(currentMemberId);
		return members.get(currentIndex).getIncomeDates(0);
	}
	
	/**
	 * 수입 끝날짜 조회 메서드
	 * @return 수입 끝날짜
	 */
		public String getIncomeFinishDates() {
			int currentIndex = exist(currentMemberId);
			int index=members.get(currentIndex).getIncomeSize()-1;
			
			return members.get(currentIndex).getIncomeDates(index);
		}
	
	
	/**
	 * 상세 조회 2. 수입출처별 조회 - 입력형태
	 * @param source 수입 출처
	 * @return 수입출처별 수입의 합계, sumItemIncome
	 * @throws RecordNotFoundException
	 */
	public int getItemIncome(String source) throws RecordNotFoundException {
		int currentIndex = exist(currentMemberId);
		int incomeSize = members.get(currentIndex).getIncomeSize();
		int sumItemIncome = 0;
		
		if(currentIndex >=0) {
			
			for(int index = 0; index < incomeSize ; index++) {
				if(members.get(currentIndex).getSources(index).equals(source)) {
					
					sumItemIncome = sumItemIncome + (int)(members.get(currentIndex).getIncome(index));
					
					System.out.println(" 날짜 : " + members.get(currentIndex).getIncomeDates(index));
					System.out.println(" 수입 : "+ members.get(currentIndex).getIncome(index)+"\n");
				}
			}
			return sumItemIncome;
		}
		throw new RecordNotFoundException();
	}

	/**
	 * 상세 항목별 전체 비율 조회 - 출처 1. 용돈 2. 월급 3. 배당금 4.기타 
	 */
	public void getItemIncomePortion() {
		int currentIndex = exist(currentMemberId);
		int incomeSize = members.get(currentIndex).getIncomeSize();
		
		// 돈 넣을 변수
		int pinMoney = 0;
		int salary = 0;
		int dividend = 0;
		int etc = 0;
		int sumIncome = 0;
		
		int width =20;
		
		// 각자의 금액을 담는다. 
		for(int index = 0; index < incomeSize; index++) {
			 
			if (members.get(currentIndex).getSources(index).equals("용돈")) {
					pinMoney += members.get(currentIndex).getIncome(index);
			}
			else if (members.get(currentIndex).getSources(index).equals("월급"))	{
					salary += members.get(currentIndex).getIncome(index);
			}
			else if (members.get(currentIndex).getSources(index).equals("배당금")) {
				dividend += members.get(currentIndex).getIncome(index);
			}
			else if (members.get(currentIndex).getSources(index).equals("기타"))
			{
					  etc += members.get(currentIndex).getIncome(index);
			}else {
				
			}	
			sumIncome += members.get(currentIndex).getIncome(index);
		}
		
		//비율 계산
		double pinMoneyPortion = (double)pinMoney/(double)sumIncome*100;
		double salaryPortion = (double)salary/(double)sumIncome*100;
		double dividendPortion = (double)dividend/(double)sumIncome*100;
		double etcPortion = (double)etc/(double)sumIncome*100;
		
		System.out.print(">> [총 수입] :"+sumIncome+"원\n");
		
		System.out.print(" [용돈] ");
		//용돈 비율만큼 ■ 프린트 
		for (int i = 0; i < pinMoneyPortion*width/100 ; i++ ) {
			System.out.print("■");
		}
		System.out.print(" "+(int)pinMoneyPortion+"%");
		System.out.println("    "+pinMoney+"원");
		
		
		System.out.print(" [월급] ");
		//월급 비율만큼 □ 프린트 
		for (int i = 0; i < salaryPortion*width/100 ; i++ ) {
			System.out.print("□");
		}
		System.out.print(" " + (int)salaryPortion + "%");
		System.out.println("    " + salary+"원");
		
		
		System.out.print("[배당금] ");
		//배당금 비율만큼 ■ 프린트 
		for (int i = 0; i < dividendPortion*width/100 ; i++ ) {
			System.out.print("■");
		}
		System.out.print(" " + (int)dividendPortion + "%");
		System.out.println("    " + dividend + "원");
		
		System.out.print(" [기타] ");
		//기타 비율만큼 □ 프린트 
		for (int i = 0; i < etcPortion*width/100 ; i++ ) {
			System.out.print("□");
		}
		System.out.print(" "+(int)etcPortion+"%");
		System.out.println("    " + etc + "원");
	}
	
	
	
	//예산 내역 관리 메서드들
	
	/**
	 * 초기 예산 등록 메서드 - 사용자 입력
	 * @param budget 예산
	 * @return 현재 예산이 0원이고 현재 로그인된 아이디가 존재하면 true 반환, 아니면 false 반환
	 * @throws DuplicateException
	 */
	public boolean addBudget(Budget budgetDto) throws DuplicateException {
		
		int index = exist(budgetDto.getMemberId());
		if(index >= 0) {
			throw new DuplicateException(budgetDto.getMemberId());
		}
		budgets.add(budgetDto);
		return true;
	}

		/**
		 * 예산 등록 메서드 - 초기화용(테스트)
		 * @param dto 객체
		 * @param budget 예산
		 * @return 현재 예산이 0원이고 현재 로그인된 아이디가 존재하면 true 반환, 아니면 false 반환
		 */
		public boolean addBudget(String memberid, int budget)  {
			
			int index = exist(dto.getMemberId());
			if(index == -1) {
				throw new RecordNotFoundException();
			}
			return members.get(index);
//			int currentIndex = exist(memberid);
//			int currentBudget = budget.getBudget();
//			
//			if(currentBudget == 0) {
//				if(currentIndex >= 0) {
//					members.get(currentIndex).setBudget(budget);
//					return true;
//				}
//			}
//			return false;
		}

		/**
		 * 현재 예산 조회 메서드
		 * @return 예산 
		 */
		public int getBudget() {
			int currentIndex = exist(currentMemberId);
			
			return members.get(currentIndex).getBudget();
		}

		/**
		 * 전체 예산 삭제 메서드
		 * @return 현재 예산이 0원이 아니고, 현재 아이디가 존재하는 아이디라면 true이고, 아니면 false
		 * @throws RecordNotFoundException
		 */
		public boolean removeBudget() throws RecordNotFoundException{
			int currentIndex = exist(currentMemberId);
			int currentBudget = members.get(currentIndex).getBudget();
			
				if(currentBudget != 0) {
					if(currentIndex >= 0) {
						members.get(currentIndex).setBudget(0);
						
						// 수입 삭제
						members.get(currentIndex).clearIncomeDates();
						members.get(currentIndex).clearIncome();
						members.get(currentIndex).clearSources();
						
						// 지출 삭제
						members.get(currentIndex).clearSpendDates();
						members.get(currentIndex).clearSpend();
						members.get(currentIndex).clearSpendType();
						members.get(currentIndex).clearSpendMethod();
						
						System.out.println(">> 예산이 삭제되었습니다.");
						return true;
					}
				}
				throw new RecordNotFoundException("예산 데이터가 존재하지 않습니다.");
		
		}
		
		
		//지출 메서드
		
		/**
		 * 지출 등록 메서드
		 * @param currentDate 현재 날짜
		 * @param spend 지출
		 * @param spendType 지출 항목
		 * @param spendMethod 결제 수단
		 * @return 현재 아이디가 존재하고 지출이 등록되면 true, 아니면 false
		 * @throws CommonException
		 */
		public boolean addSpend(String currentDate, int spend, String spendType, String spendMethod) throws CommonException {
			int currentIndex = exist(currentMemberId);
			int currentBudget =  members.get(currentIndex).getBudget();
			int updateBudget = 0;
			
			if (currentIndex >= 0) {
				
				members.get(currentIndex).setSpendDates(currentDate);
				members.get(currentIndex).setSpend(spend);
				members.get(currentIndex).setSpendType(spendType);
				members.get(currentIndex).setSpendMethod(spendMethod);
				
				updateBudget = currentBudget - spend;
				members.get(currentIndex).setBudget(updateBudget);
				
				System.out.println("지출 내역이 등록 되었습니다.");
				return true;
			} 
			throw new CommonException("지출 내역이 존재하지 않습니다.");
			
		}
		
		
		/**
		 * 지출 등록 메서드 - 초기화용, 테스트용
		 * @param dto 객체 
		 * @param currentDate 현재 날짜
		 * @param spend 지출
		 * @param spendType 지출항목
		 * @param spendMethod 결제수간
		 * @return 현재 아이디가 존재하고 지출이 등록되면 true, 아니면 false
		 * @throws CommonException
		 */
		public boolean addSpend(Member dto, String currentDate, int spend, String spendType, String spendMethod) throws CommonException {
			int index = exist(dto.getMemberId());
			int currentBudget =  members.get(index).getBudget();
			int updateBudget = 0;
			
			if (index >= 0) {
				
				members.get(index).setSpendDates(currentDate);
				members.get(index).setSpend(spend);
				members.get(index).setSpendType(spendType);
				members.get(index).setSpendMethod(spendMethod);
				updateBudget = currentBudget - spend;
				members.get(index).setBudget(updateBudget);
				
				return true;
			} 
			throw new CommonException("지출 내역이 존재하지 않습니다.");
			
		}
		
		/**
		 * 지출 전체 조회 메서드
		 */
		public void getSpend() {
			int currentIndex = exist(currentMemberId);
			
			
			if(members.get(currentIndex).getBudget() != 0) {
			for(int index = 0; index < members.get(currentIndex).getSpendSize() ; index++ ) {
				System.out.println();
				System.out.println(" 날짜 : " + members.get(currentIndex).getSpendDates(index));
				System.out.printf(" 지출 : %,d원%n", members.get(currentIndex).getSpend(index));
				System.out.println(" 지출 항목 : "+members.get(currentIndex).getSpendType(index));
				System.out.println(" 결제 수단 : "+members.get(currentIndex).getSpendMethod(index));
				
				System.out.println();
			}
			
				System.out.println("\n==*=========================*==");
				int sumSpendMoney = 0;
				for(int index = 0; index < members.get(currentIndex).getSpendSize() ; index++ ) {
					   sumSpendMoney += members.get(currentIndex).getSpend(index);
				}
				
				System.out.println(" ▶ 총 지출 : " + sumSpendMoney +"");
				System.out.println(" ▶ 현재 예산 : " + members.get(currentIndex).getBudget()+"\n");
			}
			else {
				System.out.println(">> 지출 내역이 존재하지 않습니다.\n");
				
			}
			
		}

		/**
		 * 지출 항목 상세 조회 메서드
		 * @param spendType 지출 항목
		 * @return 지출 항목에 해당되는 값들 더하여서 sumTypeSpend 반환
		 * @throws RecordNotFoundException
		 */
		public int getTypeSpend(String spendType) throws RecordNotFoundException {
				int currentIndex = exist(currentMemberId);
				int sumTypeSpend = 0;
				
				if(currentIndex >=0) {
					
					for(int index = 0; index < members.get(currentIndex).getSpendSize(); index++) {
						if(members.get(currentIndex).getSpendType(index).equals(spendType)) {
							
							sumTypeSpend = sumTypeSpend +(int)(members.get(currentIndex).getSpend(index));
							
							System.out.println(" 날짜 : " + members.get(currentIndex).getSpendDates(index));
							System.out.println(" 지출 : "+ members.get(currentIndex).getSpend(index)+"\n");
						}
					}
					return sumTypeSpend;
				}
				throw new RecordNotFoundException();
			}

		
		/**
		 * 지출 시작날짜 불러오는 메서드
		 * @return 지출 시작날짜
		 */
		public String getSpendStartDates() {
			int currentIndex = exist(currentMemberId);
			return members.get(currentIndex).getSpendDates(0);
		}

		/**
		 * 지출 끝날짜 불러오는 메서드
		 * @return
		 */
		public String getSpendFinishDates() {
			int currentIndex = exist(currentMemberId);
			int index=members.get(currentIndex).getSpendSize()-1;
			
			return members.get(currentIndex).getSpendDates(index);
		}
		
		
		/**
		 * 지출 기간별 상세 조회 메서드
		 * @param startDate 시작 날짜
		 * @param finishDate 끝 날짜
		 * @throws RecordNotFoundException
		 */
		public void getDateSpend(String startDate, String finishDate) throws RecordNotFoundException {
			int currentIndex = exist(currentMemberId);
			int finishCount=0;
			int sumDateSpend = 0;
			
				if(currentIndex >=0) {
					if(members.get(currentIndex).getBudget() != 0) {
						
							int startDateIndex = members.get(currentIndex).getSpendDates(startDate) ;
							int finishDateIndex = members.get(currentIndex).getSpendDates(finishDate) ;
							int size = members.get(currentIndex).getSpendSize();

							
							for(int index = startDateIndex; index < size ; index++ ) {
								if(members.get(currentIndex).getSpendDates(index).equals(finishDate)) {
									
									finishCount++;
								}
							}
							
								for(int index = startDateIndex; index < finishDateIndex+finishCount; index++) {
									sumDateSpend = sumDateSpend  + members.get(currentIndex).getSpend(index) ;
									System.out.print("["+members.get(currentIndex).getSpendDates(index)+"] 1. 지출 :");
									System.out.print(members.get(currentIndex).getSpend(index)+"원"
											+ "");
									System.out.print(", 2. 지출 항목 : "+members.get(currentIndex).getSpendType(index));
									System.out.println(", 3. 결제 수단 : "+members.get(currentIndex).getSpendMethod(index));
								}
								
								if(sumDateSpend > 0) {
									System.out.println("\n["+startDate+" ~ "+finishDate+"]\n");
									System.out.println("▶ 총 지출 :"+sumDateSpend);
									System.out.println("▶ 총 예산 :"+getBudget());
								} else {
								}
							
					} else {
						System.out.println(">> 지출 내역이 존재하지 않습니다.\n");
						throw new RecordNotFoundException();
					}
					
				}
		}
		
		/**
		 * 결제 수단별 상세 조회 메서드
		 * @param spendMethod 결제 수단
		 * @return 사용자가 선택한 결제수단의 지출 총 합
		 * @throws RecordNotFoundException
		 */
		public int getMethodSpend(String spendMethod) throws RecordNotFoundException {
			int currentIndex = exist(currentMemberId);
			int sumMethodSpend = 0;
			
			if(currentIndex >=0) {
				
				for(int index = 0; index < members.get(currentIndex).getSpendSize(); index++) {
					if(members.get(currentIndex).getSpendMethod(index).equals(spendMethod)) {
						
						sumMethodSpend = sumMethodSpend +(int)(members.get(currentIndex).getSpend(index));
						
						System.out.println(" 날짜 : " + members.get(currentIndex).getSpendDates(index));
						System.out.println(" 지출 : "+ members.get(currentIndex).getSpend(index)+"\n");
					}
				}
				return sumMethodSpend;
			}
			throw new RecordNotFoundException();
		}

		/**
		 * 지출 삭제
		 * @param date 날짜
		 * @param spend 지출
		 * @param spendType 지출 항목
		 * @param spendMethod 결제 수단
		 */
		public void removeSpend(String date, int spend, String spendType, String spendMethod) {
				int currentIndex = exist(currentMemberId);
				int currentBudget = members.get(currentIndex).getBudget();
				int updateBudget = 0;
				
				members.get(currentIndex).removeSpendDates(date);
				members.get(currentIndex).removeSpendType(spendType);
				members.get(currentIndex).removeSpend(spend);
				members.get(currentIndex).removeSpendMethod(spendMethod);
				
				
				updateBudget = currentBudget + spend;
				members.get(currentIndex).setBudget(updateBudget);
				System.out.println("\n >> 지출내역이 삭제 되었습니다.");
			
		}

		/**
		 * 전체 지출 항목별 비율 조회 메서드 - 지출 항목 : 1.식비 2.주거비 3.의류비 4.교통비 5.문화비 6.기타
		 */
		public void getSpendTypePortion() {
			int currentIndex = exist(currentMemberId);
			int spendSize = members.get(currentIndex).getSpendSize();
			
			// 돈 넣을 변수
			int foodExpenses = 0;
			int houseExpenses = 0;
			int clothingCost = 0;
			int transportationFee = 0;
			int culturalCost = 0;
			int etc = 0;
			
			int sumSpend = 0;
			
			int width =20;
			
			// 각자의 금액을 담는다. 
			for(int index = 0; index < spendSize; index++) {
				 
				if (members.get(currentIndex).getSpendType(index).equals("식비")) {
					foodExpenses += members.get(currentIndex).getSpend(index);
				}
				else if (members.get(currentIndex).getSpendType(index).equals("주거비")) {
					houseExpenses += members.get(currentIndex).getSpend(index);
				}
				else if (members.get(currentIndex).getSpendType(index).equals("의류비")) {
					clothingCost += members.get(currentIndex).getSpend(index);
				}
				else if (members.get(currentIndex).getSpendType(index).equals("교통비")) {
					transportationFee += members.get(currentIndex).getSpend(index);
				}
				else if (members.get(currentIndex).getSpendType(index).equals("문화비")) {
					culturalCost += members.get(currentIndex).getSpend(index);
				}
				else if (members.get(currentIndex).getSpendType(index).equals("기타"))
				{
						  etc += members.get(currentIndex).getSpend(index);
				}else {
					
				}	
				sumSpend += members.get(currentIndex).getSpend(index);
			}
			
			//비율 계산
			double foodExpensesPortion = (double)foodExpenses/(double)sumSpend*100;
			double houseExpensesPortion = (double)houseExpenses/(double)sumSpend*100;
			double clothingCostPortion = (double)clothingCost/(double)sumSpend*100;
			double transportationFeePortion = (double)transportationFee/(double)sumSpend*100;
			double culturalCostPortion = (double)culturalCost/(double)sumSpend*100;
			double etcPortion = (double)etc/(double)sumSpend*100;
			
			System.out.print(">> [총 지출] :"+sumSpend+"원\n");
			
			System.out.print(" [식비] ");
			//식비 비율만큼 ■ 프린트 
			for (int i = 0; i < foodExpensesPortion*width/100 ; i++ ) {
				System.out.print("■");
			}
			System.out.print(" "+(int)foodExpensesPortion+"%");
			System.out.println("\t"+foodExpenses+"원");
			
			
			System.out.print("[주거비] ");
			//주거비 비율만큼 □ 프린트 
			for (int i = 0; i < houseExpensesPortion*width/100 ; i++ ) {
				System.out.print("□");
			}
			System.out.print(" " + (int)houseExpensesPortion + "%");
			System.out.println("\t" + houseExpenses+"원");
			
			
			System.out.print("[의류비] ");
			//의류비 비율만큼 ■ 프린트 
			for (int i = 0; i < clothingCostPortion*width/100 ; i++ ) {
				System.out.print("■");
			}
			System.out.print(" " + (int)clothingCostPortion + "%");
			System.out.println("\t" + clothingCost + "원");
			
			System.out.print("[교통비] ");
			//교통비 비율만큼 □ 프린트 
			for (int i = 0; i < transportationFeePortion*width/100 ; i++ ) {
				System.out.print("□");
			}
			System.out.print(" " + (int)transportationFeePortion + "%");
			System.out.println("\t" + transportationFee + "원");
			
			System.out.print("[문화비] ");
			//문화비 비율만큼 ■ 프린트 
			for (int i = 0; i < culturalCostPortion*width/100 ; i++ ) {
				System.out.print("■");
			}
			System.out.print(" " + (int)culturalCostPortion + "%");
			System.out.println("\t" + culturalCost + "원");
			
			
			System.out.print(" [기타] ");
			//기타 비율만큼 □ 프린트 
			for (int i = 0; i < transportationFeePortion*width/100 ; i++ ) {
				System.out.print("□");
			}
			System.out.print(" "+(int)etcPortion+"%");
			System.out.println("\t" + etc + "원");
			
		}

	

	
	
	
	
}
