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

	<div style="width: auto; height: 35px; background-color: #f5f5f5; padding-top: 15px;">Annual Term Add Form</div>
	<div id="errMsg" style="width: 35%; text-align: center; color: red;">&nbsp;</div>
	<table>
		<tr>
			<td>
				<s:textfield label="Annual Term Name" id="annTermName" required="true" name="at_name" style="width: 200px; height: 30px;"/>
				
				<s:submit onclick="saveAnnualTerm()" value="Save"/>
			</td>
		</tr>
	</table>

</body>
</html>