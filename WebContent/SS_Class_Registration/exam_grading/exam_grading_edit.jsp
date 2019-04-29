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
	
	<s:set var="subcl_id" value="subcl_id"/>
	<s:set var="sub_name" value="sub_name"/>
	<div>
		Subject: <s:property value="sub_name"/>
	</div>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 35px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
					<div style="color: #3d6e9f; cursor: pointer" onclick="backToGradeList('${subcl_id}')"> <img alt="click" src="images/next.gif" width="8px"> Back to Grading List</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				
				<div class="content_background" style="min-height: 440px">
	
					
					<div id="errMsg" style="color: RED"></div>
					<table>
						<tr>
							<td>
								<s:set var="escg_id" value="escg_id"/>
								<s:textfield label="Grade Value" id="eg_id" readonly="true" name="eg_value" style="height: 30px; width: 200px"/>
								<s:textfield label="Grade From" id="grade_from" readonly="true" name="grade_from" style="height: 30px; width: 200px"/>
								<s:textfield label="Grade To" id="grade_to" readonly="true" name="grade_to" style="height: 30px; width: 200px"/>
								<s:select label="Status" id="escg_status" list="#{'A':'Active', 'I':'Inactive'}" name="escg_status" style="height: 30px; width: 205px"/>
								
							</td>
						</tr>
						<tr><td>
							<button onclick="updateSubGrade('${subcl_id}', '${sub_name}', '${escg_id}')" class="btn">Update</button>						
						</td></tr>
					</table>
				
				</div>
			
			</td>
		</tr>
	</table>

</body>
</html>