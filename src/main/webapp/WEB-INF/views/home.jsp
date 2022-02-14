<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="false"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript"
	src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
</head>
<body>
	<h1>Hello world!</h1>
	<div id="sliderImgZone" class="slider">
		<c:forEach var="sliderImages" items="${sliderImagesList }">
			<div>
				<img src="../../resources/Img/${sliderImages.fileName }"
					width="360px">
			</div>
		</c:forEach>
	</div>

	<P>The time on the server is ${serverTime}.</P>
	<script>
		$(document).ready(function() {
			var rfrr = document.referrer;
			if (rfrr == 'http://localhost:8282/admin/menu') {
				console.log("menu");
				window.history.pushState(null, "", window.location.href);
				window.onpopstate = function() {
					history.go(1);
				};
			}

			$('.sliderImgZone').slick({
				dots : true,
				infinite : true,
				speed : 500,
				fade : true,
				cssEase : 'linear'
			});
		});
	</script>
</body>
</html>
