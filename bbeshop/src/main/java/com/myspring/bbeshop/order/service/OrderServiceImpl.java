package com.myspring.bbeshop.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.bbeshop.order.dao.OrderDAO;
import com.myspring.bbeshop.order.vo.OrderVO;

import org.springframework.transaction.annotation.Propagation;

@Service("orderService")
@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws Exception {
		return orderDAO.listMyOrderGoods(orderVO);
	}

	// 주문하기 + 삭제
	@Override
	public void addNewOrder(List<OrderVO> myOrderList) throws Exception {
		orderDAO.insertNewOrder(myOrderList);
		orderDAO.removeGoodsFromCart(myOrderList);
	}

	@Override
	public OrderVO findMyOrder(String order_id) throws Exception {
		return orderDAO.findMyOrder(order_id);
	}

	//	주문하기
//	public void addNewOrder(List<OrderVO> myOrderList) throws Exception{
//		//주문하기
//		orderDAO.insertNewOrder(myOrderList);
//		
//		//카트에서 주문 상품 제거한다.
//		orderDAO.removeGoodsFromCart(myOrderList);
//		System.out.println("장바구니에서 해당 상품을 삭제했습니다.");
//	}	

}
