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
<%@ page import="com.pradheep.dao.model.event.EventModel"%>
<%@ page import="com.pradheep.dao.model.event.EventWrapper"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google.css"/>
	media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google1.css"/>
	media="screen">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="description"
	content="Event Registration Form" />
<title>Registration Form</title>


<link rel="icon" type="image/png"
	href=<c:url value="/resources/images/favicon.png"/>>
<!-- CSS Style -->

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
	<link rel="stylesheet" href=<c:url value="/resources/css/event.css"/>
	id="stylesheet">
<!-- Megamenu CSS -->

<script type="text/javascript">

	
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
</head>
<body>
<div class="wrapper">
<div class="title-msg">								
<u> ${eventModel.welcomeNote} for ${eventModel.eventName} </u>
</div>
<br>
Registration Form - Fields marked with (*) are mandatory fields.

<table>
<form name="registrationForm" method="post" action="/events/submitRegistration">
<tr> 
<td> Registering for: </td> 
<td> 
<select id="registeringFor" name="registeringFor"  style="width:70%;height:30px;font-size:12px;">
<option value="MySelf">Myself</option>
<option value="My-Family">My Family</option>
<option value="My-Friends">My Friends</option>
</select>
</td>									
</tr>

<tr> 
<td> Name: </td> 
<td> <input type="text" id="name" style="height:30px;width:300px;font-size:13px;" /> </td>									
</tr>
									
<tr> 
<td> Contact number: </td> 
<td> <input type="text" id="mobileNumber" style="height:30px;width:300px;font-size:13px;" /> </td>									
</tr>
									
<tr> 
<td> Email: </td> 
<td> <input type="text" id="email" style="height:30px;width:300px;font-size:13px;" /> </td>									
</tr>
									
<tr> 
<td> Food preference: </td> 
<td> 
<select id="foodPreference" name="foodPreference" style="width:70%;height:30px;font-size:12px;">
<option value="Vegeterian">Vegeterian</option>
<option value="Non-Vegeterian">Non-Vegeterian</option>
<option value="Non-Vegeterian-Hallal">Non-Vegeterian(Hallal)</option>
</select>
</td>									
</tr>
																		
<tr> 
<td> Friend who invited you: </td> 
<td> <input type="text" id="personWhoInvited" style="height:30px;width:300px;font-size:13px;" /> </td>									
</tr>
									
<tr> 
<td> Number of children accompany you: </td> 
<td> <select id="childCount" name="childCount" style="width:70%;height:30px;font-size:12px;">
<option value="1">1</option> <option value="2">2</option> <option value="3">3</option>
<option value="4">4</option> <option value="5">5</option> <option value="6">6</option>
<option value="7">7</option> <option value="8">8</option> <option value="9">9</option>
<option value="10">10</option>
</select>
</td>									
</tr>
									
<tr> 
<td> Number of adults accompany you: </td> 
<td> <select id="adultCount" name="adultCount" style="width:70%;height:30px;font-size:12px;">
<option value="1">1</option> <option value="2">2</option> <option value="3">3</option>
<option value="4">4</option> <option value="5">5</option> <option value="6">6</option>
<option value="7">7</option> <option value="8">8</option> <option value="9">9</option>
<option value="10">10</option>
</select>
</td>									
</tr>
<tr><td> <input name="submit" type="submit" value="SUBMIT" class="submit-1"></td></tr>
</form>
</table>
<br>
								

</div>


			<!-- Content -->
<div class="content">
<section class="grid-holder">
<section class="grid">					
<article class="column" style="width: 700px; padding-left: 10%">
<c:choose>
<c:when test="${errorMsg != ''}">
</c:when>
<c:otherwise>${errorMsg}</c:otherwise>
</c:choose>
</article>
</section>
</section>
</div>

	<div class="event-footer">
	Event hosted by: ${eventModel.organizer}<br>
	Contact Number: ${eventModel.eventOrgContactNumber } 
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
