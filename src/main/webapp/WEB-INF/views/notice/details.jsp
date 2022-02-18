<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<style>
div {
	margin-right: 0.5rem;
	''
}
</style>
</head>
<body>
	<div class="contains">
		<!-- title -->
		<div id="title" class="d-flex">
			<div>
				<h3>${notice.title }</h3>
			</div>
		</div>
		<!-- updatedAt -->
		<div id="upDate" class="d-flex">
			<div>
				<b>最終修正日</b>
			</div>
			<div>${notice.updatedAt }</div>
		</div>
		<!-- content -->
		<div id="content">
			<div>
				<h5>内容</h5>
			</div>
			<div>${notice.contents }</div>
		</div>

	</div>
</body>
</html>