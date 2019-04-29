<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
</head>
<body>

<img alt="error" src="../images/error.jpeg"/>
<span class="#container">
	SORRY, Bug was found, kindly contact the developer. <br>
	<s:property value="exception.message"/>
	<s:property value="exceptionStack"/>
</span>

</body>
</html>