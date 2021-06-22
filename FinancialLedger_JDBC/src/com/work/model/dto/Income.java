/**
 * 
 */
package com.work.model.dto;

import java.util.ArrayList;

/**
 * <pre>
 * 수입 도메인 클래스
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class Income {
	
	
	/** 회원 아이디 : 참조키 */
	private String memberId;
	
	/**수입 날짜 : 필수 */
	private String incomeDates;
	
	/**수입 : 필수*/
	private int income;
	
	/**수입 출처 : 필수*/
	private String sources;
		
	
	/**수입 기본 생성자*/
	public Income() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 수입 전체 생성자
	 * @param memberId 아이디
	 * @param incomeDates 수입 날짜
	 * @param income 수입
	 * @param sources 수입 출처
	 */
	public Income(String memberId, String incomeDates, int income, String sources) {
		super();
		this.memberId = memberId;
		this.incomeDates = incomeDates;
		this.income = income;
		this.sources = sources;
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
	 * @return the incomeDates
	 */
	public String getIncomeDates() {
		return incomeDates;
	}



	/**
	 * @param incomeDates the incomeDates to set
	 */
	public void setIncomeDates(String incomeDates) {
		this.incomeDates = incomeDates;
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
	 * @return the sources
	 */
	public String getSources() {
		return sources;
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
		Income other = (Income) obj;
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
		
		builder.append(". 날짜 : ");
		builder.append(incomeDates);
		builder.append(", 수입 : ");
		builder.append(income);
		builder.append(", 수입 출처 : ");
		builder.append(sources);
		builder.append("]");
		builder.append("\n");

		return builder.toString();
	}

	

}
