<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
	
	<s:set var="at_id" value="at_id"/>
	<div style="width: auto; height: 35px; background-color: #f5f5f5; padding-top: 15px;">Edit User Role</div>
	<div id="errMsg" style="width: 35%; text-align: center; color: red;">&nbsp;</div>
	<table>
		<tr>
			<td>
				<s:textfield label="Annual Term Name" required="true" id="annTermName" name="at_name" style="width: 200px; height: 30px;"/>
				<s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" id="atStatus" name="at_status" style="width: 206px; height: 30px;"/>
				
			</td>
		</tr>
		<tr><td>
			<button onclick="updateAnnualTerm('${at_id}')" class="btn">Update</button>						
		</td></tr>
	</table>
	
</body>
</html>