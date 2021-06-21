/**
 * 
 */
package com.work.model.dto;

/**
 * <pre>
 * 예산 도메인 클래스
 * </pre>
 * @author 김수정
 * @version ver.1.0
 * @since jdk1.8
 */
public class Budget {

	/** 회원 아이디 : 참조키 */
	private String memberId;
	
	/** 예산 : 필수 */
	private int budget;
	
	
	/**
	 * 기본 생성자
	 */
	public Budget() {
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * 전체 생성자
	 * @param memberId 아이디 
	 * @param budget 예산
	 */
	public Budget(String memberId, int budget) {
		super();
		this.memberId = memberId;
		this.budget = budget;
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




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + budget;
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
		Budget other = (Budget) obj;
		if (budget != other.budget)
			return false;
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
		builder.append(memberId+" 회원님");
		builder.append("\n");
		builder.append("\t ▶ 예산 : ");
		builder.append(budget);
		builder.append("\n");
		return builder.toString();
	}
	
	
}
