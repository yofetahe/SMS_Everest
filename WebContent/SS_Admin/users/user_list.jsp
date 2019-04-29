<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
				
					<table cellpadding="0" cellspacing="0" >
						<tr>
							<td>&nbsp;</td>						
							<td width="180px" height="25px" align="center">
								<div id="nonAcd" class="header_btn_inactive" style="width: 100%" onclick="noneAcademicUser()">
									None-academic Users
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="180px" height="25px" align="center">
								<div id="tech" class="header_btn_inactive" style="width: 100%" onclick="teacherUser()">
									Teacher Users
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="180px" height="25px" align="center">
								<div id="stud" class="header_btn_inactive" style="width: 100%" onclick="studentUser()">
									Student Users
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
				<div id="usrList" style="padding-top: 10px;">
				
					
				</div>
			</td>
		</tr>
	</table>

</body>
</html>