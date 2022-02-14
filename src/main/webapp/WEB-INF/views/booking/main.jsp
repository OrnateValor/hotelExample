<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<style>
.searchBtn {
	width: 240px;
	height: 100px;
}
</style>
</head>
<body>
	<h3>予約確認</h3>

	<div class="row">
		<div class="col-sm-5">
			<button id="bookingForName" class="btn btn-lg btn-primary searchBtn">名前と電話番号で探す</button>
		</div>
		<div class="col-sm-5">
			<button id="bookingForNo" class="btn btn-lg btn-primary searchBtn">予約番号で探す</button>
		</div>
	</div>
	<form></form>
	<script>
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

		$('#bookingForName').click(function() {
			location.href = '/booking/searchName';
		});

		$('#bookingForNo').click(function() {
			location.href = '/booking/searchNo';
		});
	</script>
</body>
</html>