package com.myspring.bbeshop.member.service;

import java.util.Map;

import com.myspring.bbeshop.member.vo.MemberVO;



public interface MemberService {
	//로그인
	public MemberVO login(Map  loginMap) throws Exception;
	
	//회원가입
	public void addMember(MemberVO memberVO) throws Exception;
	
	//아이디 중복확인
	public String overlapped(String memberId) throws Exception;
}
