<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<style>
</style>
</head>
<body>
	<div class="container">
		<form action="/booking/add" method="post">
			<table class="table table-secondary table-bordered"
				style="width: 720px;">
				<tr>
					<td>ルームナンバー</td>
					<td colspan="3"><input type="hidden" name="roomNo"
						value=${roomInfo.no } readonly> <!-- 실제 VO에 들어갈 정보 -->
						${roomInfo.roomNum } <!-- 화면에 표시만 해줄 정보 --></td>
				</tr>
				<tr>
					<td style="width: 120px;">姓名</td>
					<td style="width: 240px;"><input type="text" id="name"
						name="name" required></td>
					<td style="width: 120px;">電話番号</td>
					<td style="width: 240px;"><input type="text" id="phone"
						name="phone" required></td>
				</tr>
				<tr>
					<td>最大人数</td>
					<td colspan="3">${roomInfo.max }</td>
				</tr>
				<tr>
					<td style="width: 120px;">大人</td>
					<td style="width: 240px;"><input type="number" id="adult"
						name="adult" min=0 value="0">人</td>
					<td style="width: 120px;">小人</td>
					<td style="width: 240px;"><input type="number" id="child"
						name="child" min=0 value="0">人</td>
				</tr>
				<tr>
					<td style="width: 120px;">開始日</td>
					<td style="width: 240px;"><input type="date" id="startDate"
						name="startDate"></td>
					<td style="width: 120px;">終了日</td>
					<td style="width: 240px;"><input type="date" id="endDate"
						name="endDate"></td>
				</tr>
				<tr>
					<td>オプション</td>
					<td colspan="3"><select name="options">
							<c:forEach var="options" items="${optionsList }">
								<option value="${options.no}">${options.item }</option>
							</c:forEach>
							<option>
					</select></td>
				</tr>
				<tr>
					<td>銀行名</td>
					<td colspan="3"><input name="bankName"></td>
				</tr>
				<tr>
					<td>支店番号</td>
					<td colspan="3"><input name="bankBranchCode"></td>
				</tr>
				<tr>
					<td>口座番号ー</td>
					<td colspan="3"><input name="bankAccountNumber"></td>
				</tr>
				<tr>
					<td>総額</td>
					<td colspan="3"><input id="totalCost" type="number"
						name="totalCost" readonly>円</td>
				</tr>
			</table>
		</form>
		<a id="sub" class="btn btn-primary">予約確定</a> <a id="can"
			class="btn btn-secondary">取り消し</a>
	</div>
	<script>
		$(function() {
			var adult = $('#adult');
			var child = $('#child');
			var adultCost = 0;
			var childCost = 0;
			var totalCost = 0;

			// 달력 최소값 설정
			var now = new Date();
			var yyyy = now.getFullYear();
			var MM = (now.getMonth() + 1) < 10 ? '0' + (now.getMonth() + 1)
					: now.getMonth() + 1; //1월 = 0
			var dd = (now.getDate()) < 10 ? '0' + (now.getDate()) : now
					.getDate();
			var dd2 = (now.getDate() + 1) < 10 ? '0' + (now.getDate() + 1)
					: now.getDate() + 1;
			now = yyyy + '-' + MM + '-' + dd;
			next = yyyy + '-' + MM + '-' + dd2;
			$('#startDate').attr('min', now);
			$('#startDate').attr('value', now);
			$('#endDate').attr('min', next);

			// 인원수 체크
			function chkCost() {
				var max = '${roomInfo.max}';
				var total = Number(adult.val()) + Number(child.val());

				//인원 유무 체크
				if (total < 1 || total == null) {
					console.log(total);
					alert("人数を入力してください。");
					return false;
				}

				//최대인원 체크
				if (total > max) {
					console.log(total);
					alert("最大人数を超えました。ご確認ください。");
					return false;
				}
				console.log(total);
				return true;
			}

			// 날짜 체크
			function chkDate() {
				// 시작일 체크
				if ($('#startDate').val() == '') {
					alert('開始日を選択してください。');
					return false;
				}

				// 종료일 체크
				if ($('#endDate').val() == '') {
					alert('終了日を選択してください。');
					return false;
				}
				return true;
			}

			// 이름 및 전화번호 체크
			function chkInfo() {
				// 이름 체크
				if ($('#name').val() == '') {
					alert('姓名を入力してください。');
					return false;
				}
				// 전화번호 체크
				if ($('#phone').val() == '') {
					alert('電話番号を入力してください。');
					return false;
				}
				return true;
			}

			// 가격 변경
			$('input[type="number"]').change(
					function() {
						console.log("adultCost : " + '${roomInfo.adultCost}'
								* adult.val());
						console.log("childCost : " + '${roomInfo.childCost}'
								* child.val());

						adultCost = '${roomInfo.adultCost}' * adult.val();
						childCost = '${roomInfo.childCost}' * child.val();

						totalCost = adultCost + childCost;
						$('#totalCost').val(totalCost);
					});

			// submit
			$('#sub').click(function() {
				console.log("totalCost : " + totalCost);
				// check
				if (chkInfo() && chkCost() && chkDate())
					$('form').submit();
			});

			// cancel
			$('#can').click(function() {
				$('form').each(function() {
					this.reset();
				});
				location.href = "/roomInfo/details/" + '${roomInfo.no}';
			});
		});
	</script>
</body>
</html>