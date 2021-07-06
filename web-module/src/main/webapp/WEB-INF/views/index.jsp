<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/> media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google.css"/> media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google1.css"/> media="screen">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="description" content="This page contains information about gospel, bible verses, Sharing good news of Jesus Christ, Bible quiz, Important Bible Verses in multi-language"/>
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
<link rel="stylesheet" href=<c:url value="/resources/css/${main_style_sheet}"/> type="text/css" media="all">
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
<link rel="stylesheet" href=<c:url value="/resources/css/${menu_css}"/> type="text/css" media="screen" />
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
	font-family: meerainimai;
	src: url(<c:url value = "/resources/fonts/meera-inimai.ttf" />)		
}
@font-face {
	font-family: satisfy;
	src: url(<c:url value = "/resources/fonts/Satisfy-Regular.ttf" />)		
}
@font-face {
	font-family: abel;
	src: url(<c:url value = "/resources/fonts/abel-Regular.ttf" />)		
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
		<li><a href="<c:url value="/login-page" />" class="default">black</a></li>
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
			<div class="head-banner">
				<header id="header">
					<div class="header-wrapper home subnav-fixed">
						<div class="header-holder">
							<!-- <h1 id="logo"> -->
								<!-- <h2><a href="index.html"><style font-family:cambria;size:15px;font-color:white></style>Praise Your Redeemer</style></a></h2> -->
							<!-- </h1> -->
							<div class="nav">							
							<div style="float:left;width:50%;color:orange;font-size:17px;margin-top: 22px;font-family:${bannerfont}"><spring:message code="mainpage.banner.verse1.title"/></div>
								<ul class="menu menu_blue">									
									<li class="active"><a href="index.html"><spring:message code="mainpage.home" /></a></li>
									
										<li class="fullwidth"><a href="#" class="drop"><spring:message code="mainpage.language"/></a>
										<div class="dropdown_fullwidth first_fullwidth">											
											<div class="col_2_unused">
												
												<div class="col_1 firstcolumn">
													<ul class="plus">
														<li><a href='<c:url value="?lang=en" />'>English</a></li>
														<li><a href='<c:url value="?lang=ta" />' style="font-family:Vijaya;font-size:14px">தமிழ்</a></li>														
													</ul>
												</div>
											</div>
										</div></li>
										<li class="fullwidth"><a href="#" class="drop"><spring:message code="mainpage.services"/></a>
										<div class="dropdown_fullwidth first_fullwidth">											
											<div class="col_2_unused">
												
												<div class="col_1 firstcolumn">
													<ul class="plus">
														<li><a href=<c:url value="/prayers" />><spring:message code="mainpage.section2.prayer"/></a></li>
														<li><a href=<c:url value="/messages" />><spring:message code="mainpage.services.message"/></a></li>
														<li><a href=<c:url value="/important-verses" />><spring:message code="mainpage.services.verses"/></a></li>
														<li><a href='<c:url value="/quizMainPage" />'><spring:message code="mainpage.services.quiz"/></a></li>
														<li><a href='<c:url value='/subscribe?key=${token}'/>'><spring:message code="subscription.link.title"/></a></li>														
													</ul>
												</div>
											</div>
										</div></li>
									<li class="fullwidth"><a href='<c:url value="/about" />' class="drop"><spring:message code="mainpage.about"/></a>
										<div class="dropdown_fullwidth first_fullwidth">
											
											<div class="col_2_unused">
												
												<div class="col_1 firstcolumn">
													<ul class="plus">
														<li><a href='<c:url value="/about" />'><spring:message code="mainpage.about.read"/> </a></li>														
													</ul>
												</div>
											</div>
										</div></li>
									
								</ul>
								
							</div>
						</div>
					</div>
				</header>
				<div class="gallery-holder slideshow">
					<div class="gallery">
						<div class="gholder">
							<a class="btn-prev" href="#">next</a>
							<div class="gmask">
								<ul>
									
									<li><img src=<c:url value="/resources/images/banner-img-8.jpg"/> alt="image" />
										<%-- <div class="left">
											<div class="banner-caption">
												<a href="#"><spring:message code="mainpage.banner1.title1"/></a><span>&nbsp;</span>
											</div>
											<div class="banner-caption caption-1">
												<a href="#"><spring:message code="mainpage.banner1.title2"/></a><span>&nbsp;</span>
											</div>
										</div> --%>
										</li>
									 <li><img src=<c:url value="/resources/images/banner-img-9.jpg"/> alt="image" />
										<%-- <div class="left">
											<div class="banner-caption">
												<a href="#"><spring:message code="mainpage.banner2.title1"/></a><span>&nbsp;</span>
											</div>
											<div class="banner-caption caption-1">
												<a href="#"><spring:message code="mainpage.banner2.title2" /></a><span>&nbsp;</span>
											</div>
										</div> --%>
										</li> 
										 <li><img src=<c:url value="/resources/images/banner3.png"/> alt="image" />
										<%-- <div class="left">
											<div class="banner-caption">
												<a href="#"><spring:message code="mainpage.banner3.title1"/></a><span>&nbsp;</span>
											</div>
											<div class="banner-caption caption-1">
												<a href="#"><spring:message code="mainpage.banner3.title2"/></a><span>&nbsp;</span>
											</div>
										</div> --%>
										</li>
										 
								</ul>
							</div>
							<a class="btn-next" href="#">next</a>
						</div>
						
					</div>
					<span class="banner-bottom2"><img
						src=<c:url value="/resources/images/banner-bottom.png"/> alt=""></span>
				</div>
			</div>
			<!-- Content -->
			<div class="content mt0">
				<article class="banner-bottom">
					<h2><spring:message code="mainpage.banner.verse1.title" /></h2>
					<span class="detail"><spring:message code="mainpage.banner.verse1" />
					 </span>
				</article>
				<section class="grid-holder">
					<section class="grid">
						<div class="heading-holder">
							<h3 class="content-heading">
								<span><em class="h-left"></em><span class="inner-heading"><spring:message code="mainpage.services.title" /></span><em class="h-right"></em></span>
							</h3>
						</div>
						<article class="column c-one-fourth service-box">
							<div class="new-box">
								<div class="ch-item ch-img-1">
									<div class="ch-info">
										<h4>
										<a href=<c:url value="/prayers" />><spring:message code="mainpage.prayers.title" /></a>											
										</h4>
										<p><spring:message code="mainpage.prayers.subtitle" /></p>
									</div>
								</div>
							</div>
							<div class="text">
								<p><spring:message code="mainpage.section2.prayer" /></p>
							</div>
						</article>
						<article class="column c-one-fourth service-box">
							<div class="new-box">
								<div class="ch-item ch-img-2">
									<div class="ch-info">
										<h4>
											<a href=<c:url value="/messages" />><spring:message code="mainpage.services.message"/></a>
										</h4>
										<p><spring:message code="mainpage.services.message.heading1"/></p>
									</div>
								</div>
							</div>
							<div class="text">
								<p><spring:message code="mainpage.services.message"/></p>
							</div>
						</article>
						<article class="column c-one-fourth service-box">
							<div class="new-box">
								<div class="ch-item ch-img-3">
									<div class="ch-info">
										<h4>
											<a href=<c:url value="/important-verses" />><spring:message code="mainpage.services.verses.heading1"/></a>
										</h4>
										<p><spring:message code="mainpage.services.verses.heading2"/></p>
									</div>
								</div>
							</div>
							<div class="text">
								<p><spring:message code="mainpage.services.verses.heading3"/></p>
							</div>
						</article>
						<article class="column c-one-fourth service-box">
							<div class="new-box">
								<div class="ch-item ch-img-4">
									<div class="ch-info">
										<h4>
											<a href=<c:url value="/quizMainPage" />><spring:message code="mainpage.services.quiz.heading1"/></a>
										</h4>
										<p><spring:message code="mainpage.services.quiz.heading2" /></p>
									</div>
								</div>
							</div>
							<div class="text">
								<p><spring:message code="mainpage.services.quiz.heading3"/></p>
							</div>
						</article> 
					</section>
				</section>
				<section class="grid-holder">
					<section class="grid">
						<div class="heading-holder">
							<h3 class="content-heading">
								<span><em class="h-left"></em><span class="inner-heading"><spring:message code="mainpage.banner.prophets" /></span><em class="h-right"></em></span>
							</h3>
						</div>
						<article class="column c-one-third">
							<div class="img-holder slide-1">
								<a href='<c:url value="/daniel-prophet" />'><img
									src=<c:url value="/resources/images/daniel1.jpg"/> alt="image" /></a>
							</div>
						</article>
						<article class="column c-one-third">
							<div class="img-holder slide-1">
								<a href='<c:url value="/isaiah-prophet" />'><img
									src=<c:url value="/resources/images/isaiah.jpg"/> alt="image" /></a>
							</div>
						</article>
						<article class="column c-one-third">
							<div class="img-holder slide-1">
								<a href='<c:url value="/under-development" />'><img
									src=<c:url value="/resources/images/jeremiah.jpg"/> alt="image" /></a>
							</div>
						</article>
					</section>
				</section>
				<div class="buy-now">
					<section class="grid-holder">
						<section class="grid">							
							<article class="column c-four-fifth">
								<span class="c-theme1"><spring:message code="mainpage.banner.verse" /> </span>									
							</article>
						</section>
					</section>
				</div>
			
				<section class="grid-holder">
					<section class="grid">
						<article class="column c-one-third">
							<div class="post">
								<h4><spring:message code="mainpage.dailyverse.heading"/></h4>
								<p><c:out value="${todayVerse}"></c:out>-&nbsp;<b> <font color='red'><c:out value="${todayChapter}"></c:out></font></b></p>
								<div class="read-more">
									<a href='<c:url value='/subscribe?key=${token}'/>'>Get as alerts</a>
								</div>
							</div>
						</article>
						<article class="column c-one-third">
							<div class="post">
								<ul class="post-slide" id="s1">
									<li>
										<h4><spring:message code="mainpage.readbibleinayear.heading1"/></h4> <strong class="top-line"><spring:message code="mainpage.readbibleinayear.heading2" /> <c:out value="${oneYearBibleDate}"></c:out> </strong>
										<p><Aside style="font-family:'cambria'"><c:out value="${oneYearBibleChapter}"></c:out></Aside></p><!--  <span
										class="tech-text">Read <em>More ..</em></span> -->
										<div class="read-more">
									    <a href='<c:url value='/subscribe?key=${token}'/>'>Get as alerts</a>
								      </div>
									</li>
									 <li>
										<h4><spring:message code="mainpage.readbibleinayear.heading3"/></h4> <strong class="top-line"><spring:message code="mainpage.readbibleinayear.heading4" /> <c:out value="${oneYearBibleDateYesterday}"></c:out></strong>
										<p><Aside style="font-family:'cambria'"><c:out value="${oneYearBibleChapterYesterday}"></c:out></Aside></p></p> 
											<!-- <span class="tech-text">Posted by St. Patrick in <em>Teachings</em></span> -->
									</li>
								</ul>
							<a href="#" class="arrow-left">left</a> <a href="#"
									class="arrow-last-right">right</a>
							</div>
						</article>
						<article class="column c-one-third">
							<div class="post">
								<h4><spring:message code="mainpage.didyouknow.heading1" /></h4>								
								<p><c:out value="${didYouKnowText}"/></p>									
							</div>
						</article>
					</section>
				</section>
			</div>
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
												<br><span>Contact</span>
												<span>Email:administrator@praiseyourredeemer.org</span>
												<span>Phone:+91 7401504728</span>
											</address>										
										</div>
									</article>
									<article class="column c-one-fifth">
										<div class="box">
											<h5>Total Visitors</h5>
											<p><c:out value="${totalVisits}"/> Visitors</p>											
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
	<script type="text/javascript" src=<c:url value="/resources/js/jquery.slideshow.js"/>> </script>
	<!-- jQuery main banner -->
	<script src=<c:url value="/resources/js/jquery-u.js"/>></script>
	<!-- jQuery Ui -->
	<script src=<c:url value="/resources/js/sourtin-jquery.js"/>></script>
	<!-- sourtin-jquery -->
	<script src=<c:url value="/resources/js/jqueryi-icon-menu.js"/>></script>
	<!-- Js For all Small Style  -->
	<script src=<c:url value="/resources/js/tytabs.js"/>></script>
	<!-- jQuery Plugin tytabs  -->
	<script src=<c:url value="/resources/js/speakker-big-1.2.02r134.min.js"/>></script>
	<script src=<c:url value="/resources/js/jquery-inner-slider.js"/>></script>
	<!-- inner-slider -->
	<script src=<c:url value="/resources/js/jquery.timelinr-0.9.52.js"/>></script>
	<!-- timline-slider -->
	<script src=<c:url value="/resources/js/browser-detect.js"/>></script>
	<!-- Browser-Detect -->
	<script src=<c:url value="/resources/js/sticky-header.js"/>></script>
	<!-- Sticky-header -->
	<script src=<c:url value="/resources/js/jquery.countdown.js"/>></script>
</body>
</html>