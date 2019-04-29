<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<link rel="stylesheet" type="text/css" href="css/sms_css.css">
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
                $('#clstudlist').dataTable( {
                    //"sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->
</head>
<body>

	<s:set var="cl_id" value="cl_id"/>
	<s:set var="exsub_id" value="exsub_id"/>
	<s:set var="subcl_id" value="subcl_id"/>

	<s:set var="rwcount" value="0"/>
	<s:set var="examDateStatus" value="clstudlist_rslt[0].examDate_status"/>
	
	<table  width="100%" cellpadding="0" cellspacing="0" style=" padding-top: 5px;">
		<tr>
			<td>
				<div>
				<table id="clstudlist" width="100%" cellpadding="0" cellspacing="1" border="0">
					<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
						<tr>
							<td width="10%">No</td>
							<td width="50%">Student Name</td>
							<td>Exam Mark</td>
							<td>Out Of</td>
						</tr>
					</thead>
					<s:iterator status="stat" value="clstudlist_rslt">
						<tr height="30px">
						 	<td align="center">
						 		<s:property value="#stat.index + 1"/>
						 	</td>
						 	<td>
						 		<s:property value="clstudlist_rslt[#stat.index].fname"/> <s:property value="clstudlist_rslt[#stat.index].mname"/> <s:property value="clstudlist_rslt[#stat.index].gname"/>
						 		<s:set var="si_id" value="clstudlist_rslt[#stat.index].si_id"/>
						 		<s:set var="total_mark" value="clstudlist_rslt[#stat.index].total_mark"/>
						 	</td>
						 	<td align="center">
						 		<div id="strslt_${stat.index}"></div>					 		
						 		<s:set var="result" value="clstudlist_rslt[#stat.index].result"/>								 		
						 		<div id="rsltFrm_${stat.index}">							 		
					 				
<!-- 					 				<table><tr><td> -->
<%-- 					 					<s:textfield id="rslt_%{#stat.index}" value="%{#result}"  --%>
<%-- 					 					onchange="checkStudExamRslt(this.value, '${si_id}', '${exsub_id}', '${stat.index}', '${total_mark}')"  --%>
<%-- 					 					style="height: 20px; width: 100px"/> --%>
<!-- 						 			</td></tr></table> -->
						 			
						 			<input id="rslt_${stat.index}" value="${result}" onchange="checkStudExamRslt(this.value, '${si_id}', '${exsub_id}', '${stat.index}', '${total_mark}')" 
						 			style="height: 20px; width: 100px">
						 			
					 			</div>	
							</td>
							<td align="center">
								<s:property value="clstudlist_rslt[#stat.index].total_mark"/>								
							</td>
						</tr>
						<s:set var="rwcount" value="%{#rwcount + 1}"/>
					</s:iterator>
				</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td> 
		</tr>
		<s:if test="%{#rwcount > 0 && #examDateStatus == \"true\"}">
			<tr>
				<td style="padding-top: 3px; padding-bottom: 3px" align="center">
					<div id="examResultSaveButton" class="save_update_btn" style="background-color: #f5f5f5; cursor: pointer; color: #3d6e9f; width: 300px; height: 25px; padding-top: 5px;">
						<div onclick="saveExamResult('${cl_id}', '${rwcount}', '${exsub_id}', '${totalMark}')">SAVE RESULT</div>
					</div>
				</td>
			</tr>
		</s:if>
	</table>

</body>
</html>