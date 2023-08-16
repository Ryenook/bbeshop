package com.myspring.bbeshop.member.vo;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {
	
	private String memberName;
	private String memberId;
	private String memberPw;
	private String hp1;
	private String zipcode;
	private String member_address;
	private String subaddress;
	private String email;
	private String bbeshop_money;
	private String joinDate;
	private String del_yn;
	
	
	public MemberVO() {
		
	}
	
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getHp1() {
		return hp1;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getSubaddress() {
		return subaddress;
	}
	public void setSubaddress(String subaddress) {
		this.subaddress = subaddress;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBbeshop_money() {
		return bbeshop_money;
	}
	public void setBbeshop_money(String bbeshop_money) {
		this.bbeshop_money = bbeshop_money;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	
	
}

