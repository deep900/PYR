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
<%@ page import="com.pradheep.dao.model.DidYouKnow" %>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<c:set var="context" value="${pageContext.request.contextPath}" />

<!-- Basic Page Needs -->
<meta charset="utf-8">
<%@ page contentType="text/html;charset=UTF-8"%>
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
function showWarning(url){
	var result = confirm("Do you wish to delete ? ");
	if(result == true){
		alert("You opted for deleting.. ");
		window.location.href=url;
	}
	
}
</script>
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
							<div class="nav">
								<ul class="menu menu_blue">
									<li><a href=<c:url value="/j_spring_security_logout" />>Logout</a></li>
									<li><a href=<c:url value="/admin/adminHome"/>>Admin Home</a></li>
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
								<span><em class="h-left"></em><span class="inner-heading">View Did you Know</span><em class="h-right"></em></span>
							</h3>
						</div>
						
							<div class="blog-post-admin">
								<br class="clear" />
								<table style="width:auto;margin-botton:10px;text-align:center;border-collapse:collapse;border-spacing:0;margin-left:35px;">
								<thead>
								<tr>
								
								<th>DYK - English</th>
								<th>DYK - Tamil</th>
								<th>Last Modified</th>
								<th>Edit</th>
								<th>Delete</th>
								</tr>								
								</thead>
								<tbody>
								<c:forEach items="${didYouKnowList}" var="dykModel">
										<tr>										
										<td><c:out value="${dykModel.didYouKnowContent}"></c:out></td>											
										<td><font face="CustomTamil50"><c:out value="${dykModel.didYouKnowContentTamil}"></c:out></font></td>
										<td><c:out value="${dykModel.lastModified}"></c:out></td>
										<td><a href="<c:out value='${context}/admin/editDidYouKnow?id=${dykModel.id}&key=${token}'></c:out>">View/Edit</a></td>
										<td><a href="#" onclick=showWarning('<c:out value='${context}/admin/deleteDidYouKnow?id=${dykModel.id}&key=${token}'></c:out>') >Delete</a></td>
										
										</tr>
									</c:forEach>
								</tbody>
								</table>								
							</div>						
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