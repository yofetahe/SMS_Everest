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

	<s:set var="cl_id" value="cl_id"/>
	<s:set var="clcd_id" value="clcd_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="ClassDetailList('${cl_id}')"><img alt="click" src="images/next.gif" width="8px"> Back to Detail List</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 380px">
					<s:fielderror id="classDtlError"/>
					<div id="errMsg" style="color: red; text-align: center; width: 50%;"></div>
					<table align="left"><tr><td>	
					
						<s:textfield id="cl_capacity" label="Class Capacity" required="true" key="cl_capacity" style="height: 25px; width: 200px"/>
						<s:select id="rel_status" label="Relation Status" required="true" headerValue="Select Status" headerKey="0" list="#{'Active':'Active', 'Inactive':'Inactive'}" key="rel_status" style="height: 25px; width: 206px"/>
												
					</td></tr>
					<tr><td>
						<button onclick="updateClCdRel('${clcd_id}', '${cl_id}')">Update</button>
					</td></tr></table>
				</div>
				
			</td>
		</tr>
	</table>

</body>
</html>