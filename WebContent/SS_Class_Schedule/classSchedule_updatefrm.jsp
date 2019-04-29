<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>

<s:set var="prd" value="period"/>
<s:set var="wdays" value="week_day"/>
<s:set var="cl_id" value="cl_id"/>
<s:set var="cd_id" value="cd_id"/>
<s:set var="cs_id" value="cs_id"/>
<s:set var="ac_year" value="academic_year"/>

<div id="title">Subject</div>
<div id="content"> 
<!-- 	<table><tr><td> -->
<%-- 		<s:select id="subid" list="clSubRelList" listKey="sub_id" headerValue=" " headerKey="0" listValue="sub_name" style="width: 100px" onchange="teacherForSelectedSub(this.value, '${prd}', '${wdays}', '${cl_id}', '${cd_id}')"/> --%>
<!-- 	</td></tr></table> -->
	
	<select id="subid" onchange="teacherForSelectedSub(this.value, '${prd}', '${wdays}', '${cl_id}', '${cd_id}')" style="width: 100px">
		<option value="0"> </option>
		<c:forEach var="clSubRelList" items="${clSubRelList}">
			<option value="${clSubRelList.sub_id}">${clSubRelList.sub_name}</option>
		</c:forEach>
	</select>
	
</div>
										
<div id="title">Teacher</div>
<div id="content">
	<div  id="t<s:property value="%{#wdays+''+#prd}"/>">
		<table><tr><td>
			<s:select id="taid" list="teach_list" headerValue=" " headerKey="0" listValue="sub_name" listKey="ta_id" style="width: 100px"/>
		</td></tr></table>
	</div>
</div>
<div id="title" style="cursor: pointer; width: auto; background-color: #3d6e9f; color: #fff" onclick="updateSelectedSchedule('${prd}', '${wdays}', '${cl_id}', '${cd_id}', '${cs_id}', '${ac_year}')">Update</div>

</body>
</html>