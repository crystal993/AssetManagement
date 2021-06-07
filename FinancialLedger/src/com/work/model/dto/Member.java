/**
 * 
 */
package com.work.model.dto;

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
	
	/**회원 예산 : 선택*/
	private int budget;
	
	/**회원 수입 : 선택 */
	private int income;
	
	/**회원 지출 : 선택 */
	private int spend;
	
	/**회원 결제 수단 : 선택, (CARD, CASH, ACCOUNT) */
	private String methodPayment;
	
//	/** 회원 금액 : 선택 */
//	int amount;
	
//	/** 조회 항목 : 의류, 주거, 식비, 취미, 교통 */
//	
//	/** 수입 출처 : 월급, 배당금, 용돈, 기타 */
	
	
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
		this.date= date;
	}
	
	
	/**
	 * 생성자 - 전체 데이터 
	 * @param memberId 아이디 
	 * @param memberPw 비밀번호
	 * @param name 이름
	 * @param mobile 휴대폰
	 * @param email 이메일
	 * @param date 가입일
	 * @param budget 예산
	 * @param income 수입
	 * @param spend 지출
	 * @param amount 금액 //amount 파라미터로 존재하는데 빼야되면 빼기
	 * @param methodPayment 결제수단
	 */
	public Member(String memberId, String memberPw, String name, String mobile, String email, String date, 
			int budget, int income, int spend, int amount, String methodPayment) {
		this(memberId, memberPw, name, mobile, email,date);
		this.budget = 0;
		this.income = 0;
		this.spend = 0;
//		this.amount = 0;
		this.methodPayment = "기본";
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
	 * @return the income
	 */
	public int getIncome() {
		return income;
	}


	/**
	 * @param income the income to set
	 */
	public void setIncome(int income) {
		this.income = income;
	}


	/**
	 * @return the spend
	 */
	public int getSpend() {
		return spend;
	}


	/**
	 * @param spend the spend to set
	 */
	public void setSpend(int spend) {
		this.spend = spend;
	}


	/**
	 * @return the methodPayment
	 */
	public String getMethodPayment() {
		return methodPayment;
	}


	/**
	 * @param methodPayment the methodPayment to set
	 */
	public void setMethodPayment(String methodPayment) {
		this.methodPayment = methodPayment;
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
		builder.append("\t ▶ 예산 : ");
		builder.append(budget);
		builder.append("\n");
		builder.append("\t ▶ 수입 : ");
		builder.append(income);
		builder.append("\n");
		builder.append("\t ▶ 지출 : ");
		builder.append(spend);
		builder.append("\n");
		builder.append("\t ▶ 결제수단 : ");
		builder.append(methodPayment);
		builder.append("\n");
		return builder.toString();
	}
	
	
}
