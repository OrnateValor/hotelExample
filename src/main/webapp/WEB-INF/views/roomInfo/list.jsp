<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<title>宿泊ブラン一覧</title>
<style>
.container {
	margin-top: 1rem;
	margin-bottom: 1rem;
}
</style>
</head>
<body>
	<div class="container">
		<h3>宿泊プラン管理</h3>
		<table class="table table-hover table-light">
			<thead>
				<tr>
					<th>ルームナンバー</th>
					<th>ルーム名</th>
					<th>最大人数</th>
					<c:if test="${admin != null }">
						<th>表示状態</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:if test="${roomInfoList != null }">
					<c:forEach var="roomInfo" items='${roomInfoList}'>
						<tr id='${roomInfo.no}'>
							<td>${roomInfo.roomNum }</td>
							<td>${roomInfo.roomTitle }</td>
							<td>${roomInfo.max }</td>
							<c:if test="${admin != null }">
								<td><c:choose>
										<c:when test="${roomInfo.deleteFlag != 0 }">非表示</c:when>
										<c:when test="${roomInfo.deleteFlag != 1 }">表示</c:when>
									</c:choose></td>
							</c:if>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<c:if test="${admin != null }">
		<div id="buttonBox">
			<button id="addRoom" class="btn btn-primary">新規追加</button>
		</div>
	</c:if>
	<form></form>
	<script>
		$('tr').click(
				function() {
					$('form').attr('action',
							'/roomInfo/details/' + $(this).attr('id'));
					$('form').submit();
				});

		$('#addRoom').click(function() {
			console.log("AddRoom click");
			$('form').attr('action', '/roomInfo/addForm/');
			$('form').attr('method', 'post');
			$('form').submit();
		});
	</script>
</body>
</html>