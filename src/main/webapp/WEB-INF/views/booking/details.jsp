<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<style>
input:read-only {
	background-color: #D3D3D3;
	border-radius: 5px;
	border-color: #ccc;
}

.container {
	margin-top: 1rem;
	margin-bottom: 1rem;
}

#adult, #child {
	width: 30%;
}
</style>
</head>
<body>
	<div class="container">
		<h4>予約情報</h4>
		<form action="" method="post">
			<input type="hidden" name="no" value="${booking.no }">
			<table class="table table-secondary table-bordered"
				style="width: 720px;">
				<tr>
					<td>ルームナンバー</td>
					<td colspan="3"><input type="hidden" name="roomNo"
						value="${booking.roomNo }" readonly> <!-- 실제 VO에 들어갈 정보 -->
						${roomInfo.roomNum } <!-- 화면에 표시만 해줄 정보 --></td>
				</tr>
				<tr>
					<td style="width: 120px;">姓名</td>
					<td style="width: 240px;"><input type="text" id="name"
						name="name" value="${booking.name }" readonly></td>
					<td style="width: 120px;">電話番号</td>
					<td style="width: 240px;"><input type="text" id="phone"
						name="phone" value="${booking.phone }" readonly></td>
				</tr>
				<tr>
					<td>最大人数</td>
					<td colspan="3">${roomInfo.max }</td>
				</tr>
				<tr>
					<td style="width: 120px;">大人</td>
					<td style="width: 240px;"><input type="number" id="adult"
						name="adult" min=0 value="${booking.adult }" readonly></td>
					<td style="width: 120px;">小人</td>
					<td style="width: 240px;"><input type="number" id="child"
						name="child" min=0 value="${booking.child }" readonly></td>
				</tr>
				<tr>
					<td style="width: 120px;">開始日</td>
					<td style="width: 240px;"><input type="date" id="startDate"
						name="startDate" value="${booking.startDate }" readonly></td>
					<td style="width: 120px;">終了日</td>
					<td style="width: 240px;"><input type="date" id="endDate"
						name="endDate" value="${booking.endDate }" readonly></td>
				</tr>
				<tr>
					<td>オプション</td>
					<td colspan="3">${options.item }</td>
				</tr>
				<tr>
					<td>銀行名</td>
					<td colspan="3"><input name="bankName"
						value="${booking.bankName }" readonly></td>
				</tr>
				<tr>
					<td>支店番号</td>
					<td colspan="3"><input name="bankBranchCode"
						value="${booking.bankBranchCode }" readonly></td>
				</tr>
				<tr>
					<td>口座番号ー</td>
					<td colspan="3"><input name="bankAccountNumber"
						value="${booking.bankAccountNumber }" readonly></td>
				</tr>
				<tr>
					<td>総額</td>
					<td colspan="3"><input id="totalCost" type="number"
						name="totalCost" value="${booking.totalCost }" readonly></td>
				</tr>
			</table>
			<input type="hidden" name="buildCode" value="${booking.buildCode }">
			<input type="hidden" name="options" value="${booking.options }">
		</form>
		<a id="mod" class="btn btn-primary">修正</a>
	</div>
	<script>
		$(function() {
			if ('${stat}' != "") {
				alert('${stat}' + "に成功しました。");
			}
		});
		// modify
		$('#mod').click(function() {
			$('form').attr("action", "/booking/mod");
			$('form').submit();
		});
	</script>
</body>
</html>