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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.pradheep.dao.model.BibleQuizEng"%>
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google.css"/>
	media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google1.css"/>
	media="screen">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="description"
	content="This page contains information about gospel, bible verses, Sharing good news of Jesus Christ, Bible quiz, Important Bible Verses in multi-language" />
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
<link rel="stylesheet"
	href=<c:url value="/resources/css/${main_style_sheet}"/>
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
	function showNewRegistrationForm() {
		var newRegisterFormDiv = document.getElementById('newregisterdiv');
		if (newRegisterFormDiv.style.display === "none") {
			newRegisterFormDiv.style.display = "block";
		} else {
			newRegisterFormDiv.style.display = "none";
		}
	}
	
	function validateForm() {
		let name = document.forms["dailyQuizPage"]["name"].value;
		let email = document.forms["dailyQuizPage"]["email"].value;
		if (name == "") {
			alert("Enter the name to proceed. (Minimum 3 character)");
			return false;
		}
		if (name.length < 3) {
			alert("Name must be greater than three character");
			return false;
		}
		if (name.length > 25) {
			alert("Name must be less than 25 character");
			return false;
		}
		if (email.length > 30) {
			alert("Email must be less than 30 character");
			return false;
		}

		if (name.toLowerCase().contains("update")
				|| name.toLowerCase().contains("delete")
				|| email.toLowerCase().contains("update")
				|| email.toLowerCase().contains("delete")
				|| name.toLowerCase().contains("select")
				|| email.toLowerCase().contains("select")) {
			alert("Enter a valid name and email.");
			return false;
		}

		return true;
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
	font-family: meerainimai;
	src: url(< c : url value = "/resources/fonts/meera-inimai.ttf"/ >)
}

@font-face {
	font-family: satisfy;
	src: url(< c : url value = "/resources/fonts/Satisfy-Regular.ttf"/ >)
}

@font-face {
	font-family: abel;
	src: url(< c : url value = "/resources/fonts/abel-Regular.ttf"/ >)
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
</head>
<body>
	<ul id="sheetswitch">
		<li><a href="<c:url value="/" />" class="default">black</a></li>
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
								<div
									style="float: left; width: 50%; color: orange; font-size: 17px; margin-top: 22px; font-family: Cambria">
									<spring:message code="mainpage.banner.verse1.title" />
								</div>
								<ul class="menu menu_blue">
									<li class="active"><a href=<c:url value="/" />><spring:message
												code="mainpage.home" /></a></li>
									<li><a href='<c:url value="?lang=en" />'>English</a></li>
									<li><a href='<c:url value="?lang=ta" />'
										style="font-family: CustomKomalaB; font-size: 12px">தமிழ்</a></li>
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
								<span><em class="h-left"></em><span class="inner-heading"><spring:message
											code="daily.bible.quiz.title" /></span><em class="h-right"></em></span>
							</h3>
						</div>
						<article class="column" style="width: 700px;padding-left:10%">
<c:choose>
<c:when test="${errorMsg != 'Error while loading the quiz.'}">
<form:form action="${context}/submitDailyQuiz" method="post" name="dailyQuizPage" onsubmit="return validateForm()">
<div class="dailyQuizDiv">	
<label style="padding-left:100px"><b>Quiz date: ${quizDate}</b></label>								
<table>
<tr>
<td><label style="width:50px;height:30px;"><spring:message code="bible.quiz.new.user.name" /></label> </td>
<td>
<form:input type="text" path="name" style="height:30px;width:300px;font-size:13px;" />
</td>
</tr>
<form:hidden path="quizId"/>	
<form:hidden path="language"/>
<tr><td>
<label style="width:50px;height:30px;float:left"><spring:message code="bible.quiz.new.user.email" /></label></td>
<td><form:input type="text" path="email" style="height:30px;width:300px;font-size:13px;" />	</td></tr>									
<tr> <td colspan="2" style="width:100%"><label>Quiz : ${todayQuiz.question}</label></td></tr>
<c:set var="qc" value="${fn:split(todayQuiz.choice, ',')}" />
<c:set var="anscount" value="0" scope="page" />
<tr><td colspan="2" style="width:100%">
<form:radiobuttons path="answer" items="${qc}" style="float:left;margin-right:10px;" element="br"/>									
</td></tr>
</table>							
<hr color="blue">
<br> <input name="submit" type="submit" value="SUBMIT" class="submit-1" style="background: black">
</div>
</form:form>
</c:when>
<c:otherwise>${errorMsg}</c:otherwise>
</c:choose>
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
												<span>Phone:+65 84530859</span>
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
	<!-- jQuery main banner -->
	<script src=<c:url value="/resources/js/jquery-u.js"/>></script>
	<!-- jQuery Ui -->
	<script src=<c:url value="/resources/js/sourtin-jquery.js"/>></script>
	<!-- sourtin-jquery -->
	<script src=<c:url value="/resources/js/jqueryi-icon-menu.js"/>></script>
	<!-- Js For all Small Style  -->
	<script src=<c:url value="/resources/js/tytabs.js"/>></script>
	<!-- jQuery Plugin tytabs  -->
	<script
		src=<c:url value="/resources/js/speakker-big-1.2.02r134.min.js"/>></script>
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
