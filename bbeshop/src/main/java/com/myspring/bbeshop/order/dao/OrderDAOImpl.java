package com.myspring.bbeshop.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.bbeshop.order.vo.OrderVO;

@Repository("orderDAO")
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private SqlSession sqlSession;


	@Override
	public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException {
		List<OrderVO> orderGoodsList=new ArrayList<OrderVO>();
		orderGoodsList=(ArrayList)sqlSession.selectList("mapper.order.selectMyOrderList",orderVO);
		return orderGoodsList;
	}
	
	//주문하기
	@Override
	public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException {
		//리턴된 주문번호와 함께 주문 table에 주문정보를 insert한다.
		int order_id = selectOrderID();
		for (int i = 0; i < myOrderList.size(); i++) {
			OrderVO orderVO = (OrderVO)myOrderList.get(i);
			//주문번호 객체에 set
			orderVO.setOrder_id(order_id);
			sqlSession.insert("mapper.order.insertNewOrder",orderVO);
			
		}
	}
	
	@Override
	public OrderVO findMyOrder(String order_id) throws DataAccessException {
		return sqlSession.selectOne("mapper.order.selectMyOrder",order_id);
	}	
	
	//주문완료시 장바구니에서 상품 제거
	@Override
	public void removeGoodsFromCart(List<OrderVO> myOrderList)throws DataAccessException{
		for(int i=0; i<myOrderList.size();i++){
			OrderVO orderVO =(OrderVO)myOrderList.get(i);
			sqlSession.delete("mapper.order.deleteGoodsFromCart",orderVO);	
		}
	}
	
	//주문완료시 장바구니에서 상품 제거
	@Override
	public void removeGoodsFromCart(OrderVO orderVO)throws DataAccessException{
			//주문상품리스트의 정보를 가지고 delete문을 myOrderList만큼, for문을 돌려 실행한다.
			sqlSession.delete("mapper.order.deleteGoodsFromCart",orderVO);	
	}


	// 주분번호 시퀀스 생성
	private int selectOrderID() throws DataAccessException{
		//주문번호 시퀀스를 생성하여 결과값을 반환한다.
		return sqlSession.selectOne("mapper.order.selectOrderID");
	}

}


