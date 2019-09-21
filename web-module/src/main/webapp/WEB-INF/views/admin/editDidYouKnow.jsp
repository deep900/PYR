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
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/>
	media="screen">
<c:set var="context" value="${pageContext.request.contextPath}" />

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
							<!--  <h1 id="logo"><a href="index.html">The Church</a></h1> -->
							<div class="nav">
								<ul class="menu menu_blue">
									<li><c:url value="/admin/adminHome" />>Home</a></li>
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
            <h3 class="content-heading"><span><em class="h-left"></em><span class="inner-heading">Edit did you know</span><em class="h-right"></em></span></h3>
          </div>
          <article>
            <div>
              <div>
                <div>
                  <form:form name="editDidYouKnoForm" method="post" action="${context}/admin/editDidYouKnowAction">
                    <fieldset>
                      <!-- <div class="row"> -->
                        <div class="cleanform">
                        
                        <form:input path="id" id="id" type="hidden"/>
                        <table>
                        <tr><td>Did you know </td><td><form:textarea path="didYouKnowContent" />
                        </td>
                        </tr>
                        <tr>
                        <td>
                          Did you know Tamil</td><td>
                          <form:textarea path="didYouKnowContentTamil" />
                          </td>
                          </tr>
                        
                        <tr><td>
                          Last Modified</td> 
                          <td><form:input path="lastModified" disabled="true"/></td>
                        </tr>
                        <tr><td>
                      <input name="" type="submit" value="SUBMIT"></td></tr>
                      </table>
                    </fieldset>
                    <input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
                  </form:form>
                </div>
              </div>
            </div>
          </article>
          
        </section>
      </section>
    </div>	
		</div>

		<!-- footer -->


	</div>
	
</body>
</html>