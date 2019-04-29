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
	<s:set var="clb_id" value="clb_id"/>	
	
		<div class="formtitle">Update Form</div>
		<br/><br/>
		Club Name
		<table cellpadding="0" cellspacing="0">
			<s:textfield id="clbname" name="clb_name" style="width: 200px; height: 30px;"/>
		</table>
		About the Club
		<table cellpadding="0" cellspacing="0">
			<s:textarea id="clbdesc" name="clb_desc" rows="10px" style="width: 200px;"/>
		</table>
		Club Status
		<table cellpadding="0" cellspacing="0">
			<s:select id="clbstatus" list="#{'A':'Active', 'I':'Inactive'}" name="clb_status" style="width: 205px; height: 30px;"/>
		</table>		
		Department Under
		<table cellpadding="0" cellspacing="0">
			<s:select id="depid" list="depList" listKey="dep_id" listValue="dep_name" name="dep_id" headerValue="---" headerKey="0" style="width: 205px; height: 30px;"/>
		</table>
		
		<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
			<s:submit onclick="updateCocurActClub('${clb_id}')" value="Update" style="width: 205px; height: 30px;"/>
		</table>	
	
</body>
</html>