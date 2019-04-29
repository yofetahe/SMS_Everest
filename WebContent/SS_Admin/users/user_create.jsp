<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
	<s:set var="ftype" value="formType"/>
	
	<s:if test="%{#ftype == \"noneacademic\"}">
		<div id="errMsg" style="color: red; text-align: center; width: 40%;">
			<s:actionerror id="repeatedUsername"/>
		</div>
		
		<table>
			<tr>
				<td> 
				
					<s:select label="Name" id="nti_id" list="non_acadamic_list" listKey="nti_id" listValue="full_name" headerValue="----" headerKey="0" required="true" style="width: 206px; height: 25px;"/>
					<s:textfield label="Login User Name" id="username" style="width: 200px; height: 25px;" required="true"/>
					<s:password label="Password" value="pass*word" showPassword="true" readonly="true" id="password" style="width: 200px; height: 25px;" required="true"/>
					<s:select label="User Role" id="utype" list="userTypeList" listKey="utId" listValue="ut_name" required="true" headerValue="----" headerKey="0" style="width: 206px; height: 25px;"/>
					
					<s:submit onclick="saveAcUser()" value="Save"/>
				
				</td>
			</tr>
		</table>
	</s:if>
	<s:elseif test="%{#ftype == \"teacher\"}">
		<div id="errMsg" style="color: red; text-align: center; width: 40%;">
			<s:actionerror id="repeatedUsername"/>
		</div>
		
		<table>
			<tr>
				<td>
					
					<s:select label="Teacher Name" id="tiid" list="teacherList" listKey="ti_id" listValue="tname" headerValue="----" headerKey="0" required="true" style="width: 206px; height: 25px;"/>
					<s:textfield label="Login User Name" id="username" style="width: 200px; height: 25px;" required="true"/>
					<s:password label="Password" value="pass*word" showPassword="true" readonly="true" id="password" style="width: 200px; height: 25px;" required="true"/>
					<s:select label="User Role" id="teacherutype" list="userTypeListTeacher" onchange="displayClassList(this.value)" listKey="utId" listValue="ut_name" required="true" headerValue="----" headerKey="0" style="width: 206px; height: 25px;"/>
					
					<s:submit onclick="saveTeachUser()" value="Save"/>
				
				</td>
			</tr>
		</table>
	</s:elseif>
	<s:elseif test="%{#ftype == \"student\"}">
		Student
	</s:elseif>
	
</body>
</html>