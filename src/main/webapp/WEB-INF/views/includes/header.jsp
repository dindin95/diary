<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Fonts -->

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<head>

<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="/resources/assets/img/apple-icon.png">
<link rel="icon" type="image/png"
	href="/resources/assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Paper Dashboard 2 by Creative Tim</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no'
	name='viewport' />
<!--     Fonts and icons     -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200"
	rel="stylesheet" />
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<!-- CSS Files -->
<link href="/resources/assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="/resources/assets/css/paper-dashboard.css?v=2.0.1"
	rel="stylesheet" />
<!-- CSS Just for demo purpose, don't include it in your project -->
<link href="/resources/assets/demo/demo.css" rel="stylesheet" />
</head>

<body class="">
	<div class="wrapper ">
		<div class="sidebar" data-color="white" data-active-color="danger">
			<div class="logo">
				<a href="https://www.creative-tim.com" class="simple-text logo-mini">
					<div class="logo-image-small">
						<img src="/resources/assets/img/logo-small.png">
					</div> <!-- <p>CT</p> -->
				</a> <a href="/diary/list"
					class="simple-text logo-normal"> 36.5℃ 사진관 <!-- <div class="logo-image-big">
            <img src="/resources/assets/img/logo-big.png">
          </div> -->
				</a>
			</div>
			<div class="sidebar-wrapper">
				<ul class="nav">
					<li><a href="./dashboard.html"> <i class="nc-icon nc-bank"></i>
							<p>Dashboard</p>
					</a></li>
					<li><a href="./icons.html"> <i class="nc-icon nc-diamond"></i>
							<p>Icons</p>
					</a></li>
					<li><a href="./map.html"> <i class="nc-icon nc-pin-3"></i>
							<p>Maps</p>
					</a></li>
					<li><a href="./notifications.html"> <i
							class="nc-icon nc-bell-55"></i>
							<p>Notifications</p>
					</a></li>
					<li><a href="./user.html"> <i class="nc-icon nc-single-02"></i>
							<p>User Profile</p>
					</a></li>
					<li class="active "><a href="./tables.html"> <i
							class="nc-icon nc-tile-56"></i>
							<p>Table List</p>
					</a></li>
					<li><a href="./typography.html"> <i
							class="nc-icon nc-caps-small"></i>
							<p>Typography</p>
					</a></li>
					<li class="active-pro"><a href="./upgrade.html"> <i
							class="nc-icon nc-spaceship"></i>
							<p>Upgrade to PRO</p>
					</a></li>
				</ul>
			</div>
		</div>
		<div class="main-panel">
			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-absolute fixed-top navbar-transparent">
				<div class="container-fluid">
					<div class="navbar-wrapper">
						<div class="navbar-toggle">
							<button type="button" class="navbar-toggler">
								<span class="navbar-toggler-bar bar1"></span> <span
									class="navbar-toggler-bar bar2"></span> <span
									class="navbar-toggler-bar bar3"></span>
							</button>
						</div>
						
					</div>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navigation" aria-controls="navigation-index"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-bar navbar-kebab"></span> <span
							class="navbar-toggler-bar navbar-kebab"></span> <span
							class="navbar-toggler-bar navbar-kebab"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-end"
						id="navigation">
						<form>
							<div class="input-group no-border">
							
								<div class="input-group-append">
									
										<i class="nc-icon nc-zoom-split"></i>
									
								</div>
							</div>
						</form>
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link btn-magnify"
								href="javascript:;"> <i class="nc-icon nc-layout-11"></i>
									<p>
										<span class="d-lg-none d-md-block">Stats</span>
									</p>
							</a></li>
							<li class="nav-item btn-rotate dropdown"><a
								class="nav-link dropdown-toggle" href="http://example.com"
								id="navbarDropdownMenuLink" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false"> <i
									class="nc-icon nc-bell-55"></i>
									<p>
										<span class="d-lg-none d-md-block">Some Actions</span>
									</p>
							</a>
								<div class="dropdown-menu dropdown-menu-right"
									aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="#">Action</a> <a
										class="dropdown-item" href="#">Another action</a>
										
										<sec:authorize access="isAuthenticated()">
										 <a class="dropdown-item" href="http://localhost:8080/customLogin">Logout</a>
										 </sec:authorize>
										 										<sec:authorize access="isAnonymous()">
										 <a class="dropdown-item" href="http://localhost:8080/customLogin">Login</a>
										 </sec:authorize>
								</div></li>
						<!-- 	<li class="nav-item"><a class="nav-link btn-rotate"
								href="javascript:;"> <i class="nc-icon nc-settings-gear-65"></i>
									<p>
										<span class="d-lg-none d-md-block">Account</span>
									</p> </li> -->
							</a>
						</ul>
					</div>
				</div>
			</nav>
			<!-- End Navbar -->
			
				<div class="col-md-12">
					<div class="card-body">
						<table class="table">
							</thead>
							<tbody>
								<tr>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
			
			</div>
		 	<style>

    .uploadResult {
        width: 100%;
  
    }

    .uploadResult ul {
        display: flex;
        flex-flow: row;
        justify-content: center;
        align-items: center;
    }

    .uploadResult ul li {
        list-style: none;
        padding: 10px;
        align-content: center;
        text-align: center;
    }

    .uploadResult ul li img {
        width: 150px;
    }
    
     .uploadResult ul li span {
        color: #131212;
    }

    .bigPictureWrapper {
        position: absolute;
        display: none;
        justify-content: center;
        align-items: center;
        top: 100%;
        width: 100%;
        height: 100%;
        background-color: #ebccd1;
        z-index: 100;
        background: rgba(255, 255, 255, 0.5);
    }

    .bigPicture {
        position: relative;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .bigPicture img {
        width: 600px;
    
}
  
    
</style> 
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

