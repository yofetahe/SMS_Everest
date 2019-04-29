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
<body style="margin: 0">

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px">
				<div class="menu_shadow" style="background-color: #f5f5f5; height: 35px; padding-top: 15px;  background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="backToCDList()"><img alt="click" src="images/next.gif" width="8px"> Back to List</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 380px">
					<s:fielderror id="cDtlError"/>
					<div id="errMsg" style="color: red; text-align: center; width: 50%;"></div>
					<table align="left"><tr><td>					
						
						<s:textfield id="cd_name" label="Detail Name" required="true" key="cd_name" style="height: 25px; width: 200px"/>
						
						<s:submit onclick="saveCDetail()" value="Save"/>
						
					</td></tr></table>
				</div>
			
			</td>
		</tr>
	</table>

</body>
</html>