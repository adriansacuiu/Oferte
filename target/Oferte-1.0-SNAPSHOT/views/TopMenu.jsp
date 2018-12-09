<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Assets</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../favicon.ico">
	<c:set var="val" value="${pageContext.request.servletPath}"/>
				
	<c:choose> 
		<c:when test="${val == '/index.jsp'}">
			 <!-- Bootstrap core CSS -->
			    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
			
			    <!-- Custom styles for this template -->
			    <link href="resources/css/dashboard.css" rel="stylesheet">
			    <link href="resources/css/shCore.css" rel="stylesheet">
			     <!-- Bootstrap core JavaScript
			    ================================================== -->
			    <!-- Placed at the end of the document so the pages load faster -->
			    <script src="resources/scripts/jquery.js"></script>
			    <script src="resources/scripts/bootstrap.min.js"></script>
			    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
			    <script src="resources/scripts/holder.js"></script>
			     <script src="resources/scripts/require.js"></script>
			    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
			    <script type="text/javascript" src="resources/scripts/ie10-viewport-bug-workaround.js"></script>
			    <script src="resources/scripts/shCore.js"></script>
			    <script src="resources/scripts/app.js"></script>
			    <script src="resources/scripts/adminApp.js"></script>
			    <script src="resources/scripts/assetsUser.js"></script>
				
		</c:when>
		<c:otherwise>
		<!-- Bootstrap core CSS -->
			    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">
			
			    <!-- Custom styles for this template -->
			    <link href="../resources/css/dashboard.css" rel="stylesheet">
			    <link href="../resources/css/shCore.css" rel="stylesheet">
			     <!-- Bootstrap core JavaScript
			    ================================================== -->
			    <!-- Placed at the end of the document so the pages load faster -->
			    <script src="../resources/scripts/jquery.js"></script>
			    <script src="../resources/scripts/bootstrap.min.js"></script>
			    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
			    <script src="../resources/scripts/holder.js"></script>
			     <script src="../resources/scripts/require.js"></script>
			    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
			    <script type="text/javascript" src="../resources/scripts/ie10-viewport-bug-workaround.js"></script>
			    <script src="../resources/scripts/shCore.js"></script>
			    <script src="../resources/scripts/app.js"></script>
				<script src="../resources/scripts/adminApp.js"></script>
				<script src="../resources/scripts/assetsUser.js"></script>
		</c:otherwise>
	</c:choose>
	
    <title>Dashboard Template for Bootstrap</title>

  </head>

  <body>
<%-- <h1><c:out value="${val}"/></h1> --%>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="">Assets Management</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
          <security:authorize access="isAuthenticated()">	
            <li><a href="">Dashboard</a></li>
            <li><a href="">Profile</a></li>
            <li><a href="/AssetManagement/j_spring_security_logout">Logout</a></li>
            </security:authorize>
          </ul>
         
        </div>
      </div>
    </nav>
