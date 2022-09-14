<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.pradheep.dao.model.BibleQuizEng"%>

<%@ page contentType="text/html;charset=UTF-8"%>
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
<head>
<script type="text/javascript">
	function changeStyle(url) {
		document.getElementById('stylesheet').href = 'css/' + url;
	}
	function onLoadFunction() {	
		setInterval(updateTime, 1000);
	}

	var defaultTime = 300;
	var counter = 1;
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
	function updateTime() {
		var currentTime = document.getElementById("timeLeft");
		counter = counter + 1;
		var timeLeft1 = defaultTime - counter;
		currentTime.innerHTML = "Time Left :" + timeLeft1
				+ " Seconds [Quiz Ends Automatically]";
		if (timeLeft1 == 0) {
			document.getElementById("sub1").click();
		}
	}
</script>
<style>
.vl {
	border-left: 4px solid green;
	height: 25px;
	width: 47%;
	float: right;
	width: 47%;
}

.custom-style1 {
	width: 350px;
}

.custom-style1 table {
	border: 0px;
}

.custom-style1 input {
	background: #fff;
	border: 1px solid #ccc;
	width: 89%;
	height: 26px;
	padding: 0 6px;
	margin-left: 8px;
}

.custom-style1 select {
	background: #fff;
	border: 1px solid #ccc;
	width: 89%;
	height: 26px;
	padding: 0 6px;
	margin-left: 8px;
}

.custom-style1 label {
	color: black;
	font-size: 15px;
	line-height: 24px;
	float: left;
}

.custom-style2 {
	
}

.custom-style2 input {
	background: #fff;
	border: 1px solid #ccc;
	height: 26px;
	padding: 0 6px;
	margin-left: 8px;
	margin-right: 8px;
}

.custom-style2 label {
	color: black;
	font-size: 15px;
	line-height: 24px;
	float: left;
}

.custom-style2 p {
	color: black;
	font-size: 12px;
	line-height: 24px;
	float: left;
}
</style>

<!-- End JavaScript -->
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
<![endif]-->

<style type="text/css">
/* Declare custom tamil fonts. */
@font-face {
	font-family: CustomKomalaB;
	src: url(<c:url value = "/resources/fonts/TMOTKomalaBold_Ship.eot"/>)
}

@font-face {
	font-family: CustomKomalaB;
	src: url(<c:url value = "/resources/fonts/TMOTKomalaBold_Ship.ttf"/>)
}

@font-face {
	font-family: meerainimai;
	src: url(<c:url value = "/resources/fonts/meera-inimai.ttf"/>)
}

@font-face {
	font-family: satisfy;
	src: url(<c:url value = "/resources/fonts/Satisfy-Regular.ttf"/>)
}

@font-face {
	font-family: abel;
	src: url(<c:url value = "/resources/fonts/abel-Regular.ttf"/>)
}

@font-face {
	font-family: CustomNambi;
	src: url(<c:url value = "/resources/fonts/TMOTNambi_Ship.eot"/>)
}

@font-face {
	font-family: CustomNambi;
	src: url(<c:url value = "/resources/fonts/TMOTNambi_Ship.ttf"/>)
}

@font-face {
	font-family: CustomTamil50;
	src: url(<c:url value = "/resources/fonts/Uni-Tamil150.eot"/>)
}

@font-face {
	font-family: CustomTamil50 src : url(<c:url value =
		"/resources/fonts/Uni-Tamil150.ttf"/>)
}
</style>
</head>
<body onload="onLoadFunction()">
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
								<div
									style="float:left;width:50%;color:orange;font-size:17px;margin-top: 22px;font-family:${bannerfont}">
									<spring:message code="mainpage.banner.verse1.title" />
								</div>
								<ul class="menu menu_blue">
									<li class="active"><a href=<c:url value="/" />><spring:message
												code="mainpage.home" /></a></li>

								</ul>

							</div>
						</div>
					</div>
				</header>

			</div>
			<!-- Content -->
			<div class="content mt0">

				<section class="grid-holder">
					<section class="grid">
						<div class="heading-holder">
							<h3 class="content-heading">
								<span><em class="h-left"></em><span class="inner-heading"><spring:message
											code="mainpage.services.quiz.heading1" /></span><em class="h-right"></em></span>
							</h3>
						</div>
						<article class="column">
							<div class="blog-post-1">

								<br class="clear" />
								<div class="blog-holder">
									<h4 class="post-title">
										<spring:message code="bible.quiz.entry.page.level" />
										: ${level} /
										<spring:message code="bible.quiz.entry.page.portion" />
										: ${portion}
									</h4>
									<h3>
										<spring:message code="bible.quiz.entry.page.welcome.msg.user" />
										${quiz_user_name} -
										<spring:message code="bible.quiz.answer.questions" />
									</h3>
									<p id="timeLeft" style="color: red">Calculating Time..</p>
									<c:set var="count" value="0" scope="page" />
									<div class="col_1 firstcolumn" id="quizoption">
										<form action=<c:url value="/submitQuizAnswers" />
											method="post" id="bibleQuizAnswerForm">
											<div class="custom-style2">
												<c:forEach var="questionObj" items="${quizQuestions}">
													<c:set var="count" value="${count + 1}" scope="page" />
													<div id="quest">
														<label>${count}.
															&nbsp;&nbsp;${questionObj.question}</label> <br>
														<table style="margin-left: 18px;border=0px;width:auto">
															<c:set var="qc"
																value="${fn:split(questionObj.choice, ',')}" />
															<c:set var="anscount" value="0" scope="page" />
															<c:forEach var="choice" items="${qc}">
																<tr>
																	<td style="width: 3%; padding: 6px; border: 0px">
																	<input type="radio" name=${questionObj.id} value=${anscount} ></td>
																	<td style="color: black; border: 0px">${choice}</td>
																</tr>
																<c:set var="anscount" value="${anscount + 1}" scope="page" />
															</c:forEach>
														</table>
														<hr color="blue">
													</div>
												</c:forEach>

												<br> <input id="sub1" name="submit" type="submit"
													value="SUBMIT" class="submit-1" style="background: black">
												<br> <br> <input type="hidden" id="emailAddress"
													name="emailAddress" value=${quiz_user_email } /> <br>
												<input type="hidden" id="questionIds" name="questionIds"
													value=${question_ids } />
											</div>
										</form>

										<hr>

									</div>

									<div class="blog-comments">
										<ul class="list-link">
											<li><a href=<c:url value="/quizMainPage" />
												class="author">Back</a></li>

											<li><a href=<c:url value="/quizMainPage" />
												class="author">Register for Quiz</a></li>
											<li><a
												href="mailto:administrator@praiseyourredeemer.org?Subject=Quiz%20Feedback"
												class="author">Feedback</a></li>
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
											<a href='<c:url value="/about" />' class="view-all">Read
												More</a>
										</div>
									</article>

									<article class="column c-one-fifth">
										<div class="box">
											<h5>Praise Your Redeemer Ministry</h5>
											<address>
												<span>Chennai, India</span> <br>
												<span>Contact</span> <span>Email:administrator@praiseyourredeemer.org</span>
												<span>Phone:+65 84530859</span>
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
	<script src=<c:url value="/resources/js/jquery-1.js"/></script>
<script type="text/javascript" src=<c:url value="/resources/js/jquery-inner-slider.js"/></script>
<script src=<c:url value="/resources/js/browser-detect.js"/>></script>

<script src=<c:url value="/resources/js/jquery.countdown.js"/>></script>
</body>
</html>