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

	<div id="saveEditForm" class="formboarder">	
					
		<div class="formtitle">Update Assignment Form</div>
		
		<s:set var="thra_id" value="techAssignmentInfo[0].thra_id"/>
		<s:set var="ti_id" value="techAssignmentInfo[0].ti_id"/>
		<s:set var="tname" value="techAssignmentInfo[0].tname"/>
		<s:set var="cl_id" value="techAssignmentInfo[0].class_id"/>
		<s:set var="cl_name" value="techAssignmentInfo[0].class_name"/>
		<s:set var="cd_id" value="techAssignmentInfo[0].cd_id"/>
		<s:set var="cd_name" value="techAssignmentInfo[0].cd_name"/>
		<s:set var="thra_status" value="techAssignmentInfo[0].thra_status"/>
					
		<div id="eMsg" style="color: #ff0000; width: 100%; text-align: center;"></div>
		<div style="color: #ff0000; width: 100%; text-align: center;"><s:fielderror id="errMsg"/></div>
		<table align="center" style="padding-top: 20px; padding-bottom: 20px;"><tr><td>
			<s:textfield label="Teacher" id="tiid" value="%{#tname}" readonly="true" style="width: 200px; height: 30px;"/>
<%-- 			<s:select label="Teachers" id="tiid" list="teacherList" listKey="ti_id" listValue="tname" headerValue="%{#tname}" headerKey="%{#ti_id}" style="width: 200px; height: 30px;"/> --%>
			<s:select label="Class" id="clid" list="classList" listKey="cl_id" listValue="class_name" headerValue="%{#cl_name}" headerKey="%{#cl_id}" onchange="filterClassDetail(this.value)" style="width: 206px; height: 30px;"/>
			<s:select label="Class Detail" id="cdid" list="#{'':''}" headerValue="%{#cd_name}" headerKey="%{#cd_id}" style="width: 206px; height: 30px;"/>
			<s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" id="thra_status" name="thra_status" style="width: 206px; height: 30px;"/>
			
		</td></tr>
		<tr><td>
			<button onclick="updateAssignment('${thra_id}')">Update Assignment</button>						
		</td></tr></table>
					
	</div>

</body>
</html>