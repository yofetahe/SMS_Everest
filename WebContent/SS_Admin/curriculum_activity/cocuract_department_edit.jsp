<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
</head>
<body>
	<s:set var="dep_id" value="dep_id"/>	
	
		<div class="formtitle">Update Form</div>
		<br/><br/>
		Department Name
		<table cellpadding="0" cellspacing="0">
			<s:textfield id="depname" name="dep_name" style="width: 200px; height: 30px;"/>
		</table>
		About the Department
		<table cellpadding="0" cellspacing="0">
			<s:textarea id="depdesc" name="dep_desc" rows="10px" style="width: 200px;"/>
		</table>
		Department Status
		<table cellpadding="0" cellspacing="0">
			<s:select id="depstatus" list="#{'A':'Active', 'I':'Inactive'}" name="dep_status" style="width: 205px; height: 30px;"/>
		</table>
		
		<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
		<s:submit onclick="updateCocurActDepartment('${dep_id}')" value="Update" style="width: 205px; height: 30px;"/>
		</table>	
	
</body>
</html>