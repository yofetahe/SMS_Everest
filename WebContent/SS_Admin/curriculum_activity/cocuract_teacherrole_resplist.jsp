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
	<s:set var="roleid" value="role_id"/>
	<s:if test="%{#roleid == \"1\"}">
		<s:select id="tchRespList" list="respList" listKey="dep_id" listValue="dep_name" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
	</s:if>
	<s:if test="%{#roleid == \"2\"}">
		<s:select id="tchRespList" list="respList" listKey="clb_id" listValue="clb_name" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
	</s:if>
	<s:if test="%{#roleid == \"3\"}">
		<s:select id="tchRespList" list="respList" listKey="clb_id" listValue="clb_name" headerKey="0" headerValue="---" style="width: 205px; height: 30px;"/>
	</s:if>
</body>
</html>