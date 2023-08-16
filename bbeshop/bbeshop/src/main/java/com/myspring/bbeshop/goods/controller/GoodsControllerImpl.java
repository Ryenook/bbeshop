package com.myspring.bbeshop.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.bbeshop.common.basecontroller.BaseController;
import com.myspring.bbeshop.goods.service.GoodsService;
import com.myspring.bbeshop.goods.vo.GoodsVO;

import net.sf.json.JSONObject;

@Controller("goodsController")
@RequestMapping(value = "/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController {

	private static final Logger logger = LoggerFactory.getLogger(GoodsControllerImpl.class);

	@Autowired
	private GoodsService goodsService;

	// 리스트페이지
	@Override
	@RequestMapping(value = "menuGoods.do", method = RequestMethod.GET)
	public ModelAndView menuGoods(@RequestParam(value = "menuGoods", required = false) String menuGoods,
	                              HttpServletRequest request, HttpServletResponse response) throws Exception {
	    System.out.println("▶▶ menuGoods 리스트페이지 메서드 진입 ");
	    logger.info("▶▶ menuGoods 리스트페이지 메서드 진입");

	    String viewName = (String) request.getAttribute("viewName");
	    List<GoodsVO> goodsList = goodsService.menuGoods(menuGoods);

	    ModelAndView mav = new ModelAndView(viewName);
	    mav.addObject("goodsList", goodsList);
	    mav.addObject("menuGoods", menuGoods);

	    return mav;
	}
	// 키워드
	@RequestMapping(value = "/keywordSearch.do", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public @ResponseBody String keywordSearch(@RequestParam String keyword, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// @ResponseBody >> 해당 어노테이션은 메서드의 리턴값이 HTTP응답의 본문으로 전송되어야 함을 나타낸다.
		// 리턴하는 데이터가 JSON 형식으로 HTTP응답의 본문에 포함되어 클라이언트로 전달된다.
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		// keyword가 null인경우는 아무것도 return하지않는다.
		if (keyword == null || keyword.equals(""))
			return null;

		// 대소문자를 구분하지않고 검색하도록 한다.
		keyword = keyword.toUpperCase();
		List<String> keywordList = goodsService.keywordSearch(keyword);

		// 결과값 산출
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("keyword", keywordList);
		String jsonInfo = jsonObject.toString();

		// 변환한 string jsonObject, jsonInfo 리턴
		return jsonInfo;
	}

	// 검색
	@RequestMapping(value = "/searchGoods.do", method = RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam String searchWord, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("▶▶  searchGoods.do 메서드  진입");
		String viewName = (String) request.getAttribute("viewName");
		List<GoodsVO> goodsList = goodsService.searchGoods(searchWord);
		ModelAndView mav = new ModelAndView(viewName);
		System.out.println("▶▶  받아온 viewName : " + viewName);
		mav.addObject("goodsList", goodsList);
		return mav;
	}

	// 상품상세
	@RequestMapping(value = "/goodsDetail.do", method = RequestMethod.GET)
	public ModelAndView goodsDetail(@RequestParam String goods_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("▶▶  goodsDetail.do 메서드 진입");
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		System.out.println("▶▶  받아온 viewName : " + viewName);
		HttpSession session = request.getSession();
//		String action = (String) session.getAttribute("action");

		// goods_id값에 맞는 상세정보 가져와 goodsMap 할당
		Map goodsMap = goodsService.goodsDetail(goods_id);
		mav.addObject("goodsMap", goodsMap);

		// goodsMap을 goodsVO 객체에 대입
		GoodsVO goodsVO = (GoodsVO) goodsMap.get("goodsVO");

		// 퀵메뉴에 방문한 해당 상품정보를 추가
		addGoodsInQuick(goods_id, goodsVO, session);

		// 뷰 + 상품상세 정보 리턴
		return mav;
	}

	// 퀵메뉴
	private void addGoodsInQuick(String goods_id, GoodsVO goodsVO, HttpSession session) {
		// 중복체크를 위한 변수 초기화
		boolean already_existed = false;

		// 기존 퀵메뉴 리스트 quickGoodsList 할당
		List<GoodsVO> quickGoodsList;
		quickGoodsList = (ArrayList<GoodsVO>) session.getAttribute("quickGoodsList");

		// 퀵메뉴에 리스트가 있을때
		if (quickGoodsList != null) {

			// 퀵메뉴 리스트에는 3개의 리스트만 표시할것임.
			if (quickGoodsList.size() < 3) {
				for (int i = 0; i < quickGoodsList.size(); i++) {
					String _goodsBean = String.valueOf(quickGoodsList.get(i).getGoods_id());
					// 상품id, goods_id가 동일하다면 already_existed=true, 코드종료.
					if (goods_id.equals(_goodsBean)) {
						already_existed = true;
						break;
					}
				}
				// already_existed이 false, 중복되지않는 새로운 상품일 경우 add
				if (already_existed == false) {
					quickGoodsList.add(goodsVO);
				}

				// 퀵메뉴 리스트가 3개를 넘어가게 될경우
			} else {
				// 첫번재 상품을 없애고 새로운 상품을 추가.
				quickGoodsList.remove(0);
				quickGoodsList.add(goodsVO);
			}

			// 퀵메뉴에 리스트가 없을 경우 새 ArrayList생성 및 추가 add
		} else {
			quickGoodsList = new ArrayList<GoodsVO>();
			quickGoodsList.add(goodsVO);
		}

		// 위 작업을 완료 한 뒤 세션에 저장.
		session.setAttribute("quickGoodsList", quickGoodsList);
		session.setAttribute("quickGoodsListNum", quickGoodsList.size());
	}

}
