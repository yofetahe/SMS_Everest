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
	<s:set var="class_name" value="class_name"/>
	<s:set var="subcl_id" value="subcl_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer;" onclick="addSubjectRel('${cl_id}', '${class_name}')"><img alt="click" src="images/next.gif" width="8px"> Back to Subject List</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background" style="min-height: 380px">
					<s:fielderror id="clSubRelError"/>
				
					<table align="left"><tr><td>
						<s:textfield label="Subject" key="sub_name" readonly="true" style="height: 25px; width: 200px"/>
<%-- 						<s:textfield label="Added To the total Mark" id="addToTotal" readonly="true"  key="add_to_total" style="height: 25px; width: 200px"/> --%>
						<s:select id="added_to_total" label="Added To the total Mark" list="#{'Yes':'Yes', 'No':'No'}" key="add_to_total" style="height: 25px; width: 206px" />
						<s:select id="conToGrade" label="Convert to Grading" list="#{'YES':'Yes', 'NO':'No'}" key="con_to_grade" style="height: 25px; width: 200px"/>
<%-- 						<s:textfield label="Convert to Grading" id="conToGrade" readonly="true" key="con_to_grade" style="height: 25px; width: 200px"/> --%>
						<s:select id="rel_status" label="Status" list="#{'A':'Active', 'U':'Inactive'}" key="rel_status" style="height: 25px; width: 206px" />
												
					</td></tr></table>
				</div>
				
			</td>
		</tr>
		<tr><td>
			<button onclick="updateClassSubRel('${subcl_id}', '${cl_id}')" class="btn">Update</button>						
		</td></tr>
	</table>


</body>
</html>