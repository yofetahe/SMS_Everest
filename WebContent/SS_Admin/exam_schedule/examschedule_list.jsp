<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="../css/sms_css.css">

</head>
<body>
<div id="examScheInfo">
	<div style="background-color: #f5f5f5; color: #3d6e9f; height: 30px; width: 100%; padding-top: 10px;">
		<div onclick="addNewExamSchedule()" style="width: 200px; cursor: pointer;"><img alt="click" src="images/next.gif" width="8px">&nbsp;Add New Exam Schedule</div>
	</div>
	<div style="height: 10px"></div>
	
	<table style="border-bottom: thin; border-bottom-color: #E5E5E5; border-bottom-style: solid; border-bottom-width: thin;" width="100%"><tr><td width="20%">
		<table><tr><td>
			<s:select id="ac_year" label="Academic Year" list="acyear_list" style="width: 80px; height: 25px;"/>
		</td></tr></table>
	</td><td>
		<table><tr><td>
			<s:select id="at_id" label="Quarters" list="annualTermList" listKey="at_id" listValue="at_name" style="width: 90px; height: 25px;"/>
		</td></tr></table>
	</td>
	</tr><tr>
	<td colspan="2">

		<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<s:iterator status="stat" value="classList">
					<td style="padding-left: 5px; padding-right: 5px;">
						<s:set var="cl_id" value="classList[#stat.index].cl_id"/>
						<div class="save_update_btn" style="height: 25px; padding-top: 7px; cursor: pointer;" align="center" onclick="filterExamSchedule('${cl_id}')"> 
						<s:property value="classList[#stat.index].class_name"/> 
						</div>
					</td>
				</s:iterator>
			</tr>
		</table>		
		
	</td></tr></table>	
	
	<div style="height: 5px;"></div>
	
	<div id="examsch_filter">

	</div>
	
</div>
</body>
</html>