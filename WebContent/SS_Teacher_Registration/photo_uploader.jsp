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
	<s:set var="ti_id" value="ti_id"/>
	<s:form action="uploadTeacherFile" enctype="multipart/form-data" method="post">
		<s:text name="Teacher's photo uploading"/> <br/>
		<s:hidden key="ti_id" name="ti_id" value="%{#ti_id}"></s:hidden>		
		<s:file name="fileUpload" label="Select photo" size="30"/>
		<br/>
		<s:submit value="Upload" align="left"/>
	</s:form>

</body>
</html>