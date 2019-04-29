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
			<td colspan="3">
				<div class="menu_shadow" style="background-color: #f5f5f5; height: 35px; padding-top: 15px">
					<a href="/<s:text name='global.sysname'/>/teacher.action" style="text-decoration: none; color: #000000">
					<img src="images/next.gif" width="8px"/> Back to List
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<div class="content_background">
					<table cellpadding="0" cellspacing="0" width="100%">
						<tr>
						<td width="148px" valign="top">
							<s:include value="menu.jsp"/>
						</td>
						<td width="2%">
							&nbsp;
						</td>
						<td valign="top">
							<!-- form -->
							<div id="teachFroms">
								<s:include value="teacher_perinfo_edit_form.jsp"/>
							</div>
							<!-- form -->
						</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>

</body>
</html>