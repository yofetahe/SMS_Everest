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
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="background-color: #f5f5f5; height: 50px; color: #3d6e9f">
				<!-- class attend -->
				<s:set var="tabnum" value="1"/>
				<table cellpadding="0" cellspacing="0">
					<tr>
						<s:iterator status="stat" value="grdattend_rslt">
							<td style="width: 90px; background-color: silver; cursor: pointer;" align="center">
								<s:set var="clid" value="grdattend_rslt[#stat.index].cl_id"/>
								<s:set var="siid" value="si_id"/>
								<div id="${clid}" onclick="grdAttended('${clid}', '${siid}')" >
									<s:property value="grdattend_rslt[#stat.index].class_name"/>
								</div>
							</td>
							<td width="4px">&nbsp;</td>							
						</s:iterator>
					</tr>
				</table>
				<!-- class attend -->
			</td>
		</tr>
		<tr>
			<td style="padding-top: 5px">
				<s:property value="selTab"/>
				<div id="subject_list">
				
				</div>
				
			</td>
		</tr>
	</table>
</body>
</html>