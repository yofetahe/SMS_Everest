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

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 50px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px">
				<img alt="click" src="images/next.gif" width="8px"> Back to List
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
			
				<div class="content_background" style="min-height: 380px">
					<div id="errMsg" style="color: red; text-align: center; width: 50%;"></div>
					<table align="left"><tr><td>
						<s:set var="sub_id" value="sub_id"/>
						<s:textfield id="sub_name" label="Subject" required="true" key="sub_name" style="height: 25px; width: 200px"/>
						<s:select id="sub_status" label="Status" list="#{'A':'Active', 'I':'Inactive'}" key="sub_status" style="height: 30px; width: 206px"/>
						
					</td></tr>
		<tr><td>
			<button onclick="updateSubject('${sub_id}')" class="btn">Update</button>						
		</td></tr></table>
				</div>
			
			</td>
		</tr>
	</table>

</body>
</html>