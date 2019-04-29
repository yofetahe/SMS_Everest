<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script src="js/sms_teacher_registration.js" type="text/javascript"></script>

</head>
<body>

<s:set var="ti_id" value="ti_id"/>
<div id="assignContainer">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px">
				<div onclick="assignList('${ti_id}')" style="cursor: pointer; color: #3d6e9f"><img src="images/next.gif" width="8px"/> Back to List</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<s:fielderror id="assignError"/>
				
				<div style="width: 400px; height: 180px">
					<table align="left"><tr><td>
					
						<s:set var="ta_id" value="ta_id"/>
						<s:select id="cl" label="Class" headerValue="Select class" headerKey="0" list="clroom_rslt" listValue="class_name" listKey="cl_id" key="cl_id" style="height: 30px; width: 206px"/>						
						<s:select id="sub" label="Subject" headerValue="Select Subject" headerKey="0" list="subjectList" listValue="sub_name" listKey="sub_id" key="sub_id" style="height: 30px; width: 206px"/>
						<s:select id="status" label="Status" list="#{'A':'Active', 'I':'Inactive'}" key="ta_status" style="height: 30px; width: 206px"/>
						
					</td></tr>
		<tr><td>
			<button onclick="updateTeachAssign('${ta_id}', '${ti_id}')" class="btn">Update</button>						
		</td></tr></table>
				</div>
			</td>
		</tr>
	</table>
</div>

</body>
</html>