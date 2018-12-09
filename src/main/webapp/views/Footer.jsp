<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-fixed-bottom navbar-inverse bg-info">
				<div class="text-muted pull-left" style="padding:10px;">
					Copyright &copy; 2014 <a href="#">Domain Name</a> - All Rights
					Reserved
				</div>
				<div class="text-muted pull-right" style="padding:10px;">
					<security:authorize access="isAuthenticated()">	
						<a href="#">Hello</a> <a href="#"><c:out value="${logged_user.firstName}"/> <c:out value="${logged_user.lastName}"/></a>
						</security:authorize>
				</div>
				
</div>

 