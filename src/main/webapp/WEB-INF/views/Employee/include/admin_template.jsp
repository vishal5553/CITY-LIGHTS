<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>City Light</title>
   <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="<spring:url value='/ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'/>"></script>
 
<link rel="stylesheet" href="<spring:url value='/admin_style.css'/>"
	type="text/css" />
	
	 <script>$("#sidenavToggler").click(function(e) {
	    e.preventDefault();
	    $("body").toggleClass("sidenav-toggled");
	    $(".navbar-sidenav .nav-link-collapse").addClass("collapsed");
	    $(".navbar-sidenav .sidenav-second-level, .navbar-sidenav .sidenav-third-level").removeClass("show");
	  });</script>
	<script type="text/javascript" src="<spring:url value='/admin_dashboard.js'/>"></script>
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="<spring:url value='/Employee/Dashboard'/>">City Light</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="<spring:url value='/Employee/Dashboard'/>">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Dashboard</span>
          </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="layout">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#layout" data-parent="#exampleAccordion">
             <i class="fa fa-fw fa-sitemap"></i>
            <span class="nav-link-text">Work</span>
          </a>
            <ul class="sidenav-second-level collapse" id="layout">
            <li>  
              <a href="<spring:url value='/Employee/complaints'/>"> <i class="fa fa-fw fa-link"></i>Complaints</a>
            </li>
            <li>
              <a href="<spring:url value='/Employee/lineCutForm'/>"><i class="fa fa-fw fa-link"></i>Line Cut Request</a>
            </li>
              <li>  
              <a href="<spring:url value='/Employee/linecutStatus'/>"> <i class="fa fa-fw fa-link"></i>Line Cut Status</a>
            </li>
          </ul>
        </li>
      </ul>
      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
					<form class="form-inline my-2 my-lg-0 mr-lg-2">
						<div class="input-group">
							<h3 Style="color:white;">${sessionScope.userDetails.name}</h3> <span class="input-group-append">
							</span>
						</div>
					</form>
				</li>
        <li class="nav-item">
          <a class=" btn btn-danger nav-link" data-toggle="modal" data-target="#exampleModal">
            Logout</a>
        </li>
      </ul>
    </div>
  </nav>