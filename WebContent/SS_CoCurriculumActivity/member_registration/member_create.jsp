<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

</head>
<body>

	<s:set var="clbname" value="clbname"/>
	<s:set var="clbid" value="clbid"/>
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 40px; padding-top: 2px;">
				Club Members Add Form
			</td>
		</tr>
		<tr>
			<td style="padding-top: 30px;">				
				<table><tr><td>
					<div style="color: #ff0000"><s:fielderror id="errorMsg"></s:fielderror></div>
					<div id="errMsg" style="color: #ff0000; width: auto; text-align: center;"></div>
					<div class="formboarder">
						<table style="padding-top: 20px; padding-bottom: 20px;"><tr><td align="right">
						
						<table><s:select label="Class List" id="cl_id" list="class_rslt" onchange="getClassDetailList(this.value)" listKey="cl_id" listValue="class_name" headerKey="0" headerValue="---" style="height: 25px; width: 205px;"/></table>
						<table id="cdlist"><s:select label="Class Detail" list="#{'':''}" id="cd_id" onchange="getStudentListUnderSelectedClass(this.value)" headerKey="0" headerValue="Please select grade detail" style="height: 25px; width: 205px;"/></table>
						<table id="studlist"><s:select label="Student List" list="#{'':''}" id="studId" headerKey="0" headerValue="Please select student" style="height: 25px; width: 205px;"/></table>
						<table><s:textarea label="Reason to join" id="rsn" name="cm_reasontojoin" cols="10" rows="15" style="height: 25px; width: 200px;"/></table>
						<table><s:textarea label="Performance Evaluation" id="pereval" name="cm_evaluation" cols="10" rows="15" style="height: 25px; width: 200px;"/></table>
						
						<table><s:submit onclick="saveClubMember('${clbid}')" value="Save"/></table>
						
						</td></tr></table>
					</div>
				</td></tr></table>
			</td>
		</tr>
	</table>

</body>
</html>