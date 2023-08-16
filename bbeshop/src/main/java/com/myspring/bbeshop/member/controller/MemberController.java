package com.myspring.bbeshop.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.bbeshop.member.vo.MemberVO;



public interface MemberController {
	
	//로그인
	public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	//회원가입
	public ResponseEntity addMember(@ModelAttribute MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	//아이디 중복확인
	public ResponseEntity overlapped(@RequestParam String memberId, HttpServletRequest request,
			HttpServletResponse response) throws Exception;

	//로그아웃
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
