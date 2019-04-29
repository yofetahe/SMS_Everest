<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:set var="cd_id" value="cd_id"/>
<s:set var="prd" value="period"/>
<s:set var="wdays" value="week_day"/>
<s:set var="cl_id" value="cl_id"/>

<!-- <table><tr><td> -->
<%-- <s:select id="taid" list="teach_list" headerValue=" " headerKey="0" listValue="fullName" listKey="ta_id"  style="width: 100%" onchange="addScheduleProgram(this.value, '${prd}', '${wdays}', '${cd_id}', '${cl_id}')"/> --%>
<!-- </td></tr></table> -->

<select id="taid" onchange="addScheduleProgram(this.value, '${prd}', '${wdays}', '${cd_id}', '${cl_id}')" style="width: 100px; height: 25px">
	<option value="0"> </option>
	<c:forEach var="teach_list" items="${teach_list}" varStatus="stat">
		<option value="${teach_list.ta_id}">${teach_list.fullName}</option>
	</c:forEach>
</select>