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

	<div style="width: auto; height: 35px; background-color: #f5f5f5; padding-top: 15px;">School Event Update Form</div>
	<div id="errMsg" style="color: red; text-align: center; width: 50%;"></div>
	<table>
		<tr>
			<td>
				<s:hidden id="se_id" name="se_id"/>
				<table style="padding-left: 10px;"><s:textfield label="School Event Name" id="se_name" required="true" name="se_name" style="width: 200px; height: 30px;"/></table>
				<table style="padding-left: 20px;"><s:textarea label="Event Description" id="se_desc" required="true" name="se_description" style="width: 200px;" rows="5"></s:textarea></table>				
				<table><tr><td style="font-style: italic; width: 150px; text-align: right;">School Event Date<span style="color:red">*</span>: </td>
								<td><sj:datepicker id="se_greg_date" name="se_greg_date"
										displayFormat="dd-MM-yy" readonly="true" 
										minDate="today" changeMonth="true" 
										changeYear="true" style="height: 25px; width: 200px"></sj:datepicker></td></tr></table>
				<table style="padding-left: 98px;"><s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" id="se_status" required="true" name="se_status" style="width: 206px; height: 30px;"/></table>
				
				<table style="padding-left: 154px;">		
					<s:submit onclick="updateSchoolEvent()" value="Update"/>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>