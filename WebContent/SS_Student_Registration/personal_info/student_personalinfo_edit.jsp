<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head />
<s:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">

</head>
<body>
	<s:set var="class_id" value="class_id"/>
	<s:set var="cd_id" value="cd_id"/>
	
	<table width="100%" cellpadding="0" cellspacing="0" border="0">	
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; cursor: pointer;" colspan="2">				
				<img alt="click" src="images/next.gif" width="8px"> Editing Students Personal Information				
			</td>
		</tr>
		<tr>
			<td width="65%">
				
				<s:include value="personalinfo_edit_form.jsp"/>
				
			</td>
			<td align="center" valign="top" style="padding-top: 10px;">
				
				<div id="dropout_div">
					
					<s:include value="../student_dropout_form.jsp"/>
					
				</div>
				<br/>
				<div class="formboarder" id="cl_change" style="padding-top: 10px; padding-bottom: 10px;">
				
					Change The Class Room
					<div id="cdErrMsg" style="color: GREEN"></div>
					<table style="padding-top: 10px;"><tr><td>
						<table><tr><td>
						<s:select label="Class Detail" list="class_detail" id="cd_id" listKey="cd_id" listValue="cd_name" key="cd_id" class="listbox-format"/>
						</td></tr></table>
					</td></tr>
					<tr><td>
						<div class="btn" onclick="changeClassDetail('${siid}', '${class_id}', '${cd_id}')">Change</div>
					</td></tr></table>
					
				</div>
				<br/>
				<div class="formboarder" id="specialNeed" style="padding-top: 10px; padding-bottom: 10px;">
									
					<div id="errMsg" style="color: RED"></div>
					<table>
						<s:radio id="atid" label="Special needs required?" required="true" name="stud_special_need_status" onchange="specialNeedRequired(this.value)" list="#{'1':'Yes', '2':'No'}"/>
					</table>
					<table style="padding-top: 10px;">
						<s:select label="Special needs Category" list="specialNeedsCategoryList" id="snc_id" listKey="snc_id" listValue="pay_amount" key="snc_id" headerKey="0" headerValue="---" class="inputtext-format"/>						
					</table>
					<table>
						<s:set var="need_status" value="stud_special_need_status"/>
						<s:if test="%{#need_status == \"1\"}">
							<s:set var="ssnr_id" value="ssnr_id"/>
<%-- 							<s:submit value="UPDATE" class="btn" onclick="updateSpecialNeedRequirement('${siid}', '${ssnr_id}')" align="center"/> --%>
						</s:if>
						<s:else>
<%-- 							<s:submit value="SAVE" class="btn" onclick="saveSpecialNeedRequirement('${siid}')" align="center"/> --%>
						</s:else>
					</table>
					
				</div>
				
			</td>
		</tr>
	</table>	
	
</body>
</html>