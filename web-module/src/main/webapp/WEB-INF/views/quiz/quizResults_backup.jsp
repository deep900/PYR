<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page import="com.pradheep.dao.model.BibleQuizEng"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="description"
	content="Bible quiz program in english and tamil" />
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<!-- Basic Page Needs -->
<meta charset="utf-8">
<title>Bible Quiz - English / Tamil</title>
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
	href=<c:url value="/resources/css/all_quiz_page.css"/> type="text/css"
	media="all">
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
var defaultTime = 300;
var counter = 1;
function changeStyle(url) {
document.getElementById('stylesheet').href ='css/'+url;
}
function showNewRegistrationForm(){
	var newRegisterFormDiv = document.getElementById('newregisterdiv');
	if (newRegisterFormDiv.style.display === "none") {
		newRegisterFormDiv.style.display = "block";	
    } else {
    	newRegisterFormDiv.style.display = "none";    	
    }
}
function updateTime(){	
	var currentTime = document.getElementById("timeLeft");
	counter = counter + 1;
	var timeLeft1 = defaultTime - counter;
	currentTime.innerHTML = "Time Left :"  + timeLeft1 + " Seconds [Quiz Ends Automatically]";	
	if(timeLeft1 == 0){
	document.getElementById("sub1").click();
	}
}

function onLoadFunction(){	
setInterval(updateTime, 1000);
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

<!-- End JavaScript -->
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
<![endif]-->
</head>
<body onload="onLoadFunction()">
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
									<li><a href=<c:url value="/" />><font
											family="CustomNambi"><spring:message
													code="important.verses.page.home" /></font></a></li>
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
								<span><em class="h-left"></em><span class="inner-heading">Quiz
										Results</span><em class="h-right"></em></span>
							</h3>
						</div>
						<article class="column">
							<div class="blog-post-1">

								<br class="clear" />
								<div class="blog-holder">
									<h3>Dear ${quiz_user_name} - Check your answers</h3>
									<h3>Your score is ${score} out of ${total_questions} </h3>
									<c:set var="count" value="0" scope="page" />
									<div class="col_1 firstcolumn" id="quizoption">

										<div class="custom-style2">
											<c:forEach var="questionObj" items="${quizQuestions}">
												<c:set var="count" value="${count + 1}" scope="page" />
												<div id="quest">
												<c:set var="option5" value="${questionObj.correctAnswer}"/>
												<c:set var="option6" value="${questionObj.myChoice}"/>
													<c:choose>
														<c:when test="${option5 != option6}">
															<label style="color:red">${count}.
																	&nbsp;&nbsp;${questionObj.question}</label>&nbsp;&nbsp;																
                  													<a href="#"><font color="red"> [Wrong] </font></a>               														 
																	 <br>
															
														</c:when>
														<c:otherwise>
															<label style="color:green">${count}.
																	&nbsp;&nbsp;${questionObj.question}</label>&nbsp;&nbsp;																
                  													<a href="#"><font color="green"> [Correct]</font></a>
               														
															<br>
														</c:otherwise>
													</c:choose>
													<table style="margin-left: -10px;border=0px">
														<c:set var="qc"
															value="${fn:split(questionObj.choice, ',')}" />

														<c:forEach var="choice" items="${qc}">
															<tr>
																<%-- 
													<td style="width:10px;padding:6px;border:0px">
													<input type="radio" name=${questionObj.id} value="${choice}">
													</td> 
													--%>
																<td style="color: black; border: 0px">${choice}</td>

															</tr>
														</c:forEach>
														<tr>
														<c:set var="option1" value="${questionObj.correctAnswer}"/>
														<c:set var="option2" value="${questionObj.myChoice}"/>
															<td style="border:0px"><font color='blue'>Correct Answer :
																	${questionObj.correctAnswer} &nbsp;&nbsp;&nbsp; |
																	&nbsp;&nbsp;&nbsp;</font> 
																	<c:choose>
																	<c:when test="${option1 != option2}">
																		<font color="red"> Your Answer :
																			${questionObj.myChoice}</font>
																	</c:when>
																	<c:otherwise>
																		<font color="green"> Your Answer :
																			${questionObj.myChoice} </font>
																	</c:otherwise>
																</c:choose></td>
														</tr>

													</table>
													<hr color="blue">
												</div>
											</c:forEach>
										</div>
									</div>

									<div class="blog-comments">
										<ul class="list-link">
											<li><a href=<c:url value="/quizMainPage" />
												class="author">Start again</a></li>

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