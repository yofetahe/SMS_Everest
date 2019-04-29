<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<script src="../js/sms_admin.js" type="text/javascript"></script>

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
								<div id="dep" class="header_btn_inactive" style="width: 100%" onclick="departmentList()">
									Departments
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="180px" height="25px" align="center">
								<div id="club" class="header_btn_inactive" style="width: 100%" onclick="clubList()">
									Clubs
								</div>
							</td>
							<td>&nbsp;</td>
							<td width="180px" height="25px" align="center">
								<div id="trole" class="header_btn_inactive" style="width: 100%" onclick="teachersRole()">
									Teacher Role Assignment
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
				<div id="infoList" style="padding-top: 10px;">
				
					
				</div>
			</td>
		</tr>
	</table>

</body>
</html>