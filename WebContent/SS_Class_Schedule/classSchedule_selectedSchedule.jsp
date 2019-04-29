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
	
	<s:set var="prd" value="period"/>
	<s:set var="wday" value="week_day"/>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<s:if test="teachSubSelected[0].checkExistance != \"true\"">
	<s:iterator status="stat" value="teachSubSelected">
		<div style="height: auto;">
			<div id="title">Subject</div>
			<div id="content">
				<s:property value="teachSubSelected[#stat.index].sub_name"/>
			</div>
			
			<div id="title">Teacher</div>
			<div id="content">
				<s:property value="teachSubSelected[#stat.index].fullName"/>
			</div>
			
			<div id="title" style="cursor: pointer; width: auto; background-color: #3d6e9f; color: #fff;" onclick="editSelectedSchedule_assign('${prd}', '${wday}', '${cl_id}', '${cd_id}')">Edit</div>
		</div>
	</s:iterator>
	</s:if>
	<s:else>
		<div style="height: auto;">
			Assigned
		</div>
	</s:else>
	
</body>
</html>