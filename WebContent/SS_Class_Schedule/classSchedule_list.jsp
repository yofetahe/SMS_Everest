<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

</head>
<body style="margin: 0">

	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td style="height: 35px">
				<div class="menu_shadow" style="height: 35px; padding-top: 15px; background-position: bottom;">
				<div style="color: #3d6e9f; cursor: pointer; padding-left: 5px;"> Class Schedule List </div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px">
				<div class="content_background"  style="min-height: 434px">
				
					<table cellpadding="0" cellspacing="0" width="100%" border="0">
						<tr>
							<td width="15%" valign="top">
							
								<table width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td>
										<div class="menu_button" style="text-align: center; background-color: #f5f5f5;" onclick="general_clSchedule()">
											General Schedule
										</div>
										</td>
									</tr>
									<tr>
										<td style="line-height: 10px">&nbsp;</td>
									</tr>
									<tr>
										<td>
										<div class="menu_button" style="text-align: center; background-color: #f5f5f5;" onclick="teacher_clSchedule()">
											Teachers Schedule
										</div>
										</td>
									</tr>
									<tr>
										<td style="line-height: 10px">&nbsp;</td>
									</tr>
									<tr>
										<td style="cursor: auto;">
										<div class="menu_button" style="text-align: center; background-color: #f5f5f5; cursor: auto;">
											Per Class Schedule
										</div>
										</td>
									</tr>
								</table>
							
								<table border="0" width="100%" cellpadding="0" cellspacing="0">
									<tr>
										<td style="background-color: #3d6e9f; color: #ffffff; height: 35px; text-align: center;">
											Class Category
										</td>
									</tr>
									<tr>
										<td style="line-height: 3px">&nbsp;</td>
									</tr>
									<s:iterator status="stat" value="class_rslt">
										<tr>
											<td align="center" onmouseover="this.style.color = '#3d6e9f'" onmouseout="this.style.color = '#000000'">
												<s:set var="clid" value="class_rslt[#stat.index].cl_id"/>
												<s:set var="clname" value="class_rslt[#stat.index].class_name"/>
												<div class="menu_button" id="${clid}" onclick="clSchedule_gradeSelected('${clid}', '${clname}')">
													<s:property value="class_rslt[#stat.index].class_name"/>																
												</div>
											</td>
										</tr>
										<tr>
											<td style="line-height: 3px">&nbsp;</td>
										</tr>														
									</s:iterator>
								</table>
							
							</td>
							<td width="1%">
								&nbsp;
							</td>
							<td valign="top">
							
								<div id="classScheduleList">
					
								</div>			
							
							</td>
						</tr>
					</table>			
				
				</div>
			</td>
		</tr>
	</table>

</body>
</html>