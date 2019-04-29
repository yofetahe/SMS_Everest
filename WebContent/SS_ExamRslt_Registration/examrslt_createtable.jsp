<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
<script src="js/jquery-1.6.1.js" type="text/javascript"></script>

</head>
<body>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="exsub_id" value="exsub_id"/>	
	<s:property value="total_mark"/>
	
	<div style="border-bottom-color: #f5f5f5; border-bottom-style: solid; border-bottom-width: thin; padding-bottom: 5px;">
		<table cellpadding="0" cellspacing="0">
			<tr>
				<td>Grade Detail</td>
				<td style="vertical-align: top;">
				
<!-- 					<table cellpadding="0" cellspacing="0"><tr><td> -->
<%-- 						<s:select label="Grade Detail" list="gradeDetail"  --%>
<%-- 							onchange="studListPerGradeDetail(this.value, '${cl_id}', '${exsub_id}')"  --%>
<%-- 							headerKey="0" headerValue="----" listValue="cd_name" listKey="cd_id" style="width: 150px; height: 25px"/> --%>
<!-- 					</td></tr></table> -->
					
					<select onchange="studListPerGradeDetail(this.value, ${cl_id}, ${exsub_id})" style="width: 150px; height: 25px">
						<option value="0">---</option>
					    <c:forEach items="${gradeDetail}" var="gradeDetail">
					        <option value="${gradeDetail.cd_id}">${gradeDetail.cd_name}</option>
					    </c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="studListPerGrDetail">
		

	
	</div>
</body>
</html>