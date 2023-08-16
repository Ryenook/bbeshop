package com.myspring.bbeshop.mypage.service;

import java.util.List;
import java.util.Map;

import com.myspring.bbeshop.member.vo.MemberVO;
import com.myspring.bbeshop.order.vo.OrderVO;


public interface MyPageService {
	
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception;
	public void cancelOrder(String order_id) throws Exception;
	public void returnOrder(String order_id) throws Exception;
	public void exchangeOrder(String order_id) throws Exception;
	public MemberVO myDetailInfo(String memberId) throws Exception;
	public MemberVO  modifyMyInfo(Map memberMap) throws Exception;
	public void  deleteMember(String memberId) throws Exception;
}

