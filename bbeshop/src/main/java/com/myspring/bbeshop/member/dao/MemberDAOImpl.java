package com.myspring.bbeshop.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.bbeshop.member.vo.MemberVO;


@Repository("memberDAO")
public class MemberDAOImpl  implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;	
	
	// 로그인
	@Override
	public MemberVO login(Map loginMap) throws DataAccessException{
		MemberVO member=(MemberVO)sqlSession.selectOne("mapper.member.login",loginMap);
		System.out.println(member+ " : sql들어갔다가 온 로그인 정보");
	   return member;
	}
	
	// 회원가입
	@Override
	public void insertNewMember(MemberVO memberVO) throws DataAccessException{
		sqlSession.insert("mapper.member.insertNewMember",memberVO);
	}

	// 아이디 중복확인
	@Override
	public String selectOverlappedID(String memberId) throws DataAccessException {
		String result =  sqlSession.selectOne("mapper.member.selectOverlappedID",memberId);
		return result;
	}

	
	
	
}
