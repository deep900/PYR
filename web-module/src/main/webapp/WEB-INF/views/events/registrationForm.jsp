<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<head>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.pradheep.dao.model.event.EventModel"%>
<%@ page import="com.pradheep.dao.model.event.EventWrapper"%>
<%@ page import="com.pradheep.dao.model.event.EventOptions"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<link rel="stylesheet" href=<c:url value="/resources/css/google.css"/>
	media="screen">
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta name="description"
	content="This page contains information about gospel, bible verses, Sharing good news of Jesus Christ, Bible quiz, Important Bible Verses in multi-language" />
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
<!-- <script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
	
</script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
								<div class="blog-holder"
									style="font-size: 18px; font-weight: bold !important">
									<div class="title-msg">
										<span><u> ${eventModel.welcomeNote} for
												${eventModel.eventName} </u></span>

									</div>
									<br>
									<div class="col_1 firstcolumn calign"
										style="text-align: center;">
										<img src=<c:url value="${eventModel.eventFlyerImagePath}"/>
											alt="image" style="width: 83%">
										<h3>Registration Form</h3>
									</div>
									<div class="col_1 firstcolumn"
										style="text-align: center; margin: 0 auto;">
										<form:form action="${context}/events/submitRegistration"
											method="post" name="registrationForm"
											onsubmit="return validateForm()"
											style="width: 80%; text-align: center; margin: 0 auto;">
											<input type="hidden" id="eventId" name="eventId">
											<div class="custom-style1">
												<table style="border: 0px; width: 100%; margin: 0 auto;">
													<tr>
														<td style="width: 50%; margin: 0 auto;"><label>Registering
																for: </label></td>
														<td><form:select id="registeringFor"
																name="registeringFor" path="registeringFor"
																class="mystyle"
																style="height: 34px; width: 100%; font-size: 13px;">
																<option value="MySelf">Myself</option>
																<option value="My-Family">My Family</option>
																<option value="My-Friends">My Friends</option>
															</form:select></td>
													</tr>
													<tr>
														<td style="width: 50%; margin: 0 auto;">Name:</td>
														<td><form:input type="text" id="name" path="name"
																style="height: 30px; width: 100%; font-size: 13px;" />
														</td>
													</tr>

													<tr>
														<td style="width: 50%; margin: 0 auto;">Contact
															number:</td>
														<td><form:input type="text" path="mobileNumber"
																id="mobileNumber"
																style="height: 30px; width: 100%; font-size: 13px;" />
														</td>
													</tr>

													<tr>
														<td style="width: 50%; margin: 0 auto;">Email:</td>
														<td><form:input type="text" id="email" path="email"
																style="height: 30px; width: 100%; font-size: 13px;" />
															<br> <span style="font-size: 13px; color: red">Note:
																Event passes will be sent to this email.</span></td>
													</tr>
													<c:if test="${isFoodOptionRequired}">
														<!-- <tr>
															<td style="width: 50%; margin: 0 auto;">Dinner time
																preference</td>
															<td><form:select id="dinnerTime" name="dinnerTime"
																	path="dinnerTime"
																	style="height: 34px; width: 308px; font-size: 13px;">
																	<optgroup style="font-family: Cambria">
																		<option value="Pre-Dinner">Pre-Dinner @5:30PM</option>
																		<option value="Post-Dinner" selected>Post-Dinner
																			@9PM</option>
																	</optgroup>
																</form:select></td>
														</tr>  -->

														<tr>
															<td style="width: 50%; margin: 0 auto;">Food
																preference:</td>
															<td><form:select id="foodPreference"
																	name="foodPreference" path="foodPreference"
																	style="height: 34px; width: 100%; font-size: 13px;">
																	<optgroup style="font-family: Cambria">
																		<option value="Vegeterian">Vegeterian</option>
																		<option value="Non-Vegeterian">Non-Vegeterian</option>
																		<option value="Non-Vegeterian-Hallal">Non-Vegeterian(Hallal)</option>
																		<option value="Food Not Required">Food not
																			required</option>
																	</optgroup>
																</form:select></td>
														</tr>
													</c:if>
													<tr>
														<td style="width: 50%; margin: 0 auto;">Name of
															person invited you:</td>
														<td><form:input type="text" id="personWhoInvited"
																path="personWhoInvited"
																style="height: 30px; width: 100%; font-size: 13px;" /><br>
															<span style="font-size: 13px; color: red">Hint:
																Specify CMIIC if you are already a member of church</span></td>
													</tr>

													<tr>
														<td style="width: 50%; margin: 0 auto;">Number of
															children accompany you:</td>
														<td><form:select id="childCount" name="childCount"
																path="childCount"
																style="height: 34px; width: 100%; font-size: 13px;"
																onchange="loadChildComp(${isFoodOptionRequired})">
																<optgroup style="font-family: Cambria">
																	<option value="0">Select</option>
																	<option value="1">1</option>
																	<option value="2">2</option>
																	<option value="3">3</option>
																	<option value="4">4</option>
																	<option value="5">5</option>
																</optgroup>
															</form:select>
															<div class="childDivContainer" id="childContainer"></div></td>
													</tr>

													<tr>
														<td style="width: 50%; margin: 0 auto;">Number of
															adults accompany you: <br> <span
															style="font-size: 13px; color: red">(Age Above 13)</span>
														</td>
														<td><form:select id="adultCount" name="adultCount"
																path="adultCount"
																style="height: 34px; width: 100%; font-size: 13px;"
																onchange="adultsChanged(${isFoodOptionRequired})">
																<optgroup style="font-family: Cambria">
																	<option value="0">Select</option>
																	<option value="1">1</option>
																	<option value="2">2</option>
																	<option value="3">3</option>
																	<option value="4">4</option>
																	<option value="5">5</option>
																	<option value="6">6</option>
																	<option value="7">7</option>
																	<option value="8">8</option>
																	<option value="9">9</option>
																	<option value="10">10</option>
																</optgroup>
															</form:select>
															<div class="adultDivContainer" id="adultContainer"></div></td>
													</tr>
													<c:if test="${isExtraOptionsRequired}">
														<c:set var="cnt" scope="session" value="1" />
														<c:forEach items="${eventOptionsList}" var="item">
															<tr>
																<td style="width: 50%; margin: 0 auto;">
																	${item.option}</td>
																<td style="width: 50%; margin: 0 auto;">
																<form:select id="option${cnt}" name="option${cnt}"
																	path="eventOption"
																	style="height: 34px; width: 100%; font-size: 13px;">																	
																	<c:set var="menuOptList"
																		value="${fn:split(item.optionMenuItem, ',')}" />
																	<c:forEach items="${menuOptList}" var="menuItem">
																		<optgroup style="font-family: Cambria">
																			<option value="${menuItem}">${menuItem}</option>
																		</optgroup>
																	</c:forEach>
																</form:select>
																</td>
															</tr>
															<c:set var="cnt" scope="session" value="${cnt+1}" />
														</c:forEach>
													</c:if>

												</table>
												<c:if test="${not empty eventModel.agreementContent}">
													<span class="agreement"> <input type="checkbox"
														id="agreement" name="agreement" value="agreement" /> <label>${eventModel.agreementContent}</label>
													</span>
												</c:if>
												<br> <input id="submit" name="submit" type="submit"
													value="SUBMIT" style="margin-left: 10px;"> <br>
												<br>
											</div>
											<input type="hidden" id="eventId" name="eventId"
												value="${eventModel.id}" />
										</form:form>
										<div class="alert ${csscode} hideit">
											<p>${errorMessage}</p>
											<span class="close"></span>
										</div>
										<c:if test="${not empty eventModel.eventProgramFlyerPath}">
											<img src=<c:url value="${eventModel.eventProgramFlyerPath}"/>
												alt="image" style="width: 83%">
										</c:if>
										<hr>
									</div>
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
										<span> <img
											src=<c:url value="${eventModel.eventOrgLogoPath}" />
											style="width: 70px; height: 60px" alt="image">
										</span>
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
	<!-- <script src=<c:url value="/resources/js/jquery-1.js"/>></script>
	
	<script type="text/javascript"
		src=<c:url value="/resources/js/jquery.slideshow.js"/>>
		
	</script>
	<script src=<c:url value="/resources/js/jquery-u.js"/>></script>
	<script src=<c:url value="/resources/js/jqueryi-icon-menu.js"/>></script>-->
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
			var agreementChecked = document.getElementById('agreement');		
			if(!agreementChecked.checked) {
				alert("Select the checkbox for the PDPA agreement to proceed.");
				return false;
			}
			var container = document.getElementById("childContainer");
			var childList = document.getElementById("childMembers");
			console.log("Has Child Nodes" + container.hasChildNodes());
			var childNodes = container.childNodes;
			var jsonOut = "[";
			var nameOut = "";
			for (y = 0; i < childNodes.length; y++) {
				var childId = childNodes[y].id;
				var childName = childNodes[y].value;
				console.log("Child ID" + childId);
				console.log("Child Name:" + childName);

				if (childId != undefined) {
					if (childId.includes("child")) {
						if (childName.length < 3) {
							alert("Child Name should be at least 3 characters.")
							return false;
						}
						nameOut = nameOut + "{\"name\":\"" + childName + "\",";
					}
					if (childId.includes("foodPreference")) {
						nameOut = nameOut + "{\"foodPreference\":\""
								+ childName + "\"}"
					}
				}
			}
			jsonOut = "[" + nameOut + "]";
			childList.value = jsonOut;
			console.log(jsonOut);
			alert(jsonOut);

			var aContainer = document.getElementById("adultContainer");
			var adultList = document.getElementById("adultMembers");
			console.log("Has Child Nodes" + aContainer.hasChildNodes());
			var achildNodes = aContainer.childNodes;
			for (x = 0; i < achildNodes.length; x++) {
				var childId = achildNodes[x].id;
				var childName = achildNodes[x].value;
				console.log("Child ID" + childId);
				console.log("Child Name:" + childName);
				if (childId != undefined) {
					if (childId.includes("adult")) {
						if (childName.length < 3) {
							alert("Adult Name should be at least 3 characters.")
							return false;
						}
					}
					if (childId.includes("food")) {
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
		function loadChildComp(isFoodRequired) {
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
				if(isFoodRequired) {
				var selectList = document.createElement("select");
				createFoodPreference(container, selectList, i + 1,
						"childMembers");
				}
				container.appendChild(document.createElement("br"));
				container.appendChild(document.createElement("br"));
			}
		}

		function createFoodPreference(parentElement, selectList, id, arg) {
			var array = [ "Veg", "Non-Veg", "Non-Veg-Hallal",
					"Food Not Required" ];
			selectList.id = "food-" + arg + "-" + id;
			selectList.name = arg + ".foodPreference";
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
		function adultsChanged(isFoodRequired) {
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
				if(isFoodRequired) {
				var selectList = document.createElement("select");
				createFoodPreference(container, selectList, i + 1,
						"adultMembers");
				}
				container.appendChild(document.createElement("br"));
				container.appendChild(document.createElement("br"));
			}
		}
	</script>
</body>
</html>