<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">

<style type="text/css">
.container {
	margin-top: 1rem;
	margin-bottom: 1rem;
}

th, .no, .updatedAt {
	text-align: center;
}

td.title {
	width: 60%;
	padding-left: 1rem;
}
</style>
</head>
<body>
	<c:set var="user" value='${admin }' scope="session" />
	<h3>スライドイメージ管理</h3>
	<table class="table table-hover table-bordered table-light">
		<thead>
			<tr>
				<th>No.</th>
				<th>イメージ</th>
				<th>活性化</th>
				<th>表示状態</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${sliderImagesList != null }">
				<c:forEach var="sliderImages" items='${sliderImagesList}'>
					<tr id="${sliderImages.no }">
						<td class="no">${sliderImages.no }</td>
						<td class="fileName"><img
							src="../../resources/Img/${sliderImages.fileName }" width="240px">
						</td>
						<td><c:choose>
								<c:when test='${sliderImages.activity eq "0".charAt(0) }'>活性</c:when>
								<c:when test='${sliderImages.activity eq "1".charAt(0) }'>非活性</c:when>
							</c:choose></td>
						<td><c:choose>
								<c:when test='${sliderImages.deleteFlag eq "0".charAt(0) }'>表示</c:when>
								<c:when test='${sliderImages.deleteFlag eq "1".charAt(0) }'>非表示</c:when>
							</c:choose></td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<c:if test="${user != null }">
		<button id="addSliderImages" class="btn btn-secondary">登録</button>
	</c:if>
	<form></form>
	<script>
		$('tr').click(
				function() {
					$('form').attr('action',
							'/sliderImages/details/' + $(this).attr('id'));
					$('form').submit();
				});

		$('#addSliderImages').click(function() {
			$('form').attr('action', '/sliderImages/addForm/');
			$('form').submit();
		});
	</script>
</body>
</html>