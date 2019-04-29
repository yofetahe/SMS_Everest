<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="class_name" value="class_name"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="menu_shadow" style="background-color: #f5f5f5; height: 35px; padding-top: 15px;  background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="addSubjectRel('${cl_id}', '${class_name}')"><img alt="click" src="images/next.gif" width="8px"> Back to Subject List</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 380px">
					<div style="color: GREEN; width: 400px;" align="center">*** NOTICE ***<br/> Once this relationship is created, it can't not be changed. Only the status will be updated. </div>
					<br/>
					<s:fielderror id="clSubRelError"/>
					<div id="errMsg" style="color: red; text-align: center; width: 50%;"></div>
					<table align="left"><tr><td>
					
						<s:select id="sub_id" label="Subject" required="true" headerValue="Select subject" headerKey="0" list="subject_rslt" listValue="sub_name" listKey="sub_id" style="height: 25px; width: 206px" />
						<s:select label="Added To the total Mark" id="addToTotal" required="true" list="#{'No':'No', 'Yes':'Yes'}" headerValue="---" headerKey="0" style="height: 25px; width: 206px"/>
						<s:select label="Convert to Grading" id="conToGrade" required="true" list="#{'NO':'No', 'YES':'Yes'}" headerValue="---" headerKey="0" style="height: 25px; width: 206px"/>						
						
					</td></tr>
					<tr><td>
						<button onclick="saveClassSubRel('${cl_id}')" class="btn">Update</button>						
					</td></tr></table>
				</div>
				
			</td>
		</tr>
	</table>

</body>
</html>