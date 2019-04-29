<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head />
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script type="text/javascript" src="js/sms_report.js"></script>
</head>
<body>
<div style="color: RED" id="errMsg"></div>
<div style="background-color: #f5f5f5; height: 40px;">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<table>
				<s:select id="col_type" label="Collection Type" list="#{'1':'Daily Collection', '2':'Monthly Collection'}" headerKey="0" headerValue="---" style="height: 25px; width: 150px;" onchange="selectCollectionReportType(this.value)" />
				</table>
			</td>
			<td id="col_date" style="display: none;">
				<table><tr><td>Date From: </td><td>
				<sj:datepicker id="coldate_from" name="disaction_date" displayFormat="yy-mm-dd" readonly="true" 
								maxDate="today" changeMonth="true" changeYear="true" style="height: 20px; width: 160px" ></sj:datepicker>
				</td><td>&nbsp;</td>
				<td>Date To: </td><td>
				<sj:datepicker id="coldate_to" name="disaction_date" displayFormat="yy-mm-dd" readonly="true" 
								maxDate="today" changeMonth="true" changeYear="true" style="height: 20px; width: 160px" ></sj:datepicker>
				</td></tr></table>
			</td>
			<td id="col_month" style="display: none;">
				<table cellpadding="0" cellspacing="0"><tr><td>
					<table><tr><td>
					<s:select id="colmonth_from" label="Month From" list="#{'1':'SEPTEMBER', '2':'OCTOBER', '3':'NOVEMBER', '4':'DECEMBER', '5':'JANUARY', '6':'FEBRUARY', '7':'MARCH', '8':'APRIL', '9':'MAY', '10':'JUNE', '11':'JULY', '12':'AUGUEST' }" headerKey="0" headerValue="---" style="height: 25px; width: 100px;"/>
					</td></tr></table>
				</td><td>&nbsp;</td><td>
					<table><tr><td>
					<s:select id="colmonth_to" label="Month To" list="#{'1':'SEPTEMBER', '2':'OCTOBER', '3':'NOVEMBER', '4':'DECEMBER', '5':'JANUARY', '6':'FEBRUARY', '7':'MARCH', '8':'APRIL', '9':'MAY', '10':'JUNE', '11':'JULY', '12':'AUGUEST' }" headerKey="0" headerValue="---" style="height: 25px; width: 100px;"/>
					</td></tr></table>
				</td><td>
					<table>
					<s:select id="acyear" label="Academic Year" list="acyear_list" headerKey="0" headerValue="---" style="height: 25px; width: 60px;"/>
					</table>
				</td></tr></table>
			</td>
			<td>
				<div onclick="generateCollectionReport()" class="adminHeaderButton" style="cursor: pointer; width: 100px;"> GENERATE </div>
			</td>
		</tr>
	</table>
</div>

<div id="col_report">  </div>

</body>
</html>