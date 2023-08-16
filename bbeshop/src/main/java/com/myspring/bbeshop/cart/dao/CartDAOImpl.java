package com.myspring.bbeshop.cart.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.bbeshop.cart.vo.CartVO;
import com.myspring.bbeshop.goods.vo.GoodsVO;

@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {
	@Autowired
	private SqlSession sqlSession;

	// 장바구니
	@Override
	public List<CartVO> selectCartList(CartVO cartVO) throws DataAccessException {
		List<CartVO> cartList = sqlSession.selectList("mapper.cart.selectCartList", cartVO);
		return cartList;
	}

	@Override
	public List<GoodsVO> selectGoodsList(List<CartVO> cartList) throws DataAccessException {
		List<GoodsVO> myGoodsList;
		myGoodsList = sqlSession.selectList("mapper.cart.selectGoodsList", cartList);
		return myGoodsList;
	}

	// 장바구니 추가
	@Override
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.cart.selectCountInCart", cartVO);
		System.out.println(Boolean.parseBoolean(result));
		return Boolean.parseBoolean(result);
	}

	@Override
	public void insertGoodsInCart(CartVO cartVO) throws DataAccessException {
		int cart_id = selectMaxCartId();
		cartVO.setCart_id(cart_id);
		sqlSession.insert("mapper.cart.insertGoodsInCart", cartVO);
	}

	private int selectMaxCartId() throws DataAccessException {
		int cart_id = sqlSession.selectOne("mapper.cart.selectMaxCartId");
		return cart_id;
	}

	// 장바구니 수정
	@Override
	public void updateCartGoodsQty(CartVO cartVO) throws DataAccessException {
		sqlSession.insert("mapper.cart.updateCartGoodsQty", cartVO);
	}

	// 장바구니 삭제
	@Override
	public void deleteCartGoods(int cart_id) throws DataAccessException {
		sqlSession.delete("mapper.cart.deleteCartGoods", cart_id);
	}
}
