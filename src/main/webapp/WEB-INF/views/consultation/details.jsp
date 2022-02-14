<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
</head>
<body>
	<br> title : ${consultation.title }
	<br> writer : ${consultation.name }
	<br> content : ${consultation.contents }
	<br>
	<button id="toList" class="btn btn-secondary">リストへ</button>
	<c:set var="ad" value="${admin }" scope="session" />
	<c:if test="${ad != null }">
		<button id="replyBtn" class="btn btn-primary">返答作成</button>
	</c:if>
	<button id="callDelModal" class="btn btn-danger" data-bs-toggle="modal"
		data-bs-target="#delModal">削除</button>

	<!-- Modal -->
	<!-- del 클릭 시, 모달 띄우고 비밀번호 입력 후, 처리로 이동. -->
	<div id="delModal" class="modal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">パスワードを入力してください</div>
				<div class="modal-body">
					<input id="passwd" type="password">
				</div>
				<div class="modal-footer">
					<button id="delBtn" class="btn btn-danger">確認して削除</button>
					<button class="btn btn-secondary" data-bs-dismiss="modal">閉じる</button>
				</div>
			</div>
		</div>
	</div>
	<form action="" method="post">
		<input type="hidden" name="no" value="${consultation.no }">
	</form>
</body>
<script>
	//alert
	$(function() {
		var error = '${error}';
		if (error != '') {
			alert('${error}');
		}
	});

	// 목록으로
	$('#toList').click(function() {
		location.href = "/consultation/list";
	});

	// 답변 작성
	$('#replyBtn').click(function() {
		$('form').attr('action', '/consultation/replyForm');
		$('form').append($('<input/>', {
			type : 'hidden',
			name : 'grno',
			value : '${consultation.grno }'
		}));
		$('form').submit();
	});

	// 삭제
	$('#delBtn').click(function() {
		if (confirm('本当に削除しますか？')) {
			$('form').attr('action', '/consultation/del');
			$('form').append($('<input/>', {
				type : 'hidden',
				name : 'pw',
				value : $('#passwd').val()
			}));
			$('form').submit();
		}
	});
</script>
</html>