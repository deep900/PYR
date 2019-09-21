<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/> media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google.css"/> media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google1.css"/> media="screen">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title><spring:message code="mainpage.banner.verse1.title" /></title>
<meta name="description" content>
<meta name="author" content>
<link rel="icon" type="image/png" href=<c:url value="/resources/images/favicon.png"/>>
<meta name="description" content>
<meta name="author" content>
<link rel="icon" type="image/png" href=<c:url value="/resources/images/favicon.png"/>>
<meta name="description" content>
<meta name="author" content>
<link rel="icon" type="image/png" href=<c:url value="/resources/images/favicon.png"/>>
<!-- CSS Style -->
<link rel="stylesheet" href=<c:url value="/resources/css/all.css"/> type="text/css" media="all">
<!-- ResponsiveCSS Style -->
<link rel="stylesheet" href=<c:url value="/resources/css/responsive.css"/> type="text/css" media="all">
<!-- CSS ShortCode -->
<link rel="stylesheet" href=<c:url value="/resources/css/shortcode.css"/> type="text/css" media="all">
<!-- Start JavaScript -->
<link rel="stylesheet" href=<c:url value="/resources/css/javascri.css"/> type="text/css" media="all">
<!-- gallery CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/prettyPhoto.css"/> type="text/css" media="all">
<!-- Start Player CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/player.css"/> type="text/css" media="all">
<!-- Style Switcher CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/default.css"/> id="stylesheet">
<!-- Megamenu CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/megamenu.css"/> type="text/css" media="screen" />
<script type="text/javascript">
	function changeStyle(url) {
		document.getElementById('stylesheet').href = 'css/' + url;
	}
</script>

<!-- End JavaScript -->
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
<![endif]-->

<style type="text/css">
/* Declare custom tamil fonts. */
@font-face {
	font-family: CustomKomalaB;
	src: url(<c:url value="/resources/fonts/TMOTKomalaBold_Ship.eot"/>)		
}

@font-face {
	font-family: CustomKomalaB;
	src: url(<c:url value = "/resources/fonts/TMOTKomalaBold_Ship.ttf" />)		
}

 @font-face {
	font-family: CustomNambi;
	src: url(<c:url value ="/resources/fonts/TMOTNambi_Ship.eot"/>)		
}

@font-face {
	font-family: CustomNambi;
	src: url(<c:url value ="/resources/fonts/TMOTNambi_Ship.ttf"/>)		
}

 @font-face {
	font-family: CustomTamil50;
	src: url(<c:url value ="/resources/fonts/Uni-Tamil150.eot"/>)		
}

@font-face {
	font-family: CustomTamil50
	src: url(<c:url value ="/resources/fonts/Uni-Tamil150.ttf"/>)		
}

</style>
</head>
<body>
	<ul id="sheetswitch">
		<li><a href="#" class="default"
			onClick="changeStyle('default.css');return false;">black</a></li>
		<li><a href="#" class="blue"
			onClick="changeStyle('blue.css');return false;">white</a></li>
		<li><a href="#" class="green"
			onClick="changeStyle('green.css');return false;">white</a></li>
		<li><a href="#" class="perpul"
			onClick="changeStyle('perpul.css');return false;">white</a></li>
		<li><a href="#" class="brown"
			onClick="changeStyle('brown.css');return false;">white</a></li>
	</ul>
	<div class="wrapper">
		<!-- header -->
		<div class="main">
			
			<!-- Content -->
			<article class="column c-three-fourth">
							<c:set var="count" value="0" scope="page" />
							
								<div class="blog-post">
									<div class="slide-1">
										<ul>
											<li><img id="image"
												src='<c:url value="getImage?id=${message.getId()}"/>'
												alt="image" /></li>
										</ul>
										<div class="blog-date">
											<p>${message.getLastModifiedDisplay()}</p>
										</div>
									</div>
									<br class="clear" />
									<div class="blog-holder">
										<h4 class="post-title">
											<a href=<c:url value="/messages/our-god-is-loving-god" />>
												<c:choose>
													<c:when test="${language == 'ta'}">
														<c:out value="${message.getMessageTitleTamil()}" />

													</c:when>
													<c:otherwise>
														<c:out value="${message.getMessageTitleEnglish()}" />
													</c:otherwise>
												</c:choose> <span>1</span>
											</a>
										</h4>

										<c:choose>
											<c:when test="${language == 'ta'}">
											<c:forEach items="${message.getListedParaTamil()}" var="msgModelTamil">
											<p> ${fn:replace(<c:out value='${msgModelTamil}'/>, 'st', '')}</p>
											</c:forEach>	
											</c:when>
											<c:otherwise>
												<c:forEach items="${message.getListedParaEnglish()}" var="msgModelEng">
											<p> ${fn:replace(<c:out value='${msgModelEng}'/>, 'st', '')}</p>
											</c:forEach>	
											</c:otherwise>
										</c:choose>


										<div class="blog-comments">
											<ul class="list-link">
												<li><a href="#" class="author">By Praise Your
														Redeemer</a></li>
												<li><a
													href=<c:url value="/messages/our-god-is-loving-god"/>
													class="section">Read More</a></li>
											</ul>
										</div>
									</div>
								</div>
							


						</article>
		</div>
		<!-- footer -->
		<footer class="footer">
			<div class="footer-holder">
				<div class="footer-frame">
					
					<div class="bottom">
						<div class=" holder">
							<section class="grid-holder">
								<section class="grid">
									<article class="column c-one-half">
										<div class="box">
											<h5>About</h5>
											<p>This ministry is mainly to tell the word of god with testimonies and touch life who are in need and talk about our living LORD JESUS CHRIST</p>
											<a href='<c:url value="/about" />' class="view-all">Read More</a>
										</div>
									</article>
									
									<article class="column c-one-fifth">
										<div class="box">
											<h5>Praise Your Redeemer Ministry</h5>
											<address>
												<span>Chennai, India</span>
												<span>administrator@praiseyourredeemer.org</span>
											</address>										
										</div>
									</article>
									<article class="column c-one-fifth">
										<%-- <div class="box">
											<h5>Total Visitors</h5>
											<p><c:out value="${totalVisits}"/> Visitors</p>											
										</div> --%>
									</article>
								</section>
							</section>
						</div>
					</div>
				</div>
			</div>
		</footer>
	</div>
	<!-- <script src=<c:url value="/resources/js/jquery-1.js"/>></script>
	jQuery library
	<script type="text/javascript" src=<c:url value="/resources/js/jquery.slideshow.js"/>> </script>
	jQuery main banner
	<script src=<c:url value="/resources/js/jquery-u.js"/>></script>
	jQuery Ui
	<script src=<c:url value="/resources/js/sourtin-jquery.js"/>></script>
	sourtin-jquery
	<script src=<c:url value="/resources/js/jqueryi-icon-menu.js"/>></script>
	Js For all Small Style 
	<script src=<c:url value="/resources/js/tytabs.js"/>></script>
	jQuery Plugin tytabs 
	<script src=<c:url value="/resources/js/speakker-big-1.2.02r134.min.js"/>></script>
	<script src=<c:url value="/resources/js/jquery-inner-slider.js"/>></script>
	inner-slider
	<script src=<c:url value="/resources/js/jquery.timelinr-0.9.52.js"/>></script>
	timline-slider
	<script src=<c:url value="/resources/js/browser-detect.js"/>></script>
	Browser-Detect
	<script src=<c:url value="/resources/js/sticky-header.js"/>></script>
	Sticky-header
	<script src=<c:url value="/resources/js/jquery.countdown.js"/>></script> -->
</body>
</html>