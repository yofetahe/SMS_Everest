<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<style type="text/css">
	#title{
		color: #3d6e9f;
		width: auto;
		height: 25px;
		padding-top: 5px;
	}
	
	#content{
		height: 35px;
		vertical-align: top;
	}
	
	.oddraw{
		background-color: #CDDEEB;
	}
</style>

</head>
<body>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<table width="100%" cellpadding="0" cellspacing="0" style="padding-top: 5px;">
		<tr>
			<td style="background-color: #f5f5f5; height: 35px; padding-top: 5px">
				<div style="cursor: pointer; color: #3d6e9f; padding-left: 10px;">
					Schedule Add Form
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-top: 10px;">
				<div id="errMsg" style="color: red"></div>
				<table cellpadding="0" cellspacing="0"><tr><td style="padding-left: 10px;">
					<table>
					<s:select list="year" label="Academic Year" id="ac_year" style="width: 150px; height: 20px; padding-left: 10px;"/>
					</table>
				</td><td>&nbsp;</td><td style="padding-left: 30px;">
					<div style="cursor: pointer; color: #3d6e9f;" onclick="subjectAssignmentSuggestion('${cl_id}', '${cd_id}')">Subject Assignment Suggestion</div>
				</td></tr></table>
				
				<div id="suggestedClassSchedule"> </div>
				
				<s:set var="noofperiod" value="cs_info[0].noof_period"/>
				<s:set var="noofdays" value="cs_info[0].noof_days"/>
				<s:set var="rcount" value="0"/>
				
				<table width="100%" style="padding-top: 10px;">
					<tr style="height: 40px; background-color: #f5f5f5; color: #3d6e9f;">
						<td >
							&nbsp;
						</td>
						<s:iterator status="stat" begin="1" end="%{#noofperiod}">
							<td align="center">
								Period <s:property value="%{#stat.index + 1}"/>
							</td>
						</s:iterator>
					</tr>
					<s:iterator status="wday" begin="1" end="%{#noofdays}">
						<tr style="height: 60px;">
							<td align="left" width="50px" style="background-color: #f5f5f5; height: 150px; padding-left: 5px; color: #3d6e9f;">
								<s:if test="%{#wday.index == 0}">MON</s:if>
								<s:elseif test="%{#wday.index == 1}">TUE</s:elseif>
								<s:elseif test="%{#wday.index == 2}">WEN</s:elseif>
								<s:elseif test="%{#wday.index == 3}">THU</s:elseif>
								<s:elseif test="%{#wday.index == 4}">FRI</s:elseif>
								<s:elseif test="%{#wday.index == 5}">SAT</s:elseif>
								<s:elseif test="%{#wday.index == 6}">SUN</s:elseif>
							</td>
							<s:iterator status="period" begin="1" end="%{#noofperiod}">
								<td <s:if test="%{#rcount%2 != 0 }">class="oddraw"</s:if> style="border-color: #f5f5f5; border-style: solid; border-width: thin; text-align: center; vertical-align: top; padding-top: 10px;" width="14%">
									
									<s:set var="wdays" value="%{#wday.index + 1}"/>
									<s:set var="prd" value="%{#period.index + 1}"/>
									
									<div id="<s:property value="%{#wdays+''+#prd+''+#prd}"/>">
										<div id="title">Subject</div>
										<div id="content"> 
										
<!-- 											<table><tr><td> -->
<%-- 												<s:select id="subid" list="clSubRelList" listKey="sub_id" headerValue=" " headerKey="0" listValue="sub_name" style="width: 100px" onchange="teacherForSelectedSub(this.value, '${prd}', '${wdays}', '${cl_id}', '${cd_id}')"/> --%>
<!-- 											</td></tr></table> -->
											
											<select id="subid" onchange="teacherForSelectedSub(this.value, '${prd}', '${wdays}', '${cl_id}', '${cd_id}')" style="width: 100px">
												<option value="0"> </option>
												<c:forEach var="clSubRelList" items="${clSubRelList}">
													<option value="${clSubRelList.sub_id}">${clSubRelList.sub_name}</option>
												</c:forEach>
											</select>
	
										</div>
										
										<div id="title">Teacher</div>
										<div id="content">
											<div  id="t<s:property value="%{#wdays+''+#prd}"/>">
												<table><tr><td>
													<s:select id="taid" list="teach_list" headerValue=" " headerKey="0" listValue="sub_name" listKey="ta_id" style="width: 100px"/>
												</td></tr></table>
											</div>
										</div>
									</div>
									
								</td>
							</s:iterator>						
						</tr>
						<tr>
							<td colspan="8" align="center">
								<div id="<s:property value="%{#wdays+''+#prd+''+#wdays}"/>"></div>
								<div onclick="saveAcademiceCalendarSchedule('${wdays}', '${prd}', '${cd_id}')" style="width: 200px; text-align: center; background-color: #f5f5f5; height: 25px; padding-top: 5px; color: #3d6e9f; cursor: pointer;" align="center">
									Save
								</div>
							</td>
						</tr>
						<s:set var="rcount" value="%{#rcount+1}"/>
					</s:iterator>
				</table>
				
			</td>
		</tr>		
	</table>

</body>
</html>