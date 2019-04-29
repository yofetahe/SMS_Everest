<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<link rel="icon" type="image/png" href="images/fidel_logo.png">
</head>
<body id="sessionExpire" style="margin: 0; background-image: url('images/new_bg_2.png'); background-repeat: repeat; ">
<div id="fullPageContent">

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="menu_shadow">
					<s:include value="header.jsp"/>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center" id="testid">
				<!-- body part -->
				<div style="width: 95%; min-height: 450px">					
					<table width="100%" height="450px" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<td width="13%" valign="top" style="padding-top: 1px;">
								<div>
									<s:include value="menu.jsp"/>
								</div>
							</td>
							<td valign="top" style="padding-top: 0px">
								<div id="index_cont"> 
									
									<s:include value="dashboard.jsp"/>
										
								</div>
							</td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
					</table>					
				</div>
				<!-- body part -->
			</td>
		</tr>
		<tr>
			<td>
				<s:include value="footer.jsp"/>
			</td>
		</tr>
	</table>
</div>
</body>
</html>