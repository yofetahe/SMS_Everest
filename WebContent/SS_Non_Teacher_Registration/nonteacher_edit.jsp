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
	<s:set var="nti_id" value="nti_id"/>
	<div style="width: 85%; color: #ffffff; background-color: #f5f5f5; height: 35px; padding-top: 10px;" align="center">Update Form</div>
	<div style="padding-left: 10px; width: 350px">
		<div id="errMsg" style="color: red; text-align: center; width: 100%;">&nbsp;</div>
		<table width="100%" border="0">
			<tr>
				<td>							
					<s:textfield id="fname" label="Frist Name" required="true" name="nti_fname" style="height: 25px; width: 200px"/>
					<s:textfield id="mname" label="Middle Name" required="true" name="nti_mname" style="height: 25px; width: 200px"/>
					<s:textfield id="gname" label="G.Father Name" required="true" name="nti_gname" style="height: 25px; width: 200px"/>
					<s:select id="sex" label="Gender" required="true" list="#{'M':'Male', 'F':'Femal'}" name="nti_sex" style="height: 30px; width: 206px"/>
					<s:textfield id="email" label="Email" name="nti_email" style="height: 25px; width: 200px"/>
					<s:textfield id="pos" label="Position" required="true" name="nti_position" style="height: 25px; width: 200px"/>
					<s:select id="status" label="Status" required="true" list="#{'A':'Active', 'I':'Inactive'}" name="nti_status" style="height: 30px; width: 206px"/>
																									
				</td>
			</tr>
		<tr><td>
			<button onclick="updateNonTeacher('${nti_id}')" class="btn">Update</button>						
		</td></tr>
		</table>
	</div>

</body>
</html>