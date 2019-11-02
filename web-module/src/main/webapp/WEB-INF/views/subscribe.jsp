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
<meta name="description" content="This is the page to subscribe for Bible messages, SMS alerts on bible messages for free and reading bible in one year" />
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
	<div class="wrapper">
		<!-- header -->
		<div class="main">
			<div class="head-banner">
				<header id="header">
					<div class="header-wrapper">
						<div class="header-holder">							
							<div class="nav">
								<ul class="menu menu_blue">
									<li><a href=<c:url value="/" />>Home</a></li>
									<li><a href=<c:url value="/messages" />>Messages</a></li>
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
								<span><em class="h-left"></em><span class="inner-heading">Subscription</span><em
									class="h-right"></em></span>
							</h3>
						</div>
						<article class="column c-four-fifth">
						<c:set var = "count" value = "0" scope="page"/>						
						</article>
						<!--Side-bar Style Start-->
						<article class="column c-four-fifth">
							<div class="about-box-1">
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
														<table><tr>
															<td><form:label path="name" style="font-family:Cambria;margin=right:18px;font-size:16px">Name</form:label></td>
															<td><form:input path="name" id="name" style="height:26px;width:100%" /></td>
														</tr>
														<tr>
															<td><form:label path="emailId" style="font-family:Cambria;margin=right:18px;font-size:16px">Email</form:label></td>
															<td><form:input path="emailId" id="emailId" name="emailId" style="height:26px;width:100%" /></td>
														</tr>
														<tr>
															<td><form:label path="contactNumber" style="font-family:Cambria;margin=right:18px;font-size:16px">Mobile Number</form:label></td>
															<td><form:input path="contactNumber" id="contactNumber"
																name="contactNumber" style="height:26px;width:100%"></form:input> Country code not required.</td>
														</tr>

														<tr>
															<td><form:label path="address" style="font-family:Cambria;margin=right:18px;font-size:16px">Address</form:label></td>
															<td><form:input path="address" id="address" name="address" style="height:26px;width:100%"></form:input></td>
														</tr>

														<tr>
															<td><form:label path="country" style="font-family:Cambria;margin=right:18px;font-size:16px">Country</form:label></td>
															<td><form:input path="country" id="country" name="country" style="height:26px;width:100%"></form:input></td>															
														</tr>
														<tr>
															<td><form:label path="preferredLanguage" style="font-family:Cambria;margin=right:18px;font-size:16px">Preferred Language</form:label></td>
															<td><form:select path="preferredLanguage" id="preferredLanguage" name="preferredLanguage" style="height:26px;width:100%">
															<form:option value="English" label="English"/>
															<form:option value="Tamil" label="Tamil"/>
															</form:select></td>															
														</tr>
														<tr>
															<td><a href="#" style="font-family:Cambria;margin=right:18px;font-size:16px"> Enter the text as seen</a></td>
															<td><img id="image" src='<c:url value="/getCaptcha"/>' alt="image" style="height:50px;"/> 
															<input type="text" id="captchastr" name="captchastr" style="height:28px;margin-top: -47px;margin-left: 158px;width: 144px;"/></td>
																														
														</tr>
														<tr>
														</table>
														<input name="submit" type="submit" value="SUBMIT" style="margin-top:25px"
															class="submit-1-subscription">
													</fieldset>
												</form:form>
												<ul>
												<li style="font-family:Cambria;font-size:16px"> # You will be receiving email alerts on the new articles being posted. And all india based users will receive SMS alerts on daily verses and bible chapters to be read in one year </li>
												<li style="font-family:Cambria;font-size:16px"> # Kindly subscribe the correct contact information as it cost us for sending SMS. </li>
												</ul> 
											</div>
										</li>

									</ul>
									
								</div>
								

								
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