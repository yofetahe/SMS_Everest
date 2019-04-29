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
	<div id="resultErrorMsg" style=" color: red"></div>
	<table>
		<tr>
			<td>
				<s:set var="at_id" value="at_id"/>
				<s:set var="cl_id" value="cl_id"/>
				<s:set var="cd_id" value="cd_id"/>
				<s:set var="exsub_id" value="exsub_id"/>
				<s:set var="pageFlag" value="pageFlag"/>
				
				<s:set var="er_id" value="er_id"/>
				<s:set var="result" value="result"/>
				<s:set var="total_mark" value="total_mark"/>
				
				<s:textfield label="Mark" id="rslt" key="result" style="width: 206px; height: 30px"/>
				
			</td>
		</tr>
		<tr><td>
			<button onclick="updateStudentExamResult('${er_id}', '${total_mark}', '${cl_id}', '${exsub_id}', '${cd_id}', '${at_id}', '${pageFlag}')" class="btn">Update</button>						
		</td></tr>
	</table>

</body>
</html>