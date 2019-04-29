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
<div id="emergcontFrm">
	<s:fielderror id="tchremergcontError"/>
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; cursor: pointer;">				
				<img alt="click" src="images/next.gif" width="8px"> Teacher's Emergency Contact			
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
	<div style="padding-left: 10px; width: 500px">
		<table align="left">
			<tr>
				<td>
					<s:set var="frmtype" value="frmtype"/>
					<s:set var="tiid" value="ti_id"/>
									
					<s:textfield id="contname" label="Contact Name" required="true" key="contact_name" style="height: 25px; width: 200px"/>
					<s:textfield id="rship" label="Relationship" required="true" key="relationship" style="height: 25px; width: 200px"/>
					<s:textfield id="mobileno" label="Mobile No" key="mobile_no" style="height: 25px; width: 200px"/>
					<s:textfield id="homephone" label="Home Phone No" key="home_phone" style="height: 25px; width: 200px"/>
					<s:textfield id="officephone" label="Office Phone No" key="office_phone" style="height: 25px; width: 200px"/>					
					
					<s:if test="%{#frmtype == \"save\"}">
						<button onclick="saveTeachEmrgCont('${tiid}', '${frmtype}')" class="btn">Save</button>
					</s:if>
					
					<s:if test="%{#frmtype == \"update\"}">
						<s:select id="tec_status" label="Status" list="#{'A':'Active', 'I':'Inactive'}" key="tec_status" style="height: 30px; width: 206px"/>
						<s:set var="tecid" value="tec_id"/>
						<table>
		<tr><td>
			<button onclick="updateTeachEmrgCont('${tecid}', '${frmtype}')" class="btn">Update</button>						
		</td></tr></table>	
					</s:if>
				</td>
			</tr>
		</table>
	</div>
</div>
</body>
</html>