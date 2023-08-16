package com.myspring.bbeshop.admin.member.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.myspring.bbeshop.member.vo.MemberVO;

public interface AdminMemberService {
	// 회원관리
	public ArrayList<MemberVO> listMember(HashMap condMap) throws Exception;
}
