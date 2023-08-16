package com.myspring.bbeshop.order.dao;


import java.util.List;

import org.springframework.dao.DataAccessException;

import com.myspring.bbeshop.order.vo.OrderVO;


public interface OrderDAO {

	// 주문목록
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException;
	
	//주문하기
	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;
	
	public OrderVO findMyOrder(String order_id) throws DataAccessException;
	
	//주문완료시 장바구니에서 상품 제거
	public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException;
	public void removeGoodsFromCart(OrderVO orderVO)throws DataAccessException;
	
	
}
