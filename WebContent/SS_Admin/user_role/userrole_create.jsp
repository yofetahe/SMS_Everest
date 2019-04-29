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

	<div style="width: auto; height: 35px; background-color: #f5f5f5; padding-top: 15px;">User Role Add Form</div>
	<div id="errorMsg" style="width: 35%; text-align: center;">&nbsp;</div>
	<table>
		<tr>
			<td>
				<s:textfield label="Role Name" id="roleName" name="ut_name" style="width: 200px; height: 30px;" required="true"/>
				<s:select label="Related With" list="#{'NAC':'None Academic', 'TCHR':'Teacher', 'STD':'Student'}" id="relatedWith" headerValue="----" headerKey="0" name="related_with" style="width: 206px; height: 30px;" required="true"/>
				<s:submit onclick="saveUserRole()" value="Save"/>
			</td>
		</tr>
	</table>

</body>
</html>