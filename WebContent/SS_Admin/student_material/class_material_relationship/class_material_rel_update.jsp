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
	<s:set var="pmc_id" value="pmc_id"/>
	<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
	<div id="saveEditForm" class="formboarder" style="width: 250px">					
		<table align="center"><tr><td>
			<div class="formtitle">Save Form</div>
			<br/>
			Class<span style="color: #ff0000">*</span>
			<table cellpadding="0" cellspacing="0">
				<s:select list="classList" id="cl_id" listKey="cl_id" listValue="class_name" name="cl_id" style="width: 205px; height: 30px;"/>
			</table>
			Material Name<span style="color: #ff0000">*</span>
			<table cellpadding="0" cellspacing="0">
				<s:select list="activeMatList" id="ptm_id" listKey="ptm_id" listValue="ptm_name" name="ptm_id" style="width: 205px; height: 30px;"/>
			</table>
			Payment Amount<span style="color: #ff0000">*</span>
			<table cellpadding="0" cellspacing="0">
				<s:textfield id="pay_amount" name="payment_amount" style="width: 200px; height: 20px;"/>
			</table>
			Status
			<table cellpadding="0" cellspacing="0">
				<s:select id="pmc_status" list="#{'A':'Active', 'I':'Inactive'}" name="pmc_status" style="width: 205px; height: 30px;"/>
			</table>
			
		</td></tr>
		<tr><td>
			<button onclick="updateMaterialClassRel('${pmc_id}')" class="btn">Update</button>						
		</td></tr></table>
	</div>

</body>
</html>