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
	
	<s:set var="bt_id" value="bt_id"/>
	<table align="center"><tr><td>
		<div class="formtitle">Update Form</div>
		<br/>
		Trait Name
		<table cellpadding="0" cellspacing="0">
			<s:textfield id="bt_title" name="bt_title" style="width: 200px; height: 30px;"/>
		</table>
		Trait Description
		<table cellpadding="0" cellspacing="0">
			<s:textarea id="bt_desc" name="bt_desc" rows="10px" style="width: 200px;"/>
		</table>
		Trait Status
		<table cellpadding="0" cellspacing="0">
			<s:select id="bt_status" list="#{'A':'Active', 'I':'Inactive'}" name="bt_status" rows="10px" style="width: 205px;"/>
		</table>
	</td></tr>
	<tr><td>
		<button onclick="updateBevalTrait('${bt_id}')">Update</button>						
	</td></tr></table>

</body>
</html>