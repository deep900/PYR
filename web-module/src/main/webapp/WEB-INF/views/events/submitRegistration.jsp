<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pradheep.dao.model.event.EventModel"%>
<%@ page import="com.pradheep.dao.model.event.EventWrapper"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google.css"/>
	media="screen">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Event Registration Form</title>
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
<link rel="stylesheet" href=<c:url value="/resources/css/event.css"/>
	id="stylesheet">
<!-- Megamenu CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/${menu_css}"/>
	type="text/css" media="screen" />
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Bona+Nova&display=swap"
	rel="stylesheet">
<!-- End JavaScript -->
<!--[if lt IE 9]>
      <script src="js/html5.js"></script>
<![endif]-->
</head>
<body style="font-family: 'Bona Nova', serif;">
	<div class="wrapper">
		<div class="main">
			<div class="content">
				<section class="grid-holder">
					<section class="grid">

						<article class="column1">
							<div class="blog-post-1">

								<br class="clear" />
								<div class="blog-holder">
									<div class="title-msg">
										<span><u> ${eventModel.welcomeNote} <br>
												${eventModel.eventName}
										</u></span>

									</div>
									<br>
									${errorMsg}									
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
									<article class="column c-one-fifth">
										
										<h3>Event hosted by: ${eventModel.organizer}</h3>
										<address>
											<span>Contact Number:
												${eventModel.eventOrgContactNumber } </span>

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
	<script src=<c:url value="/resources/js/jquery-u.js"/>></script>
	<script src=<c:url value="/resources/js/jqueryi-icon-menu.js"/>></script>
	<script src=<c:url value="/resources/js/tytabs.js"/>></script>
	<script src=<c:url value="/resources/js/browser-detect.js"/>></script>
	<script type="text/javascript">
		function validateForm() {
			var name = document.getElementById('name').value;
			if (name.length < 3) {
				alert("Name should be at least 3 characters.");
				return false;
			}
			var mobileNum = document.getElementById('mobileNumber').value;
			if (mobileNum.length < 8) {
				alert("Enter a valid mobile number.");
				return false;
			}
			var email = document.getElementById('email').value;
			if (email.length < 3) {
				alert("Enter a valid email address");
				return false;
			}
			var personWhoInvited = document.getElementById('personWhoInvited').value;
			if (personWhoInvited.length < 3) {
				alert("Enter a valid person who invited you");
				return false;
			}
			var container = document.getElementById("childContainer");
			console.log("Has Child Nodes" + container.hasChildNodes());
			var childNodes = container.childNodes;
			for (y=0;i<childNodes.length;y++) {
				var childId = childNodes[y].id;
				var childName = childNodes[y].value;				
				console.log("Child ID" + childId);
				console.log("Child Name:" + childName);
				if(childId != undefined){
				if (childId.includes("child")) {
					if (childName.length < 3) {
						alert("Child Name should be at least 3 characters.")
						return false;
					}
				}
				}
			}
			var aContainer = document.getElementById("adultContainer");
			console.log("Has Child Nodes" + aContainer.hasChildNodes());
			var achildNodes = aContainer.childNodes;
			for ( x=0;i<achildNodes.length;x++) {
				var childId = achildNodes[x].id;
				var childName = achildNodes[x].value;				
				console.log("Child ID" + childId);
				console.log("Child Name:" + childName);
				if(childId != undefined){
				if (childId.includes("adult")) {
					if (childName.length < 3) {
						alert("Adult Name should be at least 3 characters.")
						return false;
					}
				}
				}
			}
			return true;
		}
	</script>
	<script type="text/javascript">
		function loadChildComp() {
			var childCount = document.getElementById("childCount").value;
			//alert("Fill the details of" + childCount +" child");	
			var container = document.getElementById("childContainer");
			while (container.hasChildNodes()) {
				container.removeChild(container.lastChild);
			}
			if (childCount == "select") {
				return;
			}
			for (i = 0; i < childCount; i++) {
				container.appendChild(document.createTextNode("Child "
						+ (i + 1) + " Name:"));
				var input = document.createElement("input");
				input.type = "text";
				input.name = "childMembers.name";
				input.id = "child" + i;
				container.appendChild(input);
				var selectList = document.createElement("select");
				createFoodPreference(container, selectList, i + 1, "childMembers");
				container.appendChild(document.createElement("br"));
				container.appendChild(document.createElement("br"));
			}
		}

		function createFoodPreference(parentElement, selectList, id, arg) {
			var array = [ "Veg", "Non-Veg", "Non-Veg-Hallal" ];
			selectList.id = "food-" + arg + "-" + id;
			selectList.name=arg+".foodPreference";
			parentElement.appendChild(selectList);
			//Create and append the options
			for (var i = 0; i < array.length; i++) {
				var option = document.createElement("option");
				option.value = array[i];
				option.text = array[i];
				selectList.appendChild(option);
			}
		}
	</script>
	<script type="text/javascript">
		function adultsChanged() {
			var childCount = document.getElementById("adultCount").value;
			//alert("Fill the details of" + childCount +" child");	
			var container = document.getElementById("adultContainer");
			while (container.hasChildNodes()) {
				container.removeChild(container.lastChild);
			}
			if (childCount == "select") {
				return;
			}
			for (i = 0; i < childCount; i++) {
				container.appendChild(document.createTextNode("Adult "
						+ (i + 1) + " Name:"));
				var input = document.createElement("input");
				input.type = "text";
				input.name = "adultMembers.name";
				input.id = "adult" + i;
				container.appendChild(input);
				var selectList = document.createElement("select");
				createFoodPreference(container, selectList, i + 1, "adultMembers");
				container.appendChild(document.createElement("br"));
				container.appendChild(document.createElement("br"));
			}
		}
	</script>
</body>
</html>