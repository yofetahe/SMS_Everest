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
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="si_id" value="si_id"/>
	<s:set var="qr_id" value="qr_id"/>
	<s:set var="bsr_id" value="bsr_id"/>
	<s:set var="bt_id" value="bt_id"/>
	
	<table align="center"><tr><td>
		<div class="formtitle">Update Form</div>
		
		<div id="errMsg" style="color: #ff0000; width: auto; text-align: center;">&nbsp;</div>
		
		Trait Name
		<table cellpadding="0" cellspacing="0">
			<s:select id="btid" list="btrait_list" name="bt_id" listKey="bt_id" listValue="bt_title" onchange="evaluationGradeList(this.value)" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
		</table>
		<br/>
					
		Evaluation Point
		<table cellpadding="0" cellspacing="0"><tr><td>
			<div id="evalGrgList">
				<table cellpadding="0" cellspacing="0">
				<s:select id="bgid" list="bgrade_list" name="bg_id" listKey="bg_id" listValue="bg_name" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
				</table>
			</div>
		</td></tr></table>
		<br/>
		
		Evaluation Status
		<table cellpadding="0" cellspacing="0">
			<s:select id="bsrstatus" list="#{'A':'Active', 'I':'Inactive'}" name="bsr_status" style="width: 205px; height: 30px;"/>
		</table>
		
		<table cellpadding="0" cellspacing="0" style="padding-top: 5px;">
			<s:submit  value="Update" style="width: 205px; height: 30px;"/>
		</table>
	</td></tr>
	<tr><td>
		<button onclick="updateBevalResult('${bsr_id}', '${cl_id}', '${si_id}', '${qr_id}', '${bt_id}')" class="btn">Update</button>						
	</td></tr></table>

</body>
</html>