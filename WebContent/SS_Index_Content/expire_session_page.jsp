<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
</head>
<body style="background-image: url('images/bg_img.png'); background-repeat: repeat; padding-left: 5px;">
	<div style=" padding-top: 100px; padding-left: 4px;">
	<div style="width: 100%; box-shadow: 0px -4px 15px #e5e5e5; background-image: url('images/SimienMountains_Panorama.jpg'); height: 200px;">

		<table align="center" border="0">
			<tr>
				<td align="center" style="padding-top: 50px">
					<div>Session Expire. Please login again.</div>
					<div style="height: 30px;">&nbsp;</div>
					<a href="/<s:text name='global.sysname'/>/logout.action"
						style="color: #3d6e9f; text-decoration: none; font-size: 20px;">
						<div id="header_tab" style="height: 42px; padding-top: 12px;">
							Login
						</div>
					</a>
				</td>
			</tr>
		</table>

	</div>
	</div>
</body>
</html>