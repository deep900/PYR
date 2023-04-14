<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pradheep.dao.model.event.EventModel"%>
<%@ page import="com.pradheep.dao.model.event.EventWrapper"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google.css"/>
	media="screen">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Event Registration</title>
<meta name="description" content>
<meta name="author" content>
<link rel="icon" type="image/png"
	href=<c:url value="/resources/images/favicon.png"/>>
<meta name="description" content>
<meta name="author" content>
<link rel="icon" type="image/png"
	href=<c:url value="/resources/images/favicon.png"/>>
<meta name="description" content>
<meta name="author" content>
<link rel="icon" type="image/png"
	href=<c:url value="/resources/images/favicon.png"/>>
<!-- CSS Style -->

<!-- ResponsiveCSS Style -->
<link rel="stylesheet"
	href=<c:url value="/resources/css/responsive.css"/> type="text/css"
	media="all">
<!-- CSS ShortCode -->
<link rel="stylesheet"
	href=<c:url value="/resources/css/shortcode.css"/> type="text/css"
	media="all">
<!-- Start JavaScript -->
<link rel="stylesheet" href=<c:url value="/resources/css/javascri.css"/>
	type="text/css" media="all">
<!-- gallery CSS -->
<link rel="stylesheet"
	href=<c:url value="/resources/css/prettyPhoto.css"/> type="text/css"
	media="all">
<!-- Start Player CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/player.css"/>
	type="text/css" media="all">
<!-- Style Switcher CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/default.css"/>
	id="stylesheet">
<link rel="stylesheet" href=<c:url value="/resources/css/event.css"/>
	id="stylesheet">


<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Bona+Nova&display=swap"
	rel="stylesheet">
<!-- End JavaScript -->
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
<![endif]-->
</head>
<body style="font-family: 'Bona Nova', serif;">
	<div class="wrapper">
		<div class="main">
			<div class="content">
				<section class="grid-holder">
					<section class="grid">

						<article class="column1">
							<div class="blog-post-1">

								<br class="clear" />
								<div class="blog-holder">
									<div class="title-msg">
										<span><u> ${eventModel.eventName}
										</u></span><br>

									</div>
									<br>
									${errorMsg}									
								</div>
							</div>
						</article>
					</section>
				</section>
			</div>
		</div>
		<footer class="footer">
			<div class="footer-holder">
				<div class="footer-frame">

					<div class="bottom">
						<div class=" holder">
							<section class="grid-holder">
								<section class="grid">
									<article class="column c-one-fifth">
										
										<h3>Event hosted by: ${eventModel.organizer}</h3>
										<address>
											<span>Contact Number:
												${eventModel.eventOrgContactNumber } </span>

										</address>
						</div>
						</article>


						</section>
						</section>
					</div>
				</div>
			</div>
	</div>
	</footer>
	</div>
	<script src=<c:url value="/resources/js/jquery-1.js"/>></script>
	<!-- jQuery library -->
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery.slideshow.js"/>>
		
	</script>
	<script src=<c:url value="/resources/js/jquery-u.js"/>></script>
	<script src=<c:url value="/resources/js/jqueryi-icon-menu.js"/>></script>
	<script src=<c:url value="/resources/js/tytabs.js"/>></script>
	<script src=<c:url value="/resources/js/browser-detect.js"/>></script>
	
</body>
</html>