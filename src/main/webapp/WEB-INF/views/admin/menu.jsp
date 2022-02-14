<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
</head>
<body>
	<div class="container">
		<c:set var="ad" value="${admin }" scope="session" />
		<h3>管理者ページ</h3>
		<div>Admin : ${ad.id }</div>
		<c:if test="${ad != null }">
			<div>
				<button id="logout" class="btn btn-secondary">ログアウト</button>
			</div>
		</c:if>
		<div>
			<button id="notice" class="btn btn-primary">お知らせ登録</button>
			<button id="booking" class="btn btn-primary">予約管理</button>
			<button id="roomInfo" class="btn btn-primary">宿泊プラン管理</button>
			<button id="options" class="btn btn-primary">宿泊オプション管理</button>
			<button id="consultation" class="btn btn-primary">お問い合わせ管理</button>
			<button id="sliderImages" class="btn btn-primary">スライドイメージ管理</button>
		</div>
	</div>
	<form action="" method="post"></form>
	<script>
		$(document).ready(function() {
			var ad = '${ad}';
			if (ad == '') {
				location.replace("/admin/main");
			}
		});

		$('#notice').click(function() {
			$('form').attr("action", "/admin/notice");
			$('form').submit();
		});

		$('#booking').click(function() {
			$('form').attr("action", "/admin/booking");
			$('form').submit();
		});

		$('#roomInfo').click(function() {
			$('form').attr("action", "/admin/roomInfo");
			$('form').submit();
		});

		$('#options').click(function() {
			$('form').attr("action", "/admin/options");
			$('form').submit();
		});

		$('#consultation').click(function() {
			$('form').attr("action", "/admin/consultation");
			$('form').submit();
		});

		$('#sliderImages').click(function() {
			$('form').attr("action", "/admin/sliderImages");
			$('form').submit();
		});

		$('#logout').click(function() {
			$('form').attr("action", "/admin/logout");
			$('form').submit();
		});
	</script>
</body>
</html>