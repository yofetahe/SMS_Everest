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

	<s:set var="siid" value="si_id"/>
	<s:set var="fname" value="fname"/>
	<s:set var="mname" value="mname"/>
	<s:set var="gname" value="gname"/>
	<s:set var="dropout_status" value="dropout_status"/>
	<div class="formboarder" style="padding-top: 10px; padding-bottom: 10px;">
	    <s:set var="dropout_status" value="dropout_status_rslt"/>
		<s:if test="%{#dropout_status == true}">
			<table align="center" width="80%">
				<tr>
					<td align="center" style="height: 40px;">							
						Enrollment Status: Leave/Drop Out						
					</td>
				</tr>
			</table>
		</s:if>
		<s:else>
			<table align="center" width="80%">
				<tr>
					<td align="center" style="height: 40px;">							
						Enrollment Status: Active				
					</td>
				</tr>
				<tr>
					<td>
						<div align="center" class="btn" onclick="studentDropOut('${siid}', '${fname}', '${mname}', '${gname}', '${dropout_status}')">Leave/Drop Out</div>
					</td>
				</tr>
			</table>
		</s:else>
	</div>
</body>
</html>