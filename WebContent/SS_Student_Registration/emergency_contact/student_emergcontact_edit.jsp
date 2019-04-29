<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<div id="cadd_updateform">
	<s:set var="siid" value="si_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; color: #3d6e9f">
				<div onclick="backToEergencyContactList('${siid}')" style="cursor: pointer">
					<img alt="click" src="images/next.gif" width="8px"> Back to Emergency Contact Address list
				</div>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<div id="errMsg" style="color: red; text-align: center;"></div>
			</td>
		</tr>
		<tr>
			<td>
				<table><tr><td>
				
					<s:set var="secid" value="sec_id"/>
					<s:textfield id="contact_name" label="Contact Name" required="true" key="contact_name" style="height: 25px; width: 200px"/>
					<s:textfield id="relationship" label="Relationship" required="true" key="relationship" style="height: 25px; width: 200px"/>
					<s:textfield id="mob_no" label="Mobile Number" key="mob_no" style="height: 25px; width: 200px"/>
					<s:textfield id="offphone_no" label="Office Phone Number" key="office_phone_no" style="height: 25px; width: 200px"/>
					<s:textfield id="homephone_no" label="Home Phone Number" key="home_phone_no" style="height: 25px; width: 200px"/>
					<s:select id="status" label="Status" headerKey="-1" headerValue="Please select status" list="#{'A':'Active', 'I':'Inactive'}" key="sec_status" style="height: 30px; width: 206px"/>
				</td></tr>
		<tr><td>
			<button onclick="updateEmrgContact('${secid}', '${siid}')" class="btn">Update</button>						
		</td></tr></table>
			</td>
		</tr>
	</table>
</div>

</body>
</html>