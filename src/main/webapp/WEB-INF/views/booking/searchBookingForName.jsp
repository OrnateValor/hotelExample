<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<title>予約確認</title>
</head>
<body>
	<form action="/booking/details" method="post">
		<h3>予約確認</h3>

		<div class="row">
			<div class="col-sm-2 justify-content-center">
				<span>姓名 :</span>
			</div>
			<div class="col-sm-5">
				<input type="text" id="name" name="name">
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2 justify-content-center">
				<span>電話番号 :</span>
			</div>
			<div class="col-sm-5">
				<input type="text" id="phone" name="phone">
			</div>
		</div>
		<input type="hidden" name="no" value="">
	</form>
	<a id="submit" class="btn btn-primary">検索</a>
	<a id="toList" class="btn btn-secondary">戻る</a>
	<script>
		// 이름 및 전화번호 체크
		function chkInfo() {
			// 이름 체크
			if ($('#name').val() == '') {
				alert('姓名を入力してください。');
				return false;
			}
			// 전화번호 체크
			if ($('#phone').val() == '') {
				alert('電話番号を入力してください。');
				return false;
			}
			return true;
		}

		// submit
		$('#submit').click(function() {
			// check
			if (chkInfo())
				$('form').submit();
		});
		//toList
		$('#toList').click(function() {
			history.back();
		});
	</script>
</body>
</html>