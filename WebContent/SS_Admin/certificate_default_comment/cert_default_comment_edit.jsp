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
		
	<div class="formtitle">Update Comment</div>
						
	<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
	<div style="color: #ff0000; width: 100%; text-align: center;"><s:fielderror id="errMsg"/></div>
		
	<table>
		<tr>
			<td>
				<s:set var="edc_id" value="edc_id"/>					
				<s:textarea label="Comment" required="true" id="edc_com" name="edc_content" rows="5" cols="23"/>
				<s:textfield label="Rank From" required="true" id="rank_from" name="rank_from" style="width: 200px; height: 30px;"/>
				<s:textfield label="Rank To" required="true" id="rank_to" name="rank_to" style="width: 200px; height: 30px;"/>						
				<s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" id="edc_status" name="edc_status" style="width: 206px; height: 30px;"/>
				
			</td>
		</tr>
		<tr><td>
			<button onclick="updateDefaultComment('${edc_id}')">Update</button>						
		</td></tr>
	</table>

</body>
</html>