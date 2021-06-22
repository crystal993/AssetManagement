/**
 * 
 */
package com.work.model.dto;

import java.util.ArrayList;

/**
 * <pre>
 * 지출 도메인 클래스
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class Spend {
	
	/** 회원 아이디 : 참조키 */
	private String memberId;
	
	/**지출 날짜 : 필수 */
	private String spendDates;
	
	/**지출 : 필수*/
	private int spend;
	
	/**지출 항목 : 필수*/
	private String spendType;

	/**결제 수단 항목 : 필수*/
	private String spendMethod;

	
	/**기본 생성자*/
	public Spend() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * 지출 전체 생성자
	 * @param memberId 아이디
	 * @param spendDates 지출 날짜
	 * @param spend 지출
	 * @param spendType 지출 항목
	 * @param spendMethod 지출 수단
	 */
	public Spend(String memberId, String spendDates, int spend, String spendType, String spendMethod) {
		super();
		this.memberId = memberId;
		this.spendDates = spendDates;
		this.spend = spend;
		this.spendType = spendType;
		this.spendMethod = spendMethod;
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
	 * @return the spendDates
	 */
	public String getSpendDates() {
		return spendDates;
	}


	/**
	 * @param spendDates the spendDates to set
	 */
	public void setSpendDates(String spendDates) {
		this.spendDates = spendDates;
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
	 * @return the spendType
	 */
	public String getSpendType() {
		return spendType;
	}


	/**
	 * @param spendType the spendType to set
	 */
	public void setSpendType(String spendType) {
		this.spendType = spendType;
	}


	/**
	 * @return the spendMethod
	 */
	public String getSpendMethod() {
		return spendMethod;
	}


	/**
	 * @param spendMethod the spendMethod to set
	 */
	public void setSpendMethod(String spendMethod) {
		this.spendMethod = spendMethod;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((spendDates == null) ? 0 : spendDates.hashCode());
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
		Spend other = (Spend) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (spendDates == null) {
			if (other.spendDates != null)
				return false;
		} else if (!spendDates.equals(other.spendDates))
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(". 날짜 : ");
		builder.append(spendDates);
		builder.append(", 지출 :");
		builder.append(spend);
		builder.append(", 지출 항목 : ");
		builder.append(spendType);
		builder.append(", 지출 수단 : ");
		builder.append(spendMethod);
		builder.append("\n");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
}
