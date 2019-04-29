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
			<td>
				<table width="100%" style="border-bottom-style: solid; border-bottom-width: thin; border-bottom-color: #3d6e9f;" cellpadding="0" cellspacing="0"><tr><td>
				
					<s:set var="from" value="from"/>
					<s:set var="password" value="password"/>
					<s:set var="subject" value="subject"/>
					<s:set var="body" value="body"/>
				
					<table cellpadding="0" cellspacing="0" >
						<tr>
							<td width="140px" height="25px" align="left">
								<a href="/<s:text name='global.sysname'/>/email.action" style="text-decoration: none;">
								<div onclick="backToEmailForm()" style="cursor: pointer;">
									<img alt="" src="images/next.gif"> Back to email Form
								</div>
								</a>
							</td>
							<td>&nbsp;</td>						
							<td width="180px" height="25px" align="center">
								<div id="stud" class="header_btn_inactive" style="width: 100%" onclick="studentsList()">
									Student Parent's
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="180px" height="25px" align="center">
								<div id="teach" class="header_btn_inactive" style="width: 100%" onclick="teachersList('${from}', '${password}', '${subject}', '${body}')">
									Teachers
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="180px" height="25px" align="center">
								<div id="nteach" class="header_btn_inactive" style="width: 100%" onclick="nonacademicStuff()">
									Non-academic Stuff
								</div>
							</td>
							<td>&nbsp;</td>							
						</tr>
					</table>
				
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="emailList" style="padding-top: 10px;">
				
					
				</div>
			</td>
		</tr>
	</table>

</body>
</html>