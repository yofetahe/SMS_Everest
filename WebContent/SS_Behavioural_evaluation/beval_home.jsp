<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>

</head>
<body>

	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 5px;">
		<tr>
			<td width="15%" valign="top" style="padding-left: 5px;">
														
				<table border="0" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td style="background-color: #3d6e9f; color: #fff; height: 35px; text-align: center;">
							Class Category
						</td>
					</tr>
					<tr>
						<td style="line-height: 3px">&nbsp;</td>
					</tr>
					<s:iterator status="stat" value="grade_rslt">
						<tr>
							<td align="center" onmouseover="this.style.color = '#3d6e9f'" onmouseout="this.style.color = '#000000'">
								<s:set var="clid" value="grade_rslt[#stat.index].class_id"/>
								<s:set var="classname" value="grade_rslt[#stat.index].class_name"/>
								<div class="menu_button" id="${clid}" onclick="bevalPerGradeSelected('${clid}', '${classname}')">
									<s:property value="grade_rslt[#stat.index].class_name"/>
								</div>
							</td>
						</tr>
						<tr>
							<td style="line-height: 3px">&nbsp;</td>
						</tr>														
					</s:iterator>
				</table>
								
			</td>																		
			<td width="2%">&nbsp;</td>		
			<td valign="top">																			
				<div id="gradeDetailList">
					
				</div>
			</td>
		</tr>
	</table>

</body>
</html>