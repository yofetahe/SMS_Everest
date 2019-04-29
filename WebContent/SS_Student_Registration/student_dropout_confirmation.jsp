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
	<s:set var="siid" value="si_id"/>
	<s:set var="fname" value="fname"/>
	<s:set var="mname" value="mname"/>
	<s:set var="gname" value="gname"/>
	<s:set var="dropout_status" value="dropout_status"/>
	
	<table border="0" style="background-color: #f5f5f5;" align="center" width="80%">
		<tr>
			<td colspan="2" align="center" style="color: RED; font-weight: bold;" height="40px;">
				Confirmation
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="50px;">
				Are you sure <s:property value="%{#fname}"/> <s:property value="%{#mname}"/> <s:property value="%{#gname}"/> <br/> dropout or leave the school?
			</td>
		</tr>
		<tr>
			<td align="center">
				<table><tr><td>
					<div onclick="cancleDropOut('${siid}', '${fname}', '${mname}', '${gname}', '${dropout_status}')">Cancle</div>
				</td></tr></table>
			</td>
			<td align="center">
				<table><tr><td>
					<div onclick="confirmDropOut('${siid}')">YES</div>
				</td></tr></table>
			</td>
		</tr>
	</table>

</body>
</html>