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

<table>
<s:select label="Class Category" list="grade_rslt" listKey="class_id" headerKey="0" headerValue="---" listValue="class_name" onchange="examrslt_gradeSelectedList(this.value)" style="width: 150px; height: 25px"/>
</table>

</body>
</html>