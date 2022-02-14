<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- feather icons -->
<script
	src="https://cdn.jsdelivr.net/npm/feather-icons/dist/feather.min.js"></script>
<meta charset="UTF-8">
<title>お問い合わせ</title>
<style>
.container {
	margin-top: 1rem;
	margin-bottom: 1rem;
}

th, .name, .updatedAt {
	text-align: center;
}

td.title {
	width: 60%;
	padding-left: 1rem;
}

tr.reply td.title {
	padding-left: 1rem;
}

.lock {
	width: 14px;
	height: 14px;
}

.no-thread {
	text-align: center;
}

.title {
	width: 60%;
}

.name {
	width: 20%;
}

.updatedAt {
	width: 20%;
}
</style>
</head>
<body>
	<c:set var="ad" value="${admin }" scope="session" />
	<div class="container">
		<table class="table table-hover table-bordered table-light">
			<thead>
				<tr>
					<th class="title">タイトル</th>
					<th class="name">作成者</th>
					<th class="updatedAt">登録日</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty consultationList }">
					<td colspan="3" class="no-thread">登録された質問がありません。</td>
				</c:if>
				<c:if test="${consultationList != null }">
					<c:forEach var="consultation" items='${consultationList}'>
						<tr id=${consultation.no }
							<c:if test="${consultation.depth > 0 }">
								class="reply"
							</c:if>>
							<td class="title" style="padding-left:${consultation.depth+1}rem">
								<c:if test="${consultation.depth > 0 }">└ </c:if>
								${consultation.title } <c:if
									test='${consultation.lockFlag eq "1".charAt(0) }'>
									<i data-feather="lock" class="lock"></i>
								</c:if>
							</td>
							<td class="name">${consultation.name }</td>
							<td class="updatedAt"><fmt:formatDate
									value="${consultation.updatedAt }" pattern="yyyy年MM月dd日" /></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<button id="add" class="btn btn-primary">登録</button>
	</div>

	<!-- Password Modal -->
	<div id="pwModal" class="modal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">パスワードを入力してください</div>
				<div class="modal-body">
					<input id="passwd" type="password">
				</div>
				<div class="modal-footer">
					<button id="kakuninn" class="btn btn-success">確認</button>
					<button id="toji" class="btn btn-secondary" data-bs-dismiss="modal">閉じる</button>
				</div>
			</div>
		</div>
	</div>

	<form></form>
	<script>
		feather.replace()
		//alert
		$(function() {
			var error = '${error}';
			if (error != '') {
				alert('${error}');
			}
		});
		//Authority Check
		function authChk() {
			var admin = '${ad}';
			console.log("admin : " + admin);
			if (admin != '')
				return true;
			return false;
		}
		// lock Check
		function lockChk(e) {
			var lock = e.find('.lock').attr('class');
			if (lock != null) {
				// lock 이면 true
				console.log(lock);
				return true;
			}
			return false;
		}
		// tr onclick
		$('tr').click(
				function() {
					// 일단 경로 먼저
					$('form').attr('action',
							'/consultation/details/' + $(this).attr('id'));
					if (lockChk($(this))) {
						// 락이 걸린 게시물
						if (authChk()) {
							//관리자인 경우 그냥 진행
							console.log("submit");
							$('form').submit();
						} else {
							$('form').append($('<input/>', {
								type : "hidden",
								name : "no",
								value : $(this).attr('id')
							}));
							// 관리자가 아닌 경우 비밀번호 확인 모달
							console.log('modal');
							$('.modal').modal('show');
						}
					} else {
						// 락이 걸리지 않았다면 그냥 진행.
						console.log("submit");
						$('form').submit();
					}
				});
		// add btn click
		$('#add').click(function() {
			location.href = "/consultation/add";
		});

		// modal btn
		$('#kakuninn').click(function() {
			$('form').attr('action', '/consultation/lockChk/');
			$('form').attr('method', 'post');
			$('form').append($('<input/>', {
				type : "hidden",
				name : "pw",
				value : $('#passwd').val()
			}));
			$('form').submit();
		});
	</script>
</body>
</html>