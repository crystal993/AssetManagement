/**
 * 
 */
package com.work.model.dto;

import java.util.ArrayList;

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
	private String entryDate;
	

	
	
	/**
	 * 기본 생성자
	 */
	public Member() {
		// TODO Auto-generated constructor stub
		
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
	public Member(String memberId, String memberPw, String name, String mobile, String email, String entryDate) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.entryDate = entryDate;
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
	public String getEntryDate() {
		return entryDate;
	}


	/**
	 * @param date the date to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
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
		builder.append(entryDate);
		builder.append("\n");
		return builder.toString();
	}


	
}
