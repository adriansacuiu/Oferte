<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!-- 	<meta charset="utf-8"> -->
<!--     <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!--     <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--     The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<!--     <meta name="description" content=""> -->
<!--     <meta name="author" content=""> -->
<!--     <link rel="icon" href="../favicon.ico"> -->

<!--     <title>Dashboard Template for Bootstrap</title> -->

<!--     Bootstrap core CSS -->
<!--     <link href="../../resources/css/bootstrap.min.css" rel="stylesheet"> -->

<!--     Custom styles for this template -->
<!--     <link href="../../resources/css/dashboard.css" rel="stylesheet"> -->
</head>
<body>
	<div id="info" class="row well center-block" style="width:50%;">
	<h1>Login</h1>
<hr>
		<form class="form-horizontal col-sm-12 " role="form" action="<c:url value="/j_spring_security_check"/>" method="post">
			<div id="login_error" class="alert alert-danger fade in" style="display:none;">
				  
		 	</div>
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Username</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inputEmail3"
						placeholder="Username" name="j_username">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="inputPassword3"
						placeholder="Password" name="j_password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Sign in</button>
				</div>
			</div>
							
				
			
			<div class="form-group">
				<div class="col-sm-offset-11 col-sm-1">
					<button id="back" class="btn btn-primary" type="button" style="float: right;margin-top:-50px">Back</button>
				</div>
			</div>
		</form>
	</div>
	  <!-- Bootstrap core JavaScript
    ================================================== -->
<!--     Placed at the end of the document so the pages load faster -->
<!--     <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
<!--     <script src="../../resources/scripts/bootstrap.min.js"></script> -->
<!--     Just to make our placeholder images work. Don't actually copy the next line! -->
<!--     <script src="../../resources/scripts/holder.js"></script> -->
<!--     IE10 viewport hack for Surface/desktop Windows 8 bug -->
<!--     <script type="text/javascript" src="../../resources/scripts/ie10-viewport-bug-workaround.js"></script> -->
<!--     <script src="../../resources/scripts/app.js"></script> -->
</body>
</html>
