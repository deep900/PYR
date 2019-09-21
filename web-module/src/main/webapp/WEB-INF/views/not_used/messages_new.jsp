<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">

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
</script>

<script>
	function splitIntoArray(var arg){
		var array = arg.split(',');
		return array;
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
								<span><em class="h-left"></em><span class="inner-heading">Messages</span><em
									class="h-right"></em></span>
							</h3>
						</div>
						<article class="column c-three-fourth">
						<c:set var = "count" value = "0" scope="page"/>
						<%-- <c:forEach items="${messagesList}" var="msgModel">
							<div class="blog-post">
								<div class="slide-1">
									<ul>
										<li> <img id="image" src='<c:url value="getImage?id=${msgModel.getId()}"/>' alt="image" /></li>
									</ul>
									<div class="blog-date">
										<p>
										${msgModel.getLastModified()}
										</p>
									</div>
								</div>
								<br class="clear" />
								<div class="blog-holder">
									<h4 class="post-title">
										<a href=<c:url value="/messages/our-god-is-loving-god" />>
										<c:choose>
										<c:when test="${language == 'ta'}">
										<c:out value="${messagesTamilList.get(count)}"/> 
										<c:set var="count" value="${count + 1}" scope="page"/>
										</c:when>
										<c:otherwise>
										<c:out value="${msg1}"/>
										</c:otherwise>
										</c:choose>
										<span>1</span>
										</a>
									</h4>
									<p>
									<c:choose>
									<c:when test="${language == 'ta'}">
										${messagesTamilList.get(count)}
										<c:set var="count" value="${count + 2}" scope="page"/>
										</c:when>
										<c:otherwise>
										${msgModel.getShortDescriptionEnglish()}
										</c:otherwise>
										</c:choose>
										
									</p>
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
							</c:forEach>	 --%>			
							

						</article>
						<!--Side-bar Style Start-->
						<article class="column c-one-third">
							<div class="about-box">
								<div class="right-sidebar">
									<div class="sidebar-h">
										<strong class="right-heading"><span>Subscribe</span></strong>
									</div>
									<ul class="comments">
										<li>
											<div class="col_1 firstcolumn">
												<form:form method="post" id="subscriptionForm"
													commandName="subscriptionObject" action='subscribeAction'>
													<fieldset>
														<div class="row-contact">
															<form:label path="name">Name</form:label>
															<form:input path="name" id="name" />
														</div>
														<div class="row-contact">
															<form:label path="emailId">Email</form:label>
															<form:input path="emailId" id="emailId" name="emailId" />
														</div>
														<div class="row-contact">
															<form:label path="contactNumber">Contact</form:label>
															<form:input path="contactNumber" id="contactNumber"
																name="contactNumber"></form:input>
														</div>

														<div class="row-contact">
															<form:label path="address">Address</form:label>
															<form:input path="address" id="address" name="address"></form:input>
														</div>

														<div class="row-contact">
															<form:label path="country">Country</form:label>
															<form:input path="country" id="country" name="country"></form:input>															
														</div>
														<input name="submit" type="submit" value="SUBMIT"
															class="submit-1">
													</fieldset>
												</form:form>
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
										<strong class="right-heading"><span>Categories</span></strong>
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
									<article class="column c-one-fifth">
										<div class="box">
											<h5>Total Visitors</h5>
											<p>
												<c:out value="${totalVisits}" />
												Visitors
											</p>
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