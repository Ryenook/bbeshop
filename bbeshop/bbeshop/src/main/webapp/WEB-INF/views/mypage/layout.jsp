<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- intercepter에서 set한 회원정보에 따른 조회값  get -->
<c:set var="cartCount" value="${sessionScope.cartCount}" />
<c:set var="deliveringCount" value="${sessionScope.deliveringCount}" />
<c:set var="bbeshop_money" value="${sessionScope.bbeshop_money}" />
<!-- intercepter에서 set한 회원정보에 따른 조회값  get -->

<div class="bg-success p-2 text-dark bg-opacity-10">
	<div class="row">

<!-- count 값 표시 -->
		<div class="d-flex align-content-stretch p-0 position-relative ">
			<div
				class="border-end border-success p-2 me-2 border-opacity-50 fw-light text-center  ps-4 pt-4 " 
				style="width: 150px">
				<p class="w-100 mb-0 fs-3">MY쁘#</p>
			</div>
			<div
				class="border-end border-success p-2 me-2 border-opacity-50 fw-light text-center  ps-4 pt-4 "
				style="width: 150px">
				<p class="w-100 mb-2 small">배송중</p>
				<p class="w-100 mb-0">

				<!-- 배송중 count -->
					<span class="fw-light me-2 lh-1" style="font-size: 2.2rem;">${deliveringCount }</span>개
				<!-- 배송중 count -->
				
				</p>
			</div>
			<div class="border-end border-success p-2 me-2 border-opacity-50 fw-light text-center  ps-4 pt-4 "
				style="width: 150px">
				<p class="w-100 mb-2 small">장바구니</p>
				<p class="w-100 mb-0">
				
				<!-- 장바구니 count -->
					<span class="fw-light me-2 lh-1" style="font-size: 2.2rem;">${cartCount }</span>개
				<!-- 장바구니 count -->
				
				</p>
			</div>

			<%-- <div
				class="border-end border-success p-2 me-2 border-opacity-50 fw-light text-center  ps-4 pt-4"
				style="width: 150px">
				<p class="w-100 mb-2 small">쁘#머니</p>
				<p class="w-100 mb-0">
				
				<!-- 쁘#머니 count -->
					<span class="fw-light me-2 lh-1" style="font-size: 2.2rem;">
					<fmt:formatNumber value="${bbeshop_money}" pattern="#,###" /></span>원
				<!-- 쁘#머니 count -->
					
				</p>
			</div> --%>

			<!-- position-absolute top-100 start-0 bg-light px-3 py-4 d-flex flex-column border secondary-subtle -->
			<!-- left 메뉴 -->
			<div
				class="position-absolute top-100 start-0  px-3 py-4 d-flex flex-column border 
				bg-success p-2 text-dark bg-opacity-10"
				style="width: 150px;">
				<p class="fw-bold text-black mb-2 border-success p-2 me-2 border-opacity-50">MY쁘#</p>
				<a href="${contextPath}/mypage/listMyOrderHistory.do"
					class="text-decoration-none mb-2 small sideMenu listMyOrderHistory">주문목록</a>
				<a href="${contextPath}/cart/myCartList.do"
					class="text-decoration-none mb-2 small sideMenu myCartList">장바구니</a>
				<a href="${contextPath}/mypage/myDetailInfo.do"
					class="text-decoration-none mb-0 small sideMenu myDetailInfo">내정보</a>
			</div>
			<!-- left 메뉴 -->
			
		</div>
<!-- count 값 표시 -->

	</div>
</div>
