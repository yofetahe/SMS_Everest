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

	<s:set var="ptm_id" value="ptm_id"/>
	<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
	<table align="center"><tr><td>
		<div class="formtitle">Save Form</div>
		<br/>
		Material Name
		<table cellpadding="0" cellspacing="0">
			<s:textfield id="ptm_name" name="ptm_name" style="width: 200px; height: 30px;"/>
		</table>
		Material Description
		<table cellpadding="0" cellspacing="0">
			<s:textarea id="ptm_desc" name="ptm_desc" rows="5px" style="width: 200px;"/>
		</table>
		Material Status
		<table cellpadding="0" cellspacing="0">
			<s:select list="#{'A':'Active', 'I':'Inactive'}" id="ptm_status" name="ptm_status" rows="5px" style="width: 200px;"/>
		</table>
		
	</td></tr>
	<tr><td>
		<button onclick="updateStudentMaterial('${ptm_id}')" class="btn">Update</button>						
	</td></tr></table>

</body>
</html>