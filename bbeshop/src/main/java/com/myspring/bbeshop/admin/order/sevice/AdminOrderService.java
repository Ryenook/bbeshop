package com.myspring.bbeshop.admin.order.sevice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myspring.bbeshop.member.vo.MemberVO;
import com.myspring.bbeshop.order.vo.OrderVO;


public interface AdminOrderService {
	
	//주문목록
	public List<OrderVO> listNewOrder(Map condMap) throws Exception;

	//주문수정 - 배송수정
	public void modifyDeliveryState(Map deliveryMap) throws Exception;
}
