package com.myspring.bbeshop.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.bbeshop.common.basecontroller.BaseController;
import com.myspring.bbeshop.goods.vo.GoodsVO;
import com.myspring.bbeshop.member.vo.MemberVO;
import com.myspring.bbeshop.order.service.OrderService;
import com.myspring.bbeshop.order.vo.OrderVO;

@Controller("orderController")
@RequestMapping(value = "/order")
public class OrderControllerImpl extends BaseController implements OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderVO orderVO;

//	@Autowired
//	private ApiService01 apiService01;

	// 개별주문
	@RequestMapping(value = "/orderEachGoods.do", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView orderEachGoods(@ModelAttribute("orderVO") OrderVO _orderVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		session = request.getSession();

		// 로그인 체크
		Boolean isLogOn = (Boolean) session.getAttribute("isLogOn");
		String action = (String) session.getAttribute("action");
		System.out.println("▶▶ "+action); // 로그인 상태에선 null뜸
		// 로그아웃 상태인 경우 로그인 화면으로 이동
		
		if (isLogOn == null || isLogOn == false) {
			
			session.setAttribute("orderInfo", _orderVO);
			session.setAttribute("action", "/order/orderEachGoods.do");
			return new ModelAndView("redirect:/member/loginForm.do");
			
		} else { // 이전에 로그인 상태인 경우는 주문과정 진행
			
			if (action != null && action.equals("/order/orderEachGoods.do")) {
				orderVO = (OrderVO) session.getAttribute("orderInfo");
				session.removeAttribute("action");
			} else {
				orderVO = _orderVO;
			}
		}

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		// myOrderList에 선택한 상품정보 orderVO를 리다이렉트.
		List myOrderList = new ArrayList<OrderVO>();
		myOrderList.add(orderVO);
		session.setAttribute("myOrderList", myOrderList);

		// + 회원정보와 함께 리다이렉트.
		MemberVO memberInfo = (MemberVO) session.getAttribute("memberInfo");
		session.setAttribute("order", memberInfo);

		return mav;
	}

	// 다중주문
	@RequestMapping(value = "/orderAllCartGoods.do", method = RequestMethod.POST)
	public ModelAndView orderAllCartGoods(@RequestParam("cart_goods_qty") String[] cart_goods_qty,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session = request.getSession();
		List myOrderList = new ArrayList<OrderVO>();

		// 장바구니 리스트 저장
		Map cartMap = (Map) session.getAttribute("cartMap");
		List<GoodsVO> myGoodsList = (List<GoodsVO>) cartMap.get("myGoodsList");

		// 회원정보 저장
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");

		// 상품:수량으로 input에 저장해 넘겻던 정보를 이용
		// cart_goods_qty, 받은 input의 수만큼 for문.
		for (int i = 0; i < cart_goods_qty.length; i++) {
			if (cart_goods_qty[i].contains(":")) {
				// 상품:수량을 나눠 확인.
				String[] cart_goods = cart_goods_qty[i].split(":");
				for (int j = 0; j < myGoodsList.size(); j++) {
					// 상품 객체 get
					GoodsVO goodsVO = myGoodsList.get(j);
					// 상품 id get
					int goods_id = goodsVO.getGoods_id();
					// goodsid와 전달받은 상품 id값이 같을때
					if (goods_id == Integer.parseInt(cart_goods[0])) {
						// 주문객체를 생성한다.
						OrderVO _orderVO = new OrderVO();
						// 해당상품 title저장
						String goods_title = goodsVO.getGoods_title();
						// 해당상품 수량 저장
						int goods_sales_price = goodsVO.getGoods_sales_price();
						// 해당상품 fileName도 저장해
						String goods_fileName = goodsVO.getGoods_fileName();
						// 주문객체에 set
						_orderVO.setGoods_id(goods_id);
						_orderVO.setGoods_title(goods_title);
						_orderVO.setGoods_sales_price(goods_sales_price);
						_orderVO.setGoods_fileName(goods_fileName);
						_orderVO.setOrder_goods_qty(Integer.parseInt(cart_goods[1]));
						// 완성된 주문객체는 myOrderList에 쌓아간다.
						myOrderList.add(_orderVO);
						break;
					}
				}
			}
		}
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("order", memberVO);
		return mav;
	}

	// 결제하기
	@RequestMapping(value = "/payToOrderGoods.do", method = RequestMethod.POST)
	public ModelAndView payToOrderGoods(@RequestParam Map<String, String> receiverMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		System.out.println("payToOrderGoods의 viewName은 " + viewName);
		ModelAndView mav = new ModelAndView(viewName);

		// 주문자 정보를 가져온다.
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("order");
		String memberId = memberVO.getMemberId();
		String order_name = memberVO.getMemberName();
		String order_hp = memberVO.getHp1();

		// 주문정보를 가져온다.
		List<OrderVO> myOrderList = (List<OrderVO>) session.getAttribute("myOrderList");

		// 주문정보를 for로 돌리며 myOrderList에 수령자정보를 담는다.
		for (int i = 0; i < myOrderList.size(); i++) {
			OrderVO orderVO = (OrderVO) myOrderList.get(i);
			orderVO.setMemberId(memberId);
			orderVO.setReceiver_name(receiverMap.get("receiver_name"));
			orderVO.setReceiver_hp1(receiverMap.get("receiver_hp1"));
			orderVO.setDelivery_address(receiverMap.get("delivery_address"));

			// 추후 결제시 필요할 수 있으니 주석으로 남겨둔다.
			orderVO.setPay_method(receiverMap.get("pay_method"));
			orderVO.setCard_com_name(receiverMap.get("card_com_name"));
			orderVO.setCard_pay_month(receiverMap.get("card_pay_month"));
			orderVO.setPay_order_hp_num(receiverMap.get("pay_order_hp_num"));
			orderVO.setOrder_hp(order_hp);
			
			// 기본값 배송준비중
//			orderVO.setDelivery_state("delivery_prepared");
			
			myOrderList.set(i, orderVO);

		}
		
		// 입력 정보 전달 하면서 주문데이터 추가
		orderService.addNewOrder(myOrderList);
		
		// 주문 후 쇼핑 카트 개수 재설정
		mav.addObject("myOrderInfo", receiverMap);
		mav.addObject("myOrderList", myOrderList);
		return mav;
	}

	// 결제실패
//	@Override
//	@RequestMapping(value = "/payFail.do", method = RequestMethod.POST)
//	public ModelAndView payFail(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/order/payFail");
//		return mav;
//	}

}
