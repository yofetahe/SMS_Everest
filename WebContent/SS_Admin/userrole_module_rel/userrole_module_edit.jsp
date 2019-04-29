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
	<s:fielderror id="errorMsg"/>
	<div id="errMsg">&nbsp;</div>
	<table><tr><td>
		<s:set var="ut_id" value="utId"/>
		<s:set var="ut_name" value="ut_name"/>
		<s:set var="utm_id" value="utm_id"/>
		<s:set var="m_id" value="m_id"/>
		<s:set var="m_name" value="m_name"/>
		
		<s:select id="m_id" label="Module" list="remainingModuleList" listValue="m_name" listKey="m_id" headerValue="%{#m_name}" headerKey="%{#m_id}" style="width: 206px; height: 25px;"/>
		<s:select id="rel_status" label="Relation Status" list="#{'A':'Active', 'I':'Inactive'}" name="mrel_status"/>
					
	</td></tr>
	<tr><td>
		<button onclick="updateUTypeModuleRel('${utm_id}', '${ut_id}', '${ut_name}')" class="btn">Update</button>						
	</td></tr></table>
</body>
</html>