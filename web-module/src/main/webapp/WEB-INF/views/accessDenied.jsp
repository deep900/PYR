<!DOCTYPE html>
<html lang="en">
  <head>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">  
  <title>Access Denied</title>
  <c:set var="context" value="${pageContext.request.contextPath}" />
  <!-- ========== Css Files ========== -->
<link rel="icon" type="image/png" href=<c:url value="/resources/images/favicon.png"/>>
<meta name="description" content>
<meta name="author" content>
<link rel="icon" type="image/png" href=<c:url value="/resources/images/favicon.png"/>>
<meta name="description" content>
<meta name="author" content>
<link rel="icon" type="image/png" href=<c:url value="/resources/images/favicon.png"/>>
<!-- CSS Style -->
<link rel="stylesheet" href=<c:url value="/resources/css/all.css"/> type="text/css" media="all">
<!-- ResponsiveCSS Style -->
<link rel="stylesheet" href=<c:url value="/resources/css/responsive.css"/> type="text/css" media="all">
<!-- CSS ShortCode -->
<link rel="stylesheet" href=<c:url value="/resources/css/shortcode.css"/> type="text/css" media="all">
<!-- Start JavaScript -->
<link rel="stylesheet" href=<c:url value="/resources/css/javascri.css"/> type="text/css" media="all">
<!-- gallery CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/prettyPhoto.css"/> type="text/css" media="all">
<!-- Start Player CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/player.css"/> type="text/css" media="all">
<!-- Style Switcher CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/default.css"/> id="stylesheet">
<!-- Megamenu CSS -->
<link rel="stylesheet" href=<c:url value="/resources/css/megamenu.css"/> type="text/css" media="screen" />

  <style type="text/css">
    body{background: #F5F5F5;}
  </style>
  </head>
  <body>
    <div class="error-pages">        
        <h1>Access Denied</h1>       
        <h4> Authorization Failed</h4>       
        </div>
        <div class="bottom-links">
          <a href="${context}/login-page" class="btn btn-light">Login</a>          
          <a href="${context}/" class="btn btn-default">Go Homepage</a>
          <a href="${context}/messages" class="btn btn-default">Messages Home</a>
        </div>

</body>
</html>