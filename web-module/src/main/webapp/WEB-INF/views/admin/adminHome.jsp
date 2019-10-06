<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<c:set var="context" value="${pageContext.request.contextPath}" />
<!-- Basic Page Needs -->
<meta charset="utf-8">
<title>Praise your redeemer</title>
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
<link rel="stylesheet" href=<c:url value="/resources/css/all.css"/>
	type="text/css" media="all">
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
<!-- Megamenu CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/megamenu.css"/>
	type="text/css" media="screen" />
<script type="text/javascript">
function changeStyle(url) {
document.getElementById('stylesheet').href ='css/'+url;
}
</script>

<!-- End JavaScript -->
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
<![endif]-->
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
			<div class="head-banner">
				<header id="header">
					<div class="header-wrapper">
						<div class="header-holder">
							<!--  <h1 id="logo"><a href="index.html">The Church</a></h1> -->
							<div class="nav">
								<ul class="menu menu_blue">
									<li><a href=<c:url value="/j_spring_security_logout" />>Logout</a></li>
									<li><a href=<c:url value="/admin/adminHome" />>Home</a></li>
								</ul>

							</div>
						</div>
					</div>
				</header>
			</div>

			<!-- Content -->
			<div class="content">
				<section class="grid-holder">
					<section class="grid">
						<div class="heading-holder">
							<h3 class="content-heading">
								<span><em class="h-left"></em><span class="inner-heading">Admin Home</span><em class="h-right"></em></span>
							</h3>
						</div>
						
							<div class="blog-post-admin">
								<br class="clear" />
								<h3>Welcome to admin home</h3>
								<ul>
								<li><a href="<c:out value="${context}/admin/viewAllDailyVerses?key=${token}"></c:out>"> View daily Verses </a></li>
								<li><a href="<c:out value="${context}/admin/addNewDailyVerse?key=${token}"></c:out>"> Add a daily Verse </a></li>								
								</ul>
								<hr>
								<ul>
								<li><a href="<c:out value="${context}/admin/viewAllDidYouKnow?key=${token}"></c:out>"> View all did you know </a></li>
								<li><a href="<c:out value="${context}/admin/addDidYouKnow?key=${token}"></c:out>"> Add a did you know </a></li>
								</ul>
								<hr>
								<ul>
								<li><a href="<c:out value="${context}/admin/viewAllBibleQuiz?key=${token}"></c:out>"> View all bible quiz questions </a></li>
								<li><a href="<c:out value="${context}/admin/addNewBibleQuiz?key=${token}"></c:out>"> Add a bible quiz question </a></li>
								</ul>
								<hr>
								<ul>
								<li><a href="<c:out value="${context}/admin/viewAllMessages?key=${token}"></c:out>"> View all messages </a></li>
								<li><a href="<c:out value="${context}/admin/addNewMessage?key=${token}"></c:out>"> Add a message </a></li>
								</ul>
								<hr>
								<ul>
								<li><a href="<c:out value="${context}/admin/addBibleQuizEng?key=${token}"></c:out>"> Add Bible Quiz English </a></li>
								<li><a href="<c:out value="${context}/admin/addBibleQuizTamil?key=${token}"></c:out>"> Add Bible Quiz Tamil </a></li>
								</ul>
							</div>
				<hr><br>Visitor Counts<br>
				<strong class="short-heading">Table<span class="h-line"></span></strong>
                <table>
                  <tbody>
                    <tr>
                      <th>Page Reference</th>
                      <th>Hit Counts</th>
                      
                      
                    </tr>
                      <c:forEach var="entry" items="${hitCounterMap}">																					
                    <tr>
					  <td><c:out value="${entry.key}" /> </td>
					  <td><c:out value="${entry.value}" /> </td>
                    </tr>
					</c:forEach>
                    
                  </tbody>
                </table>
						<!--Side-bar Style Start-->

						<!--Side-bar Style End-->
					</section>
				</section>
			</div>
		</div>

		<!-- footer -->


	</div>
	<script src=<c:url value="/resources/js/jquery-1.js"/>><!-- jQuery library --> 
<script type="text/javascript" src=<c:url value="/resources/js/jquery.slideshow.js"/>></script>
	<!-- jQuery main banner -->
	<script src=<c:url value="/resources/js/jquery-u.js"/>><!-- jQuery Ui --> 
<script src=<c:url value="/resources/js/sourtin-jquery.js"/>></script>
	<!-- sourtin-jquery -->
	<script src=<c:url value="/resources/js/jqueryi-icon-menu.js"/>><!-- Js For all Small Style  --> 
<script src=<c:url value="/resources/js/tytabs.js"/>></script>
	<!-- jQuery Plugin tytabs  -->
	<script type="text/javascript"
		src=<c:url value="/resources/js/speakker-big-1.2.02r134.min.js"/>> 
<script type="text/javascript" src=<c:url value="/resources/js/jquery-inner-slider.js"/>></script>
	<!-- inner-slider -->
	<script src=<c:url value="/resources/js/jquery.timelinr-0.9.52.js"/>><!-- timline-slider --> 
<script src=<c:url value="/resources/js/browser-detect.js"/>></script>
	<!-- Browser-Detect -->
	<script src=<c:url value="/resources/js/sticky-header.js"/>> <!-- Sticky-header --> 
<script src=<c:url value="/resources/js/jquery.countdown.js"/>></script>
</body>
</html>