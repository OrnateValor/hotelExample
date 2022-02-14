<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="false"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<html>
<head>
<style>
#sliderImgZone {
	
}
</style>
</head>
<body>
	<h1>Hello world!</h1>
	<div id="sliderImgZone" class=""></div>

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
		});
	</script>
</body>
</html>
