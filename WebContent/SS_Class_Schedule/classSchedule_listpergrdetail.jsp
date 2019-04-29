<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
	<s:set var="classid" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 5px;">
		<tr>
			<td style="background-color: #f5f5f5; height: 35px; padding-top: 5px">
				<div style="cursor: pointer; color: #3d6e9f" onclick="classScheduleAddForm('${classid}', '${cd_id}')"><img alt="click" src="images/next.gif" width="8px">&nbsp;Add New Schedule</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px;">
				<table cellpadding="0" cellspacing="0" border="0"><tr><td>
					<table><tr><td>
						Academic Year
					</td><td>
					
<!-- 						<table> -->
<%-- 						<s:select label="Academic Year" list="year" style="width: 100px; height: 25px" onchange="academicCalendarPerYear(this.value, '${classid}', '${cd_id}')"/> --%>
<!-- 						</table> -->
						
						<select onchange="academicCalendarPerYear(this.value, '${classid}', '${cd_id}')" style="width: 100px; height: 25px">
							<c:forEach var="year" items="${year}" varStatus="stat">
								<option value="${year}">${year}</option>
							</c:forEach>
						</select>
						
					</td><td>&nbsp;</td><td style="padding-left: 30px;">
						<div style="cursor: pointer; color: #3d6e9f;" onclick="subjectAssignmentSuggestion('${classid}', '${cd_id}')">Subject Assignment Suggestion</div>
					</td></tr></table>
				</td>
				<td width="50px">&nbsp;</td>
				<td width="80px" style="padding-top: 4px;">				
					&nbsp;
				</td></tr></table>
				<div id="suggestedClassSchedule"> </div>
				<div id="academic_program">
					<s:include value="classSchedule_academicprogram.jsp"/>
				</div>
			</td>
		</tr>		
	</table>

</body>
</html>