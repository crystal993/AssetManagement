/**
 * 
 */
package com.work.model.service;


import java.util.ArrayList;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Budget;
import com.work.model.dto.Income;
import com.work.model.dto.Member;
import com.work.util.Utility;

/**
 * <pre>
 * 회원과 가계부 관리 서비스 클래스
 *
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class ManagementService {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "crystal993";
	String password = "limemint01r";
	
	
	Utility util = new Utility();
	
	/** MemberDao 객체 생성 
	 * 외부에서 접근 못하게 private으로
	 * 이 클래스 안에서만 쓸거기 때문에 */
	MemberDao memberDao = MemberDao.getInstance();
	BudgetDao budgetDao = BudgetDao.getInstance();
	IncomeDao incomeDao = IncomeDao.getInstance();
	SpendDao spendtDao = SpendDao.getInstance();

	
	/** 기본 생성자 */
	public ManagementService() {
		try {
			Class.forName(driver);
			System.out.println("[성공] 드라이버 로딩");
		} catch (ClassNotFoundException e) {
			System.out.println("[오류] 드라이버 로딩 오류");
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * <pre>
	 * 회원 가입
	 * 날짜는 쿼리문에서 sysdate 오늘 날짜로 자동 저장
	 * </pre>
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @param email 이메일
	 * @return 등록되면 true, 아니면 false
	 */
	public boolean addMember(String memberId, String memberPw, String name, String mobile, String email) {
			return memberDao.addMember(memberId, memberPw, name, mobile, email);
	}
	
	/**
	 * 로그인
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 성공시 true, 실패하면 false
	 */
	public boolean login(String memberId, String memberPw) {
		return memberDao.login(memberId, memberPw);
	}
	
	/**
	 * 아이디 찾기1 - 휴대폰
	 * @param mobile 휴대폰
	 * @return 휴대폰 번호가 존재하면 해당 인스턴스의 아이디를 반환
	 */
	public String findId1(String mobile) {
		return memberDao.findId1(mobile);
	}
	
	/**
	 * 아이디 찾기2 - 이메일
	 * @param email 이메일
	 * @return 이메일이 존재하면 해당 인스턴스의 아이디를 반환
	 */
	public String findId2(String email) {
		return memberDao.findId2(email);
	}	
	
	
	/**
	 * 비밀번호 찾기1 - 휴대폰
	 * @param member_id 아이디 
	 * @param name 이름 
	 * @param mobile 휴대폰
	 * @return 아이디
	 */
	public String findMemerPwByMobile(String memberId, String name, String mobile) {
		String tmpMemberPw;
		
		boolean result = memberDao.findMemerPwByMobile(memberId, name, mobile);
		
		if (result) {
			tmpMemberPw = util.getSecureAlphabetString(12, true, true);
			memberDao.setMemberPw(memberId, tmpMemberPw);
			return tmpMemberPw;
		} else {
			
		}
		
		return null;
	}
	
	/**
	 * 비밀번호 찾기2 - 이메일
	 * @param memberId 아이디
	 * @param name 이름 
	 * @param mobile 이메일
	 * @return 아이디
	 */
	public String findMemerPwByEmail(String memberId, String name, String mobile) {
		String tmpMemberPw;
		
		boolean result = memberDao.findMemerPwByMobile(memberId, name, mobile);
		
		if (result) {
			tmpMemberPw = util.getSecureAlphabetString(12, true, true);
			memberDao.setMemberPw(memberId, tmpMemberPw);
			return tmpMemberPw;
		} else {
			
		}
		
		return null;
	}
	
	/**
	 * 내 정보 조회 / 회원 상세 조회 
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @return 존재하면 회원 반환, 아니면 null
	 */
	public Member getMember(String memberId, String memberPw) {
			return memberDao.getMember(memberId, memberPw);
	}
		
	/**
	 * 내 정보 변경 - 휴대폰
	 * @param mobile 휴대폰
	 * @return
	 */
	public boolean setMemberMobile(String memberId, String mobile) {
		return memberDao.setMemberMobile(memberId, mobile);
	}


	/**
	 * 내 정보 변경 - 이메일
	 * @param email 이메일
	 * @return
	 */
	public boolean setMemberEmail(String memberId, String email) {
		return memberDao.setMemberEmail(memberId, email);
	}
	
	/**
	 * 회원 비밀번호 변경
	 * @param memberId 아이디
	 * @param memberPw 비밀번호
	 * @param modifyMemberPw 바꿀 비밀번호
	 * @return 아이디와 비밀번호가 존재하면 true, 아니면 오류 
	 * @throws RecordNotFoundException 
	 */
	public boolean setMemberPw(String memberId, String memberPw, String modifyMemberPw) {
		return memberDao.setMemberPw(memberId, memberPw, modifyMemberPw);
	}
	
	
	/**
	 * 회원 탈퇴
	 * @param dto 회원
	 * @return 회원이 존재하면 탈퇴 후 true, 존재하지 않으면 오류
	 * @throws RecordNotFoundException
	 */
	public boolean removeMember(String memberId) { 
		return memberDao.removeMember(memberId);
	}
	
	
	
	// 회원
	/**
	 * 현재 회원 등록 인원수 조회 
	 * @return 현재 등록 인원수
	 */
	public int getMemberSize( ) {
		return memberDao.getMemberSize();
	}
	
	
	// 수입 내역 관련 메서드
	
	/**
	 * 수입 내역 등록 메서드
	 * @param date 날짜
	 * @param revenue 수입
	 * @param source 수입 출처
	 * @return 등록되면 true, 아니면 false
	 */
	public boolean addIncome(String memberId, int revenue, String source) {
			return incomeDao.addIncome(memberId,revenue,source);
	}
	

	/**
	 * 수입 내역 삭제 메서드
	 * @param date 날짜
	 * @param revenue 수입
	 * @param source 수입 출처
	 */
	public boolean removeIncome(String memberId, int num) {
			return incomeDao.removeIncome(memberId, num);
	}
	

	/**
	 * 수입 전체 조회 
	 * @param memberId 아이디
	 * @return 수입목록
	 */
	public ArrayList<Income> getIncome(String memberId) {
		return IncomeDao.getIncome(memberId);
	}
	
	/**
	 * 상세조회 1. 기간별 조회 
	 * @param memberId
	 * @param startDate
	 * @param finishDate
	 */
	public ArrayList<Income> getDateIncome(String memberId, String startDate, String finishDate) {
		return incomeDao.getDateIncome(memberId, startDate, finishDate);
	}
			
	/**
	 * 수입 시작날짜 조회 메서드
	 * @return 수입 시작날짜 
	 */
	public String getIncomeStartDates(String memberId) {
		return incomeDao.getIncomeStartDates(memberId);
	}
	
	/**
	 * 수입 끝날짜 조회 메서드
	 * @return 수입 끝날짜
	 */
		public String getIncomeFinishDates(String memberId) {
			return incomeDao.getIncomeFinishDates(memberId);
		}
	
	
	/**
	 * 상세 조회 2. 수입출처별 조회 - 입력형태
	 * @param source 수입 출처
	 * @return 수입출처별 수입의 합계, sumItemIncome
	 * @throws RecordNotFoundException
	 */
	public int getItemIncome(String memberId, String source) {
		return IncomeDao.getItemIncome(memberId,source);
	}

	/**
	 * 상세 항목별 전체 비율 조회 - 출처 1. 용돈 2. 월급 3. 배당금 4.기타 
	 */
	public void getItemIncomePortion(String memberId) {
//		// 돈 넣을 변수
		int pinMoney = 0;
		int salary = 0;
		int dividend = 0;
		int etc = 0;
		int sumIncome = 0;
		
		int width =20;
		
		pinMoney = IncomeDao.getItemIncome(memberId,"용돈");
		salary = IncomeDao.getItemIncome(memberId,"월급");
		dividend = IncomeDao.getItemIncome(memberId,"배당금");
		etc = IncomeDao.getItemIncome(memberId,"기타");
		
		sumIncome = pinMoney + salary + dividend + etc;
		
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
	* 예산 등록 메서드
	* @param memberId 아이디
	* @param budget 예산
	* @return 등록하면 true 반환, 아니면 false 반환
	*/
	public boolean addBudget(String memberId, int budget)  {
		return budgetDao.addBudget(memberId,budget);
	}


	/**
	 * 현재 예산 조회 메서드
	 * @param memberId 아이디
	 * @return 존재하면 true 반환, 아니면 false 반환
	 */
	public int getBudget(String memberId) {
		return budgetDao.getBudget(memberId);
	}


	/**
	 * 전체 예산 삭제 메서드
	 * @param memberId 아이디
	 * @return 삭제되면 true이고, 아니면 false
	 */
	public boolean removeBudget(String memberId) {
		return budgetDao.removeBudget(memberId);
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
		public boolean addSpend(String currentDate, int spend, String spendType, String spendMethod) {
			return false;
			
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
		public boolean addSpend(Member dto, String currentDate, int spend, String spendType, String spendMethod) {
			return false;
			
		}
		
		/**
		 * 지출 전체 조회 메서드
		 */
		public void getSpend() {
			
			
		}

		/**
		 * 지출 항목 상세 조회 메서드
		 * @param spendType 지출 항목
		 * @return 지출 항목에 해당되는 값들 더하여서 sumTypeSpend 반환
		 * @throws RecordNotFoundException
		 */
		public int getTypeSpend(String spendType) {
				return 0;
			}

		
		/**
		 * 지출 시작날짜 불러오는 메서드
		 * @return 지출 시작날짜
		 */
		public String getSpendStartDates() {
			return null;
		}

		/**
		 * 지출 끝날짜 불러오는 메서드
		 * @return
		 */
		public String getSpendFinishDates() {
			return null;
		}
		
		
		/**
		 * 지출 기간별 상세 조회 메서드
		 * @param startDate 시작 날짜
		 * @param finishDate 끝 날짜
		 * @throws RecordNotFoundException
		 */
		public void getDateSpend(String startDate, String finishDate) {
			
		}
		
		/**
		 * 결제 수단별 상세 조회 메서드
		 * @param spendMethod 결제 수단
		 * @return 사용자가 선택한 결제수단의 지출 총 합
		 * @throws RecordNotFoundException
		 */
		public int getMethodSpend(String spendMethod) {
			return 0;
		}

		/**
		 * 지출 삭제
		 * @param date 날짜
		 * @param spend 지출
		 * @param spendType 지출 항목
		 * @param spendMethod 결제 수단
		 */
		public void removeSpend(String date, int spend, String spendType, String spendMethod) {
			
			
		}

		/**
		 * 전체 지출 항목별 비율 조회 메서드 - 지출 항목 : 1.식비 2.주거비 3.의류비 4.교통비 5.문화비 6.기타
		 */
		public void getSpendTypePortion() {
			
			
		}


}
