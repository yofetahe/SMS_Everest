<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<!-- Ethiopian Calendar JQuery -->
<c:url value="/js/js_new/jquery-1.6.1.js" var="Jq161"/>
<script type="text/javascript" src="${Jq161}"></script>
<c:url value="/js/js_new/jquery.calendars.js" var="JqCal"/>
<script type="text/javascript" src="${JqCal}"></script>
<c:url value="/js/js_new/jquery.calendars.plus.js" var="JqCalPlus"/>
<script type="text/javascript" src="${JqCalPlus}"></script>

<c:url value="/js/js_new/jquery.calendars.picker.css" var="JqPickerCss"/>
<link rel="stylesheet" type="text/css" href="${JqPickerCss}">

<c:url value="/js/js_new/jquery.calendars.picker.js" var="JqCalPicker"/>
<script type="text/javascript" src="${JqCalPicker}"></script>
<c:url value="/js/js_new/jquery.calendars.ethiopian.js" var="JqCalEth"/>
<script type="text/javascript" src="${JqCalEth}"></script>
<c:url value="/js/js_new/jquery.calendars.ethiopian-am.js" var="JqCalEthAmh"/>
<script type="text/javascript" src="${JqCalEthAmh}"></script>
<!-- Ethiopian Calendar JQuery -->

<script>
	$(document).ready(function(){
		$('#dob').calendarsPicker({
			calendar: $.calendars.instance('ethiopian'), 
			dateFormat: "yyyy-mm-dd",
			maxDate: "-1096D"
		});		
	});
</script>

</head>
<body>

	<table align="center">		
		<tr>
			<td id="info_updateform" colspan="2">
				<div id="errMsg" style="color: red; text-align: center; width: 50%;"></div>
			</td>
		</tr>
	</table>
	<table><tr><td>
	
		<s:set var="siid" value="si_id"/>
		<s:set var="stud_edit_status" value="stud_edit_status"/>
		
		<s:hidden key="si_id"/>
		
		<table width="100%"><tr><td align="right" style="padding-right: 43px;">
			
			<table><s:textfield id="fname" label="Frist Name" required="true" key="fname" class="inputtext-format"/></table>
			<table><s:textfield id="mname" label="Middle Name" required="true" key="mname" class="inputtext-format"/></table>
			<table><s:textfield id="gname" label="Grand Father Name" required="true" key="gname" class="inputtext-format"/></table>
			<table><s:textfield id="mother_name" label="Mother Name" required="true" key="mother_name" class="inputtext-format"/></table>
			<table><s:select id="sex" label="Sex" headerValue="Please select sex" required="true" headerKey="-1" list="#{'M':'Male', 'F':'Female'}" key="sex" class="listbox-format"/></table>
		
		</td></tr><tr><td align="right" style="padding-right: 43px;">
 				
 			<table align="right"><tr><td>
 				<i>Date of Birth:<span style="color:RED;">*</span></i>
 			</td><td>
				<input id="dob" readonly="readonly" type="text" value="${dob}" class="inputtext-format">
  			</td></tr></table> 
				
		</td></tr><tr><td align="right" style="padding-right: 43px;">
			
			<%-- <s:textfield id="dob" label="Date of Birth (year-month-date) E.C" required="true" key="dob" class="inputtext-format"/> --%>
			<table><s:textfield id="pob" label="Place of Birth" required="true" key="pob" class="inputtext-format"/></table>
			<table><s:textfield id="nat" label="Nationality" key="nationality" required="true" class="inputtext-format"/></table>
			<table><s:select id="status" label="Status" required="true" list="#{'A':'Active', 'I':'Inactive'}" key="si_status" class="listbox-format"/></table>
			<table><s:textfield id="idno" label="ID Number" required="true" readonly="true" key="id_no" class="inputtext-format"/></table>
											
			<div class="btn" onclick="updateStudentInfo('${siid}', '${stud_edit_status}')">Update</div>
		
		</td></tr></table>
		
	</td></tr></table>

</body>
</html>