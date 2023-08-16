<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.104.2">
<title>쁘#</title>


<!-- Custom styles for this template -->
<!-- <link href="/resources/css/main.css" rel="stylesheet"> -->
</head>
<body>
	<!-- hero섹션, 광고영역 -->

	<main>

		<div
			class="carousel slide position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center"
			data-bs-ride="carousel">


			<!-- 이미지 슬라이드 -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<a href="${contextPath}/goods/goodsDetail.do?goods_id=100"> <img
						alt="" width="600px" height="400px"
						src="${contextPath}/resources/image/main/bbeshop_w1.1.jpg"></a>

				</div>
				<div class="carousel-item">
					<a href="${contextPath}/goods/goodsDetail.do?goods_id=350"> <img
						alt="" width="600px" height="400px"
						src="${contextPath}/resources/image/main/bbeshop_c__j6.1.jpg"></a>
				</div>

			</div>


			<!-- <section class="py-5 text-center container">
				<div class="row py-lg-5">
					<div class="col-lg-6 col-md-8 mx-auto"></div>
				</div>
			</section> -->

			
	</main>
	
	<!-- <figure class="figure">
				<img src="..." class="figure-img img-fluid rounded" alt="...">
				<figcaption class="figure-caption"> 이미지 </figcaption>
			</figure>
 -->

	<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>