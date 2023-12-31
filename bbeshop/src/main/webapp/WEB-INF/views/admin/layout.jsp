<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- intercepter에서 set한 회원정보에 따른 조회값  get -->
<c:set var="goodsLen" value="${sessionScope.goodsLen}" />
<c:set var="ordersLen" value="${sessionScope.ordersLen}" />
<c:set var="totalSales" value="${sessionScope.totalSales}" />
<!-- intercepter에서 set한 회원정보에 따른 조회값  get -->
<body>
<div class="bg-success p-2 text-dark bg-opacity-10">
	<div class="row">

		<!-- count 값 표시 -->
		<div class="d-flex align-content-stretch p-0 position-relative ">
			<div
				class="border-end border-success p-2 me-2 border-opacity-50 fw-light text-center  ps-4 pt-4 "
				style="width: 150px">
				<p class="w-100 mb-0 fs-3">Bbeshop관리</p>
			</div>
			<div
				class="border-end border-success p-2 me-2 border-opacity-50 fw-light text-center  ps-4 pt-4 "
				style="width: 150px">
				<p class="w-100 mb-2 small">등록상품</p>
				<p class="w-100 mb-0">

					<!-- 등록상품 count -->
					<span class="fw-light me-2 lh-1" style="font-size: 2.2rem;">${goodsLen }</span>개
					<!-- 등록상품 count -->

				</p>
			</div>
			<div
				class="border-end border-success p-2 me-2 border-opacity-50 fw-light text-center  ps-4 pt-4 "
				style="width: 150px">
				<p class="w-100 mb-2 small">주문건수</p>
				<p class="w-100 mb-0">

					<!-- 주문건수 count -->
					<span class="fw-light me-2 lh-1" style="font-size: 2.2rem;">${ordersLen }</span>개
					<!-- 주문건수 count -->

				</p>
			</div>

			<div
				class="border-end border-success p-2 me-2 border-opacity-50 fw-light text-center  ps-4 pt-4 "
				style="width: 150px">
				<p class="w-100 mb-2 small">총 매출</p>
				<p class="w-100 mb-0">

					<!-- 매출 sum -->
					<span class="fw-light me-2 lh-1" style="font-size: 2.2rem;">
						<fmt:formatNumber value="${totalSales }" pattern="#,###" />
					</span>원
					<!-- 매출 sum -->

				</p>
			</div>

			<!-- left 메뉴 -->
			<div
				class="position-absolute top-100 start-0 px-3 py-4 d-flex flex-column border secondary-subtle bg-success bg-opacity-10"
				style="width: 150px;">
				<p class="fw-bold text-black mb-2">Bbeshop</p>
				<a href="${contextPath}/admin/goods/adminGoodsMain.do"
					class="text-decoration-none mb-2 small sideMenu adminGoodsMain">상품관리</a>
				<a href="${contextPath}/admin/order/adminOrderMain.do"
					class="text-decoration-none mb-2 small sideMenu adminOrderMain">주문관리</a>
				<a href="${contextPath}/admin/member/adminMemberMain.do"
					class="text-decoration-none mb-0 small sideMenu adminMemberMain">회원관리</a>
			</div>
			<!-- left 메뉴 -->

		</div>

		<!-- count 값 표시 -->
	</div>
</div>


</body>
