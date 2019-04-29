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

	<table cellpadding="0" cellspacing="0"><tr><td style="padding-top: 3px;">
		<s:select label="Semister" id="semisterList" list="sem_list" headerValue="----" headerKey="0" listValue="at_name" listKey="at_id" style="width: 150px; height: 25px" onchange="refreshStudentMarkList()"/>
	</td></tr></table>

</body>
</html>