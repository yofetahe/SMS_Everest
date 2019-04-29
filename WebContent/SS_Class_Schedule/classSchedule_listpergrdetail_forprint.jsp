<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>

<!-- Datatable -->
        <style type="text/css" title="currentStyle">
            @import "datatable/datatable_2/media/css/demo_page.css";
            @import "datatable/datatable_2/media/css/demo_table.css";
            @import "media/css/TableTools.css";
        </style>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.js"></script>
        <script type="text/javascript" charset="utf-8" src="datatable/datatable_2/media/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/ZeroClipboard.js"></script>
        <script type="text/javascript" charset="utf-8" src="media/js/TableTools.js"></script>
        <script type="text/javascript" charset="utf-8">
            $(document).ready( function () {
                $('#scheduletable').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<s:set var="noofperiod" value="cs_info[0].noof_period"/>
	<s:set var="noofdays" value="cs_info[0].noof_days"/>
	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<s:set var="ac_year" value="academic_year"/>
	<s:set var="noofcopies" value="noofcopies"/>
	
	<table id="scheduletable" width="100%" cellpadding="0" cellspacing="0">
		<thead>
		<tr><td>&nbsp;</td></tr>
		</thead>
		<tr><td>&nbsp;</td></tr>
		<s:iterator begin="0" end="%{#noofcopies-1}">
			<tr><td>
			<table width="100%" style="padding-top: 10px; background-color: #fff;">
				<s:iterator status="stat" value="sorted_classSchedule">
					<tr>
						<s:iterator status="st" value="sorted_classSchedule[#stat.index]">
						
							<td <s:if test="%{#stat.index == \"0\" || #st.index == \"0\"}">style="background-color: #f5f5f5; height: 50px; padding-left: 5px; text-align: center; color: #3d6e9f; width: 15px;"</s:if><s:else>style="border-color: #f5f5f5; border-style: solid; border-width: thin; text-align: center; vertical-align: top; padding-top: 10px; width: 15px;"</s:else>>
								<s:property value="sorted_classSchedule[#stat.index][#st.index]"/>
							</td>
						
						</s:iterator>
					</tr>
				</s:iterator>
			</table>
			</td></tr>
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
<!-- 				<td align="left" width="50px" style="background-color: #f5f5f5; height: 50px; padding-left: 5px; color: #3d6e9f;"> -->
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
<%-- 										<div id="content"><s:property value="cs_detail[#dtl.index].sub_name"/></div>										 --%>
<!-- 									</div>					 -->
														
<%-- 								</s:if> --%>
<%-- 							</div> --%>
<%-- 						</s:iterator> --%>
						
<!-- 					</td> -->
<%-- 				</s:iterator>						 --%>
<!-- 			</tr>			 -->
<%-- 			<s:set var="rcount" value="%{#rcount+1}"/> --%>
<%-- 		</s:iterator> --%>
<!-- 	</table> -->

</body>
</html>