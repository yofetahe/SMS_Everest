<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body style="padding-top: 20px;">

	<div class="info" style="width: 65%">
		Notice: <br/>
		* To insert <b>FATHER</b> emergency contact information, Student full name(first name, middle name and grand father name) and father mobile number is required accordingly.<br/>
		* To insert <b>MOTHER</b> emergency contact information, Student full name(first name, middle name and grand father name), mother name and mother mobile number is required accordingly.<br/>
	</div>
	
	<div style="color: RED;"><s:actionmessage/></div>
	<div id="errMsg" style="color: RED; padding-left: 240px;"></div>
	
	<s:set var="cl_id" value="class_id"/>
	<s:set var="cd_id" value="cd_id"/>
	
	<table cellpadding="0" cellspacing="0" style="min-width: 50%">
		<tr><td align="right" style="height: 40px; padding-right: 10px;">
			
			<table cellpadding="0" cellspacing="0"><tr><td style="font-style: italic;">
				<%-- <s:textfield label="File Path" id="file_path" style="height: 20px; width: 198px;"/> --%>
				File Path:		
			</td><td> 
				<input id="file_path" type="file" id="filepath"/> 
			</td></tr></table>
			
		</td></tr>		
		<tr><td align="right" style="padding-right: 10px;">
			<table cellpadding="0" cellspacing="0" id="impType" align="right">
				
				<tr><td style="height: 40px;">
					<table cellpadding="0" cellspacing="0" align="right">
					<s:textfield label="Sheet" id="sheetNum" style="height: 25px; width: 198px;"/>
					</table>				
				</td></tr>
				<tr><td style="height: 40px;">
					<table cellpadding="0" cellspacing="0" align="right">
					<s:textfield label="Row Number (x - y)" id="rowNum" style="height: 25px; width: 198px;"/>
					</table>				
				</td></tr>
			</table>
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 40px;">
			<table cellpadding="0" cellspacing="0" id="colNumTbl" align="right"> 
			<s:textfield label="Column Number (x, y, z, ...)" id="columnNum" style="height: 25px; width: 198px;"/>
			</table>
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 40px;">
			<table cellpadding="0" cellspacing="0" id="colNumTbl" align="right"> 
			<s:select label="Relationship" id="relationship" list="#{'0':'-', 'Father':'Father', 'Mother':'Mother'}" style="height: 30px; width: 202px;"/>
			</table>
		</td></tr>
		<tr><td align="right" style="padding-right: 30px; height: 30px;">
			&nbsp;
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 30px;">
			<table cellpadding="0" cellspacing="0"><tr><td>
				&nbsp;
			</td><td>&nbsp;</td><td>
				<div class="normal_btn_active" onclick="importStudContactInfoFromExcel('%{cl_id}', '%{cd_id}')" align="right">
					Import From Excel
				</div>
			</td></tr></table>
		</td></tr>
	</table>

</body>
</html>