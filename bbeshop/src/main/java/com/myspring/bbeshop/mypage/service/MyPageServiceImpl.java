package com.myspring.bbeshop.mypage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.bbeshop.member.vo.MemberVO;
import com.myspring.bbeshop.mypage.dao.MyPageDAO;
import com.myspring.bbeshop.order.vo.OrderVO;



@Service("myPageService")
@Transactional(propagation=Propagation.REQUIRED)
public class MyPageServiceImpl implements MyPageService{
	@Autowired
	private MyPageDAO myPageDAO;

	@Override
	public List<OrderVO> listMyOrderHistory(Map dateMap) throws Exception {
		return myPageDAO.selectMyOrderHistoryList(dateMap);
	}
	
	@Override
	public void cancelOrder(String order_id) throws Exception {
		myPageDAO.updateMyOrderCancel(order_id);
	}
	
	@Override
	public void returnOrder(String order_id) throws Exception {
		myPageDAO.updateMyOrderReturn(order_id);
	}
	
	@Override
	public void exchangeOrder(String order_id) throws Exception {
		myPageDAO.updateMyOrderExchange(order_id);
	}
	
	@Override
	public MemberVO myDetailInfo(String memberId) throws Exception {
		return myPageDAO.selectMyDetailInfo(memberId);
	}

	@Override
	public MemberVO modifyMyInfo(Map memberMap) throws Exception {
		String memberId = (String)memberMap.get("memberId");
		myPageDAO.updateMyInfo(memberMap);
		return myPageDAO.selectMyDetailInfo(memberId);
	}

	@Override
	public void deleteMember(String memberId) throws Exception {
		myPageDAO.deleteMember(memberId);
	}

	@Override
	public List<OrderVO> payToOrderdetail(String order_id) {
		List<OrderVO> detailList = myPageDAO.payToOrderdetail(order_id);
		System.out.println("▶▶▶  mypage DAO의 detailList" + detailList);
		return detailList;
	}


}
