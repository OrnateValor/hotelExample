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
				<th>タイトル</th>
				<th>更新日</th>
				<c:if test="${user != null }">
					<th></th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:if test="${noticeList != null }">
				<c:forEach var="notice" items='${noticeList}'>
					<tr id="${notice.no }">
						<td class="no">${notice.no }</td>
						<td class="title">${notice.title }</td>
						<td class="updatedAt"><fmt:formatDate
								value="${notice.updatedAt }" pattern="yyyy年MM月dd日" /></td>
						<c:if test="${user != null }">
							<td>
								<button class="modNotice btn btn-warning">修正</button>
							</td>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<c:if test="${user != null }">
		<button id="addNotice" class="btn btn-secondary">登録</button>
	</c:if>
	<form></form>
	<script>
		$('.title').click(
				function() {
					$('form').attr('action',
							'/notice/details/' + $(this).parent().attr('id'));
					$('form').submit();
				});

		$('.modNotice').click(
				function() {
					$('form').attr(
							'action',
							'/admin/modNotice/'
									+ $(this).parent().parent().attr('id'));

					$('form').submit();
				});
	</script>
</body>
</html>