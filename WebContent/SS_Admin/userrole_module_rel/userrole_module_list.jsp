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
	<s:set var="ut_id" value="utId"/>
	<s:set var="ut_name" value="ut_name"/>
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td width="47%" valign="top">
				<div style="width: auto; height: 35px; background-color: #f5f5f5; color: #3d6e9f; padding-top: 10px; text-align: center;">
					Assigned Module(s) for <span style="font-variant:small-caps;"><s:property value="ut_name"/></span> Users
				</div>
				<div style="height: 10px;">&nbsp;</div>
				<table cellpadding="0" cellspacing="1" width="100%" rules="rows" bordercolor="#fff">
					<tr style="background-color: #3d6e9f; color: #ffffff; height: 30px;">
						<td align="center">
							No
						</td>
						<td >
							Selected Module
						</td>
						<td align="center">
							Status
						</td>
						<td>&nbsp;</td>
					</tr>
					<s:iterator status="stat" value="modulePerRoleList">
						<tr>
							<td height="30px;" align="center">
								<s:property value="%{#stat.index + 1}"/>
								<s:set var="utm_id" value="modulePerRoleList[#stat.index].utm_id"/>
								<s:set var="m_id" value="modulePerRoleList[#stat.index].m_id"/>
								<s:set var="m_name" value="modulePerRoleList[#stat.index].m_name"/>
							</td>
							<td>
								<s:property value="modulePerRoleList[#stat.index].m_name"/>
							</td>
							<td align="center">
								<s:set var="mrel_status" value="modulePerRoleList[#stat.index].mrel_status"/>
								<s:if test="%{#mrel_status == \"A\"}">Active</s:if>
								<s:else>Inactive</s:else>
							</td>
							<td style="text-align: center; color: #3d6e9f">
								<div onclick="editUserRoleModuleRel('${utm_id}', '${ut_id}', '${m_name}', '${m_id}', '${mrel_status}', '${ut_name}')" style="cursor: pointer">Edit</div>
							</td>
						</tr>
					</s:iterator>
				</table>
				
			</td>
			<td width="6%">&nbsp;</td>
			<td valign="top" width="47%">
				<div style="width: auto; height: 35px; background-color: #f5f5f5; color: #3d6e9f; padding-top: 10px; text-align: center;">
					Adding New/Updating Module(s) for <span style="font-variant:small-caps;"><s:property value="ut_name"/></span> Users
				</div>
				<div style="height: 10px;">&nbsp;</div>
				<div id="ModUteypRel">
					
					<s:fielderror id="errorMsg"/>
					<div id="errMsg">&nbsp;</div>
					<table><tr><td>						
						<s:select id="m_id" label="Module" list="remainingModuleList" listValue="m_name" listKey="m_id" headerValue="----" headerKey="0" style="width: 206px; height: 25px;"/>
						
					</td></tr>
					<tr><td>
						<button onclick="saveUTypeModuleRel('${ut_id}', '${ut_name}')" class="btn">Update</button>						
					</td></tr></table>
					
				</div>
			</td>
		</tr>
	</table>

</body>
</html>