<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
</head>
<c:set var="ad" value="${admin }" scope="session" />
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="/">OrnateValor</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNavAltMarkup"
			aria-controls="navbarNavAltMarkup" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-link active" aria-current="page" href="/">メイン画面へ</a> <a
					class="nav-link" href="/notice/list">お知らせ</a> <a class="nav-link"
					href="/roomInfo/list">宿泊プラン一覧</a> <a class="nav-link"
					href="/consultation/list">お問い合わせ</a> <a class="nav-link"
					href="/booking/main">予約確認</a>
				<c:if test="${ad != null}">
					<a class="nav-link" href="/admin/menu">管理者ページ</a>
				</c:if>
			</div>
		</div>
	</div>
</nav>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
