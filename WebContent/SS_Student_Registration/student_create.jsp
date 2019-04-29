<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<link rel="stylesheet" type="text/css" href="css/sms_css.css">

</head>
<body>

	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td style="height: 50px; padding-top: 1px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px;  background-position: bottom;">
					<a href="/<s:text name='global.sysname'/>/student.action" style="text-decoration: none; color: #000000">				
					<img alt="click" src="images/next.gif" width="8px"> Back to List
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 5px">
				
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="top">
							<div id="student_additionalinfo_save" class="content_background" style="min-height: 480px">
								<s:include value="personal_info/student_personalinfo_create.jsp"/>
							</div>
						</td>
					</tr>
				</table>		
			
			</td>
		</tr>
	</table>

</body>
</html>