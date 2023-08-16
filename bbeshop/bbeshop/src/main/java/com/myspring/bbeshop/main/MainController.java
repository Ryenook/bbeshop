package com.myspring.bbeshop.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController")
@EnableAspectJAutoProxy 
public class MainController {
	
//	@Autowired
//	private GoodService goodService;
	
	// 메인페이지
	@RequestMapping(value = "/main/main.do", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse respons) {
		System.out.println("▶▶ main 메서드 진입");
		
		ModelAndView mav = new ModelAndView();
		// model과 view 데이터를 설정할 준비를 한다.
		String viewName = (String)request.getAttribute("viewName");
		// viewName이라는 속성(attribute)을 가져와서 뷰 이름을 설정한다.
		mav.setViewName(viewName);
		// 가져온 view 이름을 ModelAndView객체에 설정(셋팅)한다.
		
		HttpSession session = request.getSession();
		// 클라이언트와 서버간에 상태를 유지하기 위한 객체, 세션을 사용하여 사용자의 사이드 메뉴르 설정한다.
		
		System.out.println("▶▶가져온 viewName = " + viewName);
		
		return mav;
		
	}
	
	
}
