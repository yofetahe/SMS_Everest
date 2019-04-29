<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head />
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
			maxDate: "-1096D"
		});		
	});	
</script>

<style type="text/css">
.separator{
	border-bottom: thin; border-bottom-color: #e5e5e5; border-bottom-style: solid; border-bottom-width: thin; 
	width: 100%; line-height: 30px; padding-top: 5px; margin-bottom: 10px;
}
</style>

</head>
<body>

	<div id="studSaveForm">
	<s:form action="saveStudentProfileInformation" enctype="multipart/form-data" method="post">
	
		<table width="100%" cellpadding="0" cellspacing="0">	
			<tr>
				<td style="background-color: #f5f5f5; height: 50px; cursor: pointer;">
					<img alt="click" src="images/next.gif" width="8px"> <span onclick="addStudPersonalInfoFromExcel()">Adding Students Personal Information From Excel</span>				
				</td>
			</tr>
			<tr>
				<td id="info_saveform">
				
					<div style="color: GREEN; padding-left: 200px;">
						<s:actionmessage id="sucMsg"/>
					</div>
					
					<table width="100%"><tr><td width="49%" valign="top">
					
						<div class="separator" >
							<b>Students Personal Information Add Form</b>
						</div>
						
						<div style="min-height: 40px; padding-top: 10px; width: 100%; margin-bottom: 10px;" align="center">
							<div><img alt="photo" src="images/default.jpg" width="100px;"></div>
							<table><s:file name="fileUpload" label="Select Student Photo" required="true" size="30"/></table>
						</div>
												
						<table width="90%"><tr><td align="right" style="padding-right: 30px;">				
						
							<table><s:textfield id="fname" label="Frist Name" name="fname" required="true" class="inputtext-format"/></table>
							<table><s:textfield id="mname" label="Middle Name" name="mname" required="true" class="inputtext-format"/></table>
							<table><s:textfield id="gname" label="Grand Father Name" name="gname" required="true" class="inputtext-format"/></table>
							<table><s:textfield id="mothername" label="Mother Name" name="mother_name" required="true" class="inputtext-format"/></table>
							<table><s:select id="sex" label="Sex" name="sex" headerValue="Please select sex" headerKey="0" required="true" list="#{'M':'Male', 'F':'Female'}" class="listbox-format"/></table>
							<table><s:textfield id="dob" label="Date of Birth" name="dob" required="true" class="inputtext-format"/></table>
							<table><s:textfield id="pob" label="Place of Birth" name="pob" required="true" class="inputtext-format"/></table>
							<table><s:textfield id="nat" label="Nationality" name="nationality" value="Ethiopian" required="true" class="inputtext-format"/></table>
							
						</td></tr></table>
						
						<div style="margin-top: 10px;">
							<div class="separator">
								<b>Does the student has a brother or sister in the school?</b>
							</div>
							<div>
								<table width="90%"><tr><td align="right" style="padding-right: 30px;">				
									
									<table><s:textfield id="broSisNumber" label="Number of brother and sister" name="bro_sis_number" class="inputtext-format"/></table>
									
								</td></tr></table>
							</div>
						</div>
											
					</td><td>&nbsp;</td><td width="45%" valign="top">
	
						<div style="margin-bottom: 10px;">
							<div class="separator">
								<b>Student's Parent/Guardian/Emergency Contact (1)</b>
							</div>
							<div>
								<table width="90%"><tr><td align="right" style="padding-right: 23px;">				
															
									<table><s:textfield id="contact_name" label="Contact Name" name="emergencyContactBean.contact_name" required="true" class="inputtext-format"/></table>
									<table><s:select id="relationship" label="relationship" name="emergencyContactBean.relationship" headerValue="-" headerKey="0" required="true" list="#{'Father':'Father', 'Mother':'Mother', 'Guardian':'Guardian'}" class="listbox-format"/></table>
									<table><s:textfield id="mob_no" label="Mobile Number" name="emergencyContactBean.mob_no" required="true" key="mobile_no" class="inputtext-format"/></table>
									<table><s:textfield id="offphone_no" label="Office Phone Number" name="emergencyContactBean.office_phone_no" class="inputtext-format"/></table>
									<table><s:textfield id="homephone_no" label="Home Phone Number" name="emergencyContactBean.home_phone_no" class="inputtext-format"/></table>
									
									<%-- <table><s:checkbox id="sameAsEmergency" name="emergency" label="is the same as emergency contract?"/></table> --%>
									
								</td></tr></table>
							</div>
						</div>
						
						<div>
							<div class="separator">
								<b>Student's Parent/Guardian/Emergency Contact (2)</b>
							</div>
							<div>
								<table width="90%"><tr><td align="right" style="padding-right: 23px;">				
															
									<table><s:textfield id="contact_name_2" label="Contact Name" name="emergencyContactBean.contact_name_2" required="true" key="contact_name" class="inputtext-format"/></table>
									<table><s:select id="relationship_2" label="relationship" name="emergencyContactBean.relationship_2" headerValue="-" headerKey="0" required="true" list="#{'Father':'Father', 'Mother':'Mother', 'Guardian':'Guardian'}" class="listbox-format"/></table>
									<table><s:textfield id="mob_no_2" label="Mobile Number" required="true" name="emergencyContactBean.mob_no_2" class="inputtext-format"/></table>
									<table><s:textfield id="offphone_no_2" label="Office Phone Number" name="emergencyContactBean.office_phone_no_2" class="inputtext-format"/></table>
									<table><s:textfield id="homephone_no_2" label="Home Phone Number" name="emergencyContactBean.home_phone_no_2" class="inputtext-format"/></table>
									
									<%-- <table><s:checkbox id="sameAsEmergency_2" name="emergency_2" label="is the same as emergency contract?"/></table> --%>
									
								</td></tr></table>
							</div>
						</div>
						
						
						<div style="margin-top: 10px;">
							<div class="separator">
								<b>Does the student needs special medical attention?</b>
							</div>
							<div>
								<table width="90%"><tr><td align="right" style="padding-right: 30px;">				
									
									<table> <s:textarea id="specialAttentionInfo" label="Explanations" name="special_attention_info" rows="20" cols="40" class="inputtext-format"></s:textarea> </table>
									
								</td></tr></table>
							</div>
						</div>
			
					</td></tr></table>
					
					<div class="separator">&nbsp;</div>
					
					<div align="center" style="width: 100%;">
						
							<!-- onclick="saveStudentInfo()" -->
							<button class="btn" id="saveButton" onclick="saveStudentInfo()">SAVE STUDENT PROFILE INFORMATION</button>				
						
					</div>
				
				</td>
			</tr>
		</table>
	
	</s:form>
	</div>

</body>
</html>