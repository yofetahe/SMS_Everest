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
	<s:set var="cl_id" value="cl_id"/>
	<table cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<td>
				<table width="100%" cellpadding="0" cellspacing="0"><tr><td>
				
					<table cellpadding="0" cellspacing="0" >
						<tr>
							<s:iterator status="stat" value="class_detail">
								<td width="80px" height="25px" align="center">
									<s:set var="cdid" value="class_detail[#stat.index].cd_id"/>
									<s:set var="cl_name" value="cl_name"/>
									<s:set var="cd_name" value="class_detail[#stat.index].cd_name"/>
									<div class="save_update_btn" id="${cdid}_cd" style="height: 25px; width: 150px; padding-top: 7px; cursor: pointer"  onclick="parentListPerClassDetail('${cl_id}', '${cdid}', '${cl_name}', '${cd_name}')">
										<s:property value="cl_name"/> - <s:property value="class_detail[#stat.index].cd_name"/>
									</div>
								</td>
								<td>&nbsp;</td>
							</s:iterator>
						</tr>
					</table>
				
				</td></tr></table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="stud_parent_list" style="padding-top: 4px">
				
				</div>
			</td>
		</tr>
	</table>
</body>
</html>