/**
 * 
 */
package com.work.model.service;


import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Budget;
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
	 * 비밀번호 찾기
	 * @param memberId 아이디
	 * @param mobile 휴대폰
	 * @return 아이디와 휴대폰 번호가 맞으면(true) 임시 비밀번호를 기존 비밀번호에 업데이트 후 반환, false이면 오류메세지
	 */
	public String findPw(String memberId, String mobile) {
		Utility util = new Utility();
//		
//		
//		int index = exist(memberId);
//			if(members.get(index).getMobile().equals(mobile)) {
//				String tempMemberPw =  util.getSecureAlphabetString(8, false, true);
//				members.get(index).setMemberPw(tempMemberPw);
//				return tempMemberPw;
//			}
//		
//		throw new CommonException("존재하지 않거나 잘못 입력된 번호입니다.");
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
	 * 회원 전체 변경
	 * @param dto 회원
	 * @throws RecordNotFoundException 
	 */
	public boolean setMembers(Member dto) throws RecordNotFoundException {
		return false;
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
		return false;
	}
	
	
	/**
	 * 회원 탈퇴
	 * @param dto 회원
	 * @return 회원이 존재하면 탈퇴 후 true, 존재하지 않으면 오류
	 * @throws RecordNotFoundException
	 */
	public boolean removeMember(Member dto) throws RecordNotFoundException { 
		return false;
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
	 * @return 회원 존재하면 날짜, 수입, 수입출처 등록되면 true, 아니면 false
	 * @throws CommonException
	 */
	public boolean addIncome(String date, int revenue, String source) throws CommonException {
				return false;
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
			return false;
	}
	

	/**
	 * 수입 내역 삭제 메서드
	 * @param date 날짜
	 * @param revenue 수입
	 * @param source 수입 출처
	 */
	public void removeIncome(String date, int revenue, String source) {
				
	}
	
	/**
	 * 수입 전체 조회 
	 */
	public void getIncome() {
		
	}
	
	/**
	 * 상세조회 1. 기간별 조회
	 * @param startDate
	 * @param finishDate
	 * @throws RecordNotFoundException
	 */
	public void getDateIncome(String startDate, String finishDate) throws RecordNotFoundException {
		
		}
			
	/**
	 * 수입 시작날짜 조회 메서드
	 * @return 수입 시작날짜 
	 */
	public String getIncomeStartDates() {
		return null;
	}
	
	/**
	 * 수입 끝날짜 조회 메서드
	 * @return 수입 끝날짜
	 */
		public String getIncomeFinishDates() {
			return null;
		}
	
	
	/**
	 * 상세 조회 2. 수입출처별 조회 - 입력형태
	 * @param source 수입 출처
	 * @return 수입출처별 수입의 합계, sumItemIncome
	 * @throws RecordNotFoundException
	 */
	public int getItemIncome(String source) throws RecordNotFoundException {
		return 0;
	}

	/**
	 * 상세 항목별 전체 비율 조회 - 출처 1. 용돈 2. 월급 3. 배당금 4.기타 
	 */
	public void getItemIncomePortion() {
		
	}
	
	
	
	//예산 내역 관리 메서드들
	
	/**
	 * 초기 예산 등록 메서드 - 사용자 입력
	 * @param budget 예산
	 * @return 현재 예산이 0원이고 현재 로그인된 아이디가 존재하면 true 반환, 아니면 false 반환
	 * @throws DuplicateException
	 */
	public boolean addBudget(Budget budgetDto) throws DuplicateException {
	
		return false;
	}

		/**
		 * 예산 등록 메서드 - 초기화용(테스트)
		 * @param dto 객체
		 * @param budget 예산
		 * @return 현재 예산이 0원이고 현재 로그인된 아이디가 존재하면 true 반환, 아니면 false 반환
		 */
		public boolean addBudget(String memberid, int budget)  {
			

			return false;
		}

		/**
		 * 현재 예산 조회 메서드
		 * @return 예산 
		 */
		public int getBudget() {
			return 0;
		}

		/**
		 * 전체 예산 삭제 메서드
		 * @return 현재 예산이 0원이 아니고, 현재 아이디가 존재하는 아이디라면 true이고, 아니면 false
		 * @throws RecordNotFoundException
		 */
		public boolean removeBudget() throws RecordNotFoundException{
			return false;
		
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
		public boolean addSpend(Member dto, String currentDate, int spend, String spendType, String spendMethod) throws CommonException {
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
		public int getTypeSpend(String spendType) throws RecordNotFoundException {
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
		public void getDateSpend(String startDate, String finishDate) throws RecordNotFoundException {
			
		}
		
		/**
		 * 결제 수단별 상세 조회 메서드
		 * @param spendMethod 결제 수단
		 * @return 사용자가 선택한 결제수단의 지출 총 합
		 * @throws RecordNotFoundException
		 */
		public int getMethodSpend(String spendMethod) throws RecordNotFoundException {
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
