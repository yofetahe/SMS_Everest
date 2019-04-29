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

	<s:set var="si_id" value="si_id"/>
	<s:set var="class_id" value="class_id"/>
		
	<s:form action="uploadFile" enctype="multipart/form-data" method="post">
	
		<s:text name="Uploading photo's for student."/> <br/>
		
		<s:hidden key="si_id" name="si_id" value="%{#si_id}"/>
		<s:hidden key="class_id" name="class_id" value="%{#class_id}"/>		
		
		<s:file name="fileUpload" label="Select photo" size="30"/>
		
		<br/>
		
		<s:submit value="Upload" align="left"/>
		
	</s:form>

</body>
</html>