<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script src="../js/sms_admin.js" type="text/javascript"></script>
<link type="text/csss" rel="stylesheet" href="css/sms_css.css">


</head>
<body>

	

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td width="58%" valign="top">
			
				<div class="titleBottomBorder">
					<table><s:select id="ac_year" label="Academic Year" required="true" list="acyear_list" class="inputtext-format" onchange="getClSubPeriodAllotmentPerYear(this.value)"/></table>
				</div>
				
				<div id="classSubPeriodAllotmentList">
					<jsp:include page="class_allotment_list.jsp"/>
				</div>
				
			</td>
			<td>&nbsp;</td>
			<td width="40%" valign="top" id="periodAllotmentForm">
				
				<table width="100%">
					<tr>
						<td align="right" style="padding-right: 60px;" class="titleBottomBorder">
							<s:set var="ac_year" value="academic_year"/>
							<table><s:textfield id="academic_year" label="Academic Year" readonly="true" value="%{#ac_year}" class="inputtext-format"/></table>
							<table><s:select id="sex" label="Grade" headerValue="Please select grade" required="true" headerKey="0" listValue="class_name" listKey="class_id" list="grade_rslt" onchange="getSubjectListPerClass(this.value)" class="listbox-format"/></table>
						</td>
					</tr>
					<tr>
						<td align="center">							
							<div id="subjectPeriodList">
								<div class="successMessage"> <s:actionmessage/> </div>
							</div>
						</td>
					</tr>
				</table>
				
			</td>			
		</tr>
	</table>

</body>
</html>