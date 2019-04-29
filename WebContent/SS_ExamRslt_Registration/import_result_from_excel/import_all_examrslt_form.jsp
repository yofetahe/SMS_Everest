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
	
	<div style="color: RED;"><s:actionmessage/></div>
	<div id="errMsg" style="color: RED; padding-left: 240px;"></div>
	<table cellpadding="0" cellspacing="0" style="width: 80%">
		<tr><td align="right" style="padding-right: 10px; height: 40px;">
		
			<table cellpadding="0" cellspacing="0"><tr><td style="font-style: italic;">
				<s:textfield label="File Path" id="file_path" style="height: 20px; width: 198px;"/>
<!-- 				File Path:		 -->
			</td><td> 
<!-- 				<input id="file_path" type="file" id="filepath"/>  -->
			</td></tr></table>
			
			
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 30px;">
			
			<table cellpadding="0" cellspacing="0">
				<s:select label="Academic Year" id="academicYear" list="acyear_list" headerKey="0" headerValue="-" key="acyear" style="width: 204px; height: 25px"/>
			</table>
		
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 30px;">
		
			<table cellpadding="0" cellspacing="0"> 
			<s:select label="Quarter" id="at_id" list="sem_list" headerKey="0" headerValue="---" listKey="at_id" listValue="at_name" onchange="classRoomListForImportFromExcel(this.value)" style="height: 25px; width: 204px;"/> 
			</table>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 30px;">
		
			<div id="classList">  </div>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 30px;">
		
			<div id="grdList">  </div>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 30px;">
		
			<div id="subList">  </div>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 10px;">
		
			<table cellpadding="0" cellspacing="0" id="impType" style="display: none;" align="right">
				<tr><td style="height: 30px;">
					<table cellpadding="0" cellspacing="0" align="right">
					<!-- '2':'Import One Column',  -->
					<s:radio label="Import Type" id="importType" name="importType"  list="#{'1':'Import All Columns', '3':'Select Columns'}" onchange="checkImportType(this.value)"></s:radio>
					</table>
				</td></tr>
				<tr><td style="height: 30px;">
					<table cellpadding="0" cellspacing="0" align="right">
					<s:textfield label="Sheet" id="sheetNum" style="height: 18px; width: 198px;"/>
					</table>				
				</td></tr>
				<tr><td style="height: 30px;">
					<table cellpadding="0" cellspacing="0" align="right">
					<s:textfield label="Row Number (x - y)" id="rowNum" style="height: 18px; width: 198px;"/>
					</table>				
				</td></tr>
			</table>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 30px;">
		
			<table cellpadding="0" cellspacing="0" id="colNumTbl" style="display: none;" align="right"> 
			<s:textfield label="Column Number (x, y, z, ...)" id="columnNum" style="height: 18px; width: 198px;"/>
			</table>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 30px;">
		
			<div id="examList">  </div>
		</td></tr>
		<tr><td align="right" style="padding-right: 30px; height: 30px;">
			&nbsp;
		</td></tr>
	</table>
	
	<table cellpadding="0" cellspacing="0" style="width: 100%">
		<tr><td align="center" style="height: 30px;">
			<table cellpadding="0" cellspacing="0" align="center"><tr><td>
				<div class="normal_btn_active" onclick="updateExamResultFromExcel()" align="right">
					Update From Excel
				</div>
			</td><td>&nbsp;</td><td>
				<div class="normal_btn_active" onclick="importExamResultFromExcel()" align="right">
					Import From Excel
				</div>
			</td><td>&nbsp;</td><td>
				<div class="normal_btn_active" onclick="checkStudentNameMarkListFromExcel()" align="right">
					Check Student Name
				</div>
			</td></tr></table>
		</td></tr>
	</table>

</body>
</html>