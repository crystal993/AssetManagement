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
	
	/**지출 : 필수*/
//	private int spend;
	
	/**예산 : 필수*/
// private int budget;
	
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
	 * @return the spendDates
	 */
	public ArrayList<String> getSpendDates() {
		return spendDates;
	}
	
	public String getSpendDates(int index) {
		return spendDates.get(index);
	}
	
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
	 * @return the spendType
	 */
	public ArrayList<String> getSpendType() {
		return spendType;
	}

	/**
	 * @param spendType the spendType to set
	 */
	public void setSpendType(ArrayList<String> spendType) {
		this.spendType = spendType;
	}
	
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
	 * @param spendMethod the spendMethod to set
	 */
	public void setSpendMethod(ArrayList<String> spendMethod) {
		this.spendMethod = spendMethod;
	}
	
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

	
	
//	/**
//	 * @return the itemSpend
//	 */
//	public HashMap<String, Integer> getItemSpend() {
//		return itemSpend;
//	}
//
//	/**
//	 * @param itemSpend the itemSpend to set
//	 */
//	public void setItemSpend(HashMap<String, Integer> itemSpend) {
//		this.itemSpend = itemSpend;
//	}
//
//	/**
//	 * @return the dateSpend
//	 */
//	public HashMap<String, Integer> getDateSpend() {
//		return dateSpend;
//	}
//
//	/**
//	 * @param dateSpend the dateSpend to set
//	 */
//	public void setDateSpend(HashMap<String, Integer> dateSpend) {
//		this.dateSpend = dateSpend;
//	}
//
//	/**
//	 * @return the typeSpend
//	 */
//	public HashMap<String, Integer> getTypeSpend() {
//		return typeSpend;
//	}
//
//	/**
//	 * @param typeSpend the typeSpend to set
//	 */
//	public void setTypeSpend(HashMap<String, Integer> typeSpend) {
//		this.typeSpend = typeSpend;
//	}
//
//	/**
//	 * @return the itemIncome
//	 */
//	public HashMap<String, Integer> getItemIncome() {
//		return itemIncome;
//	}
//
//	/**
//	 * @param itemIncome the itemIncome to set
//	 */
//	public void setItemIncome(HashMap<String, Integer> itemIncome) {
//		this.itemIncome = itemIncome;
//	}
//
//	/**
//	 * @return the dateIncome
//	 */
//	public HashMap<String, Integer> getDateIncome() {
//		return dateIncome;
//	}
//
//	/**
//	 * @param dateIncome the dateIncome to set
//	 */
//	public void setDateIncome(HashMap<String, Integer> dateIncome) {
//		this.dateIncome = dateIncome;
//	}



	/**
	 * @return the incomeDates
	 */
	public ArrayList<String> getIncomeDates() {
		return incomeDates;
	}


	/**
	 * @param string the incomeDates to set
	 */
	public void setIncomeDates(String string) {
		this.incomeDates.add(string);
	}

	
	public void setIncomeDate(String date) {
		// TODO Auto-generated method stub
		this.incomeDates.add(date);
	}
	

	/**
	 * @return the income
	 */
	public ArrayList<Integer> getIncome() {
		return income;
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
	 * @param spend the spend to set
	 */
	public void setSpend(ArrayList<Integer> spend) {
		this.spend = spend;
	}
	
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


	public String getIncomeDates(int index) {
		// TODO Auto-generated method stub
		return incomeDates.get(index);
	}
	
	public int getIncomeDates(String date) {
		
		for(int index = 0; index < incomeDates.size() ; index++) {
			if(incomeDates.get(index).equals(date)) {
				return index;
			}
		}
		return -1;
	}
	

	public int getIncome(int index) {
		// TODO Auto-generated method stub
		return income.get(index);
	}


	public String getSources(int index) {
		// TODO Auto-generated method stub
		return sources.get(index);
	}


	public void setSources(String string) {
		// TODO Auto-generated method stub
		this.sources.add(string);
	}


	// 삭제 메서드
	public void removeIncomeDates(String date2) {
		int removeIndex = 0;
		for(int index = 0; index < incomeDates.size() ; index++) {
			if(incomeDates.get(index).equals(date2)) {
				removeIndex = index;
			}
		}
		
		this.incomeDates.remove(removeIndex);
	}


	public void removeSources(String sources2) {
		int removeIndex = 0;
		for(int index = 0; index < sources.size() ; index++) {
			if(sources.get(index).equals(sources2)) {
				removeIndex = index;
			}
		}
		
		this.sources.remove(removeIndex);
		
	}


	public void removeIncome(int revenue) {
		int removeIndex = 0;
		for(int index = 0; index < income.size() ; index++) {
			if(income.get(index) == revenue) {
				removeIndex = index;
			}
		}
		
		this.income.remove(removeIndex);
	}


	public int getIncomeSize() {
		
		return this.income.size();
	}


	public void clearIncomeDates() {
		// TODO Auto-generated method stub
		incomeDates.clear();
	}


	public void clearIncome() {
		// TODO Auto-generated method stub
		income.clear();
	}


	public void clearSources() {
		// TODO Auto-generated method stub
		sources.clear();
	}


	public void setSpendDates(String currentDate) {
		this.spendDates.add(currentDate);
	}


	public int getSpendSize() {
		return this.spend.size();
	}

	public int getSpend(int index) {
		return spend.get(index);
	}

	public String getSpendType(int index) {
		return spendType.get(index);
	}

	public String getSpendMethod(int index) {
		return spendMethod.get(index);
	}

	public void removeSpendDates(String date2) {
		int removeIndex = 0;
		for(int index = 0; index < spendDates.size() ; index++) {
			if(spendDates.get(index).equals(date2)) {
				removeIndex = index;
			}
		}
		
		this.spendDates.remove(removeIndex);
		
	}

	public void removeSpendType(String spendType2) {
		int removeIndex = 0;
		for(int index = 0; index < spendType.size() ; index++) {
			if(spendType.get(index).equals(spendType2)) {
				removeIndex = index;
			}
		}
		
		this.spendType.remove(removeIndex);
		
	}

	public void removeSpend(int spend2) {
		int removeIndex = 0;
		for(int index = 0; index < spend.size() ; index++) {
			if(spend.get(index).equals(spend2)) {
				removeIndex = index;
			}
		}
		
		this.spend.remove(removeIndex);
	}

	public void removeSpendMethod(String spendMethod2) {
		int removeIndex = 0;
		for(int index = 0; index < spendMethod.size() ; index++) {
			if(spendMethod.get(index).equals(spendMethod2)) {
				removeIndex = index;
			}
		}
		
		this.spendMethod.remove(removeIndex);
	}

	public void clearSpendDates() {
		spendDates.clear();
		
	}

	public void clearSpend() {
		spend.clear();
		
	}

	public void clearSpendType() {
		spendType.clear();
		
	}

	public void clearSpendMethod() {
		spendMethod.clear();		
	}



	
	
}
