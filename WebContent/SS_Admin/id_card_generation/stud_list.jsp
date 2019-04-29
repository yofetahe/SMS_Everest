<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	<table><tr><td>
		<div style="color: #3d6e9f; cursor: pointer;" onclick="listStudentDontHavePhoto('${cl_id}', '${cd_id}')">
			List Students don't have photo and Contact Info
		</div>
	</td><td width="50px;">&nbsp;</td><td>
		<div style="color: #3d6e9f; cursor: pointer;" onclick="listStudentDontHaveContactInfo('${cl_id}', '${cd_id}')">
			List Students don't have Contact Info
		</div>
	</td></table>
	
	<br/>
	
	<div id="studentListPerGrade">
	
		<table cellpadding="0" cellspacing="0" width="100%"><tr><td width="48%" valign="top">
			
			<s:include value="stud_without_id_list.jsp"/>
			
		</td><td>&nbsp;</td>
		<td width="48%" valign="top" id="registered_list">
		
			<s:include value="stud_with_id_list.jsp"/>
			
		</td></tr></table>
	
	</div>

</body>
</html>