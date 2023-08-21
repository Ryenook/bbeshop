package com.myspring.bbeshop.mypage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.myspring.bbeshop.member.vo.MemberVO;
import com.myspring.bbeshop.order.vo.OrderVO;

public interface MyPageDAO {
	public List<OrderVO> selectMyOrderHistoryList(Map dateMap) throws DataAccessException;
	public void updateMyOrderCancel(String order_id) throws DataAccessException;
	public void updateMyOrderReturn(String order_id) throws DataAccessException;
	public void updateMyOrderExchange(String order_id) throws DataAccessException;
	public MemberVO selectMyDetailInfo(String memberId) throws DataAccessException;
	public void updateMyInfo(Map memberMap) throws DataAccessException;
	public void deleteMember(String memberId) throws DataAccessException;
	public List<OrderVO> payToOrderdetail(String order_id) throws DataAccessException;
}
