/**
 * 
 */
package com.work.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.work.exception.CommonException;
import com.work.exception.DuplicateException;
import com.work.exception.RecordNotFoundException;
import com.work.model.dto.Member;
import com.work.util.*;

/**
 * <pre>
 * 회원 관리 서비스 클래스
 * Collection API 활용 
 * -- ArrayList
 * -- Generic Collection 
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class MemberService {
	
	/**회원들을 관리하기 위한 저장 구조 - ArrayList*/
	private ArrayList<Member> members = new ArrayList<Member>();
	
	/***/
	public String currentMemberId = null;
	
	
	//지출 
	ArrayList<String> incomeDates = new ArrayList<String>();
	ArrayList<Integer> income = new ArrayList<Integer>();
	ArrayList<Integer> spend = new ArrayList<Integer>();
	ArrayList<String> sources = new ArrayList<String>();
	
	
	int balance = 0;
	int sumItemIncome = 0;
	int sumDateIncome = 0;
	int totalOutMoney = 0;
	
	
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
		
		addMember(dto1);
		addMember(dto2);
		addMember(dto3);
		addMember(dto4);
		addMember(dto5);
		
		addIncome(dto1,"2021-06-04",5000,"배당금");
		addIncome(dto1,"2021-06-04",5000,"용돈");
		addIncome(dto1,"2021-06-05",2000,"용돈");
		addIncome(dto1,"2021-06-07",3000,"배당금");
		addIncome(dto1,"2021-06-07",3000,"용돈");
		
		
		
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
	
	// 예산 내역 관련 메서드
	
	
	// 수입 내역 관련 메서드
	
	
	/**수입 내역 등록 메서드
	 * @throws CommonException */
	public boolean addIncome(String date, int revenue, String source) throws CommonException {
				int index = exist(currentMemberId);
				
				if (index >= 0) {
					incomeDates.add(date);
					income.add(revenue);
					sources.add(source);
					
					members.get(index).setIncomeDates(incomeDates);
					members.get(index).setIncome(income);
					members.get(index).setSources(sources);
					
					System.out.println("수입내역이 등록 되었습니다.");
					return true;
				} 
				throw new CommonException("수입 내역이 존재하지 않습니다.");
	}
	
	
	/**수입 내역 등록 메서드
	 * @throws CommonException */
	public boolean addIncome(Member dto, String date, int revenue, String source) throws CommonException {
				int index = exist(dto.getMemberId());
				
				if (index >= 0) {
					incomeDates.add(date);
					income.add(revenue);
					sources.add(source);
					
					members.get(index).setIncomeDates(incomeDates);
					members.get(index).setIncome(income);
					members.get(index).setSources(sources);

					return true;
				} 
				throw new CommonException("수입 내역이 존재하지 않습니다.");
	}
	

	/**수입 내역 삭제 메서드*/
	public void removeIncome(String date, int revenue, String source) {
				incomeDates.remove(date);
				income.remove(revenue);
				sources.remove(source);
				System.out.println("수입내역이 삭제 되었습니다.");
	}
	
	//전체 조회
	public void getIncome() {
		int currentIndex = exist(currentMemberId);
		
		for(int index = 0; index <income.size() ; index++ ) {
			System.out.println();
			System.out.println(" 날짜 : " + members.get(currentIndex).getIncomeDates(index));
			System.out.printf(" 수입 : %,d원%n", members.get(currentIndex).getIncome(index));
			System.out.printf(" 출처 : "+members.get(currentIndex).getSources(index));
			System.out.println();
		}
	}
	
	//상세조회 1. 기간별 조회
	public int getDateIncome(String startDate, String finishDate) throws RecordNotFoundException {
		int currentIndex = exist(currentMemberId);
		int finishCount=0;
		
	
		if(currentIndex >=0) {
			
			int startDateIndex = 0;
			startDateIndex	= members.get(currentIndex).getIncomeDates(startDate) ;
	
			for(int index = startDateIndex; index < incomeDates.size(); index++ ) {
				if(members.get(currentIndex).getIncomeDates(index).equals(finishDate)) {
					finishCount++;
				}
			}
			
			int finishDateIndex = members.get(currentIndex).getIncomeDates(finishDate) ;
			
			for(int index = startDateIndex; index < finishDateIndex+finishCount; index++) {
				sumDateIncome = sumDateIncome + (int)(members.get(currentIndex).getIncome(index)) ;
				System.out.print("["+members.get(currentIndex).getIncomeDates(index)+"] 수입 :");
				System.out.print(members.get(currentIndex).getIncome(index)+"원"
						+ "");
				System.out.println(", 출처 :"+members.get(currentIndex).getSources(index));
			}
			return sumDateIncome;
		}
		throw new RecordNotFoundException();
		
	}
	
	//메뉴에서 이용할 시작날짜 가져오는 메서드
	public String getIncomeStartDates() {
		
		int index=0;
		return incomeDates.get(index);
	}
	
	//메뉴에서 이용할 끝날짜 가져오는 메서드
		public String getIncomeFinishDates() {
			
			int index=incomeDates.size()-1;
			return incomeDates.get(index);
		}
	
	
	//상세 조회 2. 항목별 조회 - 입력형태
	public int getItemIncome(String source) throws RecordNotFoundException {
		int currentIndex = exist(currentMemberId);
		
		if(currentIndex >=0) {
			
			for(int index = 0; index < sources.size(); index++) {
				if(members.get(currentIndex).getSources(index).equals(source)) {
					sumItemIncome = sumItemIncome + (int)(members.get(currentIndex).getIncome(index));
				}
			}
			return sumItemIncome;
		}
		throw new RecordNotFoundException();
	}

	
	
	
	
	
}
