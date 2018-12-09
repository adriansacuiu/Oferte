<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<!-- START PAGE SOURCE -->
<div class="main">
	<h1>Register</h1>
	<hr>
	<section id="content">
		<div id="info" class="row col-sm-6 well center">
			<sf:form class="form-horizontal" role="form" id="register_form"
				action="register" modelAttribute="user" method="post"
				enctype="multipart/form-data">
				<div id="login_error" class="alert alert-danger fade in"
					style="display: none;"></div>
				<div class="form-group">
					<label for="inputName1" class="col-sm-3 control-label">Username
						<span class="red">*</span>
					</label>
					<div class="col-sm-9">
						<sf:input type="text" class="form-control" id="inputName1"
							placeholder="Username" path="username" required="required" />
						<br />
					</div>
				</div>

				<div class="form-group">
					<label for="inputPassword1" class="col-sm-3 control-label">Password
						<span class="red">*</span>
					</label>
					<div class="col-sm-9">
						<sf:input type="password" class="form-control" id="inputPassword1"
							placeholder="Password" path="password" required="required" />
						<br />
					</div>
				</div>
				<hr>
				<div class="form-group">
					<label for="firstName" class="col-sm-3 control-label">First
						Name</label>
					<div class="col-sm-9">
						<sf:input type="text" class="form-control" id="fn"
							placeholder="First Name" path="firstName" />
						<br />
					</div>
				</div>
				<div class="form-group">
					<label for="lastName" class="col-sm-3 control-label">Last
						Name</label>
					<div class="col-sm-9">
						<sf:input type="text" class="form-control" id="ln"
							placeholder="Last Name" path="lastName" />
						<br />
					</div>
				</div>
				<div class="form-group">
					<label for="inputEmail1" class="col-sm-3 control-label">Email
						<span class="red">*</span>
					</label>
					<div class="col-sm-9">
						<sf:input type="email" class="form-control" id="inputEmail1"
							placeholder="Email" path="email" required="required" />
						<br />
					</div>
				</div>
				<div class="form-group">
					<label for="department" class="col-sm-3 control-label">Department</label>
					<div class="col-sm-9">
						<select type="text" class="form-control" id="department"
							placeholder="Select Department" name="idDepartment">
							<option value="0" selected="selected">Select Department</option>
							<c:forEach items="${departments}" var="department">
								<option value="<c:out value="${department.idDepartment}"/>">${department.name}</option>
							</c:forEach>
						</select> <br />
					</div>
				</div>
				<div class="form-group">
					<label for="image" class="col-sm-3 control-label">Profile
						image</label>
					<div class="col-sm-9">
						<div class="btn btn-info ">
							Browse Image<input type="file"
								onchange="javascript:show_upload();" class="" name="image"
								id="image" placeholder="Upload profile image" />
						</div>
						<div id="upload_image" class="register_image"></div>
						<br />
					</div>
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<div class="col-sm-2 pull-left" style="margin-top: 60px">
							<button id="register" class="btn btn-primary btn-lg"
								type="submit">Register</button>
						</div>
						<button id="back" class="btn btn-primary btn-lg" type="button"
							style="float: right; margin-top: 59px">Back</button>
					</div>
					<sf:input type="hidden" id="user_role" value="user" path="role" />
				</div>
			</sf:form>
		</div>
	</section>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>
