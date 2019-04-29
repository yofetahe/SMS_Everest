<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

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
	
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div class="menu_shadow" style="height: 35px; padding-top: 15px">
					<a href="/<s:text name='global.sysname'/>/teacher.action" style="text-decoration: none; color: #000000">
					<img src="images/next.gif" width="8px"/> Back to List test
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background">
				
				<table width="100%" cellpadding="0" cellspacing="0">
					<tr>
						<td style="background-color: #f5f5f5; height: 50px; cursor: pointer;">				
							<img alt="click" src="images/next.gif" width="8px"> Adding Students Personal Information			
						</td>
					</tr>
				</table>
				<!-- form -->
				<s:fielderror id="tcherSaveError"/>
				<div style="padding-left: 10px; width: 500px">
					<div id="errMsg" style="color: red; text-align: center; width: 80%;">&nbsp;</div>
					<table>
						<tr>
							<td>							
								<s:textfield id="fname" label="Frist Name" required="true" key="fname" class="inputtext-format"/>
								<s:textfield id="mname" label="Middle Name" required="true" key="mname" class="inputtext-format"/>
								<s:textfield id="gname" label="Grand Father Name" required="true" key="gname" class="inputtext-format"/>
								<s:select id="sex" label="Gender" headerValue="Please select gender" required="true" headerKey="0" list="#{'M':'Male', 'F':'Femal'}" key="sex" class="listbox-format"/>
								<s:textfield id="dob" label="Date of Birth (E.C)" readonly="true" required="true" key="dob" class="inputtext-format"/>
								<s:textfield id="pob" label="Place of birth" key="pob" class="inputtext-format"/>
								<s:textfield id="nat" label="Nationality" value="Ethiopian" key="nationality" class="inputtext-format"/>
<%-- 								<s:textfield id="idno" label="ID No" required="true" key="id_no" class="inputtext-format"/> --%>
									
								<s:submit align="right" class="btn" onclick="saveTeacher()" value="Save"/>							
							</td>
						</tr>
					</table>
				</div>
				<!-- form -->
				</div>
			</td>
		</tr>
	</table>
	
</body>
</html>