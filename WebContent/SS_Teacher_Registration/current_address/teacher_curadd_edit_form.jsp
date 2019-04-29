<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script src="js/sms_teacher_registration.js" type="text/javascript"></script>

</head>
<body>
<div id="curaddFrm">
	<s:fielderror id="tchrcuraddError"/>
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; cursor: pointer;">				
				<img alt="click" src="images/next.gif" width="8px"> Teacher's Current Address			
			</td>
		</tr>
	</table>
	<div id="errMsg" style="color: red; text-align: center; width: 45%;">&nbsp;</div>
	<div style="padding-left: 10px; width: 500px">
		<table align="left">
			<tr>
				<td>
					<div style="width: 100%" align="center">
					<s:set var="successful_save" value="successful_save"/>
					<s:set var="successful_update" value="successful_update"/>
					<s:if test="%{#successful_save == \"true\"}"> <span style="color: #3d6e9f"> Successfully Saved. </span> </s:if>
					<s:if test="%{#successful_update == \"true\"}"> <span style="color: #3d6e9f"> Successfully Updated. </span> </s:if>			
					</div>
					
					<br/>
					
					<s:set var="frmtype" value="frmtype"/>
					<s:set var="tiid" value="ti_id"/>
									
					<s:textfield id="subcity" label="Sub City" required="true" key="sub_city" style="height: 25px; width: 200px"/>
					<s:textfield id="kebele" label="Kebele" required="true" key="kebele" style="height: 25px; width: 200px"/>
					<s:textfield id="houseno" label="House No" required="true" key="house_no" style="height: 25px; width: 200px"/>
					<s:textfield id="housephoneno" label="House Phone No" key="house_phone_no" style="height: 25px; width: 200px"/>
					<s:textfield id="mobileno" label="Mobile No" required="true" key="mobile_no" style="height: 25px; width: 200px"/>
					<s:textfield id="email" label="Email" required="true" key="email" style="height: 25px; width: 200px"/>
					
					<s:if test="%{#frmtype == \"save\"}">	
						<button onclick="saveTeachCurrAdd('${tiid}', '${frmtype}')" class="btn">Save</button>
					</s:if>
					
					<s:if test="%{#frmtype == \"update\"}">
						<s:set var="tcaid" value="tca_id"/>	
						<button onclick="updateTeachCurrAdd('${tcaid}', '${frmtype}')" class="btn">Update</button>
					</s:if>				
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>