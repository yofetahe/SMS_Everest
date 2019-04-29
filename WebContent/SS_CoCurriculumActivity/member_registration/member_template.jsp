<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="../js/sms_cocurriculum_activity.js" type="text/javascript"></script>

</head>
<body>
	<s:set var="clbid" value="clb_id"/>
	<s:set var="clbname" value="memberList[0].clbname"/>
	<div id="memContent">
		<table width="100%" cellpadding="0" cellspacing="0" >
			<tr>
				<td height="40px;" style="background-color: #f5f5f5; color: #3d6e9f;">
					<s:set var="editStatus" value="editStatus"/>
					<s:if test="%{#editStatus == true}">
						<div style="cursor: pointer;" onclick="newMemberCreateForm('${clbid}', '${clbname}')">
							Add New Member
						</div>
					</s:if>
					<s:else>
						<div style="color: #fff">
							Add New Member
						</div>
					</s:else>					
				</td>
			</tr>
			<tr>			
				<td valign="top" style="padding-top: 10px;">
					<s:include value="member_list.jsp"/>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>