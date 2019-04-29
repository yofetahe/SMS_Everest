<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head/>
<sj:head/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
	
	<s:set var="clb_id" value="clb_id"/>
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 40px; padding-top: 2px;">
				Activity Edit Form
			</td>
		</tr>
	</table>

	<table border="0">
		<tr>
			<td align="right">
				<div id="errMsg" style="color: #ff0000; width: auto; text-align: center;"></div>
				<div class="formboarder">
					<s:set var="ca_id" value="ca_id"/>
					<table><s:textfield id="act_title" label="Activity Title" name="ca_activity" style="height: 25px; width: 200px;"/></table>
					<table><s:textarea id="act_desc" label="Activity Description" name="ca_activity_desc" rows="10" style="height: 25px; width: 200px;"/></table>
					<table><tr><td style="font-style: italic;">Date From:</td><td>
						<sj:datepicker label="Date From" id="date_from" name="ca_datefrom" 
							displayFormat="yy-mm-dd" readonly="true" 
							minDate="today" changeMonth="true" 
							changeYear="true" style="height: 20px; width: 200px"></sj:datepicker>
					</td></tr></table>
					<table><tr><td style="font-style: italic;">Date to:</td><td>
						<sj:datepicker label="Date to" id="date_to" name="ca_dateto" 
							displayFormat="yy-mm-dd" readonly="true" 
							minDate="today" changeMonth="true" 
							changeYear="true" style="height: 20px; width: 200px"></sj:datepicker>
					</td></tr></table>
					<table><s:select id="acyear" label="Academic Year" list="acYearList" name="academic_year" headerKey="0" headerValue="---" style="height: 25px; width: 205px;"></s:select> </table>
					<table><s:select id="ca_status" label="Activity Status" list="#{'A':'Active', 'I':'Inactive'}" name="ca_status" style="height: 25px; width: 200px;"/> </table>
					
					<s:submit onclick="updateActivities('${ca_id}', '${clb_id}')" align="center" value="Update"/>
					
				</div>
			</td>
		</tr>
	</table>

</body>
</html>