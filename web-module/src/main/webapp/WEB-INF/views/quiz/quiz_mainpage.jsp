<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
	function showNewRegistrationForm(){
		var newRegisterFormDiv = document.getElementById('newregisterdiv');
		if (newRegisterFormDiv.style.display === "none") {
			newRegisterFormDiv.style.display = "block";	
	    } else {
	    	newRegisterFormDiv.style.display = "none";    	
	    }
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
					<div class="header-wrapper">
						<div class="header-holder">
							<!--  <h1 id="logo"><a href="index.html">The Church</a></h1> -->
							<div class="nav">
								<div style="float:left;width:50%;color:orange;font-size:17px;margin-top: 22px;font-family:${bannerfont}"><spring:message code="mainpage.banner.verse1.title"/></div>
								<ul class="menu menu_blue">									
									<li class="active"><a href=<c:url value="/" />><spring:message code="mainpage.home" /></a></li>
									<li><a href='<c:url value="?lang=en" />'>English</a></li>
									<li><a href='<c:url value="?lang=ta" />' style="font-family:CustomKomalaB;font-size:12px">தமிழ்</a></li>	
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
								<span><em class="h-left"></em><span class="inner-heading"><spring:message code="bible.quiz.title"/></span><em class="h-right"></em></span>
							</h3>
						</div>
						<article class="column" style="width:700px">
							<div class="blog-post-1" style="width:700px">

								<br class="clear" />
								<div class="blog-holder">
									<h4 class="post-title"><spring:message code="bible.quiz.sub.title1"> </spring:message></h4>
									<h2><spring:message code="bible.quiz.sub.title2"/></h2>
									<form action=<c:url value="/startQuiz" /> method="post"
										name="quizEntryForm">
										<fieldset>
											<div class="row-contact">
												<label><spring:message code="bible.quiz.email"/></label> <input required
													pattern="^[a-zA-Z0-9-\_.]+@[a-zA-Z0-9-\_.]+\.[a-zA-Z0-9.]{2,5}$"
													id="email" name="email" type="text" value="" style="margin-left:4px">
											</div>
											<input name="submit" type="submit" value="SUBMIT"
												class="submit-1">
												<br>
											<div class="vl">
												<a href=<c:url value="/startQuiz?email=Guest" /> style="float: right"> <spring:message code="bible.quiz.join.as.guest"/></a>
											</div>
										</fieldset>

									</form>
									<hr>
									<h2><spring:message code="bible.quiz.new.user"/></h2>
									<a style="margin-left:10px" href="#" onClick="showNewRegistrationForm()"> <spring:message code="bible.quiz.new.user.register"/> </a>
									<div class="col_1 firstcolumn" id="newregisterdiv"
										style="display: none">
										<form:form action="${context}/registerQuizTaker" method="post" name="registerQuizTakerForm">
											<div class="custom-style1">
												<table style="border: 0px; width: 604px;margin-left:10px;margin-top:10px">
													<tr>
														<td><label><spring:message code="bible.quiz.new.user.name"/>*</label></td>
														<td><form:input type="text" path="name" style="height:30px;width:300px;font-size:13px"/></td>
													</tr>
													<tr>
														<td><label><spring:message code="bible.quiz.new.user.email"/>*</label></td>
														<td><form:input type="text" path="email" style="height:30px;width:300px;font-size:13px"/></td>
													</tr>


													<tr>
														<td><label><spring:message code="bible.quiz.new.user.contact.number"/></label></td>
														<td><form:input type="text" path="contactNumber" style="height:30px;width:300px;font-size:13px"/></td>
													</tr>

													<tr>
														<td><label><spring:message code="bible.quiz.new.user.contry"/></label></td>
														<td><form:input type="text" path="country" style="height:30px;width:300px;font-size:13px"/></td>
													</tr>
												</table>
												<div style="width: 650px">
													<form:checkbox  name="isSubscribed" path="isSubscribed"/>
													<a href="#" style="margin-left: 4px"><spring:message code="bible.quiz.auto.subscription"/></a>
												</div>
												<br>
												<input name="submit" type="submit" value="SUBMIT"
													class="submit-1" style="background: black">
											</div>
										</form:form>
										<br>
										<hr>
										<p style="margin-left:10px">
											<font color='red'><spring:message code="bible.quiz.mandatory.fields" /> </font>
										</p>
									</div>
									<div class="blog-comments">
										<ul class="list-link">
											<li><a href="#" class="author">By Praise Your
													Redeemer</a></li>
											
										</ul>
									</div>
								</div>
							</div>

						</article>
						<!--Side-bar Style Start-->

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
												<span>Phone:+91 8610279716</span>
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