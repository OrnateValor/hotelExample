<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
</head>
<body>
	<div class="page-title">
		<h2>お知らせ登録</h2>
	</div>
	<div class="contains">
		<form action="" method="post">
			<!-- title -->
			<div class="row">
				<div class="col-sm-3">タイトル</div>
				<div class="col-sm-9">
					<input id="title" name="title" value="${notice.title }">
				</div>
			</div>
			<!-- regDate -->
			<div class="row">
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<div class="col-sm-3">登録日</div>
				<div class="col-sm-9">
					<fmt:formatDate value="${now }" pattern="yyyy/MM/dd" />
				</div>
			</div>
			<!-- content -->
			<div class="row">
				<div class="col-sm-3">内容</div>
				<div class="col-sm-9">
					<textarea id="contents" name="contents">${notice.contents }</textarea>
				</div>
			</div>
		</form>
		<button id="sub" class="btn btn-primary">登録</button>
	</div>
	<script>
		$('#sub').click(function() {
			$('form').attr("action", "/admin/addNotice");
			$('form').submit();
		});
	</script>
</body>
</html>