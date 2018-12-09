<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- START PAGE SOURCE -->
<%-- <jsp:include page="/views/TopMenu.jsp" flush="true" /> --%>
<div class="main">
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <h2 id="asset_error" ></h2><h2 id="asset_confirm_del">'+msg+'</h2>
        <div id="content_data"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

	<div id="content">
<%-- 		<jsp:include page="/views/LeftMenu.jsp" flush="true" /> --%>
		<c:set var="logged_user" value="${logged_user}"/>
		<article class="col2 pad_left1">
			<div class="well removewidth normalwidth">
				<div class="panel-body">
						<h1 style="color: #000000;text-align:center;">Account Details</h1>
						<div id="user_details" class="form-group">
							<div class="form-group" style="float:left; width: 150px; margin: 0 auto;">
									<img src="<c:url value="resources/images/${logged_user.username}/${logged_user.username}.jpg"/>"
										width="150" class="img-thumbnail"
										onerror="this.src='<c:url value="resources/images/placeholder.png"/>';">
										
								</div>
								<div class="clear"></div>

									<hr>
								
									<label for="inputName1" class="col-sm-4 control-label">Username:</label>
									<div class="col-sm-8">
										<span class="form-control"><c:out value="${logged_user.username}" /></span>
									</div>

									<hr>
									<label for="inputName1" class="col-sm-4 control-label">First Name:</label>
									<div class="col-sm-8">
										<span class="form-control"><c:out value="${logged_user.firstName}" /></span>
									</div>
									<label for="inputName1" class="col-sm-4 control-label">Last Name:</label>
									<div class="col-sm-8">
										<span class="form-control"><c:out value="${logged_user.lastName}" /></span>
									</div>
									
									<label for="inputName1" class="col-sm-4 control-label">Email:</label>
									<div class="col-sm-8">
										<span class="form-control"><c:out value="${logged_user.email}" /></span>
									</div>
									
									<label for="inputName1" class="col-sm-4 control-label">Department Name:</label>
									<div class="col-sm-8">
										<span class="form-control"><c:out value="${logged_user.department.name}" /></span>
									</div>
	
							<div class="col-sm-12">
								<button id="edit" class="btn btn-primary btn-lg" type="button" style="float: right;margin-top:59px">Edit Profile</button>
							</div>
						</div>
						<div id="edit_user_details" class="form-group">
							<form role="form" action="<c:out value="${logged_user.username}" />/updateUser" method="POST" enctype="multipart/form-data">
								<div class="form-group" style="float:left; width: 150px; margin: 0 auto;">
									<div class="glyphicon glyphicon-edit edit_image">
										<input type="file" onchange="javascript:show_upload();readURL($(this))" class="" name="image" id="image" placeholder="Upload profile image" />
									</div>
									<img id="image_value" src="<c:url value="resources/images/${logged_user.username}/${logged_user.username}.jpg"/>"
										width="150" class="img-thumbnail"
										onerror="this.src='<c:url value="resources/images/placeholder.png"/>';">
										<div id="upload_image" class="upload_image"></div>
								</div>
								<div class="clear"></div>
							
									<hr>
								
									<label for="inputName1" class="col-sm-4 control-label">Username:</label>
									<div class="col-sm-8">
										<input class="form-control" id="profile_username" name="username" type="text" value="<c:out value="${logged_user.username}" />" placeholder="<c:out value="${logged_user.username}" />" />
<%-- 										 <span style="font-size:9px;"> @<b><c:out value="${logged_user.username}" /></b> you can change the username</span> --%>
									</div>
									
									<label for="inputName1" class="col-sm-4 control-label">Password:</label>
									<div class="col-sm-8">
										<input class="form-control" id="profile_password" name="password" type="password" value="" placeholder="**********" />
<%-- 										<span style="font-size:9px;"> @<b><c:out value="${logged_user.username}" /></b> you can change your password</span> --%>
									</div>
							
									<hr>
									<label for="inputName1" class="col-sm-4 control-label">First Name:</label>
									<div class="col-sm-8">
										<input class="form-control" type="text" id="profile_fn"  name="firstName" value="<c:out value="${logged_user.firstName}" />" placeholder="<c:out value="${logged_user.firstName}" />" />
<%-- 										<span style="font-size:9px;"> @<b><c:out value="${logged_user.username}" /></b> you can change your First Name</span> --%>
									</div>
									<label for="inputName1" class="col-sm-4 control-label">Last Name:</label>
									<div class="col-sm-8">
										<input class="form-control" type="text" id="profile_ln"  name="lastName" value="<c:out value="${logged_user.lastName}" />" placeholder="<c:out value="${logged_user.lastName}" />" />
<%-- 										<span style="font-size:9px;"> @<b><c:out value="${logged_user.username}" /></b> you can change your Last Name</span> --%>
									</div>
									<label for="inputName1" class="col-sm-4 control-label">Email:</label>
									<div class="col-sm-8">
										<input class="form-control" type="text" value="<c:out value="${logged_user.email}" />" name="email" id="profile_email" placeholder="<c:out value="${logged_user.email}" />" />
<%-- 										<span style="font-size:9px;"> @<b><c:out value="${logged_user.username}" /></b> you can change your email</span> --%>
									</div>
									<div class="col-sm-8">
										<input class="form-control" type="hidden" value="<c:out value="${logged_user.role}" />" name="role" id="profile_role" placeholder="<c:out value="${logged_user.role}" />" />
<%-- 										<span style="font-size:9px;"> @<b><c:out value="${logged_user.username}" /></b> you can change your email</span> --%>
									</div>
									<div class="col-sm-8">
										<input class="form-control" type="hidden" value="<c:out value="${logged_user}" />" id="profile_role" name="id_user" placeholder="<c:out value="${logged_user.role}" />" />
<%-- 										<span style="font-size:9px;"> @<b><c:out value="${logged_user.username}" /></b> you can change your email</span> --%>
									</div>
									<div class="col-sm-8">
										<label for="department" class="col-sm-3 control-label">Department</label>
										<div class="col-sm-9">
											<select type="text" class="form-control"
												id="department" placeholder="Select Department" name="idDepartment">
												<option value="0" selected="selected">Select Department</option>
												<c:forEach items="${departments}" var="department">
											    	<option value="<c:out value="${department.idDepartment}"/>">${department.name}</option>
												</c:forEach>
											</select>
											
											<br />
										</div>
									</div>
									<div class="col-sm-12">
										 &nbsp;&nbsp;&nbsp;<button id="update" class="btn btn-primary btn-lg" type="button" onclick="" style="float: right;margin-top:59px">Go Back</button> <button id="update_user" class="btn btn-primary btn-lg" type="submit" onclick="" style="float: right;margin-top:59px">Update Changes</button> &nbsp;&nbsp;&nbsp;
									</div>
							</form>
						</div>
				</div>
			</div>
		</article>
	</div>
</div>
<jsp:include page="/views/Footer.jsp" flush="true" />
<!-- END PAGE SOURCE -->

