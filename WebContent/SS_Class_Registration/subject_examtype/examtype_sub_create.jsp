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
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="sub_id" value="sub_id"/>
	<s:set var="sub_name" value="sub_name"/>
	<s:set var="subcl_id" value="subcl_id"/>
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 40px">
				Exam type list for - <s:property value="sub_name"/> subject
			</td>
		</tr>
		<tr>
			<td>
				<div class="menu_shadow" style="height: 35px; padding-top: 15px;  background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="ExamTypeList('${subcl_id}', '${sub_name}', '${cl_id}', '${sub_id}')"><img alt="click" src="images/next.gif" width="8px"> Back to Subject List</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 380px">
					<s:fielderror id="examtypesubcreateError"/>
					<div id="errMsg" style="color: red; text-align: center; width: 50%;"></div>
					<table align="left"><tr><td>
						
						<s:select id="et_id" label="Exam Type" required="true" headerValue="Select exam type" headerKey="0" list="examtypelist_rslt" listValue="et_name" listKey="et_id" style="height: 25px; width: 206px" />
						<s:textfield id="total_mark" label="Total Mark" required="true" key="total_mark" style="height: 25px; width: 200px" />
						<s:textfield id="pass_mark" label="Pass Mark" required="true" key="pass_mark" style="height: 25px; width: 200px" />
						
					</td></tr>
					<tr><td>
						<button onclick="saveClSubExRel('${subcl_id}', '${cl_id}', '${sub_id}')" class="btn">Save</button>						
					</td></tr></table>
				</div>
			</td>
		</tr>
	</table>

</body>
</html>