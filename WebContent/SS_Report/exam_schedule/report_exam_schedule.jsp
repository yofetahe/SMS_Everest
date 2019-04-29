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
	<div id="errMsg" style="color: RED;"></div>
	<table width="100%" cellpadding="0" cellspacing="0" style="height: 40px; background-color: #f5f5f5;">
		<tr>
			<td width="10%">
				<table>
				<s:select label="Quarter" id="at_id" list="sem_list" listKey="at_id" listValue="at_name" headerKey="0" headerValue="---"/>
				</table>
			</td>
			<td width="5%">&nbsp;</td>
			<td width="20%">
				<table>
				<s:select label="Exam Type" id="et_id" list="examTypeList" listKey="et_id" listValue="et_name" headerKey="0" headerValue="---"/>
				</table>
			</td>
			<td width="5%">&nbsp;</td>
			<td>
				<table>
				<s:submit value="GENERATE" onclick="generateExamScheduleReport()"/>
				</table>
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	
	<div id="report_rslt">&nbsp;</div>

</body>
</html>