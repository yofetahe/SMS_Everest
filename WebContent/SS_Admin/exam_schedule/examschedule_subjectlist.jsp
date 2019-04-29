<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:select list="subjectList" id="sub_id" headerValue="---" headerKey="0" listKey="sub_id" listValue="sub_name" onchange="examListPerSubject(this.value)" style="width: 206px; height: 30px;"/>