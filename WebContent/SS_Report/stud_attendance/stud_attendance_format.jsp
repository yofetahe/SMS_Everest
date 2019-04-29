<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
	
	<s:set var="cl_id" value="class_bean.cl_id"/>
	<s:set var="cd_id" value="cdetail_bean.cd_id"/>
	<s:set var="ac_year" value="stud_bean.ac_year"/>
	<s:set var="eduoff_filter_status" value="eduoff_filter_status"/>
	
	<div class="save_update_btn" onclick="generateStudentAttendanceFormat('${cl_id}', '${cd_id}', '${ac_year}', '${eduoff_filter_status}')" style="height: 30px; width: 210px; padding-top: 1px; padding-left: 25px; cursor: pointer; text-align: center;">
		<table><tr><td>
			<img alt="PDF" src="images/pdf.gif" height="25px">
		</td><td valign="middle">
			Generate Attendance
		</td></tr></table>
	</div>
	<br/>
<!-- 	<table width="100%" cellpadding="0" cellspacing="0" style="border: thin; border-color:gray; border-style: solid;" border="1"> -->
<!-- 		<tr> -->
<!-- 			<td>No</td> -->
<!-- 			<td>Student Name</td> -->
<!-- 			<td>Sex</td> -->
<!-- 			<td>Age</td> -->
<!-- 			<td>Parent's Phone</td> -->
<!-- 			<td>Months</td> -->
<%-- 			<s:iterator begin="1" end="30" var="days"> --%>
<%-- 				<td style="width: 20px;" align="center"> <s:property value="%{#days}"/> </td> --%>
<%-- 			</s:iterator> --%>
<!-- 		</tr> -->
<%-- 		<s:iterator status="stud" value="stud_rslt"> --%>
<!-- 			<tr valign="top"> -->
<%-- 				<td> <s:property value="%{#stud.index + 1}"/> </td> --%>
<%-- 				<td> <s:property value="stud_rslt[#stud.index].fname"/> <s:property value="stud_rslt[#stud.index].mname"/> <s:property value="stud_rslt[#stud.index].gname"/></td> --%>
<%-- 				<td> <s:property value="stud_rslt[#stud.index].sex"/> </td> --%>
<!-- 				<td> Age </td> -->
<!-- 				<td>  -->
<!-- 					<table cellpadding="0" cellspacing="0"> -->
<!-- 						<tr><td>Father Name</td></tr> -->
<!-- 						<tr><td> --- </td></tr> -->
<!-- 						<tr><td> &nbsp; </td></tr> -->
<!-- 						<tr><td> &nbsp; </td></tr> -->
<!-- 						<tr><td>Mother Name</td></tr> -->
<!-- 						<tr><td> --- </td></tr>						 -->
<!-- 					</table> -->
<!-- 				</td> -->
<!-- 				<td align="right"> -->
<!-- 					<table cellpadding="0" cellspacing="0" border="1" width="100%">						 -->
<!-- 						<tr><td>SEP</td></tr> -->
<!-- 						<tr><td>OCT</td></tr> -->
<!-- 						<tr><td>NOV</td></tr> -->
<!-- 						<tr><td>DEC</td></tr> -->
<!-- 						<tr><td>JAN</td></tr> -->
<!-- 						<tr><td>FEB</td></tr> -->
<!-- 						<tr><td>MAR</td></tr> -->
<!-- 						<tr><td>APR</td></tr> -->
<!-- 						<tr><td>MAY</td></tr> -->
<!-- 						<tr><td>JUN</td></tr> -->
<!-- 					</table> -->
<!-- 				</td> -->
<!-- 				<td colspan="30"> -->
<!-- 					<table border="1" width="100%" cellpadding="0" cellspacing="0">						 -->
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<%-- 						<tr><s:iterator begin="1" end="30" var="days"><td> &nbsp; </td></s:iterator></tr> --%>
<!-- 					</table> -->
<!-- 				</td> -->
				
<!-- 			</tr> -->
<%-- 		</s:iterator> --%>
<!-- 	</table> -->

</body>
</html>