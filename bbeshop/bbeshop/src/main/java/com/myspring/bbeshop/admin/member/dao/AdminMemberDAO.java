package com.myspring.bbeshop.admin.member.dao;

import java.util.ArrayList;
import java.util.HashMap;


import org.springframework.dao.DataAccessException;

import com.myspring.bbeshop.member.vo.MemberVO;


public interface AdminMemberDAO {
	// 회원관리
	public ArrayList<MemberVO> listMember(HashMap condMap) throws DataAccessException;
}

