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

	<s:set var="ut_id" value="utId"/>
	<div style="width: auto; height: 35px; background-color: #f5f5f5; padding-top: 15px;">Edit User Role</div>
	<div id="errorMsg" style="width: 35%; text-align: center;">&nbsp;</div>
	<table>
		<tr>
			<td>
				<s:textfield label="Role Name" id="roleName" required="true" name="ut_name" style="width: 200px; height: 30px;"/>
				<s:select label="Related With" list="#{'NAC':'None Academic', 'TCHR':'Teacher', 'STD':'Student'}" id="relatedWith" name="related_with" style="width: 206px; height: 30px;"/>
				<s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" id="roleStatus" name="ut_status" style="width: 206px; height: 30px;"/>
				
			</td>
		</tr>
		<tr><td>
			<button onclick="updateUserRole('${ut_id}')" class="btn">Update</button>						
		</td></tr>
	</table>

</body>
</html>