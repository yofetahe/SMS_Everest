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
	<s:set var="tr_id" value="tr_id"/>
	
		<div class="formtitle">Update Form</div>
		<br/>
		Teacher Name
		<table cellpadding="0" cellspacing="0">
			<s:select id="tchList" list="tchList" listKey="ti_id" listValue="tr_name" name="ti_id" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
		</table>
		
		Teacher Role
		<table cellpadding="0" cellspacing="0">
			<s:select id="tchRoleList" list="tchRoleList" listKey="role_id" listValue="role_name" name="role_id" onchange="depOrClubList(this.value)" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
		</table>
								
		Department/Club List
		<table cellpadding="0" cellspacing="0">
			<s:select id="tchRespList" list="#{'':''}" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
		</table>
		
		Academic Year
		<table cellpadding="0" cellspacing="0">
			<s:select list="acYearList" id="acyear" headerKey="0" headerValue="---" name="academic_year" style="width: 205px; height: 30px;"/>
		</table>
		
		Assignment Status
		<table cellpadding="0" cellspacing="0">
			<s:select id="trstatus" list="#{'A':'Active', 'I':'Inactive'}" name="tr_status" style="width: 205px; height: 30px;"/>
		</table>
			
		<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
			<s:submit onclick="updateCocurActTeacherRole('${tr_id}')" value="Update" style="width: 205px; height: 30px;"/>
		</table>	

</body>
</html>