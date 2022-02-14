<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<meta charset="UTF-8">
<style>
textarea {
	width: 480px;
	height: 240px;
	resize: none;
}

.container {
	margin-top: 1rem;
	margin-botton: 1rem;
}
</style>
</head>
<body>
	<div class="container">
		<div>
			<h3>質問作成</h3>
		</div>
		<div>
			<form method="post">
				<div>
					<label for="title">タイトル</label> : <input type="text" id="title"
						name="title" required="required"
						<c:if test="${consultation.title != null }">value="RE:${consultation.title }"</c:if>>
				</div>
				<div>
					<label for="name">名前</label> : <input type="text" id="name"
						name="name" required="required">
				</div>
				<div>
					<label for="passwd">パスワード</label> : <input type="password"
						id="passwd" name="passwd" required="required">
				</div>
				<div>
					<label>非公開<input id="lockFlag" name="lockFlag"
						type="checkbox" value="1"></label> <input id="lockFlag_unchecked"
						name="lockFlag" type="hidden" value="0">
				</div>
				<div>
					<div>内容</div>
					<div>
						<span id="cnt">0</span> <span id="maxLength">/4000</span>
					</div>
					<div>
						<textarea id="contents" name="contents" maxlength="4000"
							required="required"><c:if
								test="${consultation != null }">원본글 : ${consultation.contents }
								===========================================</c:if></textarea>
					</div>
				</div>
				<c:if test="${grno != 0 }">
					<input id="grno" name="grno" type="hidden"
						value="<c:if test="${grno == null }">0</c:if>
				<c:if test="${grno != null }">${grno }</c:if>">
				</c:if>
				<c:if test="${grgrod != 0 }">
					<input id="grgrod" name="grgrod" type="hidden"
						value="<c:if test="${grgrod == null }">0</c:if>
				<c:if test="${grgrod != null }">${grgrod }</c:if>">
				</c:if>
				<c:if test="${depth != 0 }">
					<input id="depth" name="depth" type="hidden"
						value="<c:if test="${depth == null }">0</c:if>
				<c:if test="${depth != null }">${depth }</c:if>">
				</c:if>
			</form>
		</div>
		<div>
			<button id="submit" class="btn btn-primary">登録</button>
			<button id="reset" class="btn btn-secondary">取り消し</button>
		</div>
	</div>
</body>
<script>
	// input Check
	function inputChk(input) {
		if (input.val() == '') {
			alert(input.closest('div').children('label').text() + "を入力してください。");
			input.focus();
			return false;
		}
		return true;
	}

	function contentsChk(input) {
		if (input.val().length < 10) {
			alert("内容を10文字以上入力してください。");
			input.focus();
			return false;
		}

		if (input.val().length > 4000) {
			alert("内容は4000文字以内にしてください。");
			input.focus();
			return false;
		}

		return true;
	}

	// contents
	$('#contents').keyup(function() {
		var contents = $(this).val();

		//글자 수 카운트
		$('#cnt').text(contents.length);
		if (contents.length == 0 || contents == '') {
			$('#cnt').text('0');
		}
	});

	// submit
	$('#submit').click(function() {
		//input check
		if (!inputChk($('#title'))) {
			return false;
		}

		if (!inputChk($('#name'))) {
			return false;
		}
		if (!inputChk($('#passwd'))) {
			return false;
		}

		if (!contentsChk($('textarea'))) {
			return false;
		}
		console.log("submit ready");
		// 전송준비
		$('form').attr("action", "/consultation/add");
		// 체크박스 값 반환. - 체크되어있으면 1, 아니면 히든에서 0
		if ($('#lockFlag').is(':checked')) {
			$('#lockFlag_unchecked').attr("disabled", true);
		}
		$('form').submit();

	});

	// reset
	$('#reset').click(function() {
		location.replace("/consultation/list");
	});
</script>
</html>