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
	<h3>お知らせ</h3>
	<table class="table table-hover table-bordered table-light">
		<thead>
			<tr>
				<th>No.</th>
				<th>イメージ</th>
				<th>活性化</th>
				<th>表示状態</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${sliderImagesList != null }">
				<c:forEach var="sliderImages" items='${sliderImagesList}'>
					<tr id="${sliderImages.no }">
						<td class="no">${sliderImages.no }</td>
						<td class="fileName">${sliderImages.no }</td>
						<td class="activity">${sliderImages.activity }</td>
						<td class="deleteFlag">${sliderImages.deleteFlag }</td>
						<td>
							<button id="mod" class="modSliderImages btn btn-warning">修正</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<c:if test="${user != null }">
		<button id="addsliderImages" class="btn btn-secondary">登録</button>
	</c:if>
	<form></form>
	<script>
		$('').click(
				function() {
					$('form').attr(
							'action',
							'/sliderImages/details/'
									+ $(this).parent().attr('id'));
					$('form').submit();
				});

		$('.modsliderImages').click(
				function() {
					$('form').attr(
							'action',
							'/admin/modSliderImages/'
									+ $(this).parent().parent().attr('id'));

					$('form').submit();
				});
	</script>
</body>
</html>