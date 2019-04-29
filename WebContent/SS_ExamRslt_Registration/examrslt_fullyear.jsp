<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                $('#stud_rslt_list').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<s:set var="cl_id" value="cl_id"/>
	<s:set var="cd_id" value="cd_id"/>
	<s:set var="cl_name" value="cl_name"/>
	<s:set var="cd_name" value="cd_name"/>

	<div style="padding-bottom: 5px; padding-top: 5px;">
		<table cellpadding="0" cellspacing="0" width="100%"><tr><td align="left">
		
			<div class="save_update_btn" onclick="generalStudentResultDecision('${cl_id}', '${cd_id}', '${cl_name}', '${cd_name}')" style="height: 30px; width: 260px; padding-bottom: 5px; padding-top: 5px; cursor: pointer; text-align: center;">
				<table align="center"><tr><td>	
					General Student's Result Decision
				</td></tr></table>
			</div>
		
		</td><td style="width: 50px;">&nbsp;</td><td align="center">
		
			<div class="save_update_btn" onclick="generateAllStudentFullYearCertificate('${cl_id}', '${cd_id}', '${cl_name}', '${cd_name}')" style="height: 30px; width: 320px; padding-bottom: 5px; padding-top: 5px; padding-left: 25px; cursor: pointer; text-align: center;">
				<table><tr><td>
					<img alt="PDF" src="images/pdf.gif" height="25px">
				</td><td valign="middle">
					Generate All Student Certificate Content
				</td></tr></table>
			</div>
			
		</td><td style="width:50px;">&nbsp;</td><td align="right">
		
			<div class="save_update_btn" onclick="generateAllStudentFullYearCertificateCover('${cl_id}', '${cd_id}', '${cl_name}', '${cd_name}')" style="height: 30px; width: 320px; padding-bottom: 5px; padding-top: 5px; padding-left: 25px; cursor: pointer; text-align: center;">
				<table><tr><td>
					<img alt="PDF" src="images/pdf.gif" height="25px">
				</td><td valign="middle">
					Generate All Student Certificate Cover 
				</td></tr></table>
			</div>
			
		</td></tr></table>
	</div>

	<div id="certView">		
		
		<table id="stud_rslt_list" width="100%" cellpadding="0" cellspacing="1">
			<thead style="background-color: #f5f5f5; height: 50px; color: #000000" align="center">
				<tr>
					<td width="6%">Rank</td>
					<td width="32%">Student Name</td>
					<td width="10%">1st<br/>Quarter</td>
					<td width="10%">2nd<br/>Quarter</td>
					<td width="10%">3rd<br/>Quarter</td>
					<td width="10%">4th<br/>Quarter</td>
					<td width="12%">Average</td>					
					<td> Decision </td>
				</tr>
			</thead>
			<s:iterator status="stat" value="studFullYearMark">
				<tr height="35px">
					<td align="center">
					
						<s:set var="year_avg_rank" value="%{#stat.index + 1}"/>
						
						<s:property value="studFullYearMark[#stat.index].stud_rank"/>
						
						<s:set var="fname" value="studFullYearMark[#stat.index].fname"/>
						<s:set var="mname" value="studFullYearMark[#stat.index].mname"/>
						<s:set var="gname" value="studFullYearMark[#stat.index].gname"/>						
						<s:set var="siid" value="studFullYearMark[#stat.index].si_id"/>
																		
					</td>
					<td>
					
						<table width="100%" border="0"><tr><td>
							<div style="color: #3d6e9f; cursor: pointer" onclick="certificateResult('${siid}', '${cl_id}', '${cl_name}', '${cd_id}', '${cd_name}', '${fname}', '${mname}', '${gname}', '${year_avg_rank}')">
								<s:property value="studFullYearMark[#stat.index].fname"/> &nbsp; <s:property value="studFullYearMark[#stat.index].mname"/> &nbsp; <s:property value="studFullYearMark[#stat.index].gname"/>
							</div>
						</td><td align="right" width="60px">
							<div style="color: #3d6e9f; cursor: pointer; width: 60px; text-align: right;" onclick="certificateResultCoverPage('${siid}', '${cl_id}', '${cl_name}', '${cd_id}', '${cd_name}', '${fname}', '${mname}', '${gname}', '${year_avg_rank}')">
								[ Cover ]
							</div>
						</td></tr></table>
									
					</td>
					<td align="center">
						<s:property value="studFullYearMark[#stat.index].qrt_one_total"/>								
					</td>
					<td align="center">
						<s:property value="studFullYearMark[#stat.index].qrt_two_total"/>				
					</td>
					<td align="center">
						<s:property value="studFullYearMark[#stat.index].qrt_three_total"/>									
					</td>
					<td align="center">
						<s:property value="studFullYearMark[#stat.index].qrt_four_total"/>									
					</td>
					<td align="center">
						<s:set var="fullYearAvg" value="studFullYearMark[#stat.index].average_quarter_mark"/>
						<s:if test="%{#fullYearAvg != \"NaN\"}">
							<fmt:formatNumber maxIntegerDigits="3" pattern="##.##">${fullYearAvg}</fmt:formatNumber>
						</s:if>
					</td>
					<td align="center">
						<s:set var="studrslt_status" value="studFullYearMark[#stat.index].studRslt_status"/>
						
						<s:if test="%{#fullyearAvg != \"--\"}">
							<s:if test="%{#studrslt_status == \"Active\"}">
<!-- 								<table><tr><td> -->
<%-- 									<s:select list="#{'---':'---', 'Passed':'Passed', 'Failed':'Failed', 'Repeat':'Repeat', 'Back':'Back'}" style="width: 80px;" onchange="SaveFinalStudentAnnualRsltDecision('${siid}', this.value, '${cl_id}', '${cl_name}', '${cd_id}', '${cd_name}')"/> --%>
<!-- 								</td></tr></table> -->
								
								<select style="width: 80px;" onchange="SaveFinalStudentAnnualRsltDecision('${siid}', this.value, '${cl_id}', '${cl_name}', '${cd_id}', '${cd_name}')">
								    <option value="---">---</option>
									<option value="Passed">Passed</option>
								    <option value="Failed">Failed</option>
								    <option value="Repeat">Repeat</option>
								    <option value="Back">Back</option>
								</select>
								
							</s:if>
							<s:else>
<!-- 								<table><tr><td> -->
<%-- 									<s:select list="#{'---':'---', 'Passed':'Passed', 'Failed':'Failed', 'Repeat':'Repeat', 'Back':'Back'}" value="%{#studrslt_status}" style="width: 80px;" onchange="SaveFinalStudentAnnualRsltDecision('${siid}', this.value, '${cl_id}', '${cl_name}', '${cd_id}', '${cd_name}')"/> --%>
<!-- 								</td></tr></table> -->
								
								<select style="width: 80px;" onchange="SaveFinalStudentAnnualRsltDecision('${siid}', this.value, '${cl_id}', '${cl_name}', '${cd_id}', '${cd_name}')">
								    <option value="---">---</option>
									<option value="Passed" <c:if test="${studrslt_status == 'Passed'}"> selected="selected" </c:if> >Passed</option>
								    <option value="Failed" <c:if test="${studrslt_status == 'Failed'}"> selected="selected" </c:if>>Failed</option>
								    <option value="Repeat" <c:if test="${studrslt_status == 'Repeat'}"> selected="selected" </c:if>>Repeat</option>
								    <option value="Back" <c:if test="${studrslt_status == 'Back'}"> selected="selected" </c:if>>Back</option>
								</select>
								
							</s:else>
						</s:if>
					</td>							
				</tr>
			</s:iterator>
		</table>
	</div>
</body>
</html>