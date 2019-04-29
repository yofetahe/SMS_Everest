<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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

	<s:set var="noofperiod" value="cs_info[0].noof_period"/>
	<s:set var="noofdays" value="cs_info[0].noof_days"/>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<s:set var="ac_year" value="academic_year"/>
	
	<table width="100%" style="padding-top: 10px;">
		<s:iterator status="stat" value="sorted_classScheduleDetail">
			<tr>
				<s:set var="wdays" value="%{#stat.index}"/>
				<s:iterator status="st" value="sorted_classScheduleDetail[#stat.index]">
					<s:set var="prd" value="%{#st.index}"/>
					<s:if test="%{#stat.index == \"0\"}">
						<td style="border-color: #f5f5f5; background-color: #f5f5f5; border-style: solid; border-width: thin; text-align: center; vertical-align: top; padding-top: 10px;">
							<s:property value="sorted_classScheduleDetail[#stat.index][#st.index].period_name"/>
						</td>
					</s:if>
					<s:elseif test="%{#stat.index != \"0\" && #st.index == \"0\"}">
						<td style="border-color: #f5f5f5; background-color: #f5f5f5; border-style: solid; border-width: thin; text-align: center; vertical-align: middle; padding-top: 10px;">
							<s:property value="sorted_classScheduleDetail[#stat.index][#st.index].day_name"/>
						</td>
					</s:elseif>
					<s:else>
						<td style="border-color: #f5f5f5; border-style: solid; border-width: thin; text-align: center; vertical-align: top; padding-top: 10px; width: 13%;">
							<div id="<s:property value="%{#wdays+''+#prd+''+#prd}"/>">
								
								<s:set var="cs_id" value="sorted_classScheduleDetail[#stat.index][#st.index].cs_id"/>
								<table cellpadding="0" cellspacing="0" width="100%"><tr><td>
									<div id="title">Subject</div>
								</td></tr><tr><td>
									<%-- <div id="content"><s:property value="sorted_classScheduleDetail[#stat.index][#st.index].sub_name"/></div> --%>
									<s:property value="sorted_classScheduleDetail[#stat.index][#st.index].sub_name"/>
								</td></tr><tr><td>
									<div id="title">Teacher</div>
								</td></tr><tr><td>
									<div id="content"><s:property value="sorted_classScheduleDetail[#stat.index][#st.index].fullName"/> </div>
								</td></tr><tr><td>
									<div id="title" style="cursor: pointer; width: auto; background-color: #3d6e9f; color: #fff" onclick="editSelectedSchedule('${prd}', '${wdays}', '${cl_id}', '${cd_id}', '${cs_id}', '${ac_year}')">Edit</div>
								</td></tr></table>
								
							</div>
						</td>
					</s:else>
				</s:iterator>
			</tr>
		</s:iterator>
	</table>
	
<%-- 	<s:set var="rcount" value="0"/> --%>
<!-- 	<table width="100%" style="padding-top: 10px;"> -->
<!-- 		<tr style="height: 40px; background-color: #f5f5f5; color: #3d6e9f;"> -->
<!-- 			<td > -->
<!-- 				&nbsp; -->
<!-- 			</td> -->
<%-- 			<s:iterator status="stat" begin="1" end="%{#noofperiod}"> --%>
<!-- 				<td align="center"> -->
<%-- 					Period <s:property value="%{#stat.index + 1}"/> --%>
<!-- 				</td> -->
<%-- 			</s:iterator> --%>
<!-- 		</tr> -->
<%-- 		<s:iterator status="wday" begin="1" end="%{#noofdays}"> --%>
<!-- 			<tr style="height: 60px;"> -->
<!-- 				<td align="left" width="50px" style="background-color: #f5f5f5; height: 150px; padding-left: 5px; color: #3d6e9f;"> -->
<%-- 					<s:if test="%{#wday.index == 0}">MON</s:if> --%>
<%-- 					<s:elseif test="%{#wday.index == 1}">TUE</s:elseif> --%>
<%-- 					<s:elseif test="%{#wday.index == 2}">WEN</s:elseif> --%>
<%-- 					<s:elseif test="%{#wday.index == 3}">THU</s:elseif> --%>
<%-- 					<s:elseif test="%{#wday.index == 4}">FRI</s:elseif> --%>
<%-- 					<s:elseif test="%{#wday.index == 5}">SAT</s:elseif> --%>
<%-- 					<s:elseif test="%{#wday.index == 6}">SUN</s:elseif> --%>
<!-- 				</td> -->
<%-- 				<s:iterator status="period" begin="1" end="%{#noofperiod}"> --%>
<!-- 					<td <s:if test="%{#rcount%2 != 0 }">class="oddraw"</s:if> style="border-color: #f5f5f5; border-style: solid; border-width: thin; text-align: center; vertical-align: top; padding-top: 10px;" width="14%"> -->
					
<%-- 						<s:set var="wdays" value="%{#wday.index + 1}"/> --%>
<%-- 						<s:set var="prd" value="%{#period.index + 1}"/> --%>
							
<%-- 						<s:iterator status="dtl" value="cs_detail"> --%>
<%-- 							<s:div> --%>
<%-- 								<s:set var="wd" value="cs_detail[#dtl.index].week_day"/> --%>
<%-- 								<s:set var="prd_val" value="cs_detail[#dtl.index].period"/> --%>
								
<%-- 								<s:if test="%{#wdays == #wd && #prd_val == #prd}"> --%>
								
<%-- 									<div id="<s:property value="%{#wd+''+#prd+''+#prd}"/>"> --%>
<%-- 										<s:set var="cs_id" value="cs_detail[#dtl.index].cs_id"/> --%>
<!-- 										<div id="title">Subject</div> -->
<%-- 										<div id="content"><s:property value="cs_detail[#dtl.index].sub_name"/></div> --%>
<!-- 										<div id="title">Teacher</div> -->
<%-- 										<div id="content"><s:property value="cs_detail[#dtl.index].tfname"/> <s:property value="cs_detail[#dtl.index].tmname"/></div> --%>
<%-- 										<div id="title" style="cursor: pointer; width: auto; background-color: #3d6e9f; color: #fff" onclick="editSelectedSchedule('${prd}', '${wdays}', '${cl_id}', '${cd_id}', '${cs_id}', '${ac_year}')">Edit</div> --%>
<!-- 									</div>					 -->
														
<%-- 								</s:if> --%>
<%-- 							</div> --%>
<%-- 						</s:iterator> --%>
						
<!-- 					</td> -->
<%-- 				</s:iterator>						 --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td colspan="8" align="center" height="1px"> -->
<!-- 					&nbsp; -->
<!-- 				</td> -->
<!-- 			</tr> -->
<%-- 			<s:set var="rcount" value="%{#rcount+1}"/> --%>
<%-- 		</s:iterator> --%>
<!-- 	</table> -->

</body>
</html>