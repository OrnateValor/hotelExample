<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
</head>
<body>
	<form id="roomModForm" action="/roomInfo/mod" method="post"
		enctype="multipart/form-data">
		<div>
			<div>識別番号：</div>
			<div>
				<input type="text" id="no" name="no" value="${roomInfo.no }"
					readonly>
			</div>
		</div>
		<div>
			<div>ルームナンバー :</div>
			<div>
				<input type="text" id="roomNum" name="roomNum"
					value="${roomInfo.roomNum }">
			</div>
		</div>
		<div>
			<div>部屋名 :</div>
			<div>
				<input type="text" id="roomTitle" name="roomTitle"
					value="${roomInfo.roomTitle }">
			</div>
		</div>
		<div>
			<div>最大人数 :</div>
			<div>
				<input type="number" id="max" name="max" min="0"
					value="${roomInfo.max }">
			</div>
		</div>
		<div>
			<div>大人料金 :</div>
			<div>
				<input type="number" id="adultCost" name="adultCost" min="0"
					value="${roomInfo.adultCost }">
			</div>
		</div>
		<div>
			<div>小人料金 :</div>
			<div>
				<input type="number" id="childCost" name="childCost" min="0"
					value="${roomInfo.childCost }">
			</div>
		</div>
		<div>
			<div>詳細説明 :</div>
			<div>
				<textarea id="explanation" name="explanation" maxlength="4000">${roomInfo.explanation }</textarea>
			</div>
		</div>
		<div>
			<div>カラーコード :</div>
			<div>
				<input type="color" id="colorCode" name="colorCode"
					value='${roomInfo.colorCode }'>
			</div>
		</div>
		<div>
			<div>部屋イメージ :</div>
			<div>
				<input type="file" id="imgs" name="imgs">
			</div>
			<div>
				<img id="preview">
			</div>
		</div>
		<c:if test='${roomInfo.deleteFlag == 1 }'>
			<div>
				<div>非表示</div>
				<div>
					<input type="checkbox" id="delFlagChkBox" value="1"
						checked="checked">
				</div>
				<div>
					<input type="hidden" id="deleteFlag" name="deleteFlag"
						value=${roomInfo.deleteFlag }>
				</div>
			</div>
		</c:if>
		<input type="hidden" id="images" name="images"
			value=${roomInfo.images }>
	</form>
	<div id="btnBox">
		<button type="submit" id="submit" class="btn btn-primary">編集</button>
		<button type="reset" id="toMenu" class="btn btn-secondary">管理者メニューへ</button>
	</div>
	<script>
		// deleteFlag controll
		function delFlag(chkBox) {
			if (!chkBox.is('checked')) {
				$('#deleteFlag').val('0');
			}
		}
		// Chk Img
		$(function() {
			$('#imgs').on('change', filechange);
		});

		// 파일이 바뀌었을 때 동작
		function filechange(e) {
			var files = e.target.files;
			var arr = Array.prototype.slice.call(files);

			arr.forEach(function(f) {
				//이미지파일인지 체크
				if (!f.type.match("image.*")) {
					alert('イメージファイルだけアップロードできます。');
					$('#images').val('');
					return;
				}

				// 미리보기
				sel_file = f;

				var reader = new FileReader();
				reader.onload = function(e) {
					$('#preview').attr('src', e.target.result);
					$('#preview').css("width", "480px");
				}
				reader.readAsDataURL(f);
			})
		};
		// input empty check
		function inputChk() {
			if ($('#roomNum').val() == '') {
				alert('部屋の番号を入力してください。');
				return false;
			}
			if ($('#roomTitle').val() == '') {
				alert('部屋名を入力してください。');
				return false;
			}
			if ($('#max').val() == '') {
				alert('最大人数を入力してください。');
				return false;
			}
			if ($('#adultCost').val() == '') {
				alert('大人料金を入力してください。');
				return false;
			}
			if ($('#childCost').val() == '') {
				alert('小人料金を入力してください。');
				return false;
			}
			if ($('#explanation').val() == '') {
				alert('詳細説明を入力してください。');
				return false;
			}
			if ($('#colorCode').val() == '') {
				alert('カラーコードを設定してください。');
				return false;
			}

			return true;
		}
		// Submit
		$('#submit').click(function() {
			delFlag($('#delFlagChkBox'));

			if (inputChk()) {
				if (confirm('この情報で編集しますか。'))
					$('form').submit();
			}
		});

		// toMenu
		$('#toMenu').click(function() {
			$('#roomModForm')[0].reset();
			$('form').attr('action', '/admin/menu');
			$('form').submit();
		});
	</script>
</body>
</html>