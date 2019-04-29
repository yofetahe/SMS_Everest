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
	<s:set var="eg_id" value="eg_id"/>
	<div class="formtitle">Save Grade</div>
						
	<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
	<div style="color: #ff0000; width: 100%; text-align: center;"><s:actionmessage id="errMsg"/></div>
					
	<table>
		<tr>
			<td>							
				<s:textfield label="Grade" required="true" id="eg_value" name="eg_value" style="width: 200px; height: 30px;"/>
				<s:textfield label="Grade Description" required="true" id="eg_desc" name="eg_desc" style="width: 200px; height: 30px;"/>		
				<s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" id="eg_status" name="eg_status" style="width: 206px; height: 30px;"/>
				
				
			</td>
		</tr>
		<tr><td>
			<button onclick="updateGrade('${eg_id}')">Update</button>						
		</td></tr>
	</table>

</body>
</html>