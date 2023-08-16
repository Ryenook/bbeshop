package com.myspring.bbeshop.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.bbeshop.member.vo.MemberVO;



public interface MemberDAO {
	
	// 로그인
	public MemberVO login(Map loginMap) throws DataAccessException;

	// 회원가입
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	
	// 아이디 중복확인
	public String selectOverlappedID(String memberId) throws DataAccessException;
}
