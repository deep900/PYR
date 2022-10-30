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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Mochiy+Pop+One&family=Satisfy&display=swap" rel="stylesheet">
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
<!-- <link rel="stylesheet"	href=<c:url value="/resources/css/shortcode.css"/> type="text/css"	media="all"> -->
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
						<div class="header-holder" style="max-width:auto;margin-left:20px;">							
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
			<div class="content" style="max-width:100%;margin-left:20px;">
				<section style="width:auto">
					<section class="grid" style="margin-left:0px">
						<div>
							<h3 style="text-transform:uppercase;text-align:center;font-weight:normal;margin-left:0px;">
								<span><em class="h-left"></em><span class="inner-heading">Bible Prophecy and its fulfilment</span><em class="h-right"></em></span>
							</h3>
							<h1 style="font-family:Cambria;font-size:24px">Prophecy Index</h1>								
							<div style="font-size:17px;font-family:Cambria;line-height: 1.5em;">							
							<c:forEach items="${prophesyModel}" var="prophesyObj">							
							<a href="#${prophesyObj.getSearchKey()}">${prophesyObj.getSubject()}</a> &nbsp;&nbsp; | &nbsp;&nbsp;							
							</c:forEach>
							</div><br>
							<hr>	<br>				
							<table class="prophecyTable">
							<tr>
							<th style="width:40%">Prophetic scripture</th>
							<th style="width:20%!important">Subject</th>
							<th style="width:40%">Fullfilment</th>
							<c:forEach items="${prophesyModel}" var="prophesyObj">
							<tr>
							<td style="width:40%">
							${prophesyObj.getPropheticScrip()}
							</td>
							<td style="width:20%!importatnt" id="${prophesyObj.getSearchKey()}">	
							${prophesyObj.getSubject()}			
							</td>
							<td style="width:40%">
							${prophesyObj.getFulfilment()}
							</td>
							</tr>
							</c:forEach>
							</table>							
						</div>						
					</section>					
				</section>
			</div>
		</div>	</div>
	<script src=<c:url value="/resources/js/jquery-1.js"/>> 
<script type="text/javascript" src=<c:url value="/resources/js/jquery.slideshow.js"/>></script>	
	<script src=<c:url value="/resources/js/jquery-u.js"/>>
<script src=<c:url value="/resources/js/sourtin-jquery.js"/>></script>	
	<script src=<c:url value="/resources/js/jqueryi-icon-menu.js"/>> 
<script src=<c:url value="/resources/js/tytabs.js"/>></script>	
	<script type="text/javascript" src=<c:url value="/resources/js/speakker-big-1.2.02r134.min.js"/>> 
<script type="text/javascript" src=<c:url value="/resources/js/jquery-inner-slider.js"/>></script>	
	<script src=<c:url value="/resources/js/jquery.timelinr-0.9.52.js"/>>
<script src=<c:url value="/resources/js/browser-detect.js"/>></script>	
	<script src=<c:url value="/resources/js/sticky-header.js"/>> 
<script src=<c:url value="/resources/js/jquery.countdown.js"/>></script>
</body>
</html>