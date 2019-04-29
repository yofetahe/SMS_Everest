<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head />
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="../css/sms_css.css">

</head>
<body>
	<s:set var="rNum" value="rowNum"/>
	<s:set var="studCount" value="0"/>	
	<s:set var="listSize" value="listSize"/>
	<s:set var="classSize" value="classSize"/>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>
	
	<div style="min-height: 40px; background-color: #f5f5f5; padding-top: 5px;">
		<div id="errMsg" style="color:red; padding-left: 190px;"></div>
		<table width="100%">
			<tr>
				<td>
					<table>
						<tr>
							<td style="font-style: italic; width: auto; text-align: right;">Attendance Date<span style="color:red">*</span>: </td>
							<td>
								<sj:datepicker id="at_date" name="disaction_date" 
									displayFormat="yy-mm-dd" readonly="true" 
									maxDate="today" changeMonth="true" 
									changeYear="true" style="height: 20px; width: 160px" 
									onchange="refreshStudentList(document.getElementById('clid').value, document.getElementById('cdid').value)">
								</sj:datepicker>
							</td>
							<td>
							
								<s:select label="Attendance Type" id="at_type" required="true" list="attendance_type_list" headerKey="0" headerValue="Select Attendance Type" listKey="attype_id" listValue="attype_description" style="height: 25px; width: 166px" />
							</td>
						</tr>
					</table>
					
				<td>
<!-- 				<td style="padding-left: 30px;"> -->
<%-- 					<div onclick="refreshStudentList('${cl_id}', '${cd_id}')" id="header_tab" style="text-align: center; height: 25px; color: #3d6e9f; padding-top: 5px; width: 200px;" onmouseover="this.style.color ='#000'" onmouseout="this.style.color='#3d6e9f'"> --%>
<!-- 						Refresh Student List -->
<%-- 					</div> --%>
<!-- 				</td> -->
				<td align="right">
					<div onclick="attendance_list('${cl_id}', '${cd_id}')" id="header_tab" style="text-align: center; height: 25px; color: #3d6e9f; padding-top: 5px; width: 200px;" onmouseover="this.style.color ='#000'" onmouseout="this.style.color='#3d6e9f'">
						Absent Reason Add Form
					</div>
				</td>
				<td style="padding-right: 10px;" align="right">
					<div onclick="aggregate_absent_list('${cl_id}', '${cd_id}')" id="header_tab" style="text-align: center; height: 25px; color: #3d6e9f; padding-top: 5px; width: 200px;" onmouseover="this.style.color ='#000'" onmouseout="this.style.color='#3d6e9f'">
						Students Absent List
					</div>
				</td>
			</tr>
		</table>
		<div>
			<table>
				<s:radio id="atid" label="Quarter" required="true" name="at_id" onchange="selectedQurter(this.value)" list="sem_list" listKey="at_id" listValue="at_name"></s:radio>
			</table>
		</div>
	</div>
	
	<div id="attendanceList">
		<s:include value="attendance_student_list.jsp"/>
	</div>
	
	
	<input id="clid" value="${cl_id}" style="visibility: hidden;">
	<input id="cdid" value="${cd_id}" style="visibility: hidden;">
	
	
</body>
</html>