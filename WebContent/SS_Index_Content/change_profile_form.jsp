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

	<table align="center">
		<tr>
			<td>
			
				<s:set var="oldUserName" value="oldUserName"/>
				<s:set var="name" value="loginUserInfo[0].name"/>
				<s:set var="ua_id" value="loginUserInfo[0].ua_id"/>
				
				<s:textfield label="Name" id="name" value="%{#name}" readonly="true" style="width: 200px; height: 25px;" required="true"/>
				<s:textfield label="Login User Name" id="username" style="width: 200px; height: 25px;" required="true"/>				
				<s:password label="Password" value="password" id="password" style="width: 200px; height: 25px;" required="true"/>				
				<s:password label="Comfirm New Password" value="password" id="comfirmPassword" style="width: 200px; height: 25px;" required="true"/>
				
				
			</td>
		</tr>
		<tr><td>
			<button onclick="validateUsername('${ua_id}', '${oldUserName}')" class="btn">Change</button>						
		</td></tr>
	</table>

</body>
</html>