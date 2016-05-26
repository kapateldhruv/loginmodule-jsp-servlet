<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dashboard</title>

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
			<div class="text-center">
				Welcome to dashboard
				<a href="${initParam.SITE_ROOT}logout">Logout</a>
			</div>
		</div>
	</div>
</body>
</html>