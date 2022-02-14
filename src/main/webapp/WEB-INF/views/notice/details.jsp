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
	<div class="contains">
		<!-- title -->
		<div>${notice.title }</div>
		<!-- updatedAt -->
		<div>${notice.updatedAt }</div>
		<!-- content -->
		<div>${notice.contents }</div>
	</div>
</body>
</html>