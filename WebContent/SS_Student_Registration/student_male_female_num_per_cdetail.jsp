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

<s:set var="class_id" value="class_id"/>

<table width="100%" cellpadding="0" cellspacing="0" style="border-bottom: thin; border-bottom-color: silver; border-bottom-style: solid; border-bottom-width: thin;">
	<tr>
		<td style="height: 15px;">
			<div style="color: #3d6e9f; cursor: pointer;" onclick="listAllClassStudents('${class_id}')">
			<img alt="arrow" src="images/next.gif" style="width: 6px;"> List All <s:property value="class_name"/> Students
			</div>
		</td>
	</tr>
</table>

<s:set var="cd" value="0"/>
<s:set var="num" value="num_of_class_detail"/>
<table cellpadding="0" cellspacing="14">
	<s:iterator begin="1" end="3">
	<tr>
		<s:iterator begin="1" end="4">
			<td>
			
				<s:if test="%{#cd < #num}">
				<div class="formboarder" style="width: 190px; height: 150px;">
				
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr><td colspan="2" align="center" height="60px;">
					
						<div class="menu_shadow">
						<span style="color: #3d6e9f; font-weight: bold;"><s:property value="class_name"/> <s:property value="class_detail[#cd].cd_name"/></span> <br/>
						Total Number: <s:property value="stud_rslt[#cd].total_number"/>
						</div>
						
					</td></tr>
					<tr><td align="center" style="padding-top: 10px;">
					
						<img alt="male" src="images/male_default.jpeg" width="55px"> <br/>
						<s:set var="male" value="stud_rslt[#cd].no_male"/>
						<s:property value="%{#male}"/>
						
					</td><td align="center" style="padding-top: 10px;">
					
						<img alt="female" src="images/female_default.jpeg" width="55px"> <br/>
						<s:set var="female" value="stud_rslt[#cd].no_female"/>
						<s:property value="%{#female}"/>
						
					</td></tr></table>
					
				</div>
				</s:if>
			</td>
			<s:set var="cd" value="%{#cd+1}"/>
		</s:iterator>
	</tr>
	</s:iterator>
</table>

</body>
</html>