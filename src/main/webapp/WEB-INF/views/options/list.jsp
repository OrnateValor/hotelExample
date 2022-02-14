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

td.item {
	width: 60%;
	padding-left: 1rem;
}
</style>
</head>
<body>
	<c:set var="user" value='${admin }' scope="session" />
	<h3>宿泊オプション管理</h3>
	<table class="table table-hover table-bordered table-light">
		<thead>
			<tr>
				<th>No.</th>
				<th>オプション名</th>
				<th>コスト</th>
				<th>活性化状態</th>
				<th>表示状態</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${optionsList != null }">
				<c:forEach var="options" items='${optionsList}'>
					<tr id="${options.no }">
						<td class="no">${options.no }</td>
						<td class="item">${options.item }</td>
						<td class="cost">${options.cost }</td>
						<c:if test="${admin != null }">
							<td><c:choose>
									<c:when test='${options.activity eq "0".charAt(0) }'>活性</c:when>
									<c:when test='${options.activity eq "1".charAt(0) }'>非活性</c:when>
								</c:choose></td>
						</c:if>
						<c:if test="${admin != null }">
							<td><c:choose>
									<c:when test='${options.deleteFlag eq "0".charAt(0) }'>表示</c:when>
									<c:when test='${options.deleteFlag eq "1".charAt(0) }'>非表示</c:when>
								</c:choose></td>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<button id="addoptions" class="btn btn-primary">登録</button>
	<form></form>
	<script>
		$('tr').click(
				function() {
					$('form').attr('action',
							'/sliderImages/details/' + $(this).attr('id'));
					$('form').submit();
				});
	</script>
</body>
</html>