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

</head>
<body>

	<div style="width: auto; height: 35px; background-color: #f5f5f5; padding-top: 15px;">Holiday Update Form</div>
	<div id="errMsg" style="color: red; text-align: center; width: 50%;"></div>
	<table>
		<tr>
			<td>
				<s:hidden id="h_id" name="h_id"/>
				<table style="padding-left: 44px;"><s:textfield label="Holiday Name" id="h_name" required="true" name="h_name" style="width: 200px; height: 30px;"/></table>
				<table><tr><td style="font-style: italic; width: 150px; text-align: right;">Holiday Date<span style="color:red">*</span>: </td>
								<td><sj:datepicker id="h_greg_date" name="h_greg_date"
										displayFormat="dd-MM-yy" readonly="true" 
										minDate="today" changeMonth="true" 
										changeYear="true" style="height: 25px; width: 200px"></sj:datepicker></td></tr></table>
				<table  style="padding-left: 60px;"><s:select label="Work Status" list="#{'Off':'Off', 'On':'On'}" required="true"  id="work_status" name="work_status" style="width: 206px; height: 30px;"/></table>
				<table  style="padding-left: 106px;"><s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" id="h_tatus" name="h_status" style="width: 206px; height: 30px;"/></table>
				
				<table><s:submit onclick="updateHoliday()" value="Update"/></table>
			</td>
		</tr>
	</table>

</body>
</html>