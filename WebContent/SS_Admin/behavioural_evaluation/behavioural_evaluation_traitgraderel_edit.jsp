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

	<s:set var="btbg_id" value="btbg_id"/>
	<s:set var="bt_id" value="bt_id"/>
	<table align="center"><tr><td>
		<div class="formtitle">Update Form</div>
		<br/>
		
		Grade Name: <s:property value="bg_name"/><br/>
		
		Relation Status
		<table cellpadding="0" cellspacing="0">
			<s:select id="btbg_rel" list="#{'A':'Active', 'I':'Inactive'}" name="btbg_rel" style="width: 205px; height: 30px;"/>
		</table>						
		
	</td></tr>
		<tr><td style="padding-top: 5px;">
			<button onclick="updateBevalTraitGradeRel('${btbg_id}', '${bt_id}')">Update</button>						
		</td></tr></table>

</body>
</html>