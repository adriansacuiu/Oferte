<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="TopMenu.jsp" flush="true" />
<div class="container-fluid">
      <div class="row">
    	  <jsp:include page="LeftMenu.jsp" flush="true" />
       
        <div class=" alert-warning well center" style="width: 768px; top:100px">
       		<security:authorize access="isAuthenticated()">	
         		
         	 </security:authorize>
         		<h4>Error-404: PAGE NOT FOUND</h4>
				<hr>
			Ooops, the page was not found. Click <a href="/AssetManagement/">here</a> to go back.
          </div>
        </security:authorize>
		<div class="row placeholders">
   			<jsp:include page="Footer.jsp" flush="true" />
   		</div>
      </div>
    </div>
    
  </body>
 
</html>
