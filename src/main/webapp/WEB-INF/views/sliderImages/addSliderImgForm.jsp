<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
</head>
<body>
	<form id="sliderImgForm" method="post" enctype="multipart/form-data">
		<div>
			<div>
				<img id="preview">
			</div>
			<div>
				<input type="file" id="file" name="file">
			</div>
		</div>
		<div>
			<div>活性化</div>
			<div>
				<input type="checkbox" id="activity" name="activity">
			</div>
		</div>
		<div>
			<div>削除</div>
			<div>
				<input type="checkbox" id="deleteFlag" name="deleteFlag">
			</div>
		</div>
	</form>
	<button type="button" id="submit" class="btn btn-primary">確定</button>
	<button type="button" id="cancel" class="btn btn-secondary">取り消し</button>

	<script>
		function inputChk() {
			if ($('#fileName').val() == '') {
				alert('イメージをアップロードしてください。');
				return false;
			}
			if ($('#sortNo').val() == '') {
				alert('ソート番号を入力してください。');
				return false;
			}
			return true;
		}

		// Chk Img
		$(function() {
			$('#file').on('change', filechange);
		});

		// 파일이 바뀌었을 때 동작
		function filechange(e) {
			var files = e.target.files;
			var arr = Array.prototype.slice.call(files);

			arr.forEach(function(f) {
				//이미지파일인지 체크
				if (!f.type.match("image.*")) {
					alert('イメージファイルだけアップロードできます。');
					$('#fileName').val('');
					return;
				}

				// 미리보기
				sel_file = f;

				var reader = new FileReader();
				reader.onload = function(e) {
					$('#preview').attr('src', e.target.result);
					$('#preview').css("width", "360px");
				}
				reader.readAsDataURL(f);
			})
		};

		// Submit
		$('#submit').click(function() {
			if (inputChk()) {
				if (confirm('このイメージをスライドに追加しますか。')) {
					$('form').attr('action', '/sliderImages/add');
					$('form').submit();
				}
			}
		});

		// Return Menu
		$('#toMenu').click(function() {
			$('#roomAddForm')[0].reset();
			$('form').attr('action', '/admin/menu');
			$('form').submit();
		});
	</script>
</body>
</html>