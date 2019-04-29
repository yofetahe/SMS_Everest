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
	
	<s:select label="Exam Type" headerValue="Select exam type" headerKey="0" list="clSubExamRelList" listValue="et_name" listKey="exsub_id" onchange="studentList(this.value)" style="width: 110px; height: 25px"/>

</body>
</html>