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

<s:select label="Select Class" id="cl_id" headerValue="Select class" headerKey="0" list="grdList_rslt" listValue="class_name" listKey="cl_id" onchange="gradeDetailList(this.value)" style="width: 110px; height: 25px"/>

</body>
</html>