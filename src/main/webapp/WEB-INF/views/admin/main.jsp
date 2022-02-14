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
	<div class="contents">
		<form action="/admin/menu" method="post">
			<h3>管理者ログイン</h3>

			<div class="row">
				<div class="col-sm-2 justify-content-center">ID :</div>
				<div class="col-sm-4">
					<input type="text" id="id" name="id">
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2 justify-content-center">パスワード :</div>
				<div class="col-sm-4">
					<input type="password" id="pw" name="pw">
				</div>
			</div>
			<input type="hidden" name="no" value="">
		</form>
	</div>
	<div id="buttonBox" class="">
		<button id="submit" class="btn btn-primary">ログイン</button>
		<button id="toList" class="btn btn-secondary">戻る</button>
	</div>
	<script>
		$(function() {
			var auth = '${auth}';
			if (auth == 'false') {
				alert('接近権限を得られませんでした。');
			}
		});
		// 이름 및 전화번호 체크
		function chkInfo() {
			// 이름 체크
			if ($('#id').val() == '') {
				alert('IDを入力してください。');
				return false;
			}
			// 전화번호 체크
			if ($('#pw').val() == '') {
				alert('パスワードを入力してください。');
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
			location.replace("/");
		});
	</script>
</body>
</html>