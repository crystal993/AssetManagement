/**
 * 
 */
package com.work.model.dto;

import java.util.ArrayList;
import java.util.HashMap;

import com.work.util.Utility;


/**
 * <pre>
 * 회원 도메인 클래스
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class Member {

	/** 회원 아이디 : 식별키, 필수 */
	private String memberId;
	
	/** 회원 비밀번호 : 필수 */
	private String memberPw;
	
	/**회원 이름 : 필수 */
	private String name;
	
	/**회원 전화번호 : 필수 */
	private String mobile;
	
	/**회원 이메일 : 필수 */
	private String email;
	
	/**회원 가입일 : 필수, 시스템 제공 */
	private String date;
	
	
	
	/** 예산 : 필수*/
	private int budget;
	
	//예산
	/**예산 날짜 : 필수 */
	ArrayList<String> incomeDates = new ArrayList<String>();
	
	/**예산 : 필수*/
	ArrayList<Integer> income = new ArrayList<Integer>();
	
	/**예산 출처 : 필수*/
	ArrayList<String> sources = new ArrayList<String>();
	
	
	//지출
	/**예산 날짜 : 필수 */
	ArrayList<String> spendDates = new ArrayList<String>();
	
	/**지출 : 필수*/
	ArrayList<Integer> spend = new ArrayList<Integer>();
	
	/**지출 항목 : 필수*/
	ArrayList<String> spendType = new ArrayList<String>();

	/**결제 수단 항목 : 필수*/
	ArrayList<String> spendMethod = new ArrayList<String>();
	
	int totalInMoney = 0;
	int totalOutMoney = 0;
	
	Utility util = new Utility();

	
	
	/**
	 * 기본 생성자
	 */
	public Member() {
		// TODO Auto-generated constructor stub
		
	}
	
	
	/**
	 * 생성자 - 사용자 입력 필수 데이터
	 * @param memberId 아이디 
	 * @param memberPw 비밀번호
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @param email 이메일
	 */
	public Member(String memberId, String memberPw, String name, String mobile, String email) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
	}
	

	/**
	 * 생성자 - 전체 필수 데이터
	 * @param memberId 아이디 
	 * @param memberPw 비밀번호
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @param email 이메일
	 * @param date 가입일
	 */
	public Member(String memberId, String memberPw, String name, String mobile, String email, String date) {
		this(memberId, memberPw, name, mobile, email);
		this.date= util.getCurrentDate();
	}
	
	

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}


	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	/**
	 * @return the memberPw
	 */
	public String getMemberPw() {
		return memberPw;
	}


	/**
	 * @param memberPw the memberPw to set
	 */
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the budget
	 */
	public int getBudget() {
		return budget;
	}
	

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}

	/**
	 * @return the spendDates
	 */
	public ArrayList<String> getSpendDates() {
		return spendDates;
	}
	
	/**
	 * @param index
	 * @return the spendDates element
	 */
	public String getSpendDates(int index) {
		return spendDates.get(index);
	}
	
	/**
	 * @param date
	 * @return 인덱스의 번호를 반환
	 */
	public int getSpendDates(String date) {
		for(int index = 0; index < spendDates.size() ; index++) {
			if(spendDates.get(index).equals(date)) {
				return index;
			}
		}
		return -1;
	}

	/**
	 * @param spendDates the spendDates to set
	 */
	public void setSpendDates(ArrayList<String> spendDates) {
		this.spendDates = spendDates;
	}
	
	/**
	 * 
	 * @param currentDate
	 */
	public void setSpendDates(String currentDate) {
		this.spendDates.add(currentDate);
	}

	/**
	 * @return the spendType
	 */
	public ArrayList<String> getSpendType() {
		return spendType;
	}
	
	/**
	 * 지출 항목 조회
	 * @param index 인덱스
	 * @return 인덱스에 해당되는 spendType 데이터 반환
	 */
	public String getSpendType(int index) {
		return spendType.get(index);
	}
	
	/**
	 * @param spendType the spendType to set
	 */
	public void setSpendType(ArrayList<String> spendType) {
		this.spendType = spendType;
	}
	
	/**
	 * @param spendType the spendType to set
	 */
	public void setSpendType(String spendType) {
		this.spendType.add(spendType);
	}


	/**
	 * @return the spendMethod
	 */
	public ArrayList<String> getSpendMethod() {
		return spendMethod;
	}
	
	/**
	 * 지출 수단 조회
	 * @param index 인덱스
	 * @return 전달받은 인덱스에 해당되는 데이터를 반환
	 */
	public String getSpendMethod(int index) {
		return spendMethod.get(index);
	}

	/**
	 * @param spendMethod the spendMethod to set
	 */
	public void setSpendMethod(ArrayList<String> spendMethod) {
		this.spendMethod = spendMethod;
	}
	
	/**
	 * @param spendMethod 등록
	 */
	public void setSpendMethod(String spendMethod) {
		this.spendMethod.add(spendMethod);
	}

	/**
	 * @param incomeDates the incomeDates to set
	 */
	public void setIncomeDates(ArrayList<String> incomeDates) {
		this.incomeDates = incomeDates;
	}
	

	/**
	 * @return the incomeDates
	 */
	public ArrayList<String> getIncomeDates() {
		return incomeDates;
	}

	/**
	 * 수입 날짜 조회 1
	 * @param index 인덱스 
	 * @return 인덱스에 있는 수입 날짜 반환
	 */
	public String getIncomeDates(int index) {
		return incomeDates.get(index);
	}
	
	/**
	 * 수입 날짜 조회2
	 * @param date 날짜
	 * @return 날짜에 해당되는 인덱스 반환
	 */
	public int getIncomeDates(String date) {
		
		for(int index = 0; index < incomeDates.size() ; index++) {
			if(incomeDates.get(index).equals(date)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * @param string the incomeDates to set
	 */
	public void setIncomeDates(String string) {
		this.incomeDates.add(string);
	}

	/**
	 * @param date 데이터 저장
	 */
	public void setIncomeDate(String date) {
		this.incomeDates.add(date);
	}
	

	/**
	 * @return the income
	 */
	public ArrayList<Integer> getIncome() {
		return income;
	}
	
	/**
	 * 수입 조회
	 * @param index 인덱스
	 * @return 인덱스의 수입값 반환
	 */
	public int getIncome(int index) {
		return income.get(index);
	}

	/**
	 * @param income the income to set
	 */
	public void setIncome(ArrayList<Integer> income) {
		this.income = income;
	}
	
	/**
	 * @param income the income to set
	 */
	public void setIncome(int income) {
		this.income.add(income);
	}


	/**
	 * @return the spend
	 */
	public ArrayList<Integer> getSpend() {
		return spend;
	}

	/**
	 * @param index 인덱스
	 * @return spend element
	 */
	public int getSpend(int index) {
		return spend.get(index);
	}
	
	/**
	 * @param spend the spend to set
	 */
	public void setSpend(ArrayList<Integer> spend) {
		this.spend = spend;
	}
	
	/**
	 * 지출 데이터 등록
	 * @param spend 지출
	 */
	public void setSpend(int spend) {
		this.spend.add(spend);
	}

	/**
	 * @return the sources
	 */
	public ArrayList<String> getSources() {
		return sources;
	}


	/**
	 * @param sources the sources to set
	 */
	public void setSources(ArrayList<String> sources) {
		this.sources = sources;
	}
	
	/**
	 * 수입 출처 조회
	 * @param index 인덱스
	 * @return 전달받은 인덱스에 해당되는 sources값 반환
	 */
	public String getSources(int index) {
		return sources.get(index);
	}

	/**
	 * 수입 출처 등록
	 * @param source 수입 출처
	 */
	public void setSources(String source) {
		this.sources.add(source);
	}

	
	// size() : 현재 등록된 데이터들 갯수 반환 메서드들
	
	/**
	 * 저장된 수입 데이터 사이즈 반환 메서드
	 * @return 현재 등록된 income size 반환
	 */
	public int getIncomeSize() {
		
		return this.income.size();
	}
	
	/**
	 * 저장된 지출 데이터 사이즈 반환 메서드
	 * @return 현재 등록된 spend size 반환
	 */
	public int getSpendSize() {
		return this.spend.size();
	}
	
	

	// 요소 데이터 삭제 메서드들 
	
	/**
	 * 수입 날짜 요소 삭제 메서드
	 * @param date2 삭제할 날짜
	 */
	public void removeIncomeDates(String date2) {
		int removeIndex = 0;
		for(int index = 0; index < incomeDates.size() ; index++) {
			if(incomeDates.get(index).equals(date2)) {
				removeIndex = index;
			}
		}
		
		this.incomeDates.remove(removeIndex);
	}

	
	/**
	 * 수입 출처 요소 삭제 메서드
	 * @param sources2 수입 출처
	 */
	public void removeSources(String sources2) {
		int removeIndex = 0;
		for(int index = 0; index < sources.size() ; index++) {
			if(sources.get(index).equals(sources2)) {
				removeIndex = index;
			}
		}
		
		this.sources.remove(removeIndex);
		
	}

	/**
	 * 수입 요소 삭제 메서드
	 * @param revenue 수입
	 */
	public void removeIncome(int revenue) {
		int removeIndex = 0;
		for(int index = 0; index < income.size() ; index++) {
			if(income.get(index) == revenue) {
				removeIndex = index;
			}
		}
		
		this.income.remove(removeIndex);
	}


	/**
	 * 지출 날짜 요소 삭제 메서드
	 * @param date2 날짜
	 */
	public void removeSpendDates(String date2) {
		int removeIndex = 0;
		for(int index = 0; index < spendDates.size() ; index++) {
			if(spendDates.get(index).equals(date2)) {
				removeIndex = index;
			}
		}
		
		this.spendDates.remove(removeIndex);
		
	}
	
	/**
	 * 지출 항목 요소 삭제 메서드
	 * @param spendType2 지출 항목
	 */
	public void removeSpendType(String spendType2) {
		int removeIndex = 0;
		for(int index = 0; index < spendType.size() ; index++) {
			if(spendType.get(index).equals(spendType2)) {
				removeIndex = index;
			}
		}
		
		this.spendType.remove(removeIndex);
		
	}

	/**
	 * 지출 요소 삭제 메서드
	 * @param spend2 지출
	 */
	public void removeSpend(int spend2) {
		int removeIndex = 0;
		for(int index = 0; index < spend.size() ; index++) {
			if(spend.get(index).equals(spend2)) {
				removeIndex = index;
			}
		}
		
		this.spend.remove(removeIndex);
	}

	/**
	 * 결제 수단 요소 삭제 메서드
	 * @param spendMethod2 결제 수단
	 */
	public void removeSpendMethod(String spendMethod2) {
		int removeIndex = 0;
		for(int index = 0; index < spendMethod.size() ; index++) {
			if(spendMethod.get(index).equals(spendMethod2)) {
				removeIndex = index;
			}
		}
		
		this.spendMethod.remove(removeIndex);
	}
	
	
	// 전체 데이터 삭제 메서드들
	
	/**
	 * 전체 수입 날짜 삭제 메서드
	 */
	public void clearIncomeDates() {
		incomeDates.clear();
	}

	/**
	 * 전체 수입 삭제 메서드
	 */
	public void clearIncome() {
		income.clear();
	}

	/**
	 * 전체 수입 출처 삭제 메서드
	 */
	public void clearSources() {
		sources.clear();
	}
	
	/**
	 * 전체 지출 날짜 삭제 메서드
	 */
	public void clearSpendDates() {
		spendDates.clear();
		
	}

	/**
	 * 전체 지출 삭제 메서드
	 */
	public void clearSpend() {
		spend.clear();
		
	}

	/**
	 * 전체 지출항목 삭제 메서드
	 */
	public void clearSpendType() {
		spendType.clear();
		
	}
	
	/**
	 *  전체 결제수단 삭제 메서드
	 */
	public void clearSpendMethod() {
		spendMethod.clear();		
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\t ▶ 아이디 : ");
		builder.append(memberId);
		builder.append("\n");
		builder.append("\t ▶ 비밀번호 : ");
		builder.append(memberPw);
		builder.append("\n");
		builder.append("\t ▶ 이름 : ");
		builder.append(name);
		builder.append("\n");
		builder.append("\t ▶ 휴대폰 : ");
		builder.append(mobile);
		builder.append("\n");
		builder.append("\t ▶ 이메일 : ");
		builder.append(email);
		builder.append("\n");
		builder.append("\t ▶ 날짜 : ");
		builder.append(date);
		builder.append("\n");
		return builder.toString();
	}


	
}
