<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
	 
	 <s:set var="ua_id" value="ua_id"/>
	 <s:set var="ti_id" value="ti_id"/>
	 <s:set var="ti_id" value="indx"/>
	 <s:set var="utId" value="utId"/>
	 <s:set var="name" value="name"/>
	 
	 <s:set var="formType" value="formType"/>
	 
	 <div style="color: RED; width: 100%; text-align: center;"><s:actionmessage/></div>
	 
	 <table style="padding-left: 80px;">
	 	<tr>
		 	<td style="color: #3d6e9f; font-size: 20px;" align="center">
		 		Are you sure!<br/> you wan to reset <span style="font-variant: small-caps; color: RED;"><s:property value="name"/></span> password.
		 	</td>
	 	</tr>
	 	<tr>
		 	<td align="center" style="border-top-color: black; border-top-style: solid; border-top-width: thin; padding-top: 10px;">
		 		<input type="button" value="RESET" onclick="resetUsersPassword('${ua_id}', '${formType}', '${name}')" style="width: 90px; height: 40px; cursor: pointer;">
		 	</td>
	 	</tr>
	 </table>

</body>
</html>