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

</head>
<body>
<div id="reward_saveform">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; color: #3d6e9f; cursor: pointer;">
				<div onclick="backToRewardList('${siid}')">
					<img alt="click" src="images/next.gif" width="8px"> Back to Reward List
				</div>
			</td>
		</tr>
		<tr>
			<td>
				&nbsp;
			</td>
		</tr>
		<tr>
			<td >
				<div id="errMsg" style="color: red; text-align: center;"></div>
			</td>
		</tr>
		<tr>
			<td>
			
				<table><tr><td align="left">
				
					<s:set var="siid" value="si_id"/>
					<s:textfield id="rw_type" label="Reward Type" required="true" key="reward_type" style="height: 25px; width: 200px"/>
					<s:textfield id="rw_reason" label="Reward Reason" required="true" key="reward_reason" style="height: 25px; width: 200px"/>
					<table><tr><td style="font-style: italic; width: 113px; text-align: right;">Reward Date<span style="color:red">*</span>: </td>
								<td><sj:datepicker id="rw_date" name="reward_date"
										displayFormat="yy-mm-dd" readonly="true" 
										maxDate="today" changeMonth="true" 
										changeYear="true" style="height: 25px; width: 200px"></sj:datepicker></td></tr></table>					
				</td></tr>
		<tr><td>
			<button onclick="saveReward('${siid}')" class="btn">Save</button>						
		</td></tr></table>
				
			</td>
		</tr>
	</table>
</div>

</body>
</html>