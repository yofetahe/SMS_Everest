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
					
					<s:set var="ua_id" value="ua_id"/>
					<s:textfield label="Name" id="name" name="name" readonly="true" style="width: 200px; height: 25px;" required="true"/>
					
					<%-- <s:textfield label="Login User Name" id="username" name="username" style="width: 200px; height: 25px;" required="true"/>
					<s:password label="Password" value="password" id="password" name="password" style="width: 200px; height: 25px;" required="true"/> --%>
					
					<s:select label="User Role" id="utype" list="userTypeList" listKey="utId" name="userType" listValue="ut_name" required="true" headerValue="---" headerKey="0" style="width: 206px; height: 25px;"/>
					<s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" id="status" required="true" name="ua_status" style="width: 206px; height: 25px;"/>
					
				
				</td>
			</tr>
			<tr><td>
				<button onclick="updateAcUser('${ua_id}')" class="btn">Update</button>						
			</td></tr>
		</table>		
	</s:if>
	<s:elseif test="%{#ftype == \"teacher\"}">
		<div id="errMsg" style="color: red; text-align: center; width: 40%;">
			<s:actionerror id="repeatedUsername"/>
		</div>
		
		<table>
			<tr>
				<td>
					<s:set var="ua_id" value="ua_id"/>
					<s:set var="ti_id" value="ti_id"/>
					<s:set var="teacherName" value="name"/>
					
					<s:textfield label="Teacher Name" id="tiid" readonly="true" value="%{#teacherName}" style="width: 202px; height: 25px;"/>
					<%-- <s:select label="Teacher Name" id="tiid" list="teacherList" listKey="ti_id" listValue="tname" name="ti_id" required="true" style="width: 206px; height: 25px;"/> --%>
					<s:select label="User Role" id="utype" list="userTypeListTeacher" listKey="utId" listValue="ut_name" name="utId" required="true" headerValue="---" headerKey="0" style="width: 206px; height: 25px;"/>
					<s:select label="Status" list="#{'A':'Active', 'I':'Inactive'}" id="teach_status" required="true" name="ua_status" style="width: 206px; height: 25px;"/>
					
					<s:submit  value="Update"/>
				
				</td>
			</tr>
			<tr><td>
				<button onclick="updateTeacherUser('${ua_id}', '${ti_id}')" class="btn">Update</button>						
			</td></tr>
		</table>
	</s:elseif>
	<s:elseif test="%{#ftype == \"student\"}">
		Student
	</s:elseif>
</body>
</html>