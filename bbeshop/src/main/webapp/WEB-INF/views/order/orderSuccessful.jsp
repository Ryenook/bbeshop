<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="tot_price" value="0" />
<c:forEach var="item" items="${myOrderList }">
	<c:set var="tot_price"
		value="${tot_price+item.goods_sales_price*item.order_goods_qty}" />
</c:forEach>
<style>

</style>
<div class="container">
	<div class="row ms-5 ps-5">
		<div class="mt-5 p-0 ps-5 align-items-center">
			<div class="ps-4">
				<h2 class="font-monospace align-items-center">주문이 정상적으로
					완료되었습니다.</h2>
					
				<!-- 주문 상품 내역  -->
				<table
					class="order_goods_info align-items-center container mb-3">
					<p class="fs-5 fw-bold  bg-success bg-opacity-10">상품 정보</p>
					<tr>
						<td class="table-light p-2 align-middle  fw-bold border  border-success border-opacity-50 text-center samll "
							style="width: 45px;">주문번호</td>
						<td class="table-light p-2 align-middle fw-bold border   border-success border-opacity-50 text-center samll "
							style="width: 45px;">주문상품</td>
						<td class="table-light p-2 align-middle fw-bold border  border-success border-opacity-50 text-center samll "
							style="width: 45px;">수량</td>
						<td class="table-light p-2 align-middle fw-bold border  border-success border-opacity-50 text-center samll "
							style="width: 45px;">상품금액</td>
						<td class="table-light p-2 align-middle fw-bold border  border-success border-opacity-50 text-center samll "
							style="width: 45px;">배송비</td>
						<td class="table-light p-2 align-middle fw-bold border  border-success border-opacity-50 text-center samll 0"
							style="width: 45px;">주문금액합계</td>
					</tr>
					
					
					<tr>
						<td class="table-light  border  border-success border-opacity-50 text-center" 
								rowspan="${myOrderList.size() }">${myOrderList[0].order_id }</td>
						<td class="table-light  border  border-success border-opacity-50 text-center">
							<a href="${contextPath}/goods/goodsDetail.do?goods_id=${myOrderList[0].goods_id }">
								<img src="${contextPath}/download.do?goods_id=${myOrderList[0].goods_id}&fileName=${myOrderList[0].goods_fileName}"
									width="75">
								<p>${myOrderList[0].goods_title }</p>
							</a>
						</td>
						<td class="table-light  border border-success border-opacity-50 text-center">
							<h5>${myOrderList[0].order_goods_qty }</h5> 개 </td>
						<td class="table-light  border  border-success border-opacity-50 text-center">
							<fmt:formatNumber value="${myOrderList[0].order_goods_qty*myOrderList[0].goods_sales_price }" />원</td>
						<td class="table-light  border  border-success border-opacity-50 text-center" rowspan="${myOrderList.size() }">0 원</td>
						<td class="table-light  border  border-success border-opacity-50 text-center" rowspan="${myOrderList.size() }">
							<fmt:formatNumber value="${tot_price }" pattern="#,###" /> 원</td>
					</tr>
					
					
					<c:if test="${myOrderList.size()>1 }">
						<c:forEach var="item" items="${myOrderList }" begin="1">
							<tr>
								<td class="table-light  border  border-success border-opacity-50 text-center">
									<a href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
										<img src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}" width="75">
										<p>${item.goods_title }</p>
									</a>
								</td>
								<td class="table-light  border  border-success border-opacity-50 text-center">${item.order_goods_qty }개</td>
								<td class="table-light  border  border-success border-opacity-50 text-center"><fmt:formatNumber value="${item.order_goods_qty*item.goods_sales_price }" /> 원</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
				<!-- 주문 상품 내역 -->


				<!-- 주문정보 -->
				<table class="orderPage_info order_table container mb-3">
					<p class="fs-5 fw-bold  bg-success bg-opacity-10">주문정보</p>
					<tr>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >이름</td>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >${order.memberName }</td>
					</tr>
					<tr>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >휴대폰 번호</td>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50"  style="width: 200px;">${order.hp1 }</td>
					</tr>
				</table>
				<!-- 주문정보 -->

				
				<!-- 배송 정보 -->
				<table class="receiver_info order_table container mb-3">
					<p class="fs-5 fw-bold bg-success bg-opacity-10">배송지 정보</p>
					<tr>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >수령인 이름</td>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >${myOrderList[0].receiver_name }</td>
					</tr>
					<tr>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >수령인 휴대폰 번호</td>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >${myOrderList[0].receiver_hp1 }</td>
					</tr>
					<tr>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >수령인 주소</td>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >${myOrderList[0].delivery_address }</td>
					</tr>

				</table>
				<!-- 배송정보 -->


				<!-- 결제정보 -->
				<table class="pay_info order_table container mb-3">
					<p class="fs-5 fw-bold bg-success bg-opacity-10">결제 정보</p>
					<tr>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >결제방법</td>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >${myOrderList[0].pay_method }</td>
					</tr>
					<tr>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >결제카드</td>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >${myOrderList[0].card_com_name }</td>
					</tr>
					<tr>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >할부기간</td>
						<td class="table-light ps-4  align-middle border border-success border-opacity-50" style="width: 200px;" >${myOrderList[0].card_pay_month }</td>
					</tr>

				</table>
				<!-- 결제정보 -->
				
				
				<button
					class="btn btn-lg border-main rounded-0 w-100 d-block fw-bold p-2 lh-lg mt-0 mb-2 fs-6
								bg-success bg-opacity-10" onclick="location.href='${contextPath }/mypage/listMyOrderHistory.do'">
								주문목록 보기</button>
				<button
					class="btn btn-lg border-main rounded-0 w-100 d-block fw-bold p-2 lh-lg mt-0 mb-2 fs-6
								bg-success bg-opacity-10" onclick="location.href='${contextPath}/main/main.do'">쇼핑 계속하기</button>
			</div>
		</div>
	</div>
</div>