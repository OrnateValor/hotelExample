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
	<div class="contains">
		<div id="#detailsBody">
			<div class="row">
				<!-- item -->
				<div class="col-sm-2">イメージ</div>
				<div class="col">
					<img alt="" src="../../resources/Img/${sliderImages.fileName }"
						width="720px">
				</div>
			</div>
			<div class="row">
				<!-- cost -->
				<div class="col-sm-2">ソート番号</div>
				<div class="col">${sliderImages.sortNo }</div>
			</div>
			<div class="row">
				<!-- activity -->
				<div class="col-sm-2">活性化状態</div>
				<div class="col">
					<c:if test='${sliderImages.activity eq "0".charAt(0)}'>活性</c:if>
					<c:if test='${sliderImages.activity eq "1".charAt(0)}'>非活性</c:if>
				</div>
			</div>
			<div class="row">
				<!-- deleteFlag -->
				<div class="col-sm-2">表示状態</div>
				<div class="col">
					<c:if test='${sliderImages.deleteFlag eq "0".charAt(0)}'>表示</c:if>
					<c:if test='${sliderImages.deleteFlag eq "1".charAt(0)}'>非表示</c:if>
				</div>
			</div>
		</div>
		<div class="d-flex">
			<div class="">
				<c:if test='${sliderImages.activity eq "0".charAt(0)}'>
					<button id="inActive" class="btn btn-warning">非活性化</button>
				</c:if>
				<c:if test='${sliderImages.activity eq "1".charAt(0)}'>
					<button id="active" class="btn btn-warning">活性化</button>
				</c:if>
			</div>
			<div class="">
				<c:if test='${sliderImages.deleteFlag eq "0".charAt(0)}'>
					<button id="del" class="btn btn-danger">削除</button>
				</c:if>
				<c:if test='${sliderImages.deleteFlag eq "1".charAt(0)}'>
					<button class="btn btn-outline-danger" disabled="disabled">削除されました。</button>
				</c:if>
			</div>
		</div>
		<form action="" method="post">
			<input type="hidden" name="no" value="${sliderImages.no }">
		</form>
	</div>
	<script>
		$('#inActive').click(function() {
			$('form').attr('action', '/sliderImages/inActive');
			$('form').append($('<input/>', {
				type : 'hidden',
				name : 'activity',
				value : '1'
			}));
			$('form').submit();
		});

		$('#active').click(function() {
			$('form').attr('action', '/sliderImages/inActive');
			$('form').append($('<input/>', {
				type : 'hidden',
				name : 'activity',
				value : '0'
			}));
			$('form').submit();
		});

		$('#del').click(function() {
			$('form').attr('action', '/sliderImages/del');
			$('form').submit();
		});
	</script>
</body>
</html>