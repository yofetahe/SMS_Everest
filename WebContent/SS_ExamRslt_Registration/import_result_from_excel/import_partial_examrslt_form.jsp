<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
</head>
<body>

	<div style="color: RED;"><s:actionmessage/></div>
	
	<div id="errMsg" style="color: RED;"></div>
	
	<table cellpadding="0" cellspacing="0">
		<tr><td align="right" style="padding-right: 30px; height: 30px;">
		
			<table cellpadding="0" cellspacing="0"> 
			<s:textfield label="File Path" id="file_path" style="height: 18px; width: 198px;"/> 
			</table>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 30px; height: 30px;">
		
			<table cellpadding="0" cellspacing="0"> 
			<s:select label="Quarter" id="at_id" list="sem_list" headerKey="0" headerValue="---" listKey="at_id" listValue="at_name" onchange="classRoomListForImportFromExcel(this.value)" style="height: 25px; width: 204px;"/> 
			</table>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 30px; height: 30px;">
		
			<div id="classList">  </div>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 30px; height: 30px;">
		
			<div id="grdList">  </div>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 30px; height: 30px;">
		
			<div id="subList">  </div>
			
		</td></tr>
	</table>
	
	<div>&nbsp;</div>
	
	<div class="normal_btn_active" onclick="importAllExamResultFromExcel()">
		Import From Excel
	</div>

</body>
</html>