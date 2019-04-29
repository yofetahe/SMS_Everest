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
			<td style="height: 50px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="backExamTypeList()"><img alt="click" src="images/next.gif" width="8px"> Back to List</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 380px">
					<s:fielderror id="etypeError"/>
					<div id="errMsg" style="color: red; text-align: center; width: 50%;"></div>
					<table align="left"><tr><td>
						<s:textfield id="et_name" label="Exam Type Name" required="true" key="et_name" style="height: 25px; width: 200px"/>	
					</td></tr><tr><td>
						<s:select list="#{'':'', 'NF':'Not-Final','F':'Final'}" id="et_type" label="Exam Type" style="height: 30px; width: 206px"/>
					</td></tr><tr><td>
						<s:submit onclick="saveExamType()" value="Save"/>		
					</td></tr></table>
				</div>
				
			</td>
		</tr>
	</table>	
	
</body>
</html>