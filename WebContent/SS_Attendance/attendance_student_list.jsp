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

	<s:set var="rNum" value="rowNum"/>
	<s:set var="studCount" value="0"/>	
	<s:set var="listSize" value="listSize"/>
	<s:set var="classSize" value="classSize"/>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>

	<table cellpadding="0" cellspacing="0" width="100%" style="padding-top: 5px;" border="0">
			<s:iterator begin="0" status="stat1" end="%{#rNum-1}">
				<tr>
					<s:iterator begin="0" status="stat2" end="5">
						<s:set var="row" value="%{#stat1.index}"/>
						<s:set var="col" value="%{#stat2.index}"/>					
						<td>												
							<s:if test="%{#classSize > #studCount}">
								<div  style="border: 2px; border-color: silver; border-style: solid; width: 120px; height: 150px;">
									<div style="margin-top: 0px; padding-left: 5px;" align="left">
										<s:property value="%{#studCount + 1}"/>
									</div>
									<s:if test="%{#listSize > #studCount}">
										<table cellpadding="0" cellspacing="0" align="center" style="padding-top: 5px;">
											<tr>
												<td align="center">
													<s:set var="photo_path" value="stud_list[#studCount].photo_path"/>
													<img alt="photo" src="Photos/stud_photo/default.jpg" width="30px">
												</td>
											</tr>
											<tr>
												<td align="center">												
													<s:property value="stud_list[#studCount].fname"/>
												</td>
											</tr>
											<tr>
												<td align="center">
													<s:property value="stud_list[#studCount].mname"/>
												</td>
											</tr>
										</table>
										<s:set var="si_id" value="stud_list[#studCount].si_id"/>
										<div onclick="studAbsent('${si_id}', '${row}', '${col}', '${cl_id}', '${cd_id}')" style="background-color: #3d6e9f; color: #fff; text-align: center; height: 25px; cursor: pointer">
											Absent
										</div>
										<div id="${row}${col}" style="width: auto; text-align: center;"></div>
									</s:if>
								</div>
							</s:if>
						</td>
						<td>&nbsp;</td>					
						<s:set var="studCount" value="%{#studCount + 1}"/>
					</s:iterator>
				</tr>
				<tr><td>&nbsp;</td></tr>
			</s:iterator>
		</table>

</body>
</html>