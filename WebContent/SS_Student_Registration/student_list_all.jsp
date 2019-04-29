<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>

	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 5px;">
		<tr>
			<td width="15%" valign="top">											
				<table border="0" width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right">
							<table cellpadding="0" cellspacing="0">
							<s:select label="Calendar Year" id="academicYear" list="acyear_list" key="acyear" style="width: 55px; height: 25px" onchange="filterStudentPerYear(this.value)"/>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td style="background-color: #3d6e9f; color: #ffffff; height: 35px; text-align: center;">
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
								<s:set var="clname" value="grade_rslt[#stat.index].class_name"/>
								<div class="menu_button" id="${clid}" onclick="gradeSelected('${clid}', '${clname}')">
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
				<div id="studpergrade">
<%-- 					<s:include value="student_list.jsp"/> --%>
					<s:include value="student_male_female_num_per_class.jsp"/>
				</div>
			</td>
		</tr>
	</table>

</body>
</html>