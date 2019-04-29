<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/sms_examrslt_registration.js" type="text/javascript"></script>

</head>
<body>

<s:set var="cl_id" value="cl_id"/>
<s:set var="cd_id" value="cd_id"/>
<s:set var="at_id" value="at_id"/>
<s:set var="si_id" value="si_id"/>
<s:set var="ac_year" value="ac_year"/>


<table width="100%" cellpadding="0" cellspacing="0" style="background-color: #f5f5f5; padding: 5px;">
	<tr>
		<td>
			Student Name: <s:property value="fname"/> <s:property value="mname"/> <s:property value="gname"/>
		</td>
	</tr>
	<tr>
		<td>
			Class: <s:property value="cl_name"/> <s:property value="cd_name"/>
		</td>
	</tr>
	<tr>
		<td>
			Academic Year: <s:property value="academic_year"/>
		</td>
	</tr>
</table>
		
<div style="line-height: 10px;">&nbsp;</div>		

<table>
	<tr>
		<td>
			<s:actionerror/>
			<div id="comErrMsg" style="color: #ff0000"></div>
			
			<s:set var="erc_content" value="getTeacherComment[0].erc_content"/>
			<s:set var="erc_id" value="getTeacherComment[0].erc_id"/>
			
			<s:textarea label="Give your comment here" id="tchrcomm" value="%{#erc_content}" name="erc_content" rows="5" cols="80"></s:textarea>
		</td>
	</tr>
	<tr><td>
		<button onclick="updateTeachersComment('${si_id}', '${cl_id}', '${cd_id}', '${at_id}', '${ac_year}', '${erc_id}')" class="btn">Update</button>						
	</td></tr>
</table>

</body>
</html>