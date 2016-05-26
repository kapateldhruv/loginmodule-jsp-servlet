<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

<!-- common css includes -->
<%@ include file="/includes/common_css.jsp"%>
<!-- common js includes -->
<%@ include file="/includes/common_js.jsp"%>
<!-- taglibs  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

</head>
<body>
	
	<div class="container-fluid into_div">
		<div class="row">
			<h3 class="text-center">Login Demo</h3>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="form-group col-md-offset-4 col-md-4">
				<form method="post" action="AdminLoginProcess.do" id="adminloginfrm">
				
				<!-- validation message -->
				<c:if test="${not empty requestScope.errorMsg}">
					<div class="bg-danger text-danger margin_bottom15">
						<c:forEach items="${requestScope.errorMsg}" var="error">
						<p>
							<c:out value="${error}"></c:out>
						</p>	
						</c:forEach>
					</div>
				</c:if>
				<!-- validation message ends-->
				
					<div class="form-group">
						<label>Username</label>
						
						<input type="text" name="username" value='<c:out value='${param.username}'></c:out>' placeholder="Email" class="form-control">
					</div>
					<div class="form-group">
						<label>Password</label>
						<input type="password" name="password" value="" placeholder="Password" class="form-control">
					</div>
					<div class="form-group">
						<input type="submit" name="login" value="Login" class="btn btn-primary">
						
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>