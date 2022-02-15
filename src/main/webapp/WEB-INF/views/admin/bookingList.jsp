<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<style>
.container {
	margin-top: 1rem;
	margin-bottom: 1rem;
}

th, td {
	text-align: center;
}
</style>
</head>
<body>

	<div class="container">
		<table class="table table-hover table-bordered table-light">
			<thead>
				<tr>
					<th>予約ナンバー</th>
					<th>ルームナンバー</th>
					<th>予約者名</th>
					<th>予約人数</th>
					<th>予約期間</th>
					<th colspan="2">決済状態</th>
				</tr>
			</thead>
			<tbody id="listBody">
				<c:if test='${bookingList != null }'>
					<c:forEach var='booking' items='${bookingList}'>
						<tr id=${booking.no }>
							<td class='no'>${booking.no }</td>
							<td class='roomNo'>${booking.roomNo }</td>
							<td class='name'>${booking.name }</td>
							<td class='people'>大人：${booking.adult}人<br>小人：${booking.child}人
							</td>
							<td class='term'>${booking.startDate }<br>~${booking.endDate }
							</td>
							<td class='paymentFlag'><c:choose>
									<c:when test='${booking.paymentFlag eq "0".charAt(0) }'>
										未決済
									</c:when>
									<c:when test='${booking.paymentFlag eq "1".charAt(0) }'>決済完了</c:when>
								</c:choose></td>
							<th><c:choose>
									<c:when test='${booking.paymentFlag eq "0".charAt(0) }'>
										<button id='${booking.no }' class='payment btn btn-primary'
											type='button'>処理</button>
									</c:when>
									<c:when test='${booking.paymentFlag eq "1".charAt(0) }'>
										<button id='${booking.no }'
											class='payment btn btn-outline-primary' type='button'
											disabled>決済完了</button>
									</c:when>
								</c:choose></th>

						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	<form method="post">
		<input type="hidden" name="name" value=""> <input
			type="hidden" name="phone" value="">
	</form>
	<script>
		console.log();
		$(function() {
			var stat = '${status}';
			if (stat == "error") {
				alert('処理に失敗しました。');
			} else if (stat == "success") {
				alert('処理に成功しました。');
			}
		});
		/* tr click */
		$('td').click(function() {
			$('form').attr('action', '/booking/details/');
			$('form').append($('<input/>', {
				type : 'hidden',
				name : 'no',
				value : $(this).parent().attr('id')
			}));
			$('form').submit();
		});
		/* button click */
		$('.payment').click(function() {
			//확인
			if (!confirm("決済状態を変えますか。")) {
				return false;
			}
			$('form').attr('action', '/admin/modPayment');
			$('form').append($('<input/>', {
				type : 'hidden',
				name : 'id',
				value : $(this).attr('id')
			}));

			$('form').submit();
		});
	</script>
</body>
</html>