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
	<table>
	<s:set var="pay_amount" value="material_pay_amount"/>
	<s:textfield label="Payment Amount" id="payRate" key="pay_amount" value="%{#pay_amount}" readonly="true" style="height: 25px; width: 200px"/>
	</table>
</body>
</html>