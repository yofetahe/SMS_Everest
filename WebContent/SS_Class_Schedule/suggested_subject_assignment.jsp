<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<style type="text/css">

.header{
	border-color: #f5f5f5; 
	background-color: #f5f5f5; 
	border-style: solid; 
	border-width: thin; 
	text-align: center; 
	vertical-align: top; 
	padding-top: 10px;
	font-weight: bold;
}

.content{
	border-color: #f5f5f5; 
	border-style: solid; 
	border-width: thin; 
	text-align: center; 
	vertical-align: top; 
	padding-top: 10px; 
	width: 13%;
}

</style>

</head>
<body>

<table width="100%">
	<tr class="header">
		<th>&nbsp;</th>
		<th>PRD-1</th>
		<th>PRD-2</th>
		<th>PRD-3</th>
		<th>PRD-4</th>
		<th>PRD-5</th>
		<th>PRD-6</th>
		<th>PRD-7</th>
	</tr>
	<s:iterator status="stat" value="suggested_classSchedule">
		<tr>
			<s:if test="%{#stat.index == 0}"> <td class="header" >MON</td> </s:if>
			<s:if test="%{#stat.index == 1}"> <td class="header" >TUS</td> </s:if>
			<s:if test="%{#stat.index == 2}"> <td class="header" >WEN</td> </s:if>
			<s:if test="%{#stat.index == 3}"> <td class="header" >THU</td> </s:if>
			<s:if test="%{#stat.index == 4}"> <td class="header" >FRI</td> </s:if>
			
			<s:iterator status="stat1" value="suggested_classSchedule[#stat.index]">
				<td class="content"> <s:property value="suggested_classSchedule[#stat.index][#stat1.index].subjectBean.sub_name"/> </td>
			</s:iterator>
		</tr>
	</s:iterator>
</table>

<div style="height: 40px;">&nbsp;</div>

</body>
</html>