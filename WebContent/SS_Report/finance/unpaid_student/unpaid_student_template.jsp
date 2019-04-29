<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript" src="js/sms_report.js"></script>
</head>
<body>
<div style="color: RED" id="errMsg"></div>
<div style="background-color: #f5f5f5; height: 40px;">
<table>
	<tr>
		<td id="col_month">
			<table cellpadding="0" cellspacing="0"><tr><td>
					<table><tr><td>
					<s:select id="class_id" label="Grade" list="grade_rslt" listKey="class_id" listValue="class_name" headerKey="0" headerValue="---" style="height: 25px; width: 100px;"/>
					</td></tr></table>
			</td><td>
					<table><tr><td>
					<s:select id="month_from" label="Month From" list="#{'1':'SEPTEMBER', '2':'OCTOBER', '3':'NOVEMBER', '4':'DECEMBER', '5':'JANUARY', '6':'FEBRUARY', '7':'MARCH', '8':'APRIL', '9':'MAY', '10':'JUNE', '11':'JULY', '12':'AUGUEST' }" headerKey="0" headerValue="---" style="height: 25px; width: 100px;"/>
					</td></tr></table>
			</td><td>&nbsp;</td><td>
					<table><tr><td>
					<s:select id="month_to" label="Month To" list="#{'1':'SEPTEMBER', '2':'OCTOBER', '3':'NOVEMBER', '4':'DECEMBER', '5':'JANUARY', '6':'FEBRUARY', '7':'MARCH', '8':'APRIL', '9':'MAY', '10':'JUNE', '11':'JULY', '12':'AUGUEST' }" headerKey="0" headerValue="---" style="height: 25px; width: 100px;"/>
					</td></tr></table>
			</td><td>
					<table>
					<s:select id="ac_year" label="Academic Year" list="acyear_list" headerKey="0" headerValue="---" style="height: 25px; width: 60px;"/>
					</table>
			</td></tr></table>
		</td>
		<td>
			<div onclick="generateUnpaidReport()" class="adminHeaderButton" style="cursor: pointer; width: 100px;"> GENERATE </div>
		</td>
	</tr>
</table>
</div>

<div id="unpaid_list">

</div>

</body>
</html>