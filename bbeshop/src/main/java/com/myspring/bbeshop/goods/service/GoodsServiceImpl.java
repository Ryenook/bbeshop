package com.myspring.bbeshop.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.bbeshop.goods.dao.GoodsDAO;
import com.myspring.bbeshop.goods.vo.GoodsVO;
import com.myspring.bbeshop.goods.vo.ImageFileVO;

@Service("goodsService")
@Transactional(propagation = Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsDAO goodsDAO;

	// 메인페이지 지정 status별, 메뉴별
	@Override
	public Map<String, List<GoodsVO>> listGoods() throws Exception {
		Map<String, List<GoodsVO>> goodsMap = new HashMap<String, List<GoodsVO>>();

		System.out.println("▶▶ DAO의 listGoods메서드 진입");

		// bestseller 저장
		List<GoodsVO> goodsList = goodsDAO.selectGoodsList("bestseller");
		goodsMap.put("bestseller", goodsList);

		goodsList = goodsDAO.selectMenusList("와인잔");
		goodsMap.put("cate_wine", goodsList);

		goodsList = goodsDAO.selectMenusList("맥주잔");
		goodsMap.put("cate_beer", goodsList);

		goodsList = goodsDAO.selectMenusList("캐릭터잔");
		goodsMap.put("cate_character", goodsList);

		goodsList = goodsDAO.selectMenusList("유아용");
		goodsMap.put("cate_kids", goodsList);

		return goodsMap;
	}

	// bestseller
	@Override
	public List<GoodsVO> menuGoods(String menuGoods) throws Exception {
		List goodsList = goodsDAO.selectGoodsByMenuGoods(menuGoods);
		return goodsList;
	}

	// 추천키워드
	@Override
	public List<String> keywordSearch(String keyword) throws Exception {
		List<String> list = goodsDAO.selectKeywordSearch(keyword);
		return list;
	}

	// 검색
	@Override
	public List<GoodsVO> searchGoods(String searchWord) throws Exception {
		List goodsList = goodsDAO.selectGoodsBySearchWord(searchWord);
		return goodsList;
	}

	// 상품상세
	public Map goodsDetail(String _goods_id) throws Exception {
		Map goodsMap = new HashMap();
		// 상품상세정보 추출
		GoodsVO goodsVO = goodsDAO.selectGoodsDetail(_goods_id);
		goodsMap.put("goodsVO", goodsVO);

		// 상품 상세이미지 추출
		List<ImageFileVO> imageList = goodsDAO.selectGoodsDetailImage(_goods_id);
		goodsMap.put("imageList", imageList);

		// 위 정보를 담은 Map return
		return goodsMap;
	}

}
