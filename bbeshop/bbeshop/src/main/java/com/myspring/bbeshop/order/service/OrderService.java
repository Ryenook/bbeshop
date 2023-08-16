package com.myspring.bbeshop.order.service;

import java.util.List;

import com.myspring.bbeshop.order.vo.OrderVO;

public interface OrderService {

	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception;
	
	//주문하기 - 결제완료후 주문 table에 insert 된다
	public void addNewOrder(List<OrderVO> myOrderList) throws Exception;

	public OrderVO findMyOrder(String order_id) throws Exception;
;
}
