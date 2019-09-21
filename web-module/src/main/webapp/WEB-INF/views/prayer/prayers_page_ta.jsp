<!DOCTYPE html>
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link rel="stylesheet" href=<c:url value="/resources/css/calendar.css"/> media="screen">

<!-- Basic Page Needs -->
<meta charset="utf-8">
<title></title>
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
									<li><a href=<c:url value="/" />>முதல் பக்கம்</a></li>
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
								<span><em class="h-left"></em><span class="inner-heading">ஜெபங்கள்</span><em
									class="h-right"></em></span>
							</h3>
						</div>
						<article class="column">
							<div class="blog-post">
								<div class="slide-1">
									<ul>
										<li><img src=<c:url value="/resources/images/prayer1.jpg"/> alt="image" /></li>
									</ul>

								</div>
								<br class="clear" />
								<div class="blog-holder">
									<h4 class="post-title">விசேஷ ஜெபங்கள்</h4>
									<p>
									<div id="accordion">

										<h3>
											<a href="#">வியாதிக்காக ஜெபம்</a>
										</h3>
										<div>
											<p>
											<h4>ஜெபம்:</h4>
											ஆண்டவரே உம்மத்துய பாதங்களுக்கு நாங்கள் வருகிறோம் , உம்முடைய
											நாமம் மகிமைப்படுவதாக , உம்முடைய ராஜியம் வருவதாக. எங்களுடைய
											அகரத்தை எங்களுக்கு அன்று அன்று தரும் , எங்கள் பாவங்களை
											மன்னியும். நாங்களும் எங்களுக்கு கடன் பட்ட எவனுக்கும்
											மன்னிக்கிறோம். எங்களை சோதனைக்கு உட்பட பண்ணாமல் எங்களை
											தீமையில் இருந்து ரட்சியும். எங்கள் சரீரத்தில் இருக்கும்
											வியாதியை நீக்கி போடும். நீர்தாமே எங்களுடைய பாவங்களை சுமந்திர்
											என்று எழுதியிருக்கிற படி அதை எங்களுக்கு செய்யும். உம்முடைய
											குணமாக்கும் வல்லமை இப்பொழுதே இறங்கி வருவதாக. நாங்கள் அதற்காக
											கார்த்திருக்கிறோம். உம்முடைய தயவு எங்கள் பாவங்களை மற்றும்
											கெட்ட சிந்தனைகளை மன்னிப்பதாக. எங்களை தொடர்ட்ந்து உம்முடைய
											வழியில் நடத்தும். உம்மடிய நாமத்திற்கு மகிமை வரும்படி
											எங்களுடைய செயல்கள் இருப்பதாக. உம்முடைய ராஜ்யத்தையும் உமது
											நீதியையும் நாங்கள் தேட கிருபை தரும். இயேசுவின் நாமத்தில்
											ஜெபம் கேளும் எங்கள் நல்ல பிதாவே, ஆமென் <br> <br> <b>In
												your prayer you can use the below references.</b><br>
											<hr>
											References:
											<ul>
												<li>Exodus 15:26 If you listen carefully to the Lord
													your God and do what is right in his eyes, <b>if you
														pay attention to his commands and keep all his decrees, I
														will not bring on you any of the diseases I brought on the
														Egyptians</b>, for I am the Lord, who heals you.
												</li>
												<li>Isaiah 53 : 5 But he was pierced for our
													transgressions, he was crushed for our iniquities; the
													punishment that brought us peace was on him, and by his
													wounds we are healed.</li>
												<li>Psalm 147:3 He heals the brokenhearted and binds up
													their wounds.</li>
												<li>Luke 8:50 Hearing this, Jesus said to Jairus, <b>'Dont
														be afraid; just believe, and she will be healed.'</b>
												</li>
												<li>Luke 4:18 The Spirit of the Lord is on me, because
													he has anointed me to proclaim good news to the poor. He
													has sent me to proclaim freedom for the prisoners and
													recovery of sight for the blind, to set the oppressed free.</li>
												<li>I Peter 2:24 'He himself bore our sins' in his body
													on the cross, so that we might die to sins and live for
													righteousness; 'by his wounds you have been healed'.</li>
												<li>Psalms 103: <br>2. Praise the Lord, my
													soul,and forget not all his benefits<br> 3. who
													forgives all your sins and heals all your diseases,
												</li>
											</ul>
											</p>
										</div>

										<h3>
											<a href="#">Prayer for salvation</a>
										</h3>
										<div>
											<p>
												LORD we thank you for your loving kindness that you have
												shown to us. You came to this world to share your word and
												save us from the everlasting punishments. LORD help us to
												keep your word in our hearts and be careful in doing them.
												We always love to seek your kingdom as we have seen your
												glory. We do not know the day of your second coming but we
												know that you said to love one another, follow your
												commandments and have a fear of LORD. We pray that we may be
												found as a spotless bride and let our testimonies among
												people and LORD be faithful. Teach us your way and finally
												when you come back again take us to your home.<br> In
												Jesus name we pray. <br> AMEN
											</p>
											<hr>
											References:
											<ul>
												<li>Psalms 103<br> 17. But from everlasting to
													everlasting the Lord's love is with those who fear him, and
													his righteousness with their children's children <br>
													<br>18.With those who keep his covenant and remember
													to obey his precepts. <br>19.The Lord has established
													his throne in heaven, and his kingdom rules over all.
												</li>
											</ul>
										</div>
										<!-- <h3>
											<a href="#"></a>Prayer for getting out of temptations
										</h3>
										<div>
											<p></p>
										</div>

										<h3>
											<a href="#">Prayer for wisdom</a>
										</h3>
										<div>
											<p></p>
										</div>


										<h3>
											<a href="#">Prayer for students appearing for exams.</a>
										</h3>
										<div>
											<p></p>
										</div>

										<h3>
											<a href="#">Prayer for those seek a job</a>
										</h3>
										<div>
											<p></p>
										</div> -->

									</div>

									</p>
									<div class="blog-comments">
										<ul class="list-link">
											<li><a href="#" class="author">By Praise Your
													Redeemer</a></li>
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
												<span>Chennai, India</span> <span>praiseyourredeemer@gmail.com</span>
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