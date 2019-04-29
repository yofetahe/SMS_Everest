<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
</head>
<body>

	<s:set var="cl_id" value="croomBean.cl_id"/>
	<s:set var="sub_counter" value="0"/>
	
	<div id="errorMessage" class="errorMessage"></div>

	<table>
		<s:iterator status="stat" value="assignPeriodList">
			<tr>
				<td valign="middle"> <table> <s:property value="assignPeriodList[#stat.index].subjectBean.sub_name"/> </table> </td>
				<td valign="middle">
					<s:set var="sub_id" value="assignPeriodList[#stat.index].subjectBean.sub_id"/>
					<s:set var="subname" value="assignPeriodList[#stat.index].subjectBean.sub_name"/>
					<s:set var="periodperweek" value="assignPeriodList[#stat.index].period_per_week"/>
					<s:set var="period" value=""/>
					<s:if test="%{#periodperweek != \"0\"}"><s:set var="period" value="%{#periodperweek}"/></s:if>
<%-- 					<table> <s:textfield label="" id="${subname}" required="true" value="%{#period}" onchange="addSubjectPeriodAllotment(this.value, '${sub_id}')" class="listbox-format"/> </table>  --%>
					<input type="text" id="${subname}" value="${period}" onchange="addSubjectPeriodAllotment(this.value, '${sub_id}')" class="listbox-format"/>
				</td>
			</tr>
			<s:set var="sub_counter" value="%{#sub_counter + 1}"/>
		</s:iterator>
		<s:set var="ppw" value="assignPeriodList[0].period_per_week"></s:set>
		<s:if test="%{#ppw == \"0\"}">
			<tr>
				<td colspan="2" align="center">
				<button onclick="saveClassPeriodAllotment('${sub_counter}', '${cl_id}')" class="btn">Save</button>
				</td>
			</tr>
		</s:if>
	</table>

</body>
</html>