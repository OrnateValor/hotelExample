<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</style>
</head>
<body>
	<div class="container">
		<img alt="" src="../../resources/Img/${roomInfo.images }"
			width="720px">
		<table class="table table-info table-bordered" style="width: 720px;">
			<tbody>
				<tr>
					<td style="width: 120px;"><b>ルームナンバー</b></td>
					<td colspan="3">${roomInfo.roomNum }</td>
				</tr>
				<tr>
					<td style="width: 120px;"><b>ルーム名</b></td>
					<td style="width: 240px;">${roomInfo.roomTitle }</td>
					<td style="width: 120px;"><b>最大人数</b></td>
					<td style="width: 240px;">${roomInfo.max }人</td>
				</tr>
				<tr>
					<td><b>大人</b></td>
					<td>${roomInfo.adultCost }円</td>
					<td><b>小人</b></td>
					<td>${roomInfo.childCost }円</td>
				</tr>
				<tr>
					<td><b>詳細</b></td>
					<td colspan="3">${roomInfo.explanation }</td>
				</tr>
			</tbody>
		</table>
		<div id="buttonBox">
			<button id="booking" class="btn btn-primary">予約</button>
			<button id="toList" class="btn btn-secondary">リストへ</button>
			<c:if test="${admin != null }">
				<button id="modify" class="btn btn-warning">編集</button>
				<c:if test="${roomInfo.deleteFlag != 1 }">
					<button id="btn-del" class="btn btn-danger">非表示</button>
				</c:if>
				<c:if test="${roomInfo.deleteFlag != 0 }">
					<button id="btn-canDel" class="btn btn-success">表示</button>
				</c:if>
			</c:if>
		</div>
	</div>
	<form></form>
	<script>
		//Delete
		$('#btn-del').click(function() {
			if (confirm('この部屋をリストから非表示にしますか。')) {
				$('form').attr('action', '/roomInfo/delete');
				$('form').append($('<input>', {
					type : 'hidden',
					name : 'no',
					value : '${roomInfo.no}'
				}));
				$('form').append($('<input>', {
					type : 'hidden',
					name : 'deleteFlag',
					value : '1'
				}));
				$('form').attr('method', 'post');
				$('form').submit();
			}
		});

		//Cancel Delete
		$('#btn-canDel').click(function() {
			if (confirm('この部屋をリストから表示にしますか。')) {
				$('form').attr('action', '/roomInfo/delete');
				$('form').append($('<input/>', {
					type : 'hidden',
					name : 'no',
					value : '${roomInfo.no}'
				}));
				$('form').append($('<input/>', {
					type : 'hidden',
					name : 'deleteFlag',
					value : '0'
				}));
				$('form').attr('method', 'post');
				$('form').submit();
			}
		});

		//Modify
		$('#modify').click(function() {
			$('form').attr('action', '/roomInfo/modify/' + '${roomInfo.no}');
			$('form').append($('<input>', {
				type : 'hidden',
				name : 'no',
				value : '${roomInfo.no}'
			}));
			$('form').submit();
		});

		// toList
		$('#toList').click(function() {
			location.replace("/roomInfo/list");
		});

		// booking
		$('#booking').click(function() {
			console.log("click");
			$('form').attr('action', '/booking/form/');
			$('form').attr('method', 'post');
			$('form').append($("<input>", {
				type : 'hidden',
				name : 'no',
				value : '${roomInfo.no}'
			}));
			$('form').append($("<input>", {
				type : 'hidden',
				name : 'roomNum',
				value : '${roomInfo.roomNum}'
			}));
			$('form').append($("<input>", {
				type : 'hidden',
				name : 'adultCost',
				value : '${roomInfo.adultCost}'
			}));
			$('form').append($("<input>", {
				type : 'hidden',
				name : 'childCost',
				value : '${roomInfo.childCost}'
			}));
			$('form').append($("<input>", {
				type : 'hidden',
				name : 'max',
				value : '${roomInfo.max}'
			}));
			$('form').submit();
		});
	</script>
</body>
</html>