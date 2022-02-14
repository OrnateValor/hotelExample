<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>予約情報を見つかりませんでした。</h4>
	<a id="toList" class="btn btn-secondary">戻る</a>
	<script>
		//toList
		$('#toList').click(function() {
			history.back();
		});
	</script>
</body>
</html>