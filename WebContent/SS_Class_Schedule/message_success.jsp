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
	
	<s:set var="rc" value="0"/>
	<s:iterator status="stat" value="rslt">
		<s:if test="%{rslt[#stat.index] == 1}">Period One - </s:if>
		<s:if test="%{rslt[#stat.index] == 2}">Period Two - </s:if>
		<s:if test="%{rslt[#stat.index] == 3}">Period Three - </s:if>
		<s:if test="%{rslt[#stat.index] == 4}">Period Four - </s:if>
		<s:if test="%{rslt[#stat.index] == 5}">Period Five - </s:if>
		<s:if test="%{rslt[#stat.index] == 6}">Period Six - </s:if>
		<s:if test="%{rslt[#stat.index] == 7}">Period Seven - </s:if>
		<s:set var="rc" value="%{#rc+1}"/>
	</s:iterator> 
	
	<s:if test="%{#rc > 0}">already exit.</s:if>
	
	<s:if test="%{#rc == 0}">The data is successfully saved!!!</s:if>
	
</body>
</html>