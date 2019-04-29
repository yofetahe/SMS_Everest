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
			maxDate: "-7280D"
		});		
	});
</script>

</head>
<body>

	<div style="width: 100%">
		<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td style="background-color: #f5f5f5; height: 50px; cursor: pointer;">				
					<img alt="click" src="images/next.gif" width="8px"> Editing Teacher's Personal Information			
				</td>
			</tr>
		</table>		
		<div id="errMsg" style="color: red; text-align: center; width: 45%;">&nbsp;</div>
		<div>
			<s:set var="successful_save" value="successful_save"/>
			<s:set var="successful_update" value="successful_update"/>
			<s:if test="%{#successful_save == \"true\"}"> <span style="color: #3d6e9f"> Successfully Saved. </span> </s:if>
			<s:if test="%{#successful_update == \"true\"}"> <span style="color: #3d6e9f"> Successfully Updated. </span> </s:if>			
		</div>
		<br/>
		<table>
			<tr>
				<td>
					<s:set var="tiid" value="ti_id"/>							
					<s:textfield id="fname" label="Frist Name" required="true" key="fname" class="inputtext-format"/>
					<s:textfield id="mname" label="Middle Name"  required="true" key="mname" class="inputtext-format"/>
					<s:textfield id="gname" label="Grand Father Name"  required="true" key="gname" class="inputtext-format"/>
					<s:select id="sex" label="Gender"  required="true" list="#{'M':'Male', 'F':'Femal'}" key="sex" class="listbox-format"/>
					<s:textfield id="dob" label="Date of birth (E.C)" readonly="true" required="true" key="dob" class="inputtext-format"/>
					<s:textfield id="pob" label="Place of birth"  key="pob" class="inputtext-format"/>
					<s:textfield id="nat" label="Nationality"  key="nationality" class="inputtext-format"/>
					<s:textfield id="idno" label="ID No" readonly="true" key="id_no" class="inputtext-format"/>
					<s:select id="tistatus" label="Status"  required="true" list="#{'A':'Active', 'I':'Inactive'}" key="ti_status" class="listbox-format"/>
								
				</td>
			</tr>
		<tr><td>
			<button onclick="updateTeacher('${tiid}')" class="btn">Update</button>						
		</td></tr>
		</table>
	</div>

</body>
</html>