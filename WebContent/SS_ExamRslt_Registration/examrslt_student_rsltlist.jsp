<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SMS</title>
<script src="js/sms_system.js" type="text/javascript"></script>

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
                $('#stud_rslt_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

<div id="studPerGrade">
		<!-- this student list include only those students who take at lease one exam type and have mark  -->
		<s:set var="cl_id" value="cl_id"/>
		<s:set var="cd_id" value="cd_id"/>
		<s:set var="cl_name" value="cl_name"/>
		<s:set var="cd_name" value="cd_name"/>
		<s:set var="at_id" value="at_id"/>
		<s:set var="academic_year" value="academic_year"/>
		
		<table><tr><td>
		
			<div class="save_update_btn" onclick="emailQuarterResultToAllStudentParents('${cl_id}', '${cd_id}', '${at_id}', '${cl_name}', '${cd_name}')" style="height: 30px; width: 320px; padding-bottom: 5px; padding-left: 25px; cursor: pointer; text-align: center;">
				<table><tr><td>
					<img alt="email" src="images/email.png" height="30px">
				</td><td valign="middle">	
					Email to All <s:property value="%{#cl_name}"/>-<s:property value="%{#cd_name}"/> Students Parent
				</td></tr></table>
			</div>
		
		</td><td style="width: 100px;">&nbsp;</td><td>
		
			<div class="save_update_btn" onclick="generateAllStudentCertificate('${cl_id}', '${cd_id}', '${at_id}', '${cl_name}', '${cd_name}', '${academic_year}')" style="height: 30px; width: 280px; padding-top: 1px; padding-left: 25px; cursor: pointer; text-align: center;">
				<table><tr><td>
					<img alt="PDF" src="images/pdf.gif" height="25px">
				</td><td valign="middle">
					Generate All Student Certificate
				</td></tr></table>
			</div>
			
		</td></tr></table>
		
		<div style="color: GREEN; width: 100%;"> <s:actionmessage/> </div>
		
		<table id="stud_rslt_list" width="100%" cellpadding="0" cellspacing="1">
			<thead style="background-color: #f5f5f5; height: 30px; color: #000000" align="center">
				<tr height="30px">
					<th width="7%">Rank</th>
					<th width="35%">Student Name</th>
					<th width="10%">Total Mark</th>
					<th width="10%">Average</th>
					<th>Teachers Comment</th>
					<th>Quarter Certificate</th>
				</tr>
			</thead>
			<s:iterator status="stat" value="studTotalMark">
				<tr height="30px">
					<td align="center">
					
						<s:property value="studTotalMark[#stat.index].stud_rank"/>
						
						<s:set var="fname" value="studTotalMark[#stat.index].fname"/>
						<s:set var="mname" value="studTotalMark[#stat.index].mname"/>
						<s:set var="gname" value="studTotalMark[#stat.index].gname"/>						
						<s:set var="siid" value="studTotalMark[#stat.index].si_id"/>
																		
					</td>
					<td>
						<div style="color: #3d6e9f; cursor: pointer" onclick="studRsltDetail('${siid}', '${fname}', '${mname}', '${gname}', '${cl_id}', '${cd_id}', '${cl_name}', '${cd_name}', '${stat.index + 1}')">
							<s:property value="studTotalMark[#stat.index].fname"/> &nbsp; <s:property value="studTotalMark[#stat.index].mname"/> &nbsp; <s:property value="studTotalMark[#stat.index].gname"/>
						</div>									
					</td>
					<td>
						<s:property value="studTotalMark[#stat.index].total_mark"/>							
					</td>
					<td>
						<s:set var="avgMark" value="studTotalMark[#stat.index].average_mark"/>
						<fmt:formatNumber maxIntegerDigits="3" pattern="#.#">${avgMark}</fmt:formatNumber>									
					</td>
					<td align="center">
					
						<table><tr><td>
							
							<div style="cursor: pointer; color: #3d6e9f;" onclick="addTeachersCertificateComment('${siid}', '${at_id}', '${fname}', '${mname}', '${gname}', '${cl_id}', '${cd_id}', '${cl_name}', '${cd_name}')">Add</div>
							
						</td><td style="width: 50px;">&nbsp;</td><td>
							
							<div style="cursor: pointer; color: #3d6e9f;" onclick="editTeachersCertificateComment('${siid}', '${at_id}', '${fname}', '${mname}', '${gname}', '${cl_id}', '${cd_id}', '${cl_name}', '${cd_name}')">Edit</div>
							
						</td></tr></table>
						
					</td>
					<td align="center">
						<div style="cursor: pointer; color: #3d6e9f;" onclick="generateQuarterReportCard('${siid}', '${at_id}', '${fname}', '${mname}', '${gname}', '${cl_id}', '${cd_id}', '${cl_name}', '${cd_name}', '${stat.index + 1}')"> Generate </div>
					</td>							
				</tr>
			</s:iterator>
		</table>
</div>

</body>
</html>