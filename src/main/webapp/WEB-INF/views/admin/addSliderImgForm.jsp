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
	<form id="sliderImgForm" action="" method="post"
		enctype="multipart/form-data">
		<div>file</div>
		<div>
			<input type="file" id="fileName" name="fileName">
		</div>
		<div>file</div>
		<div>
			<input type="file" id="fileName" name="fileName">
		</div>
		<div>sortNo</div>
		<div>
			<input type="number" id="sortNo" name="sortNo">
		</div>
		<div>活性化</div>
		<div>
			<input type="checkbox" id="activity" name="activity">
		</div>
		<div>削除</div>
		<div>
			<input type="checkbox" id="deleteFlag" name="deleteFlag">
		</div>
	</form>
	<button type="button" id="submit" >確定</button>
	<button type="button" id="cancel" >取り消し</button>
</body>
</html>