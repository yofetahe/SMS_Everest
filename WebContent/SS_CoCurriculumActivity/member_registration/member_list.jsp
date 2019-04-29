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
                $('#memlist').dataTable( {
                    "sDom": 'T<"clear">lfrtip'
                } );
            } );
        </script>
<!-- Datatable -->

</head>
<body>

	<s:set var="clbid" value="clb_id"/>
	
	<table id="memlist" width="100%" cellpadding="0" cellspacing="1">
		<thead style="background-color: #f5f5f5; height: 30px; text-align: center;">
			<tr>
				<td style="height: 30px;" width="5%">No</td>
				<td>Member Name</td>
				<td>Club Name</td>
				<td>Reason to Join</td>
				<td>Member Evaluation</td>
				<td>Member Status</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</thead>
		<s:iterator status="stat" value="memberList">
			<tr style="height: 30px" valign="top">
				<td align="center">
					<s:property value="%{#stat.index + 1}"/>
					<s:set var="cmid" value="memberList[#stat.index].cm_id"/>
				</td>
				<td>
					<s:property value="memberList[#stat.index].mem_name"/>
				</td>
				<td>
					<s:property value="memberList[#stat.index].clbname"/>
				</td>
				<td>
					<s:property value="memberList[#stat.index].cm_reasontojoin"/>
				</td>
				<td>
					<s:property value="memberList[#stat.index].cm_evaluation"/>
				</td>
				<td>
					<s:set var="cmstatus" value="memberList[#stat.index].cm_status"/>
					<s:if test="%{#cmstatus == \"A\"}">Active</s:if>
					<s:else>Inactive</s:else>
				</td>
				<td align="center">
					<s:if test="%{#editStatus == true && participant_label == 'student'}">
						<div style="color: #3d6e9f; cursor: pointer;" onclick="memberEvaluation('${cmid}', '${clbid}')">
							Evaluate
						</div>
					</s:if>					
				</td>
				<td align="center">				
					<s:set var="editStatus" value="memberList[#stat.index].editStatus"/>
					<s:set var="participant_label" value="memberList[#stat.index].participant_label"/>
					<s:if test="%{#editStatus == true && participant_label == 'student'}">
						<div style="color: #3d6e9f; cursor: pointer;" onclick="memberDataEdit('${cmid}', '${clbid}')">
							Edit
						</div>
					</s:if>
				</td>
			</tr>
		</s:iterator>
	</table>

</body>
</html>