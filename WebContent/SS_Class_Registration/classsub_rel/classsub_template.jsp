<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/sms_class_registration.js" type="text/javascript"></script>

</head>
<body>
	<s:set var="cl_id" value="cl_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 35px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
					<div style="color: #3d6e9f; cursor: pointer;" onclick="backToClassList()"><img alt="click" src="images/next.gif" width="8px"> Back to Class List</div>
				</div>
			</td>
		</tr>
		<tr>
			<td height="40px">
				Subject list for - <s:property value="class_name"/>
			</td>
		</tr>
		<tr>
			<td>
				<div id="clsubrelfrm">
				
					<s:include value="classsub_list.jsp"/>
				
				</div>
			</td>
		</tr>
	</table>

</body>
</html>