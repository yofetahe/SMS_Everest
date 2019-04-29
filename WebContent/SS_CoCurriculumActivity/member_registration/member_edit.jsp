<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body>
	
	<s:set var="clbid" value="clbid"/>
	<s:set var="cmid" value="cm_id"/>
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 40px; padding-top: 2px;">
				Club Members Edit Form
			</td>
		</tr>
		<tr>
			<td style="padding-top: 30px;">
				<table><tr><td>
					<div style="color: #ff0000"><s:fielderror id="errorMsg"></s:fielderror></div>
					<div id="errMsg" style="color: #ff0000; width: auto; text-align: center;"></div>
					<div class="formboarder">
						<table style="padding-top: 20px; padding-bottom: 20px;">
							<s:set var="cm_id" value="cm_id"/>
							<s:textfield label="Student Name" id="studName" name="mem_name" readonly="true" style="height: 25px; width: 200px;"/>
							<s:textarea label="Reason to join" id="rsn" name="cm_reasontojoin" cols="30" rows="5" style="width: 200px;"/>
<%-- 							<s:textarea label="Evaluation Content" id="eval" name="cm_evaluation" cols="30" style="height: 25px; width: 200px;"/> --%>
							<s:select label="Member Status" list="#{'A':'Active', 'I':'Inactive'}" id="cm_status" name="cm_status" style="height: 25px; width: 205px;"/>
							
							<s:submit onclick="updateClubMember('${cm_id}', '${clbid}')" value="Update"/>
						</table>
					</div>
				</td></tr></table>
			</td>
		</tr>
	</table>
	
</body>
</html>