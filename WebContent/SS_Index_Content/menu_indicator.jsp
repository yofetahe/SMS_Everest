<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu_Indicator</title>
</head>
<body>

	<table cellpadding="0" cellspacing="0" width="5px" border="0" style="padding-top: 0px">
		<tr>
			<td height="50px" valign="middle">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"student\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color:transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"teacher\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"subject\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"class\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"classSchedule\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"attendance\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"examrslt\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"examqst\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"cocuract\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"payment\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-top-style: solid; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"report\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
		<tr>
			<td height="50px" valign="middle" style="border-bottom-style: solid; border-top-style: solid; border-bottom-width: thin; border-top-width: thin; border-color: transparent;">
				<s:set var="mtype" value="menutype"/>
				<s:if test="%{#mtype == \"admin\"}">
					<div style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</div>
				</s:if>
				<s:else><div style="background-color: transparent; width: 3px; height: 40px;">&nbsp;</div></s:else>
			</td>
		</tr>
	</table>

</body>
</html>