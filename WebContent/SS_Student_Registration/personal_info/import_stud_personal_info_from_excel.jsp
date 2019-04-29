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
	
	
	<div class="info" style="width: 65%">
		Notice: <br/>
		* The column order MUST BE -> 1. Student Name, 2. Mother Name, 3. Sex, 4. DOB, 5. POB, 6. Nationality, 7. One or two emergency contact name separated by a comma(,), 7. Relationship of Emergency contact accordingly, 8. Mobile Number of Emergency contact accordingly <br/>
		* <b>DOB</b> must be changed to text format on the excel file using given formal on the next cell of DOB --> =TEXT(cell-of-dob, "dd/mm/yyyy")<br/>
		* The excel file must be with a format of Excel 97-2003 Workbook<br/>
		* The file path must be ended with .xls
	</div>
	
	
	<div style="color: RED;"><s:actionmessage/></div>
	<div id="errMsg" style="color: RED; padding-left: 240px;"></div>
	
	<table cellpadding="0" cellspacing="0" style="min-width: 50%">
		<tr><td align="right" style="height: 40px; padding-right: 10px;">
			
			<table cellpadding="0" cellspacing="0"> 
			<s:textfield label="File Path" id="file_path" required="true" class="inputtext-format"/> 
			</table>
			
<!-- 			<table cellpadding="0" cellspacing="0"><tr><td style="font-style: italic;">				 -->
<!-- 				File Path:		 -->
<!-- 			</td><td>  -->
<!-- 				<input id="file_path" type="file" id="filepath"/>  -->
<!-- 			</td></tr></table> -->
			
		</td></tr>		
		<tr><td align="right" style="height: 40px; padding-right: 10px;">
			
			<table cellpadding="0" cellspacing="0" align="right">
				<s:textfield label="Sheet" id="sheetNum" required="true" class="inputtext-format"/>
			</table>
						
		</td></tr>
		<tr><td align="right" style="height: 40px; padding-right: 10px;">
		
			<table cellpadding="0" cellspacing="0" align="right">
				<s:textfield label="Row Number (x - y)" id="rowNum" required="true" class="inputtext-format"/>
			</table>				
				
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 40px;">
		
			<table cellpadding="0" cellspacing="0" id="colNumTbl" align="right"> 
				<s:textfield label="Column Number (x, y, z, ...)" id="columnNum" required="true" class="inputtext-format"/>
			</table>
			
		</td></tr>
		<tr><td align="right" style="padding-right: 10px; height: 40px;">
			
				<div class="btn" onclick="importStudPersonalInfoFromExcel()" align="right">
					Import Excel Data
				</div>
			
		</td></tr>
	</table>

</body>
</html>