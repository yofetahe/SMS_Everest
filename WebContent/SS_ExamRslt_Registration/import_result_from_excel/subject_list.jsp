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
				
	<s:select label="Subject" id="subcl_id" headerValue="Select subject" headerKey="0" list="clSubRelList" listValue="sub_name" listKey="subcl_id" onchange="examListForImportFromExcel(this.value)" style="height: 25px; width: 204px;"/>
	
</body>
</html>