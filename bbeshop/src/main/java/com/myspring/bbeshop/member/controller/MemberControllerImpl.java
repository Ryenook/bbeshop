package com.myspring.bbeshop.member.controller;

import java.awt.Desktop.Action;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myspring.bbeshop.common.basecontroller.BaseController;
import com.myspring.bbeshop.member.service.MemberService;
import com.myspring.bbeshop.member.vo.MemberVO;
import com.myspring.bbeshop.order.vo.OrderVO;



@Controller("memberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl extends BaseController implements MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;

	//로그인
	@Override 
	@RequestMapping(value="/login.do" ,method = RequestMethod.POST)
	public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("▶▶ login 메서드 진입");
		ModelAndView mav = new ModelAndView();
		System.out.println(loginMap.toString() + " : 들어온 로그인 정보");
		memberVO = memberService.login(loginMap);
		System.out.println("▶▶ loginMap ");
		
		// memberVO가 존재할 경우
		if (memberVO != null && memberVO.getMemberId() != null) {
			System.out.println("▶▶ login정보가 있음으로 true문 진입 ");
			HttpSession session = request.getSession();
			session = request.getSession();
			
			// 로그인 여부 isLogOn와 회원정보 memberInfo를 세션에 저장한다.
			session.setAttribute("isLogOn", true);
			session.setAttribute("memberInfo", memberVO);
			
			 String action = (String) session.getAttribute("action");
			 if (action != null && action.equals("/order/orderEachGoods.do")) {
				 mav.setViewName("forward:" + action);	
	            } else {
	            	
	            	mav.setViewName("redirect:/main/main.do");
	            }
	            	
	        
	           
			// 리다이렉트로 메인페이지로 이동
			
			
		} else { //memberVO가 존재하지않을 경우 message를 담아  loginForm페이지로 이동한다.
			System.out.println("▶▶  로그인 실패  ");
			String message = "아이디나  비밀번호가 틀립니다. 다시 로그인해주세요";
			mav.addObject("message", message);
			mav.setViewName("/member/loginForm");
		}
		return mav;
	}
	
	
	

	//회원가입
	@Override
	@RequestMapping(value="/addMember.do" ,method = RequestMethod.POST)
	public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO,
			                HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" ▶ ▶   addMember 메서드 진입");
//		response.setContentType("text/html; charset=UTF-8");
//		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		String memberId = _memberVO.getMemberId();
		
		
		try { // 회원가입 성공시 안내문구와 함께 login페이지로 이동한다.
			System.out.println("▶▶  addMember성공!! ");
		    memberService.addMember(_memberVO);
		    message  = "<script>";
		    message +=" alert('가입성공! 쁘#에 오신걸 환영합니다!');";
		    message += " location.href='"+request.getContextPath()+"/member/loginForm.do?memberId="+memberId+"';";
		    message += " </script>";
		    
		}catch(Exception e) {// 회원가입 실패 시 다시 회원 가입페이지로 이동한다. 
			System.out.println("▶▶  addMember실패!! ");
			message  = "<script>";
			message += "alert('회원가입이 정상적으로 이루어지지 않았습니다.');";
		    message += " location.href='"+request.getContextPath()+"/member/memberForm.do';";
		    message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);

		return resEntity;
	}

	
	
	
	// 아이디 중복 확인
	@Override
	@RequestMapping(value="/overlapped.do" ,method = RequestMethod.POST)
	public ResponseEntity overlapped(@RequestParam String memberId,HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("▶▶  아이디 중복확인 overlapped 메서드 진입");
		ResponseEntity resEntity = null;
		
		// overlapped의 경과를 매핑해 return 한다.
		String result = memberService.overlapped(memberId);
		resEntity = new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}
	
	
	
	// 로그아웃
	@Override
	@RequestMapping(value="/logout.do" ,method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("▶▶logout 메서드 진입");
		
		ModelAndView mav = new ModelAndView();
		HttpSession session=request.getSession();
		//세션 설정 초기화 및 메인페이지 이동.
		session.setAttribute("isLogOn", false);
		session.removeAttribute("memberInfo");
		mav.setViewName("redirect:/main/main.do");
		return mav;
	}

	
}
