<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iCloud云网盘</title>
</head>
<body>
<div>
	<a href="/springMVC/user/login">log in</a>
</div>
<center>

	<c:if test="${message != undefined}">
		<div id="message" style="color:red">${message}</div>
	</c:if>
	<div style="font-size: 25px ; padding-right: 80px ;padding-top: 200px">Log in to upload files to iCloud !</div>
	    <form action="/springMVC/file/searchFileWithPage" method="get"  style="margin: 10px">
		    <input type="text" name="searchcontent" maxlength="50" size="40" style="font-size: 20px;padding-left:3px; padding-top: 5px; padding-bottom: 3px; text-shadow: blue;" >
		    <input type="submit" style=" font-size: 24px;cursor: pointer" value="Search">
	    </form>
</center>
</body>
</html>