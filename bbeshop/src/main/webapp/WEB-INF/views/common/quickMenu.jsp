<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="cartCount" value="${sessionScope.cartCount}" />

<script>
	var array_index = 0;
	var SERVER_URL = "${contextPath}/download.do";
	function fn_show_next_goods() {
		var img_sticky = document.getElementById("img_sticky");
		var cur_goods_num = document.getElementById("cur_goods_num");
		var _h_goods_id = document.frm_sticky.h_goods_id;
		var _h_goods_fileName = document.frm_sticky.h_goods_fileName;
		if (array_index < _h_goods_id.length - 1)
			array_index++;

		var goods_id = _h_goods_id[array_index].value;
		var fileName = _h_goods_fileName[array_index].value;
		img_sticky.src = SERVER_URL + "?goods_id=" + goods_id + "&fileName="
				+ fileName;
		cur_goods_num.innerHTML = array_index + 1;
	}

	function fn_show_previous_goods() {
		var img_sticky = document.getElementById("img_sticky");
		var cur_goods_num = document.getElementById("cur_goods_num");
		var _h_goods_id = document.frm_sticky.h_goods_id;
		var _h_goods_fileName = document.frm_sticky.h_goods_fileName;

		if (array_index > 0)
			array_index--;

		var goods_id = _h_goods_id[array_index].value;
		var fileName = _h_goods_fileName[array_index].value;
		img_sticky.src = SERVER_URL + "?goods_id=" + goods_id + "&fileName="
				+ fileName;
		cur_goods_num.innerHTML = array_index + 1;
	}
</script>


<script>
	var quickMenu = document.getElementById('quickmenu');
	var initialTop = quickMenu.getBoundingClientRect().top + window.scrollY;

	window.addEventListener('scroll', function() {
		var scrollY = window.scrollY;
		if (scrollY > initialTop) {
			quickMenu.classList.add('sticky');
		} else {
			quickMenu.classList.remove('sticky');
		}
	});
</script>
<style>
    .quickMenu {
        position: fixed;
        top: 75%;
        left: 0;
        transform: translateY(-50%);
        width: 130px;
        transition: top 0.3s;
    }
    
    .quickMenu.sticky {
        position: absolute;
    }
</style>


<div id="quickmenu" class="quickMenu">
	<p class="btn btn-dark rounded-0 py-2 d-block small mb-0">
		최근본상품</span>
	</p>
	<div class="d-flex flex-column bg-white border border-top-0 p-2 pb-0">



		<c:choose>

			<c:when test="${ empty quickGoodsList }">
				<p class="text-center fs-6 my-5 text-secondary">최근 본 상품이 없습니다.</p>
			</c:when>

			<c:otherwise>
				<form name="frm_sticky" class="mb-0">
					<c:forEach var="item" items="${quickGoodsList }"
						varStatus="itemNum">

						<a
							href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id}"
							class="back_eee d-block mb-2"> <img
							src="${contextPath}/download.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}"
							class="" style="width: 112px; height: 112"> <%-- <p>${item.goods_id}</p> --%>
						</a>

					</c:forEach>
				</form>
			</c:otherwise>
		</c:choose>

	</div>
</div>


