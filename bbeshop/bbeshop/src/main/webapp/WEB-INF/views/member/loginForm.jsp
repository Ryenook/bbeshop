<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.104.2">
<title>로그인페이지</title>

<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/sign-in/">

<!-- <link href="/resources/css/main.css" rel="stylesheet"> -->
<style>

.b-example-divider {
	height: 3rem;
	background-color: rgba(0, 0, 0, .1);
	border: solid rgba(0, 0, 0, .15);
	border-width: 1px 0;
	box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em
		rgba(0, 0, 0, .15);
}

.b-example-vr {
	flex-shrink: 0;
	width: 1.5rem;
	height: 100vh;
}

.bi {
	vertical-align: -.125em;
	fill: currentColor;
}

.nav-scroller {
	position: relative;
	z-index: 2;
	height: 2.75rem;
	overflow-y: hidden;
}

.nav-scroller .nav {
	display: flex;
	flex-wrap: nowrap;
	padding-bottom: 1rem;
	margin-top: -1px;
	overflow-x: auto;
	text-align: center;
	white-space: nowrap;
	-webkit-overflow-scrolling: touch;

}

.form-signin {
  max-width: 330px;
  padding: 15px;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

 .form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

</style>



<script>
	const form = document.getElementById("loginForm");
	/* const inputs = form.querySelectorAll("input[required]"); */
	var allRequiredInputCheck = document.querySelector('.allRequiredInputCheck');

/* 로그인을 클릭한 경우 */
	function login() {
		let isVaid = true;
		/* required인 input들을 모두 foreach */
		inputs.forEach(input => {
			/* 입력값이 없을때 유효하지 않은 입력을 표시하기 위해 스타일을 변경, is-invalid class add */
			 if (!input.value) {
		          isValid = false;
		          input.classList.add("is-invalid");
		        } else {/* 외 의 경우 초기화. */
		          input.classList.remove("is-invalid");
		          allRequiredInputCheck.classList.add('d-none');
		        }
		      });
		/* 모든 입력이 유효하면 폼을 제출 */
	    if (isValid) {form.submit();}
	 	/* 외의 경우 안내글 show */
	    else {allRequiredInputCheck.classList.remove('d-none');}
	}
</script>

<c:if test='${not empty message }'>
	<script>
	 window.onload=function(){alert("아이디나  비밀번호가 틀립니다. 다시 입력해주세요.");}
	</script>	
</c:if> 


</head>
<body class="text-center">

	<main class="form-signin w-100 m-auto">
		<form name="frmLogin" method="post" action="${contextPath}/member/login.do">
			<img class="mb-4" src="${contextPath}/resources/image/logo/Bbeshop.png" alt=""
				width="72" height="57">
			<h1 class="h3 mb-3 fw-normal">로그인페이지</h1>

			<div class="form-floating">
				<input type="text" name="memberId" placeholder="아이디" required class="form-control" 
					id="floatingInput" value="${param.memberId }">
				<label for="floatingInput">아이디</label>
			</div>
			
			
			<div class="mb-3"></div>
			
			
			<div class="form-floating">
				<input type="password" name="memberPw" class="form-control" id="floatingPassword"
					placeholder="비밀번호"> 
				<label for="floatingPassword">비밀번호</label>
			</div>
			
			<div class="mb-3"></div>
			
			<!-- 정보를 입력하지않고 로그인을 요청한 경우 show -->
			<div class="allRequiredInputCheck invalid-feedback text-start fs-07 mb-3 d-none">
				모든	정보를 입력해주세요.</div>
			<!-- 정보를 입력하지않고 로그인을 요청한 경우 show -->
			
			<div class="d-flex justify-content-between">
				<button class="w-100 btn btn-lg btn-primary" type="submit" onclick="login()">로그인</button>
			</div>
			
			
			<div class="mb-3"></div>
			
			
			<div class="d-flex justify-content-between">
				<button class="w-50 btn btn-lg btn-primary" type="reset">다시입력</button>
				<button class="w-50 btn btn-lg btn-primary" >
				<a style="color: white; text-decoration: none;" href="${contextPath}/member/memberForm.do">
					회원가입</a></button>
			</div>
		</form>
	</main>
</body>
</html>
