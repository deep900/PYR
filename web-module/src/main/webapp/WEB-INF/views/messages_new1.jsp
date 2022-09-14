<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google.css"/>
	media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google1.css"/>
	media="screen">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title><spring:message code="mainpage.banner.verse1.title" /></title>
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
<link rel="stylesheet" href=<c:url value="/resources/css/${main_style_sheet}"/>
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
<link rel="stylesheet" href=<c:url value="/resources/css/${menu_css}"/>
	type="text/css" media="screen" />
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
	src: url(< c : url value = "/resources/fonts/TMOTKomalaBold_Ship.eot"/ >)
}

@font-face {
	font-family: CustomKomalaB;
	src: url(< c : url value = "/resources/fonts/TMOTKomalaBold_Ship.ttf"/ >)
}

@font-face {
	font-family: CustomNambi;
	src: url(< c : url value = "/resources/fonts/TMOTNambi_Ship.eot"/ >)
}

@font-face {
	font-family: CustomNambi;
	src: url(< c : url value = "/resources/fonts/TMOTNambi_Ship.ttf"/ >)
}

@font-face {
	font-family: CustomTamil50;
	src: url(< c : url value = "/resources/fonts/Uni-Tamil150.eot"/ >)
}

@font-face {
	font-family: CustomTamil50 src : url(< c : url value =
		"/resources/fonts/Uni-Tamil150.ttf"/ >)
}
</style>
<script>
	function splitIntoArray(var arg){
		var array = arg.split(',');
		return array;
	}	
	
</script>
<script>
function loadMainContent(){
	var mainText = document.getElementById("main-content").value;
	var divElement = document.getElementById("displayContent");
	divElement.innerHTML = mainText;
	alert("Loaded the content...");
}   
</script>
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
									<li><a href=<c:url value="/" />>Home</a></li>
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
								<span><em class="h-left"></em><span class="inner-heading"><spring:message code="mainpage.services.message" /></span><em
									class="h-right"></em></span>
							</h3>
						</div>
						<article class="column c-three-fourth">
							<c:set var="count" value="0" scope="page" />
							<c:forEach items="${objectList}" var="msgModel">
								<div class="blog-post">
									<div class="slide-1">
										<ul>
											<li><a href=<c:url value="/readMessage?id=${msgModel.getId()}&ref=${uniqueIndex}" />>
											<img id="image" src='<c:url value="getImage?id=${msgModel.getId()}"/>' alt="image"/>
											</a></li>
										</ul>
										<div class="blog-date">
											<p>${msgModel.getLastModifiedDisplay()}</p>
										</div>
									</div>
									<br class="clear" />
									<div class="blog-holder">
										<h4 class="post-title">
											<a href=<c:url value="/readMessage?id=${msgModel.getId()}&ref=${uniqueIndex}" />>
												<c:choose>
													<c:when test="${language == 'ta'}">
														<c:out value="${msgModel.getMessageTitleTamil()}" />

													</c:when>
													<c:otherwise>
														<c:out value="${msgModel.getMessageTitleEnglish()}" />
													</c:otherwise>
												</c:choose> <span>1</span>
											</a>
										</h4>

										<c:choose>
											<c:when test="${language == 'ta'}">
												<c:out value="${msgModel.getShortDescriptionTamil()}" />
											</c:when>
											<c:otherwise>
												<c:out value="${msgModel.getShortDescriptionEnglish()}" />
											</c:otherwise>
										</c:choose>
										
											
										

										<div class="blog-comments">
											<ul class="list-link">
												<li><a href="#" class="author">By Praise Your
														Redeemer</a></li>
												<li><a
													href=<c:url value="/readMessage?id=${msgModel.getId()}&ref=${uniqueIndex}"/>
													class="section"><spring:message code="messages.page.read.more"/></a></li>
											</ul>
										</div>
									</div>
								</div>
							</c:forEach>
							<div>
							<hr>
							<strong class="short-heading1">More Messages<span class="h-line"></span></strong>
								<ul class="pagination-list color">
									<c:if test = "${currentIndex > 1}">
         							<li><a href="<c:url value="/messages?startIndex=${currentIndex}&action=previous"/>" class="prev">previous</a></li>
      								</c:if>							
									<c:set var="count" value="0" scope="page" />
									<c:forEach begin="${maxRecordsPerPage}" end="${maxRecordsCount}" step="${maxRecordsPerPage}" varStatus="loop">
									<c:set var="count" value="${count + 1}" scope="page"/>
									<li><a href="<c:url value="/messages?startIndex=${count}&action=nextSet"/>">${count}</a></li>
									</c:forEach>
								 	<c:if test = "${currentIndex <= count}">
									<li><a href="<c:url value="/messages?startIndex=${currentIndex}&action=next"/>" class="next">Next</a></li>
									</c:if>
								</ul>
							</div>

						</article>
						<!--Side-bar Style Start-->
						<article class="column c-one-third">
							<div class="about-box">
								<div class="right-sidebar">
									<div class="sidebar-h">
										<strong class="right-heading"><span><spring:message code="subscription.link.title"/></span></strong>
									</div>
									<ul class="comments">
										<li>
											<div class="col_1 firstcolumn">
											<p><spring:message code="messagepage.subscribe.notes"/> </p>
											<a class="readmorebtn small black" href="<c:url value='/subscribe?key=${token}'/>"><spring:message code="subscription.link.title"/></a>
											</div>
										</li>

									</ul>
									<!-- <a href="#" class="vies-calender">View Full Event Calender</a> -->
								</div>
								<!--  <div class="right-sidebar">
                <div class="sidebar-h"><strong class="right-heading"><span>NEXT Event IN:</span></strong></div>
                <div id="counter"></div>
              </div> -->

								<div class="right-sidebar">
									<div class="sidebar-h">
										<strong class="right-heading"><span><spring:message code="messages.page.category.title"/></span></strong>
									</div>
									<ul class="archives">
										<c:forEach var="entry" items="${categoryMap}">
											<c:set var="str1" value='${entry.value}' />
											<c:set var="a1" value="${fn:split(str1,',')}" />
											<li><a href=<c:url value="/showByCategory?reference="/>'${a1[1]}'><c:out
														value="${entry.key}" /></a> <span class="num-post">${a1[0]}</span></li>
										</c:forEach>
									</ul>
								</div>
								
								<div class="right-sidebar">
									<div class="sidebar-h">
										<strong class="right-heading"><span><spring:message code="messages.page.share.title"/></span></strong>
									</div>
									<ul class="archives">
										<li>
											<a href="https://plus.google.com/share?url=www.praiseyourredeemer.org/messages">Share in Google
											</a>
											</li>
											<li>
											<a href="https://www.facebook.com/sharer/sharer.php?u=www.praiseyourredeemer.org/messages">Share in FaceBook</a>
											</li>
									</ul>
								</div>
							</div>
						</article>
						
						
						<!--Side-bar Style End-->
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
											<p>This ministry is mainly to tell the word of god with
												testimonies and touch life who are in need and talk about
												our living LORD JESUS CHRIST</p>
											<a href="#" class="view-all">Read More</a>
										</div>
									</article>

									<article class="column c-one-fifth">
										<div class="box">
											<h5>Praise Your Redeemer Ministry</h5>
											<address>
												<span>Chennai, India</span> <span>administrator@praiseyourredeemer.org</span>
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



</body>
</html>