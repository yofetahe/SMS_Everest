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
                $('#exam_schedule_report').dataTable( {
                    "sDom": 'T<"clear">rt'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div style="width: 70%">
	<table cellpadding="0" cellspacing="0" id="exam_schedule_report" border="0" width="100%" > <thead> <tr> <td> 
	<table cellpadding="0" cellspacing="0" align="center" width="100%">
		<tr>
			<td align="center" style="font-weight: bold; font-size: 35px;">
				<s:text name='global.schoolname'/>
			</td>
		</tr>
		<tr>
			<td align="center">
				<s:text name='global.schooladdress'/> 
			</td>
		</tr>
		<tr><td align="center"> &nbsp; </td></tr>
		<tr>
			<td align="center" style="font-weight: bold; font-size: 30px;">
				<s:set var="et_name" value="exam_schedule_report[0].et_name"/>
				<s:set var="at_name" value="exam_schedule_report[0].at_name"/>
			 	<s:property value="%{#at_name}"/> <s:if test="%{#et_name == \"Exam 1\"}">First Test</s:if> <s:elseif test="%{#et_name == \"Exam 2\"}">Second Test</s:elseif> <s:else>Final Exam</s:else> Time Table
			</td>
		</tr>
		<tr>
			<td align="center" style="font-weight: bold;">
				From Grade 1 to Grade 9
			</td>
		</tr>
		<tr><td align="center"> &nbsp; </td></tr>
		<tr>
			<td style="border-bottom: thin; border-bottom-color: black; border-bottom-style: solid; border-bottom-width: thin;">
			
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr style="height: 40px; text-align: center; background-color: #f5f5f5;">
					<td style="border-bottom: thin; border-bottom-color: black; border-bottom-style:double; border-bottom-width:thin; border-top: thin; border-top-color: black; border-top-style:double; border-top-width:thin;">
						Exam Date
					</td>
					<td style="border-bottom: thin; border-bottom-color: black; border-bottom-style:double; border-bottom-width:thin; border-top: thin; border-top-color: black; border-top-style:double; border-top-width:thin;">
						Subject and Class
					</td>
					<td style="border-bottom: thin; border-bottom-color: black; border-bottom-style:double; border-bottom-width:thin; border-top: thin; border-top-color: black; border-top-style:double; border-top-width:thin;">
						Exam Hours
					</td>
				</tr>
				<s:iterator status="stat" value="organize_exam_schedule_report">
					<s:set var="pre_exam_date" value=""/>
					<s:set var="cur_exam_date" value="organize_exam_schedule_report[#stat.index].es_ethio_date"/>
					<s:if test="%{#stat.index != 0}">							
						<s:set var="pre_exam_date" value="organize_exam_schedule_report[#stat.index - 1].es_ethio_date"/>							
					</s:if>
					
					<s:if test="%{#stat.index == 0}">
						<tr><td colspan="3" style="padding-top: 10px;">
						<s:property value="%{#cur_exam_date}"/> (<s:property value="organize_exam_schedule_report[#stat.index].day_of_week"/>)
						</td></tr>
					</s:if>
					<s:elseif test="%{#pre_exam_date != #cur_exam_date}">
						<tr><td colspan="3" style="padding-top: 10px; border-top: thin; border-top-color: black; border-top-style: solid; border-top-width: thin;">
						<s:property value="%{#cur_exam_date}"/> (<s:property value="organize_exam_schedule_report[#stat.index].day_of_week"/>)
						</td></tr>
					</s:elseif>
					<tr>
						<td width="140px" height="50px;">&nbsp;</td>
						<td width="500px;" style="border-bottom: thin; border-bottom-color: black; border-bottom-style: solid; border-bottom-width: thin;"> 
						   	<b><s:property value="organize_exam_schedule_report[#stat.index].sub_name"/> </b> <br/> ( <s:property value="organize_exam_schedule_report[#stat.index].cl_name"/> )
						</td>
						<td align="center" width="100px;" style="border-bottom: thin; border-bottom-color: black; border-bottom-style: solid; border-bottom-width: thin;"> 
							<s:property value="organize_exam_schedule_report[#stat.index].time_from"/> - <s:property value="organize_exam_schedule_report[#stat.index].time_to"/> 
						</td>
					</tr>
				</s:iterator>
			</table>
			
			</td>
		</tr>
	</table>
	</td> </tr> </thead><tr><td height="0px;"> &nbsp; </td></tr> </table>
</div>

</body>
</html>