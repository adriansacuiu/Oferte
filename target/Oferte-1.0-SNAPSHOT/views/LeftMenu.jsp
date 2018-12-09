<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
          	<security:authorize access="hasRole('ROLE_ADMIN')">	
              <li id="my_profile"><a onclick="javascript:getMyProfile();"><h1><span class="glyphicon glyphicon-user"></span> My profile</h1></a></li>
             <li id="edit_user" ><a onclick="javascript:getUsersAdmin();"><h1><span class="glyphicon glyphicon-eye-open"></span>Users</h1></a></li>
             <li id="assets_admin"><a onclick="javascript:getAssetsAdmin();"><h1><span class="glyphicon glyphicon-th-list"></span> Assets</h1></a></li>
             <li id="requests"><a onclick="javascript:getRequestsAdmin();"><h1><span class="glyphicon glyphicon-random"></span>Requests</h1></a></li>
             <li id="transactions"><a onclick="javascript:getTransactionsAdmin();"><h1><span class="glyphicon glyphicon-download"></span>Transactions</h1></a></li>
             <li id="complaints"><a onclick="javascript:getComplaintsAdmin();"><h1><span class="glyphicon glyphicon-comment"></span>Complaints</h1></a></li>
             
             </security:authorize>
             
            <security:authorize access="hasRole('ROLE_USER')">
             <li id="my_profile"><a onclick="javascript:getMyProfile();"><h1><span class="glyphicon glyphicon-th-list"></span> My profile</h1></a></li>
           	 <li id="assets"><a onclick="javascript:getAssets('<c:url value="${logged_user.username}"/>');"><h1><span class="glyphicon glyphicon-th-list"></span> Assets</h1></a></li>
             <li id="complaints"><a onclick="javascript:getComplaints('<c:url value="${logged_user.username}"/>');"><h1><span class="glyphicon glyphicon-comment"></span> Complaints</h1></a></li>
             <li id="requests"><a onclick="javascript:getRequests('<c:url value="${logged_user.username}"/>');"><h1><span class="glyphicon glyphicon-random"></span>Requests</h1></a></li>
             <li id="transactions"><a onclick="javascript:getTransactions('<c:url value="${logged_user.username}"/>');"><h1><span class="glyphicon glyphicon-download"></span> Transactions</h1></a></li>
            </security:authorize>
          </ul>
        </div>
