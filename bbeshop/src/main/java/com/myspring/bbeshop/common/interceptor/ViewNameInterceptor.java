package com.myspring.bbeshop.common.interceptor;

import java.sql.Wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.myspring.bbeshop.member.vo.MemberVO;

public class ViewNameInterceptor extends HandlerInterceptorAdapter {

	// count를 간단히 불러오기 위해 intercrptor에 sqlSession추가.
	@Autowired
	private SqlSession sqlSession;

	// 일반사용자인지, 관리자인지를 구문하기 위해 memberVO 빈을 사용한다.
	@Autowired
	private MemberVO memberVO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		HttpSession session = request.getSession();
		try {
			//사용자확인
			memberVO=(MemberVO)session.getAttribute("memberInfo");
			String  memberId=memberVO.getMemberId();
			
			//공통, 카트갯수, 주문갯수, 쁘#머니 출력
			int cartCount = 0;
			cartCount=sqlSession.selectOne("mapper.bbeshop.counts.cartLen",memberId);
			session.setAttribute("cartCount", cartCount);
			
			int deliveringCount = 0;
			deliveringCount=sqlSession.selectOne("mapper.bbeshop.counts.deliveringLen",memberId);
			session.setAttribute("deliveringCount", deliveringCount);
			
//			long bbeshop_money = 0L;
//			bbeshop_money=(Long)sqlSession.selectOne("mapper.bbeshop.counts.bbeshop_money",memberId);
//			session.setAttribute("bbeshop_money", bbeshop_money);
			
			//관리자일경우, 상품갯수, 주문건수, 총매출 출력.
			//System.out.println(memberId);
			if(memberId.equals("cccc") == true) {
				int goodsLen = 0;
				goodsLen=sqlSession.selectOne("mapper.bbeshop.counts.goodsLen");
				session.setAttribute("goodsLen", goodsLen);
				
				int ordersLen = 0;
				ordersLen=sqlSession.selectOne("mapper.bbeshop.counts.ordersLen");
				session.setAttribute("ordersLen", ordersLen);
				
				long totalSales = 0L;
				totalSales=(Long)sqlSession.selectOne("mapper.bbeshop.counts.totalSales");
				session.setAttribute("totalSales", totalSales);
				
				
			}
			
		}catch (Exception e) {
			//System.out.println("로그인하지않았거나 예상하기 어려운 예외가 발생했습니다.");
		}
		
		//공통 viewName 리턴.
		try {
			String viewName = getViewName(request);
			request.setAttribute("viewName", viewName);
		} catch (Exception e) {e.printStackTrace();}
		
		return true;
	}

	
	private String getViewName(HttpServletRequest request) throws Exception {
		// 해당 클래스로 request 객체에서 URL 요청명을 가져와 .do를 제외한 요청명을 구한다.
		System.out.println("겟뷰네임 메서드");
		String contextPath = request.getContextPath();
		System.out.println(contextPath);

		// uri 확인하는 과정
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();
			System.out.println("요청하는 uri : " + uri);
		}

		System.out.println("컨텍스트패스 길이 : " + contextPath.length());

		int begin = 0; // 시작위치

		if ((contextPath != null) && (!("".equals(contextPath)))) {
			begin = contextPath.length();
			System.out.println("시작위치 : " + begin);
		}
		System.out.println();

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
			System.out.println(end);

		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
			System.out.println(end);
		} else {
			end = uri.length();
			System.out.println("uri의 길이 : " + end);
		}

		String fileName = uri.substring(begin, end);
		// begin과 end는 인덱스 위치라고 볼수있음 , substring으로 uri의 시작과 끝을 결정해줌
		System.out.println("중간파일명 : " + fileName);

		if (fileName.indexOf(".") != -1) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			System.out.println(fileName);
		}

		if (fileName.lastIndexOf("/") != -1) {
			fileName = fileName.substring(fileName.lastIndexOf("/", 1), fileName.length());
			// lastIndexOf를 사용하여 뒤에서부터 필요한 /의 위치를 인덱스로 설정해줌.
			System.out.println("최종파일명 : " + fileName);
		}

		return fileName;

	}
}
