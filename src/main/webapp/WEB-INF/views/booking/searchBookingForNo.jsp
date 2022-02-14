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
				<span>予約番号 :</span>
			</div>
			<div class="col-sm-5">
				<input type="text" id="no" name="no">
			</div>
			<div class="hiddenBox">
				<div>
					<input type="hidden" id="name" name="name">
				</div>
				<div>
					<input type="hidden" id="phone" name="phone">
				</div>
			</div>
		</div>
	</form>
	<a id="submit" class="btn btn-primary">検索</a>
	<a id="toList" class="btn btn-secondary">戻る</a>
	<script>
		// 빈칸 체크
		function chkInfo() {
			if ($('#no').val() == '') {
				alert('予約番号を入力してください。');
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