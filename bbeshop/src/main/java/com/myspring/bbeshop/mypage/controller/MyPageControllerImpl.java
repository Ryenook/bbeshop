package com.myspring.bbeshop.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.bbeshop.common.basecontroller.BaseController;
import com.myspring.bbeshop.member.vo.MemberVO;
import com.myspring.bbeshop.mypage.service.MyPageService;
import com.myspring.bbeshop.order.vo.OrderVO;

@Controller("myPageController")
@RequestMapping(value = "/mypage")
public class MyPageControllerImpl extends BaseController implements MyPageController {
	@Autowired
	private MyPageService myPageService;
	
	@Autowired
	private MemberVO memberVO;
	
	// 주문목록
	@Override
	@RequestMapping(value="/listMyOrderHistory.do" ,method = RequestMethod.GET)
	public ModelAndView listMyOrderHistory(@RequestParam Map<String, String> dateMap,
			                               HttpServletRequest request, HttpServletResponse response)  throws Exception {
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		HttpSession session=request.getSession();
		
		// memberInfo의 memberId get
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		String memberId=memberVO.getMemberId();
		
		// 조회기간 fixedSearchPeriod get
		String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
		
		// 조회 기간 초기화
		String beginDate=null,endDate=null;
		String [] tempDate=calcSearchPeriod(fixedSearchPeriod).split(",");
		beginDate=tempDate[0];
		endDate=tempDate[1];
		
		// 조회기간과 memberId를 dateMap에 put해 조회
		dateMap.put("beginDate", beginDate);
		dateMap.put("endDate", endDate);
		dateMap.put("memberId", memberId);
		List<OrderVO> myOrderHistList=myPageService.listMyOrderHistory(dateMap);
		
		// 검색일자를 년,월,일로 분리해서 화면에 전달
		String beginDate1[]=beginDate.split("-"); 
		String endDate1[]=endDate.split("-");
		mav.addObject("beginYear",beginDate1[0]);
		mav.addObject("beginMonth",beginDate1[1]);
		mav.addObject("beginDay",beginDate1[2]);
		mav.addObject("endYear",endDate1[0]);
		mav.addObject("endMonth",endDate1[1]);
		mav.addObject("endDay",endDate1[2]);
		mav.addObject("myOrderHistList", myOrderHistList);
		return mav;
	}
	
	// 주문취소
	@Override
	@RequestMapping(value="/cancelMyOrder.do" ,method = RequestMethod.POST)
	public ModelAndView cancelMyOrder(@RequestParam("order_id")  String order_id,
			                         HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();		
		//주문 id order_id로 db삭제 후 cancel_order message 리턴
		myPageService.cancelOrder(order_id);
		mav.addObject("message", "cancel_order");
		mav.setViewName("redirect:/mypage/listMyOrderHistory.do");
		return mav;
	}
	
	// 반품
	@Override
	@RequestMapping(value="/returnMyOrder.do" ,method = RequestMethod.POST)
	public ModelAndView returnMyOrder(@RequestParam("order_id")  String order_id,
			                         HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		//주문 id order_id로 db삭제 후 returning_goods message 리턴
		myPageService.returnOrder(order_id);
		mav.addObject("message", "returning_goods");
		mav.setViewName("redirect:/mypage/listMyOrderHistory.do");
		return mav;
	}
	
	// 교환
	@Override
	@RequestMapping(value="/exchangeMyOrder.do" ,method = RequestMethod.POST)
	public ModelAndView exchangeMyOrder(@RequestParam("order_id")  String order_id,
			                         HttpServletRequest request, HttpServletResponse response)  throws Exception {
		ModelAndView mav = new ModelAndView();
		//주문 id order_id로 db삭제 후 exchange_goods message 리턴
		myPageService.exchangeOrder(order_id);
		mav.addObject("message", "exchange_goods");
		mav.setViewName("redirect:/mypage/listMyOrderHistory.do");
		return mav;
	}
	
	// 내정보 - 단순히 페이지만 이동, myDetailInfo
	@Override
	@RequestMapping(value = "/myDetailInfo.do",method = RequestMethod.GET)
	public ModelAndView myDetailInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}

	// 회원정보 수정
	@Override
	@RequestMapping(value = "/modifyMyInfo.do",method = RequestMethod.POST)
	public ResponseEntity modifyMyInfo(
			@RequestParam("memberPw") String memberPw,
			@RequestParam("hp1") String hp1,
			@RequestParam("zipcode") String zipcode,
			@RequestParam("member_address") String member_address,
			@RequestParam("subaddress") String subaddress, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("▶▶  modifyMyInfo 진입 ");
		
		Map<String,String> memberMap = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		memberVO = (MemberVO)session.getAttribute("memberInfo");
		String memberId=memberVO.getMemberId();
		
		//받아온 정보 memberMap에 put
		memberMap.put("memberPw",memberPw);
		memberMap.put("hp1",hp1);
		memberMap.put("zipcode",zipcode);
		memberMap.put("member_address",member_address);
		memberMap.put("subaddress",subaddress);
		memberMap.put("memberId", memberId);

		//memberMap을 가지고 db수정
		memberVO=(MemberVO)myPageService.modifyMyInfo(memberMap);
		
		//수정된 회원 정보를 다시 세션에 저장한다.
		session.removeAttribute("memberInfo");
		session.setAttribute("memberInfo", memberVO);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responHeaders = new HttpHeaders();
		
		//완료후 message mod_success 리턴
		message = "mod_success";
		resEntity = new ResponseEntity(message,responHeaders,HttpStatus.OK);
		return resEntity;
	}

	//회원탈퇴
	@Override
	@RequestMapping(value = "/deleteMember.do",method = RequestMethod.POST)
	public ResponseEntity deleteMember(@RequestParam("memberId") String memberId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//@RequestParam받은 member_id를 db에서 삭제
		myPageService.deleteMember(memberId);
		
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responHeaders = new HttpHeaders();
		
		//완료 후  message delete_success 리턴
		message = "delete_success";
		resEntity = new ResponseEntity(message,responHeaders,HttpStatus.OK);
		return resEntity;
	}


}
