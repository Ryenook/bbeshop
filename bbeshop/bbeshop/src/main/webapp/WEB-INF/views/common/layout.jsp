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

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
	integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer">
<link href="${contextPath}/resources/css/style.css" rel="stylesheet"
	type="text/css" media="screen">



<link rel="shortcut icon"
	href="${contextPath}/resources/image/logo/Bbeshop.png" />

<title><tiles:insertAttribute name="title" /></title>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N"
	crossorigin="anonymous"></script>



<script type="text/javascript">

// 현재 페이지의 경로를 가져오는 함수
function getCurrentPath() {
	console.log(window.location.pathname);
    return window.location.pathname;
}

// 특정 경로인 경우 side 요소의 display 속성을 변경
function adjustSideDisplay() {
    var currentPath = getCurrentPath();
    /* var specificPath = "/bbeshop/member/loginForm.do"; // 원하는 특정 경로 설정 */
    
	// 로그인페이지 || 회원가입페이지 || 관리자 아이디 로그인 시 퀵메뉴 보이지 않게 설정 
    if (currentPath === "/bbeshop/member/loginForm.do" || currentPath ==="/bbeshop/member/memberForm.do" || ${memberInfo.memberId == 'cccc'}) {
        var sideElement = document.getElementById("quickmenu");
        if (sideElement) {
            sideElement.style.display = "none";
        }
    }
}

// 페이지 로드 시 조건 체크 및 스타일 조작 실행
window.onload = function() {
    adjustSideDisplay();
};
	
</script>





</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="mypage" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
	<tiles:insertAttribute name="quickMenu" />




	<a
		class="back-to-top text-decoration-none d-inline-block text-center lh-lg pt-2 fw-bold btn border-main"
		title="Back to top" href="#"> <i
		class="fa-solid fa-arrow-up lh-base"></i></a>



</body>

