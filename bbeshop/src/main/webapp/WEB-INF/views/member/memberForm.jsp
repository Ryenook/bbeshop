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
<title>Signin Template · Bootstrap v5.2</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/5.2/examples/sign-in/">

<!-- <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet"> -->

<style>
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

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

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
</style>


<!-- Custom styles for this template -->
<!-- <link href="signin.css" rel="stylesheet"> -->

<script type="text/javascript">
//id중복체크
function fn_overlapped(){
    var _memberId=$("#floatingInput").val();
    if(_memberId==''){
   	 alert("ID를 입력하세요");
   	 return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlapped.do",
       dataType:"text",
       data: {memberId:_memberId},
       success:function (data,textStatus){
         if(data=='false'){
       	    /* alert("사용한 가능한 ID입니다."); */
	       	    $('#btnOverlapped').prop("disabled", true);
	       	    $('#floatingInput').prop("disabled", true);
	       		$('#memberId').addClass("is-valid");
	   			$('#memberId').removeClass("is-invalid");
	   	 		$('.memberId-feedback.valid-feedback').removeClass("d-none");
	       	    $('#memberId').val(_memberId);
          }else{
	        	$('#memberId').removeClass("is-valid");
	           	$('#memberId').addClass("is-invalid");
	           	$('.memberId-feedback.invalid-feedback').removeClass("d-none");
        	  /* alert("중복ID입니다. 다른 ID를 입력해주세요."); */
          }
       }, 
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }	

</script>

</head>
<body class="text-center">

	<main class="form-signin w-100 m-auto">
		<form name="frmLogin" method="post" id="memberForm"
			action="${contextPath}/member/addMember.do">
			<img class="mb-4"
				src="${contextPath}/resources/image/logo/Bbeshop.png" alt=""
				width="72" height="57">
			<h1 class="h3 mb-3 fw-normal">회원가입페이지</h1>

			<!-- 아이디  -->
			<div class="form-floating">
				<input name="_memberId" type="text" class="form-control"
					minlength="3" id="floatingInput" placeholder="아이디" required
					onblur="fn_overlapped()"> <label for="floatingInput">아이디</label>
				<input type="hidden" name="memberId" id="memberId" />

				<div
					class="memberId-feedback valid-feedback text-start fs-07 d-none">
					사용가능한 아이디 입니다.</div>
				<div
					class="memberId-feedback invalid-feedback text-start fs-07 d-none">
					사용중인 아이디 입니다.</div>
				<div class="mb-3"></div>
			</div>
			<!-- 아이디  -->


			<!-- 비밀번호  -->
			<div class="form-floating">
				<input name="memberPw" type="password"
					class="form-control memberPwWrite" minlength="4"
					id="floatingPassword" placeholder="비밀번호"
					onblur="memberPwChecking()" required> <label
					for="floatingPassword">비밀번호</label>
			</div>
			<div class="mb-3"></div>
			<div class="form-floating">
				<input type="password" class="form-control memberPwCheck"
					minlength="4" id="floatingPasswordConfirm" placeholder="비밀번호확인"
					onblur="memberPwChecking()" required> <label
					for="floatingPasswordConfirm">비밀번호확인</label>
			</div>
			<div class="memberPw-feedback valid-feedback text-start fs-07 d-none">
				비밀번호가 일치합니다.</div>
			<div
				class="memberPw-feedback invalid-feedback text-start fs-07 d-none">
				비밀번호가 일치하지않습니다.</div>
			<!-- 비밀번호  -->

			<div class="mb-3"></div>


			<!-- 이름 -->
			<div class="form-floating">
				<input name="memberName" type="text" class="form-control"
					id="floatingInput" placeholder="이름" required oninput="etcInptut()">
				<label for="floatingInput">이름</label>
			</div>
			<!-- 이름 -->

			<div class="mb-3"></div>

			<!-- 휴대폰번호 -->
			<div class="form-floating">
				<input name="hp1" type="text" class="form-control"
					id="floatingInput" minlength="11" placeholder="휴대폰번호" required
					oninput="etcInptut()"> <label for="floatingInput">휴대폰번호</label>
			</div>
			<!-- 휴대폰번호 -->


			<div class="mb-3"></div>

			<div
				class="allRequiredInputCheck invalid-feedback text-start fs-07 mb-3 d-none">
				모든 입력해주세요.</div>


			<div class="d-flex justify-content-between">
				<button class="w-100 btn btn-lg btn-primary button1" type="submit"
					onClick="memberForm">회원가입</button>
			</div>
		</form>
	</main>
</body>
</html>

<script>


//비밀번호 확인 값체크
//password input에서 onblur시 memberPwChecking가 실행됨.
function memberPwChecking(){
	let memberPwWrite = document.querySelector('.memberPwWrite');
  let memberPwCheck = document.querySelector('.memberPwCheck');
  let memberPw_feedback = document.querySelector('.memberPw-feedback.invalid-feedback');
  let memberPw_feedback_valid = document.querySelector('.memberPw-feedback.valid-feedback');
  let button1 = document.querySelector('.button1');
  
  //memberPwWrite와 memberPwCheck가 입력되엇을때만 안내문구를 출력할 것임.
	if(memberPwWrite.value=="" || memberPwCheck.value==""){
		button1.disabled = "";
		console.log("아무것도입력되지않음");
	}else{
		//같지않을때
		if(memberPwWrite.value != memberPwCheck.value){
			button1.disabled = "disabled";
      	 memberPwWrite.classList.remove("is-valid");
      	 memberPwCheck.classList.remove("is-valid");
      	 
      	 memberPwWrite.classList.add("is-invalid");
      	 memberPwCheck.classList.add("is-invalid");
      	 memberPw_feedback.classList.remove("d-none");
      	 memberPw_feedback.classList.add("d-block");
      	 
      	 memberPw_feedback_valid.classList.remove("d-block");
      	 memberPw_feedback_valid.classList.add("d-none");
       }else{
      	//같을때
      	 button1.disabled =""; 
      	 memberPwWrite.classList.remove("is-invalid");
      	 memberPwCheck.classList.remove("is-invalid");
      	 memberPw_feedback.classList.add("d-none");
      	 memberPw_feedback.classList.remove("d-block");
      	 
      	 memberPwWrite.classList.add("is-valid");
      	 memberPwCheck.classList.add("is-valid");
      	 
      	 memberPw_feedback_valid.classList.add("d-block");
      	 memberPw_feedback_valid.classList.remove("d-none");
       }
	}
}
	//비밀번호 확인 값체크


  const form = document.getElementById("memberForm");
  /* const inputs = form.querySelectorAll("input[required]"); */
  /* const nameAndH1 = form.querySelectorAll(".nameAndH1"); */
  var allRequiredInputCheck = document.querySelector('.allRequiredInputCheck');
  
  
  //이름, 휴대폰 input 에서 this input이 공란일때 style로 경고를 표시함.
  function etcInptut(){
  	if(this.value != ""){
  		/* nameAndH1.forEach(input => {input.classList.remove("is-invalid");}); */
  		allRequiredInputCheck.classList.add('d-none');
  	}
  }
  
  
  //회원가입
  function memberForm(){
      let isValid = true;
      //input들의 값을 확인하여 값이 없을경우 style을 변경한다.
      inputs.forEach(input => {
          if (!input.value) {
            isValid = false;
            input.classList.add("is-invalid");
           
          } else {
            input.classList.remove("is-invalid");
            allRequiredInputCheck.classList.add('d-none');
          }
        });
      
   // 모든 입력이 유효하면 폼을 제출
      if (isValid) {form.submit();} 
      else {
      	allRequiredInputCheck.classList.remove('d-none');}
  }
</script>