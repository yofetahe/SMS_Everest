<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
</head>
<body>

<div style="width: 100%; padding-left:0px;">
	
	<s:set var="success_counter" value="success_counter"/>
	<s:set var="failur_counter" value="failur_counter"/>
	<s:set var="doesntexist_counter" value="doesntexist_counter"/>

	<s:if test="%{#success_counter > 0}">
		<div style="height: 40px; padding-top: 10px;">
			<table cellpadding="0" cellspacing="0" style="border-bottom: thin; border-bottom-color: #3d6e9f; border-bottom-style: solid; border-bottom-width: thin;">
			<tr><td style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</td><td style="padding-left: 5px;">
			Students name list whom their parents successful get the email message.
			</td></tr></table>
		</div>
		<div style="color: #3d6e9f; width: 100%; padding-top: 4px; min-height: 40px;" align="left">	
			<table>
				<tr>
					<td>
					<s:iterator status="stat" value="successfulEmail">		
						<b>*</b> <s:property value="successfulEmail[#stat.index]"/>						
					</s:iterator>
					</td>
				</tr>
			</table>
		</div>		
		<br/>
	</s:if>
	
	<s:if test="%{#failur_counter > 0}">
		<div style="height: 40px; padding-top: 10px;">
			<table cellpadding="0" cellspacing="0" style="border-bottom: thin; border-bottom-color: #3d6e9f; border-bottom-style: solid; border-bottom-width: thin;">
			<tr><td style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</td><td style="padding-left: 5px;">
			Students name list whom their parents <b>DO NOT</b> get the email message successfully. <br/>
			Please take the list and email individually.
			</td></tr></table>
		</div>
		<div style="color: #3d6e9f; width: 100%; padding-top: 4px; min-height: 40px;" align="left">	
			<table>
				<tr>
					<td>
					<s:iterator status="stat" value="unsuccessfulEmail">		
						<b>*</b> <s:property value="unsuccessfulEmail[#stat.index]"/>						
					</s:iterator>
					</td>
				</tr>
			</table>
		</div>	
		<br/>
	</s:if>
	
	<s:if test="%{#doesntexist_counter > 0}">
		<div style="height: 40px; padding-top: 10px;">
			<table cellpadding="0" cellspacing="0" style="border-bottom: thin; border-bottom-color: #3d6e9f; border-bottom-style: solid; border-bottom-width: thin;">
			<tr><td style="background-color: #3d6e9f; width: 4px; height: 40px;">&nbsp;</td><td style="padding-left: 5px;">
				Students name list whom their parents <b>DO NOT</b> have an email address
			</td></tr></table>
		</div>
		<div style="color: #3d6e9f; width: 100%; padding-top: 4px; min-height: 40px;" align="left">
			<table>
				<tr>
					<td>
					<s:iterator status="stat" value="emailDoesntExist">
						<b>*</b> <s:property value="emailDoesntExist[#stat.index]"/> 						
					</s:iterator>
					</td>
				</tr>
			</table>
		</div>
	</s:if>
</div>
</body>
</html>