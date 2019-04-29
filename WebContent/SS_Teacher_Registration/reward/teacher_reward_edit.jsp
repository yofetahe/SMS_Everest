<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<script src="js/sms_teacher_registration.js" type="text/javascript"></script>

</head>
<body>
	
	<s:set var="tiid" value="ti_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px">
				<div style="cursor: pointer" onclick="rewardList('${tiid}')"> <img src="images/next.gif" width="8px"/> Back to Reward List</div>
			</td>
		</tr>
		<tr>
			<td id="reward_saveform">
				<div id="errMsg" style="color: red; text-align: center; width: 40%"></div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<s:fielderror id="rwrdError"/>
				<div style="width: 500px">
					<table><tr><td>
						<s:set var="tr_id" value="tr_id"/>
						<s:textfield id="tr_type" label="Reward Type" required="true" key="tr_type"  style="height: 25px; width: 200px"/>
						<s:textfield id="tr_reason" label="Reward Reason" required="true" key="tr_reason"  style="height: 25px; width: 200px"/>
						<table><tr><td style="font-style: italic; width: 113px; text-align: right;">Reward Date<span style="color:red">*</span>: </td>
								<td><sj:datepicker id="tr_date" name="tr_date"
										displayFormat="yy-mm-dd" readonly="true" 
										maxDate="today" changeMonth="true" 
										changeYear="true" style="height: 25px; width: 200px"></sj:datepicker></td></tr></table>
						<table style="padding-left: 68px;">
						<s:select id="tr_status" label="Status" list="#{'A':'Active', 'I':'Inactive'}" key="tr_status" style="height: 30px; width: 206px"/>
						</table>
						
						<table style="padding-left: 116px;">						
		<tr><td>
			<button onclick="rewardUpdate('${tr_id}', '${tiid}')" class="btn">Update</button>						
		</td></tr>
						</table>
					</td></tr></table>
				</div>
			</td>
		</tr>
	</table>
	
</body>
</html>