<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<script src="../js/sms_examrslt_registration.js" type="text/javascript"></script>

</head>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center">
	<tr>
		<td valign="top" align="center" width="55%">
			
			<div style="width: 100%; height: 30px; padding-top: 10px; background-color: #f5f5f5; text-align: center; font-weight: bold;">
				Import All/Partial Student Exam Results from Excel File
			</div>
			
			<div style="color: GREEN; padding-top: 5px; padding-bottom: 5px;">
			Mandatory Notice: <br/>
			- The exam's name must spell exactly they exist on the system.<br/>
			- The file must start from the first line of the excel file. <br/>
			- The file must be saved in Excel 97-2003 Workbook Format. <br/>
			- The file path must end with the file extension - .xls <br/>
			- Row Number - must include student name rows list only. <br/>
			- Column Number - must include exam type columns only.
			</div>
			
			<div style="width: 100%" id="importAll" align="left">
			
				<s:include value="import_all_examrslt_form.jsp"/>
				
			</div>
			
		</td><td>&nbsp;</td>
		<td width="40%" valign="top" align="center">
			
			<div style="width: 100%" id="unmatchStudNameList" align="left">
			
				
				
			</div>
			
		</td>		
	</tr>
</table>

</body>
</html>