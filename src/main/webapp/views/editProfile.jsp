<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<!-- START PAGE SOURCE -->
<div class="main">
<h1>Update Account</h1>
<hr>
	<section id="content">
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
									<label for="department" class="col-sm-4 control-label">Department</label>
									<div class="col-sm-8">
										
<!-- 										<div class="col-sm-9"> -->
											<select class="form-control"
												id="department" placeholder="Select Department" name="idDepartment">
												<option value="0" >Select Department</option>
												<c:forEach items="${departments}" var="department">
												<c:set var="dept_name" scope="session" value="${department.name}"/>
												<c:set var="logged_dept_name" scope="session" value="${logged_user.department.name}"/>
												<c:if test="${dept_name == logged_dept_name}">
											    	<option value="<c:out value="${department.idDepartment}"/>" selected="selected">${department.name}</option>
											    </c:if>
											    <option value="<c:out value="${department.idDepartment}"/>" >${department.name}</option>	
												</c:forEach>
											</select>
										
<!-- 										</div> -->
									</div>
									<div class="col-sm-8">
										<input class="form-control" type="hidden" value="<c:out value="${logged_user.role}" />" name="role" id="profile_role" placeholder="<c:out value="${logged_user.role}" />" />
<%-- 										<span style="font-size:9px;"> @<b><c:out value="${logged_user.username}" /></b> you can change your email</span> --%>
									</div>
									<div class="col-sm-8">
										<input class="form-control" type="hidden" value="<c:out value="${logged_user.idUser}" />" id="profile_role" name="idUser" placeholder="<c:out value="${logged_user.role}" />" />
<%-- 										<span style="font-size:9px;"> @<b><c:out value="${logged_user.username}" /></b> you can change your email</span> --%>
									</div>
									
									<div class="col-sm-12">
										 &nbsp;&nbsp;&nbsp;<button id="update" class="btn btn-primary btn-lg" type="button" onclick="" style="float: right;margin-top:59px">Go Back</button> <button id="update_user" class="btn btn-primary btn-lg" type="submit" onclick="" style="float: right;margin-top:59px">Update Changes</button> &nbsp;&nbsp;&nbsp;
									</div>
							</form>
						</div>
	</section>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>
